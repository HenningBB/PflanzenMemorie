package hkbb.de.pflanzenmemorie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import hkbb.de.pflanzenmemorie.DataViewModel;
import hkbb.de.pflanzenmemorie.Models.FrageAntwortKategorie;
import hkbb.de.pflanzenmemorie.Models.Pflanze;
import hkbb.de.pflanzenmemorie.R;

public class CustomQuizListAdapter extends BaseAdapter {

    private DataViewModel model;
    private List<FrageAntwortKategorie> kategorieList;
    private List<Pflanze> pflanzeList;
    private Pflanze pflanze;
    private LayoutInflater layoutInflater;

    public CustomQuizListAdapter(Context context, DataViewModel model) {
        layoutInflater = LayoutInflater.from(context);
        this.model = model;
        pflanzeList = this.model.getSelectedPflanzeStatistik().getValue();
        pflanze = pflanzeList.get(this.model.getQuizPointer().getValue());
        this.kategorieList = pflanze.getFragen();
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.questionlistitem, null);
            holder = new ViewHolder();
            holder.frage = convertView.findViewById(R.id.txt_kategorie);
            holder.antwort = convertView.findViewById(R.id.txt_antwort);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.kat = kategorieList.get(position);
        holder.pL=pflanzeList;
        holder.frage.setText(holder.kat.getName());
        holder.antwort.setText(holder.kat.getEingabe());
        holder.antwort.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    holder.kat.setEingabe(holder.antwort.getText().toString());
                    model.setSelectedPflanzeStatistik(holder.pL);
                }
            }
        });
        return convertView;
    }


    static class ViewHolder {
        TextView frage;
        EditText antwort;
        FrageAntwortKategorie kat;
        List<Pflanze> pL;
    }
}
