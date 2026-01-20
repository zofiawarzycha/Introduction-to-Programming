import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Interface defining the capability to receive medical treatment.
 */
interface Treatable {
    /**
     * Performs the treatment procedure on the entity.
     */
    void receiveTreatment();
}

/**
 * Main interface for the Clinic System.
 * Defines all major operations available in the clinic.
 */
interface IClinic {
    /**
     * Schedules a new appointment.
     * @param p The patient booking the visit.
     * @param d The doctor performing the visit.
     * @param date The date and time of the visit.
     */
    void addAppointment(Patient p, Doctor d, LocalDateTime date);

    /**
     * Displays all scheduled appointments for a specific patient.
     * @param p The patient whose appointments should be displayed.
     */
    void displayPatientAppointments(Patient p);

    /**
     * Displays the schedule/calendar for a specific doctor.
     * @param d The doctor whose calendar should be displayed.
     */
    void displayDoctorCalendar(Doctor d);

    /**
     * Adds a prescription to a patient's medical record.
     * @param p The patient receiving the prescription.
     * @param pre The prescription object.
     */
    void addPrescription(Patient p, Prescription pre);

    /**
     * Displays all prescriptions belonging to a specific patient.
     * @param p The patient.
     */
    void displayPatientPrescriptions(Patient p);

    /**
     * Searches for patients who have been prescribed a specific medicine by a specific doctor.
     * @param medicineName The name of the medicine (e.g., "Ibuprofen").
     * @param doc The doctor who issued the prescription.
     */
    void findPatientsByMedicineAndDoctor(String medicineName, Doctor doc);
}

// ==================== 2. BASE CLASSES ====================

/**
 * Abstract base class representing a person in the system.
 */
abstract class Person {
    protected String name;
    protected String contactInfo;

    /**
     * Constructs a new Person.
     * @param name Full name of the person.
     * @param contactInfo Email or phone number.
     */
    public Person(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " (" + contactInfo + ")";
    }
}

class Treatment {
    private String description;
    public Treatment(String description) { this.description = description; }
    public String getDescription() { return description; }
    @Override public String toString() { return description; }
}

class Medicine extends Treatment {
    public Medicine(String name) { super(name); }
    @Override public String toString() { return "Medicine: " + getDescription(); }
}

class Appointment {
    private LocalDateTime dateTime;
    private Patient patient;
    private Doctor doctor;
    private String reason;

    public Appointment(LocalDateTime dateTime, Patient patient, Doctor doctor, String reason) {
        this.dateTime = dateTime;
        this.patient = patient;
        this.doctor = doctor;
        this.reason = reason;
    }

    public LocalDateTime getDate() { return dateTime; }
    public Patient getPatient() { return patient; }

    @Override
    public String toString() {
        String dateStr = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return "Date: " + dateStr + " | Dr. " + doctor.getName() + " | Reason: " + reason;
    }
}

class Prescription {
    private Doctor doctor;
    private Patient patient;
    private List<Treatment> treatments = new ArrayList<>();

    public Prescription(Doctor doctor, Patient patient) {
        this.doctor = doctor;
        this.patient = patient;
    }

    public void addTreatment(Treatment t) { treatments.add(t); }
    public List<Treatment> getTreatments() { return treatments; }
    public Doctor getDoctor() { return doctor; }

    @Override
    public String toString() {
        return "Prescription from Dr. " + doctor.getName() + ": " + treatments;
    }
}

// ==================== 3. ACTORS ====================

/**
 * Represents a patient in the clinic.
 */
class Patient extends Person implements Treatable {
    private int age;
    private List<Appointment> appointments = new ArrayList<>();
    private List<Prescription> prescriptions = new ArrayList<>();

    public Patient(String name, String contact, int age) {
        super(name, contact);
        this.age = age;
    }

    public void addAppointment(Appointment a) { appointments.add(a); }
    public void addPrescription(Prescription p) { prescriptions.add(p); }
    public List<Appointment> getAppointments() { return appointments; }
    public List<Prescription> getPrescriptions() { return prescriptions; }

    @Override
    public void receiveTreatment() {
        System.out.println("Patient " + name + " is receiving treatment...");
    }
}

abstract class Staff extends Person {
    public Staff(String name) { super(name, "Staff"); }

    public boolean scheduleAppointment(Patient p, Doctor d, LocalDateTime date, String reason) {
        if (d.isAvailable(date)) {
            Appointment app = new Appointment(date, p, d, reason);
            d.addAppointment(app);
            p.addAppointment(app);
            return true;
        }
        return false;
    }
}

