package com.example.kingpho.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.kingpho.Interface.ChangeNumberItemsListener;
import com.example.kingpho.R;
import com.example.kingpho.helper.Manager;
import com.example.kingpho.model.Food;
import java.util.ArrayList;
import java.util.HashMap;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private ArrayList<Food> cartList;
    private Manager manager;
    private ChangeNumberItemsListener changeNumberItemsListener;
    private OnItemCheckedChangeListener checkedChangeListener;
    private HashMap<Integer, Boolean> checkedStates;

    public CartAdapter(ArrayList<Food> cartList, Manager manager, ChangeNumberItemsListener changeNumberItemsListener, OnItemCheckedChangeListener checkedChangeListener) {
        this.cartList = cartList;
        this.manager = manager;
        this.changeNumberItemsListener = changeNumberItemsListener;
        this.checkedChangeListener = checkedChangeListener;
        this.checkedStates = new HashMap<>();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Food item = cartList.get(position);
        holder.bind(item, position);
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice, productQuantity;
        ImageView productImage, minusBtn, plusBtn;
        CheckBox checkBox;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.titleMethod);
            productPrice = itemView.findViewById(R.id.price);
            productQuantity = itemView.findViewById(R.id.textViewQuantity);
            productImage = itemView.findViewById(R.id.pic);
            minusBtn = itemView.findViewById(R.id.minusBtn);
            plusBtn = itemView.findViewById(R.id.plusBtn);
            checkBox = itemView.findViewById(R.id.checkbox);

            minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                manager.minusNumberFood(cartList, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });
            plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                manager.plusNumberFood(cartList, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });


            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    checkedStates.put(position, isChecked);
                    if (checkedChangeListener != null) {
                        checkedChangeListener.onItemCheckedChanged();
                    }
                }
            });
        }

        public void bind(Food item, int position) {
            productName.setText(item.getFoodTitle());
            productPrice.setText(String.format("%.0f", item.getFoodPrice()));
            productQuantity.setText(String.valueOf(item.getNumberInCart()));
            int drawableResourceId = itemView.getContext().getResources().getIdentifier(item.getFoodImage(), "drawable", itemView.getContext().getPackageName());
            Glide.with(itemView.getContext())
                    .load(drawableResourceId)
                    .into(productImage);
            checkBox.setChecked(checkedStates.containsKey(position) ? checkedStates.get(position) : false);
        }
    }

    public interface OnItemCheckedChangeListener {
        void onItemCheckedChanged();
    }

    public HashMap<Integer, Boolean> getCheckedStates() {
        return checkedStates;
    }
}









//            minusBtn.setOnClickListener(v -> {
//                int position = getAdapterPosition();
//                if (position != RecyclerView.NO_POSITION) {
//                    manager.minusNumberFood(cartList, position, () -> {
//                        notifyItemRemoved(position);
//                        notifyItemRangeChanged(position, cartList.size());
//                        if (changeNumberItemsListener != null) {
//                            changeNumberItemsListener.changed();
//                        }
//                    });
//                }
//            });

