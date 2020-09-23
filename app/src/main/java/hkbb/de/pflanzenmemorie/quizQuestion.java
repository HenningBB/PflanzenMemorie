package hkbb.de.pflanzenmemorie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import java.util.List;

import hkbb.de.pflanzenmemorie.Models.FrageAntwortKategorie;
import hkbb.de.pflanzenmemorie.Models.Pflanze;


public class quizQuestion extends Fragment {
    List<Pflanze> plantsen;
    List<FrageAntwortKategorie> fragen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_question, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DataViewModel model = new ViewModelProvider(requireActivity()).get(DataViewModel.class);
        final ListView list = view.findViewById(R.id.qustionList);
        plantsen = model.getKasten().getValue();
        fragen = plantsen.get(0).getFragen();
        final CustomQuizListAdapter adapter = new CustomQuizListAdapter(this.getContext(), fragen);
        list.setAdapter(adapter);

        Button btn = view.findViewById(R.id.btn_changeToPicture);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(quizQuestion.this).navigate(R.id.action_quizQuestion_to_quizPicture);
            }
        });

    }
}