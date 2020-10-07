package hkbb.de.pflanzenmemorie.Models;

import java.util.List;

public class Pflanze {

    private String id;

    private List<FrageAntwortKategorie> fragen;

    public Pflanze(List<FrageAntwortKategorie> fragen,String id) {
        this.fragen = fragen;
        this.id=id;
    }

    public void setFragen(List<FrageAntwortKategorie> fragen) {
        this.fragen = fragen;
    }

    public List<FrageAntwortKategorie> getFragen() {
        return fragen;
    }

    public String getId(){return id;}
}
