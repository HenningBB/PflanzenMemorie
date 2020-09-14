package hkbb.de.pflanzenmemorie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Login extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn_login = view.findViewById(R.id.login_btn);
        final EditText txt_username = view.findViewById(R.id.userName_txt);
        final EditText txt_pw = view.findViewById(R.id.userPassword_txt);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = txt_username.getText().toString();
                String pw = txt_pw.getText().toString();
                if (pw.equals("") || user.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage("Fehler: Die Textfelder dürfen nicht leer sein!");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }
}