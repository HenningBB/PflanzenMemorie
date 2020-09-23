package hkbb.de.pflanzenmemorie.Models;

import java.util.List;

public class Pflanze {

    private List<FrageAntwortKategorie> fragen;

    public Pflanze(List<FrageAntwortKategorie> fragen) {
        this.fragen = fragen;
    }

    public List<FrageAntwortKategorie> getFragen() {
        return fragen;
    }
}
