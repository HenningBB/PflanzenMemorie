package hkbb.de.pflanzenmemorie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import hkbb.de.pflanzenmemorie.Models.Benutzer;
import hkbb.de.pflanzenmemorie.Models.Pflanze;

public class DataViewModel extends ViewModel {

    private MutableLiveData<Integer> QuizPointer = new MutableLiveData<>();
    private MutableLiveData<Integer> QuizSize = new MutableLiveData<>();
    private MutableLiveData<List<Integer>> Quiz = new MutableLiveData<>();
    private MutableLiveData<Pflanze> Karte = new MutableLiveData<>();
    private MutableLiveData<List<Pflanze>> Kasten = new MutableLiveData<>();
    private MutableLiveData<Benutzer> Benutzter = new MutableLiveData<>();


    public void setQuizPointer(Integer pointer) {
        QuizPointer.setValue(pointer);
    }

    public void setQuizSize(Integer size) {
        QuizSize.setValue(size);
    }

    public void setQuiz(List<Integer> quiz) {
        Quiz.setValue(quiz);
    }

    public void setKarte(Pflanze karte) {
        Karte.setValue(karte);
    }

    public void setKasten(List<Pflanze> kasten) {
        Kasten.setValue(kasten);
    }

    public void setBenutzer(Benutzer benutzer) {
        Benutzter.setValue(benutzer);
    }


    public LiveData<Integer> getQuizPointer() {
        return QuizPointer;
    }

    public LiveData<Integer> getQuizSize() {
        return QuizSize;
    }

    public LiveData<List<Integer>> getQuiz() {
        return Quiz;
    }

    public LiveData<Pflanze> getKarte() {
        return Karte;
    }

    public LiveData<List<Pflanze>> getKasten() {
        return Kasten;
    }

    public LiveData<Benutzer> getBenutzer() {
        return Benutzter;
    }
}
