package com.demo.robofightfederation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.demo.robofightfederation.models.Robot;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class BotListActivity extends AppCompatActivity {
    BotAdapterView adapterView;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot_list);

        List<Robot> robots = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.recycler_robots);
        adapterView = new BotAdapterView(robots);
        recyclerView.setAdapter(adapterView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        db = FirebaseFirestore.getInstance();

        db.collection("robots").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                // TODO: error checking, deletes:
                // https://firebase.google.com/docs/firestore/query-data/listen

                for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                    if (doc.exists()) {
                        Robot robo = doc.toObject(Robot.class);
                        robo.setId(doc.getId());
                        adapterView.addOrUpdateBot(robo);
                    } else {
                        // TODO: removal?  Maybe?  Someday?
                        System.out.println("deleted:" + doc.getId());
                    }
                }
            }
        });
    }

    public void gotoAdd(View view) {
        Intent intent = new Intent(this, RobotBuilderActivity.class);
        startActivity(intent);
    }
}
