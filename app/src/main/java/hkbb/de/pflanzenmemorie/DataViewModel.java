package hkbb.de.pflanzenmemorie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import hkbb.de.pflanzenmemorie.Models.Benutzer;
import hkbb.de.pflanzenmemorie.Models.Pflanze;
import hkbb.de.pflanzenmemorie.Models.Statistik;

public class DataViewModel extends ViewModel {

    private MutableLiveData<Integer> StatistikPointer = new MutableLiveData<>();
    private MutableLiveData<Integer> QuizPointer = new MutableLiveData<>();
    private MutableLiveData<Integer> QuizSize = new MutableLiveData<>();
    private MutableLiveData<List<Integer>> Quiz = new MutableLiveData<>();
    private MutableLiveData<Pflanze> Karte = new MutableLiveData<>();
    private MutableLiveData<List<Pflanze>> Kasten = new MutableLiveData<>();
    private MutableLiveData<Benutzer> Benutzter = new MutableLiveData<>();
    private MutableLiveData<List<Statistik>> StatistikList = new MutableLiveData<>();
    private MutableLiveData<Statistik> SelectedStatistik = new MutableLiveData<>();
    private MutableLiveData<List<Pflanze>> SelectedStatistikEvaluation = new MutableLiveData<>();


    public void setStatistikPointer(Integer pointer) {
        StatistikPointer.setValue(pointer);
    }

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

    public void setStatistikList(List<Statistik> listS) {
        StatistikList.setValue(listS);
    }

    public void setSelectedStatistik(Statistik statistik) {
        SelectedStatistik.setValue(statistik);
    }

    public void setSelectedPflanzeStatistik(List<Pflanze> pflanzeStatistikList) {
        SelectedStatistikEvaluation.setValue(pflanzeStatistikList);
    }


    public LiveData<Integer> getStatistikPointer() {
        return StatistikPointer;
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

    public LiveData<List<Statistik>> getStatistikList() {
        return StatistikList;
    }

    public LiveData<Statistik> getSelectedStatistic() {
        return SelectedStatistik;
    }

    public LiveData<List<Pflanze>> getSelectedPflanzeStatistik() {
        return SelectedStatistikEvaluation;
    }
}
