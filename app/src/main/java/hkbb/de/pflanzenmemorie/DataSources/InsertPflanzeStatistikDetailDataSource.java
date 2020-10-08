package hkbb.de.pflanzenmemorie.DataSources;

import android.os.AsyncTask;

import androidx.navigation.NavController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import hkbb.de.pflanzenmemorie.DataViewModel;
import hkbb.de.pflanzenmemorie.R;

public class InsertPflanzeStatistikDetailDataSource extends AsyncTask<String, Void, String> {
    public static final String POST_PARAM_KEYVALUE_SEPARATOR = "=";
    public static final String POST_PARAM_SEPARATOR = "&";
    public static String DESTINATION_METHOD = "createStatEinzel";
    private URLConnection conn;
    private String mode;
    private NavController nav;

    public InsertPflanzeStatistikDetailDataSource(NavController nav, String mode) {
        this.nav = nav;
        this.mode = mode;
    }

    protected String doInBackground(String... strings) {
        try {
            getPlantStatisticConnection(strings[0], strings[1], strings[2], strings[3]);
            return readResult();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        //return "oo";
    }

    private void getPlantStatisticConnection(String IDStatistik, String IDKategorie, String IDPflanze, String Eingabe) throws IOException {
        StringBuffer dataBuffer = new StringBuffer();
        dataBuffer.append(URLEncoder.encode("method", "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(DESTINATION_METHOD, "UTF-8"));
        dataBuffer.append(POST_PARAM_SEPARATOR);
        dataBuffer.append(URLEncoder.encode("IDs", "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(IDStatistik, "UTF-8"));
        dataBuffer.append(POST_PARAM_SEPARATOR);
        dataBuffer.append(URLEncoder.encode("IDk", "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(IDKategorie, "UTF-8"));
        dataBuffer.append(POST_PARAM_SEPARATOR);
        dataBuffer.append(URLEncoder.encode("IDp", "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(IDPflanze, "UTF-8"));
        dataBuffer.append(POST_PARAM_SEPARATOR);
        dataBuffer.append(URLEncoder.encode("Eingabe", "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(Eingabe, "UTF-8"));
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

    protected void onPostExecute(String Result) {
        try {
            if (!mode.equals("working")) {
                if (mode.equals("picture")) {
                    nav.navigate(R.id.action_quizPicture_to_endStatistica);
                } else if (mode.equals("questions")) {
                    nav.navigate(R.id.action_quizQuestion_to_endStatistica);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
