interface Treatable {
    void receiveTreatment();
}

abstract class Person {
    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void introduce() {
        System.out.println("Hi, I am " + name + " and I am " + age + " years old.");
    }
}

class Patient extends Person implements Treatable {
    private String condition;

    public Patient(String name, int age, String condition) {
        super(name, age);
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    @Override
    public void receiveTreatment() {
        System.out.println("Patient " + name + " is receiving treatment for: " + condition);
    }
}

abstract class Staff extends Person {
    protected String role;

    public Staff(String name, int age, String role) {
        super(name, age);
        this.role = role;
    }

    public abstract void performDuties();
}

class Doctor extends Staff {
    public Doctor(String name, int age) {
        super(name, age, "Doctor");
    }

    @Override
    public void performDuties() {
        System.out.println(role + " " + name + " is diagnosing patients.");
    }

    public void prescribeMedicine(String medicine) {
        System.out.println("Dr. " + name + " prescribes: " + medicine);
    }
}

class Nurse extends Staff {
    public Nurse(String name, int age) {
        super(name, age, "Nurse");
    }

    @Override
    public void performDuties() {
        System.out.println(role + " " + name + " is assisting doctors.");
    }

    public void checkVitals(Patient patient) {
        System.out.println("Nurse " + name + " is checking vitals of " + patient.name);
    }
}

class Receptionist extends Staff {
    public Receptionist(String name, int age) {
        super(name, age, "Receptionist");
    }

    @Override
    public void performDuties() {
        System.out.println(role + " " + name + " is scheduling appointments.");
    }
}

public class ClinicTest {
    public static void main(String[] args) {
        Doctor doc = new Doctor("Zosia", 50);
        Nurse nurse = new Nurse("Hania", 30);
        Receptionist receptionist = new Receptionist("Hubert", 25);
        Patient patient1 = new Patient("Maja", 40, "Flu");
        Patient patient2 = new Patient("Jula", 22, "Broken Arm");

        Person[] people = {doc, nurse, receptionist, patient1, patient2};
        Staff[] staffMembers = {doc, nurse, receptionist};
        Patient[] patients = {patient1, patient2};

        System.out.println("--- Introductions ---");
        for (Person p : people) {
            p.introduce();
        }

        System.out.println("\n--- Staff Duties ---");
        for (Staff s : staffMembers) {
            s.performDuties();
        }

        System.out.println("\n--- Patient Treatments ---");
        for (Patient p : patients) {
            p.receiveTreatment();
        }

        System.out.println("\n--- Unique Methods ---");
        doc.prescribeMedicine("Ibuprofen");
        nurse.checkVitals(patient1);
    }
}