package hkbb.de.pflanzenmemorie.DataSources;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.widget.ImageView;

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

import hkbb.de.pflanzenmemorie.DataViewModel;

public class PictureDataSource extends AsyncTask<String, Void, String> {
    public static final String POST_PARAM_KEYVALUE_SEPARATOR = "=";
    public static final String POST_PARAM_SEPARATOR = "&";
    public static String DESTINATION_METHOD = "getPBilder";
    private URLConnection conn;
    private ImageView image;
    private DataViewModel model;


    public PictureDataSource(ImageView image, DataViewModel model) {
        this.image = image;
        this.model = model;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            if (DESTINATION_METHOD.equals("getPBilder")) {
                OpenConnection(strings[0]);
                return readResult();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void OpenConnection(String user) throws IOException {
        StringBuffer dataBuffer = new StringBuffer();
        dataBuffer.append(URLEncoder.encode("method", "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(DESTINATION_METHOD, "UTF-8"));
        dataBuffer.append(POST_PARAM_SEPARATOR);
        dataBuffer.append(URLEncoder.encode("IDp", "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(user, "UTF-8"));
        //Adresse der PHP Schnittstelle für die Verbindung zur MySQL Datenbank
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
            JSONObject blobject = object.getJSONObject(0);
            String blob = blobject.getString("bild");
            byte[] imageBytes = blob.getBytes();
            Bitmap bm;
            byte[] decodedString = Base64.decode(blob, Base64.DEFAULT);
            bm = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            image.setImageBitmap(bm);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
