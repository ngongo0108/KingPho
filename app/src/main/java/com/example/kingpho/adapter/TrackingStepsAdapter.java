package com.example.kingpho.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kingpho.R;
import com.example.kingpho.model.TrackingStep;

import java.util.List;

public class TrackingStepsAdapter extends RecyclerView.Adapter<TrackingStepsAdapter.TrackingStepsViewHolder> {

    private List<TrackingStep> trackingSteps;

    public TrackingStepsAdapter(List<TrackingStep> trackingSteps) {
        this.trackingSteps = trackingSteps;
    }

    @NonNull
    @Override
    public TrackingStepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tracking_step, parent, false);
        return new TrackingStepsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackingStepsViewHolder holder, int position) {
        TrackingStep step = trackingSteps.get(position);
        holder.stepTitle.setText(step.getTitle());
        holder.stepSubtitle.setText(step.getSubtitle());
        holder.stepIcon.setImageResource(step.getIconResource());

        // Set status dot background based on step status
        switch (step.getStatus()) {
            case "completed":
                holder.statusDot.setBackgroundResource(R.drawable.dot_completed_orange);
                break;
            case "ongoing":
                holder.statusDot.setBackgroundResource(R.drawable.dot_ongoing_orange);
                break;
            case "pending":
                holder.statusDot.setBackgroundResource(R.drawable.dot_pending);
                break;
            default:
                holder.statusDot.setBackgroundResource(R.drawable.dot_pending);
                break;
        }
    }


    @Override
    public int getItemCount() {
        return trackingSteps.size();
    }

    public static class TrackingStepsViewHolder extends RecyclerView.ViewHolder {
        TextView stepTitle, stepSubtitle;
        ImageView stepIcon;
        View statusDot; // Add statusDot view

        public TrackingStepsViewHolder(@NonNull View itemView) {
            super(itemView);
            stepTitle = itemView.findViewById(R.id.step_title);
            stepSubtitle = itemView.findViewById(R.id.step_subtitle);
            stepIcon = itemView.findViewById(R.id.step_icon);
            statusDot = itemView.findViewById(R.id.status_dot); // Initialize statusDot view

        }
    }
}
