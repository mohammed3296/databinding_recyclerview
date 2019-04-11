package com.mohammedabdullah3296.bindingrecyclerview;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.provider.ContactsContract;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import retrofit2.CallAdapter;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    public List<User> data = Collections.emptyList();
    User current;

    int currentPos = 0;


    private int layoutId;
    private List<User> users;
    private ViewModel viewModel;




    public UsersAdapter( ) {
     }

    public void setCallData(List<User> recipesIn) {
        data = recipesIn;
    }



    public UsersAdapter(@LayoutRes int layoutId, UserViewModel viewModel) {
        this.layoutId = layoutId;
        this.viewModel = viewModel;
    }

    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }

    @Override
    public int getItemCount() {
        return users == null ? 0 : users.size();
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);

        return new MyHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        holder.bind((UserViewModel) viewModel, position);

    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    class MyHolder extends RecyclerView.ViewHolder {

        final ViewDataBinding binding;

        MyHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(UserViewModel viewModel, Integer position) {
             binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }

    }

    public void clear() {
        final int size = data.size();
        data.clear();
        notifyItemRangeRemoved(0, size);
    }
}