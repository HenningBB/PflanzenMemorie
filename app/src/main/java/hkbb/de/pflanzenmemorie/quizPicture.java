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
                model.setQuizPointer(model.getQuizPointer().getValue() + 1);
                if (model.getQuizPointer().getValue() < model.getQuizSize().getValue()) {
                    NavHostFragment.findNavController(quizPicture.this).navigate(R.id.action_quizPicture_self);
                } else {
                    model.setStatistikPointer(0);
                    NavHostFragment.findNavController(quizPicture.this).navigate(R.id.action_quizPicture_to_endStatistica);
                }
            }
        });

    }
}