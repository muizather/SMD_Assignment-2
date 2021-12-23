package com.example.assignment_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView.OnQueryTextListener;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Donors> donorsList;
    private RecyclerView recyclerview;
    private DonorsRecyclerViewAdapter adapter;
    FloatingActionButton findButton;
    ImageButton btnOutgoingCall;
    DonorDAO db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initAllDonors();
        db = new DonorDAO(this);

        setContentView(R.layout.activity_main);
        if(donorsList==null){
            donorsList = new ArrayList<>();
        }

        recyclerview = findViewById(R.id.recycler_view);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        donorsList = new ArrayList<>();

        donorsList.clear();
        donorsList = Donors.load(db);

        adapter=new DonorsRecyclerViewAdapter(donorsList);


        findButton = findViewById(R.id.find_button);
        btnOutgoingCall = (ImageButton) findViewById(R.id.phone);

        recyclerview.setAdapter(adapter);
    }




    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        try
        {
            outState.putSerializable("donorsList",donorsList);
        }
        catch (Exception ex){}
        super.onSaveInstanceState(outState);
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
        donorsList.add(new Donors("0","0","Md Shawon","Dhaka","Ready to Donate","Available","O+",R.drawable.blood_o_p));
        donorsList.add(new Donors("1","1","Rifay","Dhaka","Ready to Donate","Available","O+",R.drawable.blood_o_p));
        donorsList.add(new Donors("2","2","Minhazul Islam","Dhaka","Can't Donate now!","Hidden!","O+",R.drawable.blood_o_p));
        donorsList.add(new Donors("3","3","Muqdho","Dhaka","Can't Donate now!","Hidden!","O+",R.drawable.blood_o_p));
        donorsList.add(new Donors("4","4","Nazmul Islam","Dhaka","Can't Donate now!","Hidden!","O+",R.drawable.blood_o_p));
        donorsList.add(new Donors("5","5","Hridika","Dhaka","Ready to Donate","Available","O+",R.drawable.blood_o_p));
        donorsList.add(new Donors("6","6","Md. Atiqur Rahman","Dhaka","Ready to Donate","Available","O+",R.drawable.blood_o_p));
        donorsList.add(new Donors("7","7","Muiz Ather","Lahore","Ready to Donate","Available","B+",R.drawable.blood_b_p));
        donorsList.add(new Donors("8","8","Abdul Majid","Lahore","Can't Donate now!","Hidden!","A+",R.drawable.blood_a_p));
        donorsList.add(new Donors("9","9","Aimen","Lahore","Can't Donate now!","Hidden!","A-",R.drawable.blood_a_));
        donorsList.add(new Donors("10","10","Anas Ameen","Lahore","Can't Donate now!","Hidden!","B-",R.drawable.blood_b_));
        donorsList.add(new Donors("11","11","Muhammad Bin Azeem","Lahore","Ready to Donate","Available","AB-",R.drawable.blood_ab_));
        donorsList.add(new Donors("12","12","Maham Sajjad","Lahore","Can't Donate now!","Hidden!","O-",R.drawable.blood_o_));
        donorsList.add(new Donors("13","13","Faizan Farooq","Lahore","Ready to Donate","Available","O-",R.drawable.blood_o_));
        donorsList.add(new Donors("14","14","Sad Abdullah","Lahore","Can't Donate now!","Hidden!","AB+",R.drawable.blood_ab_p));

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

    @Override
    protected void onResume() {
        super.onResume();

        if(donorsList.isEmpty())
        {
            donorsList = Donors.load(db);
            adapter=new DonorsRecyclerViewAdapter(donorsList);
            recyclerview.setAdapter(adapter);
        }
    }

   /* @Override
    protected void onPause() {
        super.onPause();
        Donors.deleteAll(db);
        Donors.save(donorsList,db);
    }*/

}