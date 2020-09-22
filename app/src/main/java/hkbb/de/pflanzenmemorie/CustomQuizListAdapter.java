package hkbb.de.pflanzenmemorie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import hkbb.de.pflanzenmemorie.Models.FrageAntwortKategorie;
import hkbb.de.pflanzenmemorie.Models.Pflanze;

public class CustomQuizListAdapter extends BaseAdapter {

    private List<FrageAntwortKategorie> kategorieList;
    private LayoutInflater layoutInflater;

    public CustomQuizListAdapter(Context context, List<FrageAntwortKategorie> list) {
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.questionlistitem, null);
            holder = new ViewHolder();
            holder.frage = convertView.findViewById(R.id.txt_kategorie);
            holder.antwort = convertView.findViewById(R.id.txt_antwort);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        FrageAntwortKategorie kat = this.kategorieList.get(position);
        holder.frage.setText(kat.getName());

        return convertView;
    }

    static class ViewHolder {
        TextView frage;
        EditText antwort;
    }
}
