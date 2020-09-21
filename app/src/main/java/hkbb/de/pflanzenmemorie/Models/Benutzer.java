package hkbb.de.pflanzenmemorie.Models;

public class Benutzer {
    String id;
    String name;
    String vorname;
    String ausbildung;
    String fachrichtung;
    String pruefung;

    public Benutzer(String id, String name, String vorname, String ausbildung, String fachrichtung, String pruefung) {
        this.id = id;
        this.name = name;
        this.vorname = vorname;
        this.ausbildung = ausbildung;
        this.fachrichtung = fachrichtung;
        this.pruefung = pruefung;
    }

    public Benutzer( String name, String vorname, String ausbildung, String fachrichtung) {
        this.id ="0";
        this.name = name;
        this.vorname = vorname;
        this.ausbildung = ausbildung;
        this.fachrichtung = fachrichtung;
        this.pruefung = "0";
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

    public String getPruefung() {
        return pruefung;
    }
}
