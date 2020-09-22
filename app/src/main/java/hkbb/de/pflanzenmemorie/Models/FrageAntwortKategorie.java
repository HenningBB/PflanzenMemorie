package hkbb.de.pflanzenmemorie.Models;

public class FrageAntwortKategorie {
    String Name;
    String Antwort;

    public FrageAntwortKategorie(String name, String antwort) {
        Name = name;
        Antwort = antwort;
    }

    public String getName() {
        return Name;
    }

    public String getAntwort() {
        return Antwort;
    }
}
