package hkbb.de.pflanzenmemorie.Models;

public class FrageAntwortKategorie {
    String ID;
    String Name;
    String Antwort;
    String Eingabe;
    String Overflowy;

    public FrageAntwortKategorie(String id,String name, String antwort) {
        ID=id;
        Name = name;
        Antwort = antwort;
    }

    public FrageAntwortKategorie(String name, String antwort, String eingabe,String over) {
        Overflowy=over;
        Name = name;
        Antwort = antwort;
        Eingabe = eingabe;
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
