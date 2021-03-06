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

public class InsertPflanzeStatistikDataSource extends AsyncTask<String, Void, String> {
    public static final String POST_PARAM_KEYVALUE_SEPARATOR = "=";
    public static final String POST_PARAM_SEPARATOR = "&";
    public static String DESTINATION_METHOD = "createStatistik";
    private URLConnection conn;
    private NavController nav;
    private DataViewModel model;
    private String mode;

    public InsertPflanzeStatistikDataSource(NavController nav, DataViewModel model, String mode) {
        this.nav = nav;
        this.model = model;
        this.mode = mode;
    }

    protected String doInBackground(String... strings) {
        try {
            getPlantStatisticConnection();
            return readResult();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        //return "oo";
    }

    private void getPlantStatisticConnection() throws IOException {
        StringBuffer dataBuffer = new StringBuffer();
        dataBuffer.append(URLEncoder.encode("method", "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(DESTINATION_METHOD, "UTF-8"));
        dataBuffer.append(POST_PARAM_SEPARATOR);
        dataBuffer.append(URLEncoder.encode("IDaz", "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(model.getBenutzer().getValue().getId(), "UTF-8"));
        dataBuffer.append(POST_PARAM_SEPARATOR);
        dataBuffer.append(URLEncoder.encode("FQuote", "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(model.getSelectedStatistic().getValue().getFehlerquote(), "UTF-8"));
        dataBuffer.append(POST_PARAM_SEPARATOR);
        dataBuffer.append(URLEncoder.encode("Zeit", "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(model.getSelectedStatistic().getValue().getZeit(), "UTF-8"));
        dataBuffer.append(POST_PARAM_SEPARATOR);
        dataBuffer.append(URLEncoder.encode("IDp", "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(model.getSelectedStatistic().getValue().getSchlechtestePlfanze(), "UTF-8"));
        //Adresse der PHP Schnittstelle für die Verbindung zur MySQL Datenbank
        URL url = new URL(model.getdbString().getValue());
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
            new StatistikDataSource(model,nav,mode).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
