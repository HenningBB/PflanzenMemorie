package hkbb.de.pflanzenmemorie.Models;

public class Benutzer {
    String id;
    String name;
    String vorname;
    String ausbildung;
    String fachrichtung;
    String idQuiz;

    public Benutzer(String id, String name, String vorname, String ausbildung, String fachrichtung, String idQuiz) {
        this.id = id;
        this.name = name;
        this.vorname = vorname;
        this.ausbildung = ausbildung;
        this.fachrichtung = fachrichtung;
        this.idQuiz = idQuiz;
    }

    public Benutzer(String id, String name, String vorname, String ausbildung, String fachrichtung) {
        this.id = id;
        this.name = name;
        this.vorname = vorname;
        this.ausbildung = ausbildung;
        this.fachrichtung = fachrichtung;
        this.idQuiz = "1";
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

    public String getIdQuiz() {
        return idQuiz;
    }
}
