package hkbb.de.pflanzenmemorie;

public class Pflanze {
    private String Gattungsname;
    private String Artname;
    private String DeutscherName;
    private String FamilienName;
    private String Herkunft;
    private String Bluete;
    private String Bluetezeit;
    private String Blatt;
    private String Habitus;
    private String Besonderheiten;

    public Pflanze(String gattungsname, String artname, String deutscherName, String familienName, String herkunft, String bluete, String bluetezeit, String blatt, String habitus, String besonderheiten) {
        Gattungsname = gattungsname;
        Artname = artname;
        DeutscherName = deutscherName;
        FamilienName = familienName;
        Herkunft = herkunft;
        Bluete = bluete;
        Bluetezeit = bluetezeit;
        Blatt = blatt;
        Habitus = habitus;
        Besonderheiten = besonderheiten;
    }

    public String getGattungsname() {
        return Gattungsname;
    }

    public String getArtname() {
        return Artname;
    }

    public String getDeutscherName() {
        return DeutscherName;
    }

    public String getFamilienName() {
        return FamilienName;
    }

    public String getHerkunft() {
        return Herkunft;
    }

    public String getBluete() {
        return Bluete;
    }

    public String getBluetezeit() {
        return Bluetezeit;
    }

    public String getBlatt() {
        return Blatt;
    }

    public String getHabitus() {
        return Habitus;
    }

    public String getBesonderheiten() {
        return Besonderheiten;
    }
}
