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

import hkbb.de.pflanzenmemorie.DataSources.PlantDataSource;


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

        new PlantDataSource(builder,nav,model).execute();

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