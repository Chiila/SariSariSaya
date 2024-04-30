package com.lim.sarisarisaya;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AboutUsAdapter extends RecyclerView.Adapter<AboutUsAdapter.MemberViewAdapter> {
    private List<Member> members;

    public AboutUsAdapter(List<Member> members) {
        this.members = members;
    }

    @NonNull
    @Override
    public MemberViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_item, parent, false);

        // Return a new instance of MemberViewAdapter with the inflated layout view
        return new MemberViewAdapter(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewAdapter holder, int position) {
        // Bind data to the views in each item of the RecyclerView
        Member member = members.get(position);

        holder.imgMember.setImageResource(member.getMemberImg());
        holder.txtName.setText(member.getMemberName());
        holder.txtRole.setText(member.getMemberRole());
    }

    @Override
    public int getItemCount() {
        // Return the total number of items in the RecyclerView
        return members.size();
    }

    // ViewHolder class to hold the views for each item in the RecyclerView
    public static class MemberViewAdapter extends RecyclerView.ViewHolder {
        private ImageView imgMember;
        private TextView txtName;
        private TextView txtRole;
        public MemberViewAdapter(@NonNull View itemView) {
            super(itemView);

            // Initialize views by finding their respective IDs
            imgMember = itemView.findViewById(R.id.member_img);
            txtName = itemView.findViewById(R.id.member_name);
            txtRole = itemView.findViewById(R.id.member_role);
        }
    }
}