//            plusBtn.setOnClickListener(v -> {
//                int position = getAdapterPosition();
//                if (position != RecyclerView.NO_POSITION) {
//                    manager.plusNumberFood(cartList, position, () -> {
//                        notifyItemChanged(position);
//                        if (changeNumberItemsListener != null) {
//                            changeNumberItemsListener.changed();
//                        }
//                    });
//                }
//            });
//public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
//    private ArrayList<Food> cartItemList;
//    private Manager manager;
//    private ArrayList<Food> selectedItems;
//    private CartAdapterListener listener;
//    private ChangeNumberItemsListener changeNumberItemsListener;
//
//    public CartAdapter(ArrayList<Food> cartItemList, Manager manager, ArrayList<Food> selectedItems, CartAdapterListener listener, ChangeNumberItemsListener changeNumberItemsListener) {
//        this.cartItemList = cartItemList;
//        this.manager = manager;
//        this.selectedItems = selectedItems;
//        this.listener = listener;
//        this.changeNumberItemsListener = changeNumberItemsListener;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
//        return new ViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Food item = cartItemList.get(position);
//        holder.bind(item);
//
//        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            if (isChecked) {
//                selectedItems.add(item);
//            } else {
//                selectedItems.remove(item);
//            }
//            listener.onItemCheckedChanged(selectedItems);
//        });
//
//        holder.minusBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                manager.minusNumberFood(cartItemList, position, new ChangeNumberItemsListener() {
//                    @Override
//                    public void changed() {
//                        notifyDataSetChanged(); // Cập nhật lại giao diện của RecyclerView
//                        changeNumberItemsListener.changed(); // Thông báo thay đổi cho Fragment hoặc Activity
//                    }
//                });
//            }
//        });
//
//
//
//
//        holder.plusBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                manager.plusNumberFood(cartItemList, position, new ChangeNumberItemsListener() {
//                    @Override
//                    public void changed() {
//                        notifyDataSetChanged();
//                        changeNumberItemsListener.changed();
//                    }
//                });
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return cartItemList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        TextView productName, productPrice, productQuantity;
//        ImageView productImage, minusBtn, plusBtn;
//        CheckBox checkBox;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            productName = itemView.findViewById(R.id.titleMethod);
//            productPrice = itemView.findViewById(R.id.price);
//            productQuantity = itemView.findViewById(R.id.textViewQuantity);
//            productImage = itemView.findViewById(R.id.pic);
//            minusBtn = itemView.findViewById(R.id.minusBtn);
//            plusBtn = itemView.findViewById(R.id.plusBtn);
//            checkBox = itemView.findViewById(R.id.checkbox);
//        }
//
//        public void bind(Food item) {
//            productName.setText(item.getFoodTitle());
//            productPrice.setText(String.valueOf(item.getFoodPrice()));
//            productQuantity.setText(String.valueOf(item.getNumberInCart()));
//            int drawableResourceId = itemView.getContext().getResources().getIdentifier(item.getFoodImage(), "drawable", itemView.getContext().getPackageName());
//            Glide.with(itemView.getContext())
//                    .load(drawableResourceId)
//                    .into(productImage);
//
//            checkBox.setChecked(selectedItems.contains(item));
//        }
//
//    }
//
//    public ArrayList<Food> getSelectedItems() {
//        return selectedItems;
//    }
//}

//public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
//    private ArrayList<Food> cartItemList;
//    private Manager manager;
//    private ChangeNumberItemsListener changeNumberItemsListener;
//    private ArrayList<Food> selectedItems;
//
//    public CartAdapter(ArrayList<Food> cartItemList, Manager manager, ChangeNumberItemsListener changeNumberItemsListener) {
//        this.cartItemList = cartItemList;
//        this.manager = manager;
//        this.changeNumberItemsListener = changeNumberItemsListener;
//        this.selectedItems = new ArrayList<>();
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
//        return new ViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        Food item = cartItemList.get(position);
//        holder.productName.setText(item.getFoodTitle());
//        holder.productPrice.setText(String.valueOf(item.getFoodPrice()));
//        holder.productQuantity.setText(String.valueOf(item.getNumberInCart()));
//        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(item.getFoodImage(), "drawable", holder.itemView.getContext().getPackageName());
//        Glide.with(holder.itemView.getContext())
//                .load(drawableResourceId)
//                .into(holder.productImage);
//
//        holder.checkBox.setOnCheckedChangeListener(null);
//        holder.checkBox.setChecked(selectedItems.contains(item));
//        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    if (!selectedItems.contains(item)) {
//                        selectedItems.add(item);
//                    }
//                } else {
//                    selectedItems.remove(item);
//                }
//            }
//        });
//
//        holder.minusBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                manager.minusNumberFood(cartItemList, position, new ChangeNumberItemsListener() {
//                    @Override
//                    public void changed() {
//                        notifyDataSetChanged();
//                        changeNumberItemsListener.changed();
//                    }
//                });
//            }
//        });
//
//        holder.plusBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                manager.plusNumberFood(cartItemList, position, new ChangeNumberItemsListener() {
//                    @Override
//                    public void changed() {
//                        notifyDataSetChanged();
//                        changeNumberItemsListener.changed();
//                    }
//                });
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return cartItemList.size();
//    }
//
//    public ArrayList<Food> getSelectedItems() {
//        return selectedItems;
//    }
//    public double getTotalPrice() {
//        double total = 0;
//        for (Food item : selectedItems) {
//            total += item.getFoodPrice();
//        }
//        return total;
//    }
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        TextView productName, productPrice, productQuantity;
//        ImageView productImage, minusBtn, plusBtn;
//        CheckBox checkBox;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            productName = itemView.findViewById(R.id.titleMethod);
//            productPrice = itemView.findViewById(R.id.price);
//            productQuantity = itemView.findViewById(R.id.textViewQuantity);
//            productImage = itemView.findViewById(R.id.pic);
//            minusBtn = itemView.findViewById(R.id.minusBtn);
//            plusBtn = itemView.findViewById(R.id.plusBtn);
//            checkBox = itemView.findViewById(R.id.checkbox);
//        }
//    }
//}






