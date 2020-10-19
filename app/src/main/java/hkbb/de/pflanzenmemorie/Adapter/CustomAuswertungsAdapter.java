package hkbb.de.pflanzenmemorie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import hkbb.de.pflanzenmemorie.Models.FrageAntwortKategorie;
import hkbb.de.pflanzenmemorie.R;

public class CustomAuswertungsAdapter extends BaseAdapter {
    private List<FrageAntwortKategorie> kategorieList;
    private LayoutInflater layoutInflater;

    public CustomAuswertungsAdapter(Context context, List<FrageAntwortKategorie> list) {
        this.kategorieList = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return kategorieList.size();
    }

    @Override
    public Object getItem(int position) {
        return kategorieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

            CustomAuswertungsAdapter.ViewHolder holdero;
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.auswertungsliste, null);
                holdero = new CustomAuswertungsAdapter.ViewHolder();
                holdero.kategorie = convertView.findViewById(R.id.txt_kategorieAuswertung);
                holdero.antwort = convertView.findViewById(R.id.txt_antwortAuswertung);
                holdero.loesung = convertView.findViewById(R.id.txt_richtigeAntwort);
                convertView.setTag(holdero);
            } else {
                holdero = (CustomAuswertungsAdapter.ViewHolder) convertView.getTag();
            }
            holdero.kat = this.kategorieList.get(position);
        try {
            holdero.kategorie.setText(holdero.kat.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            holdero.antwort.setText(holdero.kat.getEingabe());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            holdero.loesung.setText(holdero.kat.getAntwort());
        } catch (Exception e) {
            e.printStackTrace();
        }


        return convertView;
    }


    static class ViewHolder {
        TextView kategorie;
        TextView antwort;
        TextView loesung;
        FrageAntwortKategorie kat;
    }
}
