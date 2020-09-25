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

import hkbb.de.pflanzenmemorie.Adapter.CustomStatisticListAdapter;

public class statistic_list extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistic_list, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            DataViewModel model = new ViewModelProvider(requireActivity()).get(DataViewModel.class);
            ListView list = view.findViewById(R.id.listOfStatistics);
            CustomStatisticListAdapter adapter = new CustomStatisticListAdapter(this.getContext(), model.getStatistikList().getValue());
            list.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Button btnToMenu = view.findViewById(R.id.btn_StatisticListToMenu);
        btnToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(statistic_list.this).navigate(R.id.action_statistic_list_to_mainMenu);
            }
        });
    }
}