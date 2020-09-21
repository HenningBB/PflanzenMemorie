package hkbb.de.pflanzenmemorie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import hkbb.de.pflanzenmemorie.Models.Benutzer;
import hkbb.de.pflanzenmemorie.Models.Pflanze;

public class DataViewModel extends ViewModel {
    private MutableLiveData<Pflanze> Karte = new MutableLiveData<>();
    private MutableLiveData<List<Pflanze>> Kasten = new MutableLiveData<>();
    private MutableLiveData<List<Pflanze>> Quiz = new MutableLiveData<>();
    private MutableLiveData<Benutzer> Benutzter = new MutableLiveData<>();

    public void setKarte(Pflanze karte) {
        Karte.setValue(karte);
    }

    public void setKasten(List<Pflanze> kasten) {
        Kasten.setValue(kasten);
    }

    public void setQuiz(List<Pflanze> quiz) {
        Quiz.setValue(quiz);
    }

    public void setBenutzer(Benutzer benutzer) {
        Benutzter.setValue(benutzer);
    }

    public LiveData<Pflanze> getKarte() {
        return Karte;
    }

    public LiveData<List<Pflanze>> getKasten() {
        return Kasten;
    }

    public LiveData<List<Pflanze>> getQuiz() {
        return Quiz;
    }

    public LiveData<Benutzer> getBenutzer() {
        return Benutzter;
    }
}
