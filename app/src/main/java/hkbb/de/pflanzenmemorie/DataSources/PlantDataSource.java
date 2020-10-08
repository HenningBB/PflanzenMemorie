package hkbb.de.pflanzenmemorie.DataSources;

import android.app.AlertDialog;
import android.os.AsyncTask;

import androidx.navigation.NavController;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import hkbb.de.pflanzenmemorie.DataViewModel;
import hkbb.de.pflanzenmemorie.Models.FrageAntwortKategorie;
import hkbb.de.pflanzenmemorie.Models.Pflanze;

public class PlantDataSource extends AsyncTask<String, Void, String> {

    public static final String POST_PARAM_KEYVALUE_SEPARATOR = "=";
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
        //URL url = new URL("http://10.33.11.142/API/dbSchnittstelle.php"); //Steven, offiziel!
        URL url = new URL("https://pflanzenbestimmung.000webhostapp.com/dbSchnittstelle.php");
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
            List<Pflanze> pflanzeList = new ArrayList<>();
            JSONArray object = new JSONArray(result);
            for (int i = 0; i < object.length(); i++) {
                JSONArray kategories = new JSONArray(object.getJSONObject(i).getString("0"));
                List<FrageAntwortKategorie> kategorieList = new ArrayList<>();
                for (int j = 0; j < kategories.length(); j++) {
                    JSONObject ubject = kategories.getJSONObject(j);
                    FrageAntwortKategorie kat = new FrageAntwortKategorie(ubject.getString("kategorie_id"),ubject.getString("kategorie_name"), ubject.getString("antwort"));
                    kategorieList.add(kat);
                }
                Pflanze pflanze = new Pflanze(kategorieList,object.getJSONObject(i).getString("id_pflanze"));
                pflanzeList.add(pflanze);
            }
            model.setKasten(pflanzeList);

            builder.setMessage(result);
            // nav.navigate(R.id.action_login_to_mainMenu);
        } catch (Exception e) {
            builder.setMessage("Fehler!");
            e.printStackTrace();
        }
        final AlertDialog alertDialog = builder.create();
        //alertDialog.show();
    }
}