package hkbb.de.pflanzenmemorie.DataSources;

import android.app.AlertDialog;

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

public class StatistikDataSource {

    public static final String POST_PARAM_KEYVALUE_SEPARATOR = "=";
    public static final String POST_PARAM_SEPARATOR = "&";
    public static String DESTINATION_METHOD = "login";
    private AlertDialog.Builder builder;
    private URLConnection conn;
    private NavController nav;


    public StatistikDataSource(NavController nav,String method) {
        this.nav = nav;
        DESTINATION_METHOD = method;
    }

    protected String doInBackground(String... strings) {
        try {
            if (DESTINATION_METHOD == "getStatistik") {
                getStatisticConnection(strings[0], strings[1]);
                return readResult();
            }
            else if (DESTINATION_METHOD == "createStatistik"){

            }
            else if (DESTINATION_METHOD == "getStatDetails"){

            }
            else if (DESTINATION_METHOD == "createStatDeatails"){

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void getStatisticConnection(String user, String pw) throws IOException{
        StringBuffer dataBuffer = new StringBuffer();
        dataBuffer.append(URLEncoder.encode(DESTINATION_METHOD, "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(DESTINATION_METHOD, "UTF-8"));
        //Adresse der PHP Schnittstelle für die Verbindung zur MySQL Datenbank
        URL url = new URL("http://10.33.11.142/API/dbSchnittstelle.php");
        conn = url.openConnection();
        conn.setDoOutput(true);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(dataBuffer.toString());
        wr.flush();
    }

    private void setStatisticConnection(String user, String pw) throws IOException{

    }

    private void getStatisticDetailConnection(String user, String pw) throws IOException{

    }

    private void setStatisticDetailConnection(String user, String pw) throws IOException{

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
}
