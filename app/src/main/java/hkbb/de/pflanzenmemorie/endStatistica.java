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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import hkbb.de.pflanzenmemorie.Adapter.CustomAuswertungsAdapter;
import hkbb.de.pflanzenmemorie.DataSources.PictureDataSource;
import hkbb.de.pflanzenmemorie.Models.FrageAntwortKategorie;
import hkbb.de.pflanzenmemorie.Models.Pflanze;

public class endStatistica extends Fragment {

    private List<Pflanze> plants;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_end_statistica, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final DataViewModel model = new ViewModelProvider(requireActivity()).get(DataViewModel.class);
        ImageView image = view.findViewById(R.id.imageView);
        try {
            plants = model.getSelectedPflanzeStatistik().getValue();
            List<FrageAntwortKategorie> antw = plants.get(model.getStatistikPointer().getValue()).getFragen();

            ListView list = view.findViewById(R.id.auswertungsList);
            CustomAuswertungsAdapter adapter = new CustomAuswertungsAdapter(this.getContext(), antw);

            list.setAdapter(adapter);

            new PictureDataSource(image, model).execute(plants.get(model.getStatistikPointer().getValue()).getId());
            //new PictureDataSource(image).execute("3");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String notiz = plants.get(model.getStatistikPointer().getValue()).getNotiz();

        if (!notiz.isEmpty()) {
            if (!notiz.contains("null")) {
                TextView notiztxt = view.findViewById(R.id.Notiz_txt);
                notiztxt.setText(notiz);
            }
        }

        Button btnStatistik = view.findViewById(R.id.btn_goToStatistic);
        btnStatistik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < plants.size(); i++) {
                    for (int j = 0; j < plants.get(i).getFragen().size(); j++) {
                        plants.get(i).getFragen().get(j).setEingabe("");
                    }
                }
                NavHostFragment.findNavController(endStatistica.this).navigate(R.id.action_endStatistica_to_statistic);
            }
        });

        Button btnScroll = view.findViewById(R.id.nextPlant_btn);
        btnScroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (model.getStatistikPointer().getValue() < model.getSelectedPflanzeStatistik().getValue().size() - 1) {
                    model.setStatistikPointer(model.getStatistikPointer().getValue() + 1);
                } else {
                    model.setStatistikPointer(0);
                }

                NavHostFragment.findNavController(endStatistica.this).navigate(R.id.action_endStatistica_self);
            }
        });
    }
}