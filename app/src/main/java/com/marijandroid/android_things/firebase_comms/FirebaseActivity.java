package com.marijandroid.android_things.firebase_comms;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Marijan on 02/03/2017.
 */
public class FirebaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseReference testbase = FirebaseDatabase.getInstance().getReference();

        testbase.child("testThread").setValue("test message!");

    }
}
