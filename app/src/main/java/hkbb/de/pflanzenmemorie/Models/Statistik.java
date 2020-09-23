package hkbb.de.pflanzenmemorie.Models;

public class Statistik {
String id;
String datum;
String azubiId;
String fehlerquote;
String zeit;
String schlechtestePlfanze;

    public Statistik(String id, String datum, String azubiId, String fehlerquote, String zeit, String schlechtestePlfanze) {
        this.id = id;
        this.datum = datum;
        this.azubiId = azubiId;
        this.fehlerquote = fehlerquote;
        this.zeit = zeit;
        this.schlechtestePlfanze = schlechtestePlfanze;
    }

    public String getId() {
        return id;
    }

    public String getDatum() {
        return datum;
    }

    public String getAzubiId() {
        return azubiId;
    }

    public String getFehlerquote() {
        return fehlerquote;
    }

    public String getZeit() {
        return zeit;
    }

    public String getSchlechtestePlfanze() {
        return schlechtestePlfanze;
    }
}

