package hkbb.de.pflanzenmemorie.DataSources;

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
import hkbb.de.pflanzenmemorie.R;

public class PflanzeStatistikDataSource extends AsyncTask<String, Void, String> {

    public static final String POST_PARAM_KEYVALUE_SEPARATOR = "=";
    public static final String POST_PARAM_SEPARATOR = "&";
    public static String DESTINATION_METHOD = "getStatistik";
    private URLConnection conn;
    private NavController nav;
    private DataViewModel model;

    public PflanzeStatistikDataSource(NavController nav, DataViewModel model) {
        this.nav = nav;
        this.model = model;
    }

    protected String doInBackground(String... strings) {
        try {
            if (strings[0].equals("getStatDetails") || strings[0].isEmpty()) {
                getPlantStatisticConnection();
            } else {
                getPlantStatisticDetailConnection();
            }
            return readResult();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    private void getPlantStatisticConnection() throws IOException {
        StringBuffer dataBuffer = new StringBuffer();
        dataBuffer.append(URLEncoder.encode("method", "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(DESTINATION_METHOD, "UTF-8"));
        dataBuffer.append(POST_PARAM_SEPARATOR);
        dataBuffer.append(URLEncoder.encode("IDs", "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(model.getSelectedStatistic().getValue().getId(), "UTF-8"));
        //Adresse der PHP Schnittstelle für die Verbindung zur MySQL Datenbank
        //URL url = new URL("http://10.33.11.142/API/dbSchnittstelle.php");
        URL url = new URL("https://pflanzenbestimmung.000webhostapp.com/dbSchnittstelle.php");
        conn = url.openConnection();
        conn.setDoOutput(true);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(dataBuffer.toString());
        wr.flush();
    }

    private void getPlantStatisticDetailConnection() throws IOException {

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


    protected void onPostExecute(String result) {
        try {
            if (DESTINATION_METHOD.equals("getStatistik")) {
                List<Pflanze> statistikList = new ArrayList<>();
                JSONArray array = new JSONArray(result);
                JSONObject object = array.getJSONObject(0);
                JSONArray plnts = new JSONArray(object.getString("pflanzen"));
                for (int i = 0; i < plnts.length(); i++) {
                    JSONObject objecto = plnts.getJSONObject(i);
                    JSONArray arrayy = objecto.getJSONArray("antworten");
                    List<FrageAntwortKategorie> fr = new ArrayList<>();
                    for (int j = 0; j < arrayy.length(); j++) {
                        JSONObject stst = arrayy.getJSONObject(j);
                        FrageAntwortKategorie stat = new FrageAntwortKategorie(1,
                                stst.getString("kategorie"),
                                stst.getString("korrekt"),
                                stst.getString("eingabe"));
                        fr.add(stat);
                    }
                    Pflanze pl = new Pflanze(fr,objecto.getString("id_pflanze"));
                    statistikList.add(pl);
                }
                model.setSelectedPflanzeStatistik(statistikList);
                model.setStatistikPointer(0);
                nav.navigate(R.id.action_statistic_to_endStatistica);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
