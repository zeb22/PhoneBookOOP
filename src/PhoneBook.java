public class PhoneBook extends Utility{


    static Person[] listaPersoane = new Person[10];
    static int index = 0;


    public static void main(String[] arguments) {

        int opt = 0;
        do {
            printConsole("Meniul principal:");
            printConsole("\n1-Afisarea persoana" +
                    "\n2-Adaugare persoana " +
                    "\n3-Cautare nume" +
                    "\n4-Modificare nume " +
                    "\n5-Stergere nume" +
                    "\n6-Adaugare fara duplicat" +
                    "\n0-EXIT");
            printConsole("++++++++++++++++++++++");

            opt = readIntConsole("Alegeti optiunea dorita:\n");


            if (opt == 1)
                listare();
            else if (opt == 2) {
                String nume = readStringConsole("Introduceti numele:");
                String telefon = readStringConsole("Introduceti telefon: ");
                creare(nume, telefon);
            }
            else if (opt == 3){
                String valoare = readStringConsole("introduceti numele cautat: ");
                cautareAfisare(valoare);
            }
            else if (opt ==4){
                String nume = readStringConsole("Cauta dupa numele:");
                modificare(nume);
            }
            else if (opt == 5){
                String nume = readStringConsole("introduceti numele de sters:");
                stergere(nume);
            }
            else if (opt == 6) {
                String nume = readStringConsole("Nume unic: ");
                String telefon = readStringConsole("Telefon: ");
                creareFaraDuplicat(nume, telefon);
            }

        } while (opt != 0);
    }



    //AFISARE SIR DE NUME
    public static void listare() {
        for (int i = 0; i < listaPersoane.length; i++) {
            if (listaPersoane[i] != null) {  // => la inceput nu afiseaza nimic, pentru ca nu am introdus nume
                printConsole(listaPersoane[i].getName());
                printConsole(listaPersoane[i].getPhoneNumber());

                /*
                sau mai poti face altfel (dar acelasi lucru)

                Person p = listaPersoane[i];
                printConsole(p.getName());
                printConsole(p.getPhoneNumber());
                */
            }
        }
        System.out.println();
    }

    //CREARE NUME NOU IN AGENDA
    public static void creare(String nume, String telefon) {
        Person p = new Person(nume, telefon);
        listaPersoane[index]=p;
        index++;
    }

    //CREARE NUME NOU FARA DUPLICAT
    public static void creareFaraDuplicat(String nume, String telefon) {
        int i = cautareAfisare(nume); //cautam daca mai exista
        if (i == -1) {  //numele nu a fost gasit, deci adauga
            if (index < listaPersoane.length) {
                creare(nume, telefon);
            } else { // incercam sa gasim locuri libere in sir
                for (int j = 0; j < listaPersoane.length; j++) {
                    if (listaPersoane[j].getName() == null) { //a gasit primul loc liber, il scriem aici
                        creare(nume, telefon);
                        printConsole("Numele " + nume + " adaugat!\n"
                                + "Cu telefon: " + telefon + "\n");
                        break;
                    } else {
                        printConsole("Ne pare rau, dar PhoneBook este plina deja!");
                        break;
                    }
                }

            }
        } else {
            printConsole("Numele introdus exista deja.\n");
        }
    }

    //CAUTARE NUME IN AGENDA
    public static int cautare(String numePersoana) {
        int r = -1;

        for (int i = 0; i < listaPersoane.length; i++) {
            if (listaPersoane[i] != null) {
                if (numePersoana.equals(listaPersoane[i].getName())) { //pers de la pozitia i este:
                    r = i;
                    break;
                }
            }
        }
        if (r == -1) {
            System.out.println("Numele " + numePersoana + " nu a fost gasit in PhoneBook!\n");
        }
        return r;
    }

    //CAUTARE NUME + AFISARE POZITIE
    public static int cautareAfisare(String numePersoana) {
        int r = -1;

        for (int i = 0; i < listaPersoane.length; i++) {
            if (listaPersoane[i] != null) {
                if (numePersoana.equals(listaPersoane[i].getName())) {
                    r = i;
                    printConsole("Numele " + numePersoana + " a fost gasit pe pozitia " + r);
                    break;
                }
            }
        }
        if (r == -1) {
            System.out.println("Numele " + numePersoana + " nu a fost gasit in PhoneBook!\n");
        }
        return r;
    }

    //MODIFICARE NUME IN AGENDA
    public static void modificare(String nume) {
        int index = cautare(nume); //indexul numelui de modificat
        if (index == -1) {
            printConsole("Numele " + nume + " nu exista in PhoneBook!");
        } else {
            String numeModif = readStringConsole("Introduceti numele dorit: ");
            listaPersoane[index].setName(numeModif);//inlocuim numele de la indexul respectiv cu numele nou
            printConsole("Nume vechi: " + nume
                        + "\nNume nou: " + listaPersoane[index].getName());
        }
    }

    //STERGERE NUME
    public static void stergere(String nume) {
        int index = cautare(nume); //indexul numelui de sters
        if (index == -1) {
            printConsole("\nNumele " + nume + " nu exista in PhoneBook!\n");
        }
        else {
            listaPersoane[index].setName(null);
            printConsole("Nume " + nume + " a fost sters cu succes.\n");
        }
    }


}