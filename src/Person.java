public class Person extends Utility {

    private String name;
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Person(String n, String p) {
        name = numeCorect(n);
        phoneNumber = p;
    }

    public static boolean numaiCaractere(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public String numeCorect(String name) {
        boolean run = true;

        do {
            if (numaiCaractere(name)) {
                setName(name);
                run = false;
            } else {
                printConsole("Numele " + name + " trebuie sa contina numai caractere!");
                name = readStringConsole("Reintroduceti nume: ");
            }
        } while (run);

        return name;
    }



}
