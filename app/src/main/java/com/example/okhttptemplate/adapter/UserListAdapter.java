package com.example.okhttptemplate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.okhttptemplate.databinding.ListItemUserBinding;
import com.example.okhttptemplate.vo.UserListModel;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    private List<UserListModel.User> mData;
    private final Context mContext;
    private final UserListListener mListener;

    public UserListAdapter(Context context, UserListListener listener) {
        this.mData = new ArrayList<>();
        this.mContext = context;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListItemUserBinding binding = ListItemUserBinding.inflate(inflater, parent, false);
        ViewHolder viewHolder = new ViewHolder(binding);
        if (mListener != null)
            binding.getRoot().setOnClickListener(v -> mListener.onItemClicked((UserListModel.User) v.getTag()));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        UserListModel.User item = mData.get(position);
        viewHolder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<UserListModel.User> list) {
        if (list == null)
            mData = new ArrayList<>();
        else
            mData = list;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ListItemUserBinding binding;

        public ViewHolder(@NonNull ListItemUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(UserListModel.User userItem) {
            binding.setUserItem(userItem);
            binding.getRoot().setTag(userItem);
            binding.executePendingBindings();
        }
    }

    public interface UserListListener {
        void onItemClicked(UserListModel.User user);
    }

}
