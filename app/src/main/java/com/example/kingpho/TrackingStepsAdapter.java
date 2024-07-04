package com.example.kingpho;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TrackingStepsAdapter extends RecyclerView.Adapter<TrackingStepsAdapter.TrackingStepViewHolder> {

    private List<TrackingStep> trackingSteps;

    public TrackingStepsAdapter(List<TrackingStep> trackingSteps) {
        this.trackingSteps = trackingSteps;
    }

    @NonNull
    @Override
    public TrackingStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tracking_step, parent, false);
        return new TrackingStepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackingStepViewHolder holder, int position) {
        TrackingStep step = trackingSteps.get(position);
        holder.stepTitle.setText(step.getTitle());
        holder.stepSubtitle.setText(step.getSubtitle());

        // Hide connecting line for the first item
        if (position == 0) {
            holder.verticalLine.setVisibility(View.INVISIBLE);
        } else {
            holder.verticalLine.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return trackingSteps.size();
    }

    static class TrackingStepViewHolder extends RecyclerView.ViewHolder {
        TextView stepTitle, stepSubtitle;
        View verticalLine;

        TrackingStepViewHolder(View itemView) {
            super(itemView);
            stepTitle = itemView.findViewById(R.id.step_title);
            stepSubtitle = itemView.findViewById(R.id.step_subtitle);
            verticalLine = itemView.findViewById(R.id.vertical_line);
        }
    }
}
