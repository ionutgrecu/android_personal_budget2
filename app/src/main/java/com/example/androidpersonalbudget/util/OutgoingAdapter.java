package com.example.androidpersonalbudget.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import java.util.List;

import androidx.annotation.NonNull;

import com.example.androidpersonalbudget.database.models.Outgoing;

public class OutgoingAdapter extends ArrayAdapter<Outgoing> {

    private Context context;
    private int resource;
    private List<Outgoing> Outgoings;
    private LayoutInflater inflater;

    public OutgoingAdapter(@NonNull Context context, int resource, @NonNull List<Outgoing> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.Outgoings = objects;
        this.inflater = inflater;
    }

//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View view = inflater.inflate(resource, parent, false);
//        Outgoing Outgoing = Outgoings.get(position);
//        if (Outgoing != null) {
//            addDate(view, Outgoing.getDate());
//            addCategory(view, Outgoing.getCategory());
//            addAmount(view, Outgoing.getAmount());
//        }
//        return view;
//    }
//
//    private void addDate(View view, Date date) {
//        TextView textView = view.findViewById(R.id.tv_lv_Outgoing_row_date);
//        addTextViewContent(textView, DateConverter.fromDate(date));
//    }
//
//    private void addCategory(View view, String category) {
//        TextView textView = view.findViewById(R.id.tv_lv_Outgoing_row_category);
//        addTextViewContent(textView, category);
//    }
//
//    private void addAmount(View view, Double amount) {
//        TextView textView = view.findViewById(R.id.tv_lv_Outgoing_row_amount);
//        String value = null;
//        if (amount != null) {
//            value = context.getString(R.string.lv_Outgoing_row_amount_value, amount.toString());
//        }
//        addTextViewContent(textView, value);
//    }
//
//    private void addTextViewContent(TextView textView, String value) {
//        if (value != null && !value.isEmpty()) {
//            textView.setText(value);
//        } else {
//            textView.setText(R.string.lv_Outgoing_row_default_value);
//        }
//    }
}
