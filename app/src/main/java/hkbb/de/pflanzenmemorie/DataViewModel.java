package hkbb.de.pflanzenmemorie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import hkbb.de.pflanzenmemorie.Models.Benutzer;
import hkbb.de.pflanzenmemorie.Models.Pflanze;
import hkbb.de.pflanzenmemorie.Models.Statistik;

public class DataViewModel extends ViewModel {

    //Start des Quizes
    private MutableLiveData<Long> tStart = new MutableLiveData<>();

    //Ende des Quizes
    //private MutableLiveData<Long> tEnd = new MutableLiveData<>();

    //auf welcher Pflanze die Statistik ist
    private MutableLiveData<Integer> StatistikPointer = new MutableLiveData<>();

    //auf welcher Pflanze das Quiz ist
    private MutableLiveData<Integer> QuizPointer = new MutableLiveData<>();

    //Größe des Quizes
    private MutableLiveData<Integer> QuizSize = new MutableLiveData<>();

    //aktuell angezeigte Pflanze
    private MutableLiveData<Pflanze> Karte = new MutableLiveData<>();

    //alle Pflanzen
    private MutableLiveData<List<Pflanze>> Kasten = new MutableLiveData<>();

    //eingelogter Benutzer
    private MutableLiveData<Benutzer> Benutzter = new MutableLiveData<>();

    //Liste der Statistiken des Benutzers
    private MutableLiveData<List<Statistik>> StatistikList = new MutableLiveData<>();

    //aktuell ausgewählte Statistik
    private MutableLiveData<Statistik> SelectedStatistik = new MutableLiveData<>();

    //Liste mit Pflanzen der aktuell ausgewählten Statistik
    private MutableLiveData<List<Pflanze>> SelectedStatistikEvaluation = new MutableLiveData<>();


    public void settStart(Long time) {
        tStart.setValue(time);
    }

    public void setStatistikPointer(Integer pointer) {
        StatistikPointer.setValue(pointer);
    }

    public void setQuizPointer(Integer pointer) {
        QuizPointer.setValue(pointer);
    }

    public void setQuizSize(Integer size) {
        QuizSize.setValue(size);
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


    public LiveData<Long> gettStart() {
        return tStart;
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
