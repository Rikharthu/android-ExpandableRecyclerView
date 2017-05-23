package com.example.uberv.expandablerecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Person> people = new ArrayList<>();
        people.add(new Person(34, "John", "Wick", "23125141"));
        people.add(new Person(34, "Bon", "Wick", "23125141"));
        people.add(new Person(34, "Don", "Wick", "23125141"));
        people.add(new Person(34, "Alan", "Wick", "23125141"));
        people.add(new Person(34, "Dolan", "Wick", "23125141"));


        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        ExpandableAdapter adapter = new ExpandableAdapter(this, recycler);
        adapter.setData(people);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }
}
