package com.example.assignment_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.SearchManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.SearchView.OnQueryTextListener;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Donors> donorsList;
    private RecyclerView recyclerview;
    private DonorsRecyclerViewAdapter adapter;
    FloatingActionButton findButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAllDonors();
        setContentView(R.layout.activity_main);
        if(donorsList==null){
            donorsList = new ArrayList<>();
        }

        recyclerview = findViewById(R.id.recycler_view);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter=new DonorsRecyclerViewAdapter(this,donorsList);
        recyclerview.setAdapter(adapter);

        findButton = findViewById(R.id.find_button);
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        try
        {
            outState.putSerializable("donorsList",donorsList);
        }
        catch (Exception ex){}
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        try
        {
            donorsList=(ArrayList<Donors>) savedInstanceState.getSerializable("donorsList");
        }
        catch(Exception ex){}
    }


    private void initAllDonors() {
        donorsList=new ArrayList<>();
        donorsList.add(new Donors("Md Shawon","Dhaka","Ready to Donate","Available","O+",R.drawable.blood_o_p));
        donorsList.add(new Donors("Rifay","Dhaka","Ready to Donate","Available","O+",R.drawable.blood_o_p));
        donorsList.add(new Donors("Minhazul Islam","Dhaka","Can't Donate now!","Hidden!","O+",R.drawable.blood_o_p));
        donorsList.add(new Donors("Muqdho","Dhaka","Can't Donate now!","Hidden!","O+",R.drawable.blood_o_p));
        donorsList.add(new Donors("Nazmul Islam","Dhaka","Can't Donate now!","Hidden!","O+",R.drawable.blood_o_p));
        donorsList.add(new Donors("Hridika","Dhaka","Ready to Donate","Available","O+",R.drawable.blood_o_p));
        donorsList.add(new Donors("Md. Atiqur Rahman","Dhaka","Ready to Donate","Available","O+",R.drawable.blood_o_p));
        donorsList.add(new Donors("Muiz Ather","Lahore","Ready to Donate","Available","B+",R.drawable.blood_b_p));
        donorsList.add(new Donors("Abdul Majid","Lahore","Can't Donate now!","Hidden!","A+",R.drawable.blood_a_p));
        donorsList.add(new Donors("Aimen","Lahore","Can't Donate now!","Hidden!","A-",R.drawable.blood_a_));
        donorsList.add(new Donors("Anas Ameen","Lahore","Can't Donate now!","Hidden!","B-",R.drawable.blood_b_));
        donorsList.add(new Donors("Muhammad Bin Azeem","Lahore","Ready to Donate","Available","AB-",R.drawable.blood_ab_));
        donorsList.add(new Donors("Maham Sajjad","Lahore","Can't Donate now!","Hidden!","O-",R.drawable.blood_o_));
        donorsList.add(new Donors("Faizan Farooq","Lahore","Ready to Donate","Available","O-",R.drawable.blood_o_));
        donorsList.add(new Donors("Sad Abdullah","Lahore","Can't Donate now!","Hidden!","AB+",R.drawable.blood_ab_p));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setQueryHint("Search by name, city or blood group");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }




}