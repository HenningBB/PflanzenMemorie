package hkbb.de.pflanzenmemorie.Models;

public class Statistik {
    String id;
    String erstellDatum;
    String fehlerquote;
    String zeit;
    String schlechtestePlfanzeID;

    public Statistik(String id, String erstellDatum, String fehlerquote, String zeit, String schlechtestePlfanze) {
        this.id = id;
        this.erstellDatum = erstellDatum;
        this.fehlerquote = fehlerquote;
        this.zeit = zeit;
        this.schlechtestePlfanzeID = schlechtestePlfanze;
    }

    public Statistik(String fehlerquote, String zeit, String schlechtestePlfanze) {
        this.fehlerquote = fehlerquote;
        this.zeit = zeit;
        this.schlechtestePlfanzeID = schlechtestePlfanze;
    }

    public String getId() {
        return id;
    }

    public String getErstellDatum() {
        return erstellDatum;
    }

    public String getFehlerquote() {
        return fehlerquote;
    }

    public String getZeit() {
        return zeit;
    }

    public String getSchlechtestePlfanze() {
        return schlechtestePlfanzeID;
    }
}

