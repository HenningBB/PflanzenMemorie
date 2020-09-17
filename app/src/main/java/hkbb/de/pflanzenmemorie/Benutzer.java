package hkbb.de.pflanzenmemorie;

public class Benutzer {
    String id;
    String name;
    String vorname;
    String ausbildung;
    String fachrichtung;
    int pruefunger;

    public Benutzer(String id, String name, String vorname, String ausbildung, String fachrichtung, int pruefung) {
        this.id = id;
        this.name = name;
        this.vorname = vorname;
        this.ausbildung = ausbildung;
        this.fachrichtung = fachrichtung;
        this.pruefung = pruefung;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getVorname() {
        return vorname;
    }

    public String getAusbildung() {
        return ausbildung;
    }

    public String getFachrichtung() {
        return fachrichtung;
    }

    public int getPruefung() {
        return pruefung;
    }
}
