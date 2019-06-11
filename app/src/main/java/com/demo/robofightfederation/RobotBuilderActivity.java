package com.demo.robofightfederation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.demo.robofightfederation.models.Robot;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RobotBuilderActivity extends AppCompatActivity {

    private static final String TAG = "RobotBuilderActivity";

    FirebaseFirestore db;
    EditText editCallsign;
    EditText editAttackStyle;
    EditText editWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robot_builder);

        editCallsign = findViewById(R.id.edit_callsign);
        editAttackStyle = findViewById(R.id.edit_attack_style);
        editWeight = findViewById(R.id.edit_weight);

        db = FirebaseFirestore.getInstance();


    }

    public void onCreateClick(View view) {

        Robot robo = new Robot();
        robo.setAttackStyle(editAttackStyle.getText().toString());
        robo.setCallSign(editCallsign.getText().toString());

        int weight = Integer.parseInt(editWeight.getText().toString());

        robo.setWeight(weight);

        db.collection("robots")
                .add(robo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "Robo FF created: " + documentReference.getId());
                    }
                });
    }
}