/**
 * Represents a Doctor who can examine patients and issue prescriptions.
 */
class Doctor extends Staff {
    private List<Appointment> appointments = new ArrayList<>();

    public Doctor(String name) { super(name); }

    /**
     * Checks if the doctor is available at a given time slot.
     * @param checkDate The date and time to check.
     * @return true if available, false if slot is occupied.
     */
    public boolean isAvailable(LocalDateTime checkDate) {
        for (Appointment a : appointments) {
            if (a.getDate().isEqual(checkDate)) return false;
        }
        return true;
    }

    public void addAppointment(Appointment a) { appointments.add(a); }
    public List<Appointment> getAppointments() { return appointments; }

    /**
     * Creates a new prescription for a patient.
     * @param p The patient.
     * @param medicineNames List of medicine names to include.
     * @return The created Prescription object.
     */
    public Prescription createPrescription(Patient p, List<String> medicineNames) {
        Prescription pres = new Prescription(this, p);
        for (String medName : medicineNames) {
            pres.addTreatment(new Medicine(medName.trim()));
        }
        p.addPrescription(pres);
        return pres;
    }
}

// ==================== 4. MANAGER & PARSER ====================

/**
 * Implementation of the Clinic System.
 * Handles data loading from CSV and managing clinic operations.
 */
class ClinicManager implements IClinic {
    private Map<String, Patient> patients = new HashMap<>();
    private Map<String, Doctor> doctors = new HashMap<>();

