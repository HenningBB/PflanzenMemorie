package hkbb.de.pflanzenmemorie;

import android.app.AlertDialog;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class ActivityDataSource extends AsyncTask<String, Void, String> {
    public static final String POST_PARAM_KEYVALUE_SEPARATOR = "=";
    public static final String POST_PARAM_SEPARATOR = "&";
    public static final String DESTINATION_METHOD = "login";
    public static final String USERVALUE="User";
    public static final String PWVALUE="PW";
    private AlertDialog.Builder builder;
    private URLConnection conn;

    public ActivityDataSource(AlertDialog.Builder alertDialog) {
        this.builder = alertDialog;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            openConnection(strings[0],strings[1]);
            return readResult();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void openConnection(String user,String pw) throws IOException {
        StringBuffer dataBuffer = new StringBuffer();
        dataBuffer.append(URLEncoder.encode("method", "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(DESTINATION_METHOD, "UTF-8"));
        dataBuffer.append(POST_PARAM_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(USERVALUE,"UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(user,"UTF-8"));
        dataBuffer.append(POST_PARAM_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(PWVALUE,"UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(pw,"UTF-8"));
        //Adresse der PHP Schnittstelle für die Verbindung zur MySQL Datenbank
        URL url = new URL("http://10.33.111.1/Pflanzenbestimmung/logintest.php");
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
        String line = null;
        //Solange Daten bereitstehen werden diese gelesen.
        while ((line=reader.readLine()) != null){
            sb.append(line);
        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        if ( (!isBlank(result))) {
            builder.setMessage(result);

        }
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
