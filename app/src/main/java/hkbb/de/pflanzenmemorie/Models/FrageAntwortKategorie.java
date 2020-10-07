package hkbb.de.pflanzenmemorie.Models;

public class FrageAntwortKategorie {
    String ID;
    String Name;
    String Antwort;
    String Eingabe;

    public FrageAntwortKategorie(String id, String name, String antwort, String eingabe) {
        ID = id;
        Name = name;
        Antwort = antwort;
        Eingabe = eingabe;
    }

    public FrageAntwortKategorie(int id, String name, String antwort, String eingabe) {
        Name = name;
        Antwort = antwort;
        Eingabe = eingabe;
    }

    public FrageAntwortKategorie(String id, String name, String antwort) {
        ID = id;
        Name = name;
        Antwort = antwort;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getAntwort() {
        return Antwort;
    }

    public String getEingabe() {
        return Eingabe;
    }

    public void setEingabe(String eingabe) {
        Eingabe = eingabe;
    }
}
