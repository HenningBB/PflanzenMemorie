package hkbb.de.pflanzenmemorie;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.List;

import hkbb.de.pflanzenmemorie.DataSources.PlantDataSource;
import hkbb.de.pflanzenmemorie.Models.Pflanze;


public class quizQuestion extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_question, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DataViewModel model = new ViewModelProvider(requireActivity()).get(DataViewModel.class);
        ListView list = view.findViewById(R.id.qustionList);
        List<Pflanze> plantsen = model.getKasten().getValue();
        CustomQuizListAdapter adapter = new CustomQuizListAdapter(this.getContext(), plantsen.get(0).getFragen());
        list.setAdapter(adapter);

        Button btn = view.findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(quizQuestion.this).navigate(R.id.action_quizQuestion_to_quizPicture);
            }
        });

    }
}