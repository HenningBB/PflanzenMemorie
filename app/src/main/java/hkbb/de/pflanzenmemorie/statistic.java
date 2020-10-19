package hkbb.de.pflanzenmemorie;

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
import android.widget.TextView;

import hkbb.de.pflanzenmemorie.DataSources.PflanzeStatistikDataSource;
import hkbb.de.pflanzenmemorie.Models.Statistik;


public class statistic extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistic, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController nav = NavHostFragment.findNavController(statistic.this);
        final DataViewModel model = new ViewModelProvider(requireActivity()).get(DataViewModel.class);
        Statistik statistik = model.getSelectedStatistic().getValue();

        TextView txt_fehler = view.findViewById(R.id.errorRateText_lbl);
        txt_fehler.setText(statistik.getFehlerquote());

        TextView txt_zeit = view.findViewById(R.id.timeText_lbl);
        txt_zeit.setText(statistik.getZeit());

        TextView txt_pflanze = view.findViewById(R.id.plantText_lbl);
        txt_pflanze.setText(statistik.getSchlechtestePlfanze());

        Button backToMenu = view.findViewById(R.id.btn_TatisticToStatisticlist);
        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(statistic.this).navigate(R.id.action_statistic_to_statistic_list2);
            }
        });

        Button btnDetail = view.findViewById(R.id.statisticToEvaluation_btn);
        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PflanzeStatistikDataSource(nav,model).execute("getStatDetails");
            }
        });

    }
}