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

public class QuizDataSource extends AsyncTask<String, Void, String> {

    public static final String POST_PARAM_KEYVALUE_SEPARATOR = "=";
    public static final String POST_PARAM_SEPARATOR = "&";
    public static final String DESTINATION_METHOD = "getQuizArt";
    public static final String VALUE = "IDaz";
    private AlertDialog.Builder builder;
    private URLConnection conn;
    private DataViewModel model;
    private String id;

    public QuizDataSource(AlertDialog.Builder builder, DataViewModel model) {
        this.builder = builder;
        this.model = model;
        id = model.getBenutzer().getValue().getIdQuiz();
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
        /*dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(VALUE, "UTF-8"));
        dataBuffer.append(POST_PARAM_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(id, "UTF-8"));*/
        URL url = new URL(model.getdbString().getValue());
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
            JSONArray object = new JSONArray(result);
            int tryquiz = 0;
            boolean myquiz = false;
            while (!myquiz) {
                JSONObject quizes = object.getJSONObject(tryquiz);
                if (quizes.get("id").equals(model.getBenutzer().getValue().getIdQuiz())) {
                    myquiz = true;
                    JSONArray pflanzen = quizes.getJSONArray("pflanzen");
                    List<String> quizpflanzen = new ArrayList<>();
                    for (int i = 0; i < pflanzen.length(); i++) {
                        JSONObject id = pflanzen.getJSONObject(i);
                        quizpflanzen.add(id.getString("id_pflanze"));
                    }
                    model.setQuiz(quizpflanzen);
                } else {
                    if (tryquiz == object.length() - 1) {
                        break;
                    } else {
                        tryquiz++;
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
