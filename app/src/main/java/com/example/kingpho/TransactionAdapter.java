package com.example.kingpho;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TransactionAdapter extends ArrayAdapter<Transaction> {

    public TransactionAdapter(Context context, List<Transaction> transactions) {
        super(context, 0, transactions);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Transaction transaction = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_transaction, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        TextView tvSubtitle = convertView.findViewById(R.id.tvSubtitle);
        TextView tvDate = convertView.findViewById(R.id.tvDate);

        // Assuming you have a method to get the drawable resource ID for the Pho image
        int imageResId = getContext().getResources().getIdentifier(transaction.getImageName(), "drawable", getContext().getPackageName());
        imageView.setImageResource(imageResId);

        tvTitle.setText(transaction.getItemName());
        tvSubtitle.setText("Subtitle text here"); // Replace with actual subtitle if available
        tvDate.setText(transaction.getDate());

        return convertView;
    }
}

