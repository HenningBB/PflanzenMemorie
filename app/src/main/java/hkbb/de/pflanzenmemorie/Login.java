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
import android.widget.EditText;

import hkbb.de.pflanzenmemorie.DataSources.LoginDataSource;


public class Login extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final DataViewModel model = new ViewModelProvider(requireActivity()).get(DataViewModel.class);
        Button btn_login = view.findViewById(R.id.login_btn);
        final EditText txt_username = view.findViewById(R.id.userName_txt);
        final EditText txt_pw = view.findViewById(R.id.userPassword_txt);
        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage("Fehler: Die Textfelder dürfen nicht leer sein!");
        final AlertDialog dialog = builder.create();
        final NavController nav = NavHostFragment.findNavController(Login.this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = txt_username.getText().toString();
                String pw = txt_pw.getText().toString();
                if (pw.equals("") || user.equals("")) {
                    dialog.show();
                }
                else{
                    new LoginDataSource(builder,nav,model).execute(user,pw);
                }
            }
        });
    }
}