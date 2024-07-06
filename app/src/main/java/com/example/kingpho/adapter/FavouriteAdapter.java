    package com.example.kingpho.adapter;

    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.TextView;

    import androidx.annotation.NonNull;
    import androidx.constraintlayout.widget.ConstraintLayout;
    import androidx.recyclerview.widget.RecyclerView;

    import com.bumptech.glide.Glide;
    import com.example.kingpho.R;
    import com.example.kingpho.helper.Manager;
    import com.example.kingpho.model.Food;

    import java.util.ArrayList;

    public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {
        private ArrayList<Food> favouritesList;
        private Manager manager;

        public FavouriteAdapter(ArrayList<Food> favouritesList, Manager manager) {
            this.favouritesList = favouritesList;
            this.manager = manager;
        }

        @NonNull
        @Override
        public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_favourite, parent, false);
            return new FavouriteViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull FavouriteViewHolder holder, int position) {
            Food food = favouritesList.get(position);
            holder.bind(food);
        }

        @Override
        public int getItemCount() {
            return favouritesList.size();
        }

        class FavouriteViewHolder extends RecyclerView.ViewHolder {
            private ImageView foodImage;
            private TextView foodName;
            private TextView foodPrice;
            private ImageView removeFavouriteButton;

            public FavouriteViewHolder(@NonNull View itemView) {
                super(itemView);
                foodImage = itemView.findViewById(R.id.pic);
                foodName = itemView.findViewById(R.id.title);
                foodPrice = itemView.findViewById(R.id.price);
                removeFavouriteButton = itemView.findViewById(R.id.favBtn);
            }

            public void bind(Food food) {
                foodName.setText(food.getFoodTitle());
                foodPrice.setText(String.valueOf(food.getFoodPrice()));

                int drawableResourceId = itemView.getContext().getResources().getIdentifier(food.getFoodImage(), "drawable", itemView.getContext().getPackageName());
                Glide.with(itemView.getContext())
                        .load(drawableResourceId)
                        .into(foodImage);

//                updateFavouriteButton(manager.isFavourite(food));
                if(favouritesList.size() > 0) {
                    removeFavouriteButton.setImageResource(R.drawable.heart_ic_change);
                }
                removeFavouriteButton.setOnClickListener(v -> {
                    manager.removeFromFavourites(food);
                    favouritesList.remove(food);
                    notifyItemRemoved(getAdapterPosition());
                });
            }

//            private void updateFavouriteButton(boolean isFavourite) {
//                if (isFavourite) {
//                    removeFavouriteButton.setImageResource(R.drawable.heart_ic_change);
//                } else {
//                    removeFavouriteButton.setImageResource(R.drawable.btn3);
//                }
//            }
        }
    }