    /**
     * Loads patient and doctor data from a CSV file.
     * @param filePath The absolute path to the CSV file.
     */
    public void loadDataFromFile(String filePath) {
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            System.out.println("Loading file: " + filePath);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                // Remove quotes
                line = line.replace("\"", "");

                if (line.isEmpty()) continue;

                try {
                    parseLine(line);
                } catch (Exception e) {
                    // System.out.println("Error parsing line: " + line); // Uncomment for debug
                }
            }
            scanner.close();
            System.out.println(">>> Data Loaded! Patients: " + patients.size() + ", Doctors: " + doctors.size());

        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found! Check path: " + filePath);
        }
    }

    private void parseLine(String line) {
        // 1. Date
        String[] parts = line.split(",", 2);
        String dateStr = parts[0].trim();
        LocalDate localDate = LocalDate.parse(dateStr);
        LocalDateTime dateTime = localDate.atTime(9, 0);

        // 2. Doctor
        String doctorName = "Unknown";
        int docIndex = line.indexOf("Doctor:");
        if (docIndex != -1) {
            String rawDoc = line.substring(docIndex + 7).trim();
            if (rawDoc.endsWith(".")) {
                rawDoc = rawDoc.substring(0, rawDoc.length() - 1);
            }
            doctorName = rawDoc;

            if (doctorName.contains(",")) {
                String[] nameParts = doctorName.split(",");
                if (nameParts.length >= 2) {
                    doctorName = nameParts[1].trim() + " " + nameParts[0].trim();
                }
            }
        }

        Doctor doc;
        if (doctors.containsKey(doctorName)) {
            doc = doctors.get(doctorName);
        } else {
            doc = new Doctor(doctorName);
            doctors.put(doctorName, doc);
        }

        // 3. Patient
        String ageStr = extractRegex(line, "is (\\d+) years old");
        int age = (ageStr != null) ? Integer.parseInt(ageStr) : 0;

        String email = extractValue(line, "Email:");
        String cell = extractValue(line, "Cell:");
        String contact = (email != null) ? email : (cell != null ? cell : "No Contact");
        String patientId = (email != null) ? email : "Patient_" + contact.replaceAll("[^0-9]", "");

        Patient patient;
        if (patients.containsKey(patientId)) {
            patient = patients.get(patientId);
        } else {
            patient = new Patient(patientId, contact, age);
            patients.put(patientId, patient);
        }

        // 4. Appointment
        while (!doc.isAvailable(dateTime)) {
            dateTime = dateTime.plusMinutes(30);
        }
        String reason = extractRegex(line, "visiting for (.*?),");
        if (reason == null) reason = "Checkup";

        Appointment apt = new Appointment(dateTime, patient, doc, reason);
        doc.addAppointment(apt);
        patient.addAppointment(apt);

        // 5. Prescriptions
        String medsStr = extractValue(line, "Patient takes:");
        if (medsStr != null) {
            int cutIndex = medsStr.length();
            if (medsStr.contains(", Email:")) cutIndex = Math.min(cutIndex, medsStr.indexOf(", Email:"));
            if (medsStr.contains(", Cell:")) cutIndex = Math.min(cutIndex, medsStr.indexOf(", Cell:"));
            if (medsStr.contains(", Doctor:")) cutIndex = Math.min(cutIndex, medsStr.indexOf(", Doctor:"));

            String cleanMeds = medsStr.substring(0, cutIndex);
            String[] medArray = cleanMeds.split(",");
            List<String> medsList = new ArrayList<>();
            for (String m : medArray) medsList.add(m);

            doc.createPrescription(patient, medsList);
        }
    }

    private String extractValue(String text, String key) {
        int index = text.indexOf(key);
        if (index == -1) return null;

        String sub = text.substring(index + key.length()).trim();
        int commaIndex = sub.indexOf(",");

        if (commaIndex != -1 && !key.equals("Patient takes:")) {
            return sub.substring(0, commaIndex).trim();
        }
        return sub;
    }

    private String extractRegex(String text, String patternStr) {
        Pattern p = Pattern.compile(patternStr);
        Matcher m = p.matcher(text);
        if (m.find()) return m.group(1);
        return null;
    }

    public Doctor getDoctorByName(String name) {
        return doctors.get(name);
    }

    // --- IClinic Implementation ---
    @Override
    public void addAppointment(Patient p, Doctor d, LocalDateTime date) {
        Staff receptionist = new Staff("Reception") {};
        if(receptionist.scheduleAppointment(p, d, date, "Manual"))
            System.out.println("Booked.");
        else
            System.out.println("Busy.");
    }

    @Override
    public void displayPatientAppointments(Patient p) {
        System.out.println("\n--- Appointments for: " + p.getName() + " ---");
        for (Appointment a : p.getAppointments()) System.out.println(a);
    }

    @Override
    public void displayDoctorCalendar(Doctor d) {
        System.out.println("\n--- Calendar for Dr. " + d.getName() + " ---");
        d.getAppointments().sort(Comparator.comparing(Appointment::getDate));
        for (Appointment a : d.getAppointments()) System.out.println(a);
    }

    @Override
    public void addPrescription(Patient p, Prescription pre) { p.addPrescription(pre); }

    @Override
    public void displayPatientPrescriptions(Patient p) {
        System.out.println("\n--- Prescriptions for: " + p.getName() + " ---");
        for (Prescription pre : p.getPrescriptions()) System.out.println(pre);
    }

    @Override
    public void findPatientsByMedicineAndDoctor(String medicineName, Doctor doc) {
        System.out.println("\n--- SEARCH: Who takes '" + medicineName + "' from Dr. " + doc.getName() + "? ---");
        boolean found = false;
        for (Patient p : patients.values()) {
            for (Prescription pre : p.getPrescriptions()) {
                if (pre.getDoctor() == doc) {
                    for (Treatment t : pre.getTreatments()) {
                        if (t.getDescription().toLowerCase().contains(medicineName.toLowerCase())) {
                            System.out.println("FOUND: " + p.getName());
                            found = true;
                        }
                    }
                }
            }
        }
        if (!found) System.out.println("No results found.");
    }
}

// ... (wszystkie poprzednie klasy zostają tak jak były) ...

// ==================== 5. MAIN ====================

/**
 * Main class to run the Clinic System demonstration.
 * This class handles the initialization and execution of the clinic management system.
 *
 * @author Zofia Warzycha
 * @version 1.0
 */
public class ClinicSystem {

    /**
     * Default constructor for the ClinicSystem class.
     * Required for Javadoc generation to avoid warnings about missing constructor comments.
     */
    public ClinicSystem() {
        // Default constructor
    }

    /**
     * Main entry point of the application.
     * Loads patient data from a CSV file and demonstrates the system functionality
     * by displaying a doctor's calendar and searching for patients by medication.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Correct path
        String filePath = "C:\\Users\\zosia\\OneDrive\\Pulpit\\data.csv";

        ClinicManager clinic = new ClinicManager();
        clinic.loadDataFromFile(filePath);

        System.out.println("\n=== SYSTEM DEMO ===");

        Doctor testDoc = clinic.getDoctorByName("Laura Wilson");

        if (testDoc != null) {
            clinic.displayDoctorCalendar(testDoc);
            clinic.findPatientsByMedicineAndDoctor("Lisinopril", testDoc);
        } else {
            System.out.println("Doctor Laura Wilson not found.");
        }
    }
}