package hkbb.de.pflanzenmemorie.DataSources;

import android.os.AsyncTask;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

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
import hkbb.de.pflanzenmemorie.Models.Statistik;
import hkbb.de.pflanzenmemorie.R;
import hkbb.de.pflanzenmemorie.mainMenu;

public class StatistikDataSource extends AsyncTask<String, Void, String> {

    public static final String POST_PARAM_KEYVALUE_SEPARATOR = "=";
    public static final String POST_PARAM_SEPARATOR = "&";
    public static String DESTINATION_METHOD = "getStatList";
    private URLConnection conn;
    private DataViewModel model;
    private String mode;

    public StatistikDataSource(DataViewModel model) {
        this.model = model;
    }

    protected String doInBackground(String... strings) {
        mode = strings[0];
        try {
            if (strings[0].equals("getStatistik") || strings[0].isEmpty()) {
                getStatisticConnection();
            } else {

            }
            return readResult();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    private void getStatisticConnection() throws IOException {
        StringBuffer dataBuffer = new StringBuffer();
        dataBuffer.append(URLEncoder.encode("method", "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(DESTINATION_METHOD, "UTF-8"));
        dataBuffer.append(POST_PARAM_SEPARATOR);
        dataBuffer.append(URLEncoder.encode("IDaz", "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(model.getBenutzer().getValue().getId(), "UTF-8"));
        //Adresse der PHP Schnittstelle für die Verbindung zur MySQL Datenbank
        URL url = new URL("http://10.33.11.142/API/dbSchnittstelle.php");
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


    protected void onPostExecute(String result) {
        try {
            if (mode == "getStatistik") {
                List<Statistik> statistikList = new ArrayList<>();
                JSONArray array = new JSONArray(result);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    Statistik stat = new Statistik(object.getString("id_statistik"),
                            object.getString("erstellt"),
                            object.getString("fehlerquote"),
                            object.getString("zeit"),
                            object.getString("id_beste_pflanze"));
                    statistikList.add(stat);
                }
                model.setStatistikList(statistikList);
            } else {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
