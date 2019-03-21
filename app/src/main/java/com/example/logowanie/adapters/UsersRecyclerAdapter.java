package com.example.logowanie.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.logowanie.R;
import com.example.logowanie.model.User;

import java.util.List;

/**
 * Created by lalit on 10/10/2016.
 */

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder> {


    /**
     * ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewName;
        public AppCompatTextView textViewEmail;
        public AppCompatTextView textViewPassword;

        public UserViewHolder(View view) {
            super(view);
            textViewName = (AppCompatTextView) view.findViewById(R.id.textViewName);
            textViewEmail = (AppCompatTextView) view.findViewById(R.id.textViewEmail);
            textViewPassword = (AppCompatTextView) view.findViewById(R.id.textViewPassword);
        }
    }

    private final LayoutInflater mInflater;
    private List<User> mUsers;

    public UsersRecyclerAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }



    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = mInflater.inflate(R.layout.item_user_recycler, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.textViewName.setText(mUsers.get(position).getName());
        holder.textViewEmail.setText(mUsers.get(position).getEmail());
        holder.textViewPassword.setText(mUsers.get(position).getPassword());
    }

   public void setUsers(List<User> users){
        mUsers = users;
        notifyDataSetChanged();
    }


    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
//        Log.v(UsersRecyclerAdapter.class.getSimpleName(),""+mUsers.size());
        if (mUsers != null) return mUsers.size();
        else return 0;
    }





}
