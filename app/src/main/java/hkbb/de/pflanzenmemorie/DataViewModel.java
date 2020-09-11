package hkbb.de.pflanzenmemorie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class DataViewModel extends ViewModel {
    private MutableLiveData<Pflanze> Karte = new MutableLiveData<>();
    private MutableLiveData<List<Pflanze>> Kasten = new MutableLiveData<>();
    private MutableLiveData<List<Pflanze>> Quiz = new MutableLiveData<>();

    public void setKarte(Pflanze karte) {
        Karte.setValue(karte);
    }

    public void setKasten(List<Pflanze> kasten) {
        Kasten.setValue(kasten);
    }

    public void setQuiz(List<Pflanze> quiz) {
        Quiz.setValue(quiz);
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
}
