package hkbb.de.pflanzenmemorie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import hkbb.de.pflanzenmemorie.Adapter.CustomAuswertungsAdapter;
import hkbb.de.pflanzenmemorie.Models.FrageAntwortKategorie;
import hkbb.de.pflanzenmemorie.Models.Pflanze;

public class endStatistica extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_end_statistica, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
        DataViewModel model = new ViewModelProvider(requireActivity()).get(DataViewModel.class);

        List<Pflanze> plants = model.getKasten().getValue();
        List<FrageAntwortKategorie> antw = plants.get(model.getQuizPointer().getValue()).getFragen();

        ListView list = view.findViewById(R.id.auswertungsList);
        CustomAuswertungsAdapter adapter = new CustomAuswertungsAdapter(this.getContext(),antw);

            list.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Button btnStatistik=view.findViewById(R.id.btn_goToStatistic);
        btnStatistik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(endStatistica.this).navigate(R.id.action_endStatistica_to_statistic);
            }
        });
    }
}