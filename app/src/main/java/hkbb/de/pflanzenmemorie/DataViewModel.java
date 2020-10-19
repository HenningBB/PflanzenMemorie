package hkbb.de.pflanzenmemorie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import hkbb.de.pflanzenmemorie.Models.Benutzer;
import hkbb.de.pflanzenmemorie.Models.Pflanze;
import hkbb.de.pflanzenmemorie.Models.Statistik;

public class DataViewModel extends ViewModel {

    //Datenbankverbindung
    private final MutableLiveData<String> dbString = new MutableLiveData<>();

    //Start des Quizes
    private final MutableLiveData<Long> tStart = new MutableLiveData<>();

    //Ende des Quizes
    //private MutableLiveData<Long> tEnd = new MutableLiveData<>();

    //auf welcher Pflanze die Statistik ist
    private final MutableLiveData<Integer> StatistikPointer = new MutableLiveData<>();

    //auf welcher Pflanze das Quiz ist
    private final MutableLiveData<Integer> QuizPointer = new MutableLiveData<>();

    //Größe des Quizes
    private final MutableLiveData<Integer> QuizSize = new MutableLiveData<>();

    //aktuell angezeigte Pflanze
    private final MutableLiveData<Pflanze> Karte = new MutableLiveData<>();

    //alle Pflanzen
    private final MutableLiveData<List<Pflanze>> Kasten = new MutableLiveData<>();

    //eingelogter Benutzer
    private final MutableLiveData<Benutzer> Benutzter = new MutableLiveData<>();

    //Liste der Statistiken des Benutzers
    private final MutableLiveData<List<Statistik>> StatistikList = new MutableLiveData<>();

    //aktuell ausgewählte Statistik
    private final MutableLiveData<Statistik> SelectedStatistik = new MutableLiveData<>();

    //Liste mit Pflanzen der aktuell ausgewählten Statistik
    private final MutableLiveData<List<Pflanze>> SelectedStatistikEvaluation = new MutableLiveData<>();


    public void setDbString(String url) {
        dbString.setValue(url);
    }

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


    public LiveData<String> getdbString() {
        return dbString;
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
