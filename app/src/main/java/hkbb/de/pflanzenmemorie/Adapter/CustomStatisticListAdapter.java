package hkbb.de.pflanzenmemorie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import hkbb.de.pflanzenmemorie.Models.Statistik;
import hkbb.de.pflanzenmemorie.R;

public class CustomStatisticListAdapter extends BaseAdapter {

    private List<Statistik> statistikList;
    private LayoutInflater layoutInflater;

    public CustomStatisticListAdapter(Context context, List<Statistik> statistikList) {
        this.statistikList = statistikList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return statistikList.size();
    }

    @Override
    public Object getItem(int position) {
        return statistikList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.statisticlistitem, null);
            holder = new CustomStatisticListAdapter.ViewHolder();
            holder.datum = convertView.findViewById(R.id.txt_QuizDate);
            holder.fehler = convertView.findViewById(R.id.txt_QuizFehlerQuote);
            holder.zeit = convertView.findViewById(R.id.txt_QuizTime);
            convertView.setTag(holder);
        } else {
            holder = (CustomStatisticListAdapter.ViewHolder) convertView.getTag();
        }
        holder.datum.setText(statistikList.get(position).getErstellDatum());
        holder.fehler.setText(statistikList.get(position).getFehlerquote());
        holder.zeit.setText(statistikList.get(position).getZeit());
        return convertView;
    }

    static class ViewHolder {
        TextView datum;
        TextView fehler;
        TextView zeit;
    }
}
