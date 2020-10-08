package hkbb.de.pflanzenmemorie;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

import hkbb.de.pflanzenmemorie.DataSources.InsertPflanzeStatistikDataSource;
import hkbb.de.pflanzenmemorie.DataSources.PictureDataSource;
import hkbb.de.pflanzenmemorie.Models.FrageAntwortKategorie;
import hkbb.de.pflanzenmemorie.Models.Pflanze;
import hkbb.de.pflanzenmemorie.Models.Statistik;


public class quizPicture extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_picture, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final DataViewModel model = new ViewModelProvider(requireActivity()).get(DataViewModel.class);

        ImageView image = view.findViewById(R.id.plantPicture_pic);
        List<Pflanze> plants = model.getSelectedPflanzeStatistik().getValue();
        new PictureDataSource(image).execute(plants.get(model.getQuizPointer().getValue()).getId());

        Button btn = view.findViewById(R.id.btn_picToQwest);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(quizPicture.this).navigate(R.id.action_quizPicture_to_quizQuestion);
            }
        });

        Button btnNext = view.findViewById(R.id.btn_pictureToNextQuestion);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean noText = false;
                List<Pflanze> plantsen = model.getSelectedPflanzeStatistik().getValue();
                List<FrageAntwortKategorie> fragen = plantsen.get(model.getQuizPointer().getValue()).getFragen();
                for (int i = 0; i < fragen.size(); i++) {
                    if (fragen.get(i).getEingabe().equals(null)) {
                        noText = true;
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setMessage("Fehler: Die Textfelder dürfen nicht leer sein!");
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        break;
                    }
                }
                if (!noText) {
                    model.setQuizPointer(model.getQuizPointer().getValue() + 1);
                    if (model.getQuizPointer().getValue() < model.getQuizSize().getValue()) {
                        NavHostFragment.findNavController(quizPicture.this).navigate(R.id.action_quizPicture_self);
                    } else {
                        model.setStatistikPointer(0);

                        //Zeit
                        long tEnd = SystemClock.elapsedRealtime();
                        long tDelta = tEnd - model.gettStart().getValue();
                        int elapsedSeconds = (int) (tDelta / 1000);

                        int hour = elapsedSeconds / 3600;
                        elapsedSeconds %= 3600;
                        String timeH;
                        if (hour < 10) {
                            timeH = "0" + hour;
                        } else {
                            timeH = hour + "";
                        }

                        int minute = elapsedSeconds / 60;
                        elapsedSeconds %= 60;
                        String timeM;
                        if (minute < 10) {
                            timeM = "0" + minute;
                        } else {
                            timeM = minute + "";
                        }

                        int seconds = elapsedSeconds;
                        String timeS = seconds + "";
                        if (seconds < 10) {
                            timeS = "0" + seconds;
                        } else {
                            timeS = seconds + "";
                        }

                        String time = timeH + ":" + timeM + ":" + timeS;

                        //FehlerQuote
                        int fehler = 0;
                        int fragenZahl = 0;
                        List<Pflanze> pflanzes = model.getSelectedPflanzeStatistik().getValue();//abgeschlossenes Quiz
                        for (int i = 0; i < pflanzes.size(); i++) {
                            List<FrageAntwortKategorie> fragen2 = pflanzes.get(i).getFragen();//Fragen und Antworten an Stelle i
                            for (int j = 0; j < fragen2.size(); j++) {
                                fragenZahl++;
                                if (!fragen2.get(j).getAntwort().equals(fragen.get(j).getEingabe())) {
                                    fehler++;
                                }
                            }
                        }
                        String fehlerquote = fehler + "/" + fragenZahl;
                        //Schlechteste Pflanze

                        //Statistik zwischenspeichern
                        Statistik statistik = new Statistik(fehlerquote, time, "2");
                        model.setSelectedStatistik(statistik);
                        List<Statistik> stat = model.getStatistikList().getValue();
                        stat.add(statistik);
                        model.setStatistikList(stat);

                        //veränderungen hochladen
                        new InsertPflanzeStatistikDataSource(NavHostFragment.findNavController(quizPicture.this), model, "picture").execute();
                    }
                }
            }
        });

    }
}