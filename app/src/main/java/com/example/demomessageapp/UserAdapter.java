package com.example.demomessageapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.demomessageapp.databinding.RowItemBinding;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends PagedListAdapter<User,UserAdapter.UserViewHolder> {

    private Context context;
    PersonClickListener personClickListener;

    protected UserAdapter(PersonClickListener personClickListener) {
        super(userItemCallback);
        this.personClickListener = personClickListener;
    }

    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowItemBinding rowItemBinding = RowItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        context = parent.getContext();
        return new UserViewHolder(rowItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {
          User user = getItem(position);
          Avatar avatar = user.getAvatar();
          if (avatar != null){
              ExpiringUrls expiringUrls = avatar.getExpiringUrls();
              String url = expiringUrls.getMedium();
              Glide.with(context).load(url).placeholder(R.drawable.ic_baseline_account_circle_24).circleCrop().transition(DrawableTransitionOptions.withCrossFade(2000)).into(holder.imageView);
          }
          holder.textView_firstName.setText(user.getFirst_name());
          holder.textView_lastName.setText(user.getLastName());
    }

    private static DiffUtil.ItemCallback<User> userItemCallback =
            new DiffUtil.ItemCallback<User>() {
                @Override
                public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                    return oldItem.equals(newItem);
                }
            };

    class UserViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView_firstName,textView_lastName;
        public UserViewHolder(@NonNull RowItemBinding binding) {
            super(binding.getRoot());
            imageView = binding.profile;
            textView_firstName = binding.firstName;
            textView_lastName = binding.lastName;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    User user = getItem(getLayoutPosition());
                    personClickListener.invokeMethod(user.getId(),user.getFirst_name());
                }
            });
        }
    }
}
interface PersonClickListener{
    public  void invokeMethod(int id,String name);
}