package com.example.kingpho;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TrackingStepsAdapter extends RecyclerView.Adapter<TrackingStepsAdapter.ViewHolder> {

    private List<TrackingStep> steps;

    public TrackingStepsAdapter(List<TrackingStep> steps) {
        this.steps = steps;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tracking_step, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TrackingStep step = steps.get(position);
        holder.textViewStepDescription.setText(step.getDescription());
        holder.imageViewStepIcon.setImageResource(step.getIcon());
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewStepDescription;
        ImageView imageViewStepIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewStepDescription = itemView.findViewById(R.id.textViewStepDescription);
            imageViewStepIcon = itemView.findViewById(R.id.imageViewStepIcon);
        }
    }
}
