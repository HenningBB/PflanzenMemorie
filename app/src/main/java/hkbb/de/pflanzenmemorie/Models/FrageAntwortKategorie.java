package hkbb.de.pflanzenmemorie.Models;

public class FrageAntwortKategorie {
    String Name;
    String Antwort;
    String Eingabe;

    public FrageAntwortKategorie(String name, String antwort) {
        Name = name;
        Antwort = antwort;
    }

    public FrageAntwortKategorie(String name, String antwort,String eingabe) {
        Name = name;
        Antwort = antwort;
        Eingabe=eingabe;
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
