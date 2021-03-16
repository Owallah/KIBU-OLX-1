package com.ifixhubke.kibu_olx.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.ifixhubke.kibu_olx.R;
import com.ifixhubke.kibu_olx.data.Item;
import com.squareup.picasso.Picasso;
import java.util.List;

import timber.log.Timber;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.SettingsHolder> {

    List<Item> itemArrayList;
    public SettingsAdapter(List<Item> itemArrayList){
        this.itemArrayList = itemArrayList;
    }

    @NonNull
    @Override
    public SettingsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SettingsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.setting_row, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SettingsHolder holder, int position) {
            holder.name.setText(itemArrayList.get(position).getItemName());
            Picasso.get().load(itemArrayList.get(position).getItemImage()).placeholder(R.drawable.ic_image_placeholder).into(holder.image);
            holder.price.setText("Kshs. "+itemArrayList.get(position).getItemPrice());
            holder.date.setText(itemArrayList.get(position).getDatePosted());

            holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked){
                    int id = holder.name.getId();
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                    Query query = reference.child("all_items").orderByChild("itemName").equalTo(holder.name.getText().toString());
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                snapshot.getRef().removeValue();

                            }
                            holder.checkBox.setClickable(false);

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    Timber.d("Deleted");
                }
            });
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    static class SettingsHolder extends RecyclerView.ViewHolder {
        TextView name, price,date;
        ImageView image;
        CheckBox checkBox;

        public SettingsHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.p_item_name);
            price = itemView.findViewById(R.id.p_item_price);
            image = itemView.findViewById(R.id.posted_image);
            date = itemView.findViewById(R.id.posted_on);
            checkBox = itemView.findViewById(R.id.sold_out_checkbox);
        }
    }

}
