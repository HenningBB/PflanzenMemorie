package hkbb.de.pflanzenmemorie;

import android.app.AlertDialog;
import android.os.AsyncTask;

import androidx.navigation.NavController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ActivityDataSource extends AsyncTask<String, Void, String> {
    public static final String POST_PARAM_KEYVALUE_SEPARATOR = "=";
    public static final String POST_PARAM_SEPARATOR = "&";
    public static String DESTINATION_METHOD = "login";
    public static final String USERVALUE = "User";
    public static final String PWVALUE = "PW";
    private AlertDialog.Builder builder;
    private URLConnection conn;
    private NavController nav;

    public ActivityDataSource(AlertDialog.Builder alertDialog, NavController nav) {
        this.builder = alertDialog;
        this.nav = nav;
    }

    public ActivityDataSource(NavController nav, String method) {
        this.nav = nav;
        DESTINATION_METHOD = method;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            if (DESTINATION_METHOD == "login") {
                openConnection(strings[0], strings[1]);
                return readResult();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void openConnection(String user, String pw) throws IOException {
        String salt = user.substring(user.length() - 3, user.length());
        pw += salt;
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = digest.digest(pw.getBytes(StandardCharsets.UTF_8));
        pw = bin2hex(hash);

        StringBuffer dataBuffer = new StringBuffer();
        dataBuffer.append(URLEncoder.encode("method", "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(DESTINATION_METHOD, "UTF-8"));
        dataBuffer.append(POST_PARAM_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(USERVALUE, "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(user, "UTF-8"));
        dataBuffer.append(POST_PARAM_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(PWVALUE, "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(pw, "UTF-8"));
        //Adresse der PHP Schnittstelle für die Verbindung zur MySQL Datenbank
        //URL url = new URL("http://10.33.111.1/Pflanzenbestimmung/logintest.php");
        //URL url = new URL("http://10.33.11.142/Karteigarten/dbSchnittstelle.php"); //Steven
        URL url = new URL("http://10.33.156.144/dbSchnittstelle.php"); //Dirk
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
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            JSONArray object = new JSONArray(result);
            builder.setMessage(result);
            nav.navigate(R.id.action_login_to_mainMenu);

        } catch (JSONException e) {
            builder.setMessage("Benutzername oder Passwort ist falsch!");
            e.printStackTrace();
        }
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    //Hash-Funktion
    static String bin2hex(byte[] data) {
        StringBuilder hex = new StringBuilder(data.length * 2);
        for (byte b : data)
            hex.append(String.format("%02x", b & 0xFF));
        return hex.toString();
    }
}
