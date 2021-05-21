package com.example.androidpersonalbudget.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.androidpersonalbudget.R;

import com.example.androidpersonalbudget.database.models.Outgoing;

public class OutgoingAdapter extends ArrayAdapter<Outgoing> {

    private Context context;
    private int resource;
    private List<Outgoing> outgoings;
    private LayoutInflater inflater;

    public OutgoingAdapter(@NonNull Context context, int resource, @NonNull List<Outgoing> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.outgoings = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Outgoing outgoing = outgoings.get(position);
        if (null!=outgoing) {
            addDate(view, outgoing.getDate());
            addCategory(view, outgoing.getCategory());
            addAmount(view, outgoing.getAmount());
            addDescription(view, outgoing.getDescription());
        }
        return view;
    }

    private void addDate(View view, Date date) {
        TextView textView = view.findViewById(R.id.tv_lv_outgoing_row_date);
        addTextViewContent(textView, DateConverter.toString((java.sql.Date) date));
    }

    private void addCategory(View view, String category) {
        TextView textView = view.findViewById(R.id.tv_lv_outgoing_row_category);
        addTextViewContent(textView, category);
    }

    private void addAmount(View view, Double amount) {
        TextView textView = view.findViewById(R.id.tv_lv_outgoing_row_amount);
        String value = null;
        if (amount != null) {
            value = context.getString(R.string.lv_outgoing_row_amount_value, amount.toString());
        }
        addTextViewContent(textView, value);
    }

    private void addDescription(View view, String description) {
        TextView textView = view.findViewById(R.id.tv_lv_outgoing_row_description);
        addTextViewContent(textView, description);
    }

    private void addTextViewContent(TextView textView, String value) {
        if (value != null && !value.isEmpty()) {
            textView.setText(value);
        } else {
            textView.setText(R.string.lv_outgoing_row_default_value);
        }
    }
}
