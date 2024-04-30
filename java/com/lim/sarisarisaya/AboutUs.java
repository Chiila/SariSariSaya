package com.lim.sarisarisaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class AboutUs extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Member> members;
    private AboutUsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.about_recyclerView);

        // Initialize the list of members
        initList();

        // Set layout manager for the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create and set adapter for the RecyclerView
        adapter = new AboutUsAdapter(members);
        recyclerView.setAdapter(adapter);
    }

    // Method to initialize the list of members
    private void initList() {
        members = new ArrayList<>();
        members.add(new Member(R.drawable.member_adlawan, "Cilla Adlawan", "Project Manager"));
        members.add(new Member(R.drawable.member_lim, "Christopher Lim", "Ast. Project Manager"));
        members.add(new Member(R.drawable.member_cantuba, "Kurt Cantuba", "App Developer"));
        members.add(new Member(R.drawable.member_reyes, "Karl Reyes", "App Developer"));
        members.add(new Member(R.drawable.member_francisco, "Irish Francisco", "App Developer"));
        members.add(new Member(R.drawable.member_amido, "Cezzann Amido", "App Developer"));
        members.add(new Member(R.drawable.member_villanueva, "Celestine Villanueva", "Documentation"));
        members.add(new Member(R.drawable.member_polintan, "Justine Polintan", "Booth Designer"));
        members.add(new Member(R.drawable.member_joderial, "Precious Joderial", "Booth Designer"));
    }

    // Override onBackPressed to handle back navigation
    public void onBackPressed() {
        ActivityHelper.openNewActivity(AboutUs.this, MainActivity.class);
    }
}