public class PhoneBook extends Utility{


    static Person[] listaPersoane = new Person[10];
    static int index;


    public static void main(String[] arguments) {

        int opt;
        do {
            printConsole("\nMeniu principal: " +
                        "\n1-Afisarea" +
                        "\n2-Adaugare" +
                        "\n3-Adaugare fara duplicat" +
                        "\n4-Cautare contact " +
                        "\n5-Modificare contact" +
                        "\n6-Stergere" +
                        "\n0-EXIT" +
                        "\n-----------------");

            opt = readIntConsole("Alegeti optiunea dorita:\n");

            switch (opt) {
                case 1:
                    listare();
                    break;
                case 2:
                    creare(readStringConsole("Introduceti numele: "),
                            readStringConsole("Introduceti telefon: "));
                    break;
                case 3:
                    creareFaraDuplicat(readStringConsole("Nume unic: "),
                            readStringConsole("Telefon: "));
                    break;
                case 4:
                    cautareAfisare(readStringConsole("Cauta dupa nume sau telefon: "));
                    break;
                case 5:
                    modificare(readStringConsole("Cauta numele: "));
                    break;
                case 6:
                    stergere(readStringConsole("Sterge numele: "));
                    break;
            }
        } while (opt != 0);
    }



    //afisare
    public static void listare() {
        for (int i = 0; i < listaPersoane.length; i++) {
            if (listaPersoane[i] != null) {
                printConsole("Contact:" +
                        "\n\tNume: " + listaPersoane[i].getName() +
                        "\n\tTelefon: " + listaPersoane[i].getPhoneNumber() + "\n");
            }
        }
    }

    //creare
    public static void creare(String nume, String telefon) {
        Person p = new Person(nume, telefon);
        listaPersoane[index] = p;
        index++;
    }

    //creare fara duplicat
    public static void creareFaraDuplicat(String nume, String telefon) {
        int i = cautare(nume);
        if (i == -1) {
            if (index < listaPersoane.length) {
                creare(nume, telefon);
            } else {
                for (int j = 0; j < listaPersoane.length; j++) {
                    if (listaPersoane[j].getName() == null) {
                        creare(nume, telefon);
                        printConsole("Numele " + nume + " adaugat!\n"
                                + "Cu telefon: " + telefon + "\n");
                        break;
                    } else {
                        printConsole("Ne pare rau, dar agenda este plina deja!");
                        break;
                    }
                }

            }
        } else {
            printConsole("Numele introdus exista deja.\n");
        }
    }

    //cautare
    public static int cautare(String numePersoana) {
        int r = -1;

        for (int i = 0; i < listaPersoane.length; i++) {
            if (listaPersoane[i] != null) {
                if (numePersoana.equals(listaPersoane[i].getName())) {
                    r = i;
                    break;
                }
            }
        }
        if (r == -1) {
            printConsole("Numele " + numePersoana + " nu a fost gasit in agenda!\n");
        }
        return r;
    }

    //cautare + afisare
    public static int cautareAfisare(String rezultat) {
        int r = -1;

        for (int i = 0; i < listaPersoane.length; i++) {
            if (listaPersoane[i] != null) {
                if (rezultat.equals(listaPersoane[i].getName())) {
                    r = i + 1;
                    printConsole("Numele " + rezultat + " a fost gasit pe pozitia " + r + "\n");
                } else if (rezultat.equals(listaPersoane[i].getPhoneNumber())) {
                    r = i + 1;
                    printConsole("Contactul cu numarul: " + rezultat + " se afla pe pozitia " + r + "\n");
                }
            }
        }
        if (r == -1) {
            printConsole("Contactul cu numele/telefon: " + rezultat + " nu a fost gasit in agenda!\n");
        }
        return r;
    }

    //modificare
    public static void modificare(String nume) {
        int index = cautare(nume);
        if (index == -1) {
            printConsole("Numele " + nume + " nu exista in angeda!");
        } else {
            String numeModif = readStringConsole("Introduceti numele dorit: ");
            listaPersoane[index].setName(numeModif);
            printConsole("Nume vechi: " + nume
                        + "\nNume nou: " + listaPersoane[index].getName());
            String continuare = readStringConsole("Modifica si telefon? Y/N ").toUpperCase();
            if (continuare.equals("Y")) {
                listaPersoane[index].setPhoneNumber(readStringConsole("Telefon nou: "));
            }
        }
    }

    //stergere
    public static void stergere(String nume) {
        int index = cautare(nume);
        if (index == -1) {
            printConsole("\nNumele " + nume + " nu exista in PhoneBook!\n");
        }
        else {
            listaPersoane[index] = null;
            printConsole("Contactul " + nume + " a fost sters cu succes.\n");
        }
    }




}