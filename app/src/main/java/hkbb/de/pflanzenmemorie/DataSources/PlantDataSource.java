package hkbb.de.pflanzenmemorie.DataSources;

import android.app.AlertDialog;
import android.os.AsyncTask;

import androidx.navigation.NavController;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import hkbb.de.pflanzenmemorie.DataViewModel;
import hkbb.de.pflanzenmemorie.Models.Benutzer;
import hkbb.de.pflanzenmemorie.R;

public class PlantDataSource extends AsyncTask<String, Void, String> {

    public static final String POST_PARAM_KEYVALUE_SEPARATOR = "=";
    public static final String POST_PARAM_SEPARATOR = "&";
    public static final String DESTINATION_METHOD = "getPflanzen";
    private AlertDialog.Builder builder;
    private URLConnection conn;
    private NavController nav;
    private DataViewModel model;

    public PlantDataSource(AlertDialog.Builder builder, NavController nav, DataViewModel model) {
        this.builder = builder;
        this.nav = nav;
        this.model = model;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            OpenConnection();
            return readResult();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void OpenConnection() throws IOException {
        StringBuffer dataBuffer = new StringBuffer();
        dataBuffer.append(URLEncoder.encode("method", "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(DESTINATION_METHOD, "UTF-8"));
        URL url = new URL("http://10.33.11.142/API/dbSchnittstelle.php"); //Steven, offiziel!
        conn = url.openConnection();
        conn.setDoOutput(true);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(dataBuffer.toString());
        wr.flush();
    }

    private String readResult() throws IOException {
        String result = null;
        //Lesen der Rückgabewerte vom Server
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        //Solange Daten bereitstehen werden diese gelesen.
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        try {
           // JSONArray object = new JSONArray(result);
            builder.setMessage(result);
           // nav.navigate(R.id.action_login_to_mainMenu);
        } catch (Exception e) {
            builder.setMessage("Fehler!");
            e.printStackTrace();
        }
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
