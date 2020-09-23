package hkbb.de.pflanzenmemorie;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import hkbb.de.pflanzenmemorie.DataSources.PlantDataSource;
import hkbb.de.pflanzenmemorie.Models.Pflanze;


public class mainMenu extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_menu, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final DataViewModel model = new ViewModelProvider(requireActivity()).get(DataViewModel.class);
        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        final NavController nav = NavHostFragment.findNavController(mainMenu.this);

        if (model.getKasten().getValue() == null) {
            new PlantDataSource(builder, nav, model).execute();
        }

        Button btn_abmelden = view.findViewById(R.id.menuLogout_btn);
        btn_abmelden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(mainMenu.this).navigate(R.id.action_mainMenu_to_login);
            }
        });

        Button btnRandomQuiz = view.findViewById(R.id.ownQuiz_btn);
        btnRandomQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //here we use the quizsize we get from the db
                model.setQuizSize(2);

                List<Pflanze> list = model.getKasten().getValue();
                List<Integer> qui = new ArrayList<>();
                for (int i = 0; i < model.getQuizSize().getValue(); i++) {
                    qui.add(i);
                }
                model.setQuizPointer(0);
                model.setQuiz(qui);

                NavHostFragment.findNavController(mainMenu.this).navigate(R.id.action_mainMenu_to_quizPicture);
            }
        });

        Button toStatistic = view.findViewById(R.id.statistic_btn);
        toStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(mainMenu.this).navigate(R.id.action_mainMenu_to_statistic_list);
            }
        });
    }
}