package com.marijandroid.android_things.text_to_speech;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

/**
 * Created by Marijan on 02/03/2017.
 */
public class TextToSpeechActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private static final String MESSAGE_REFERENCE = "/message";
    
    TextToSpeech textToSpeech;
    DatabaseReference databaseRef;

    boolean isFirebaseInitDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        textToSpeech = new TextToSpeech(this, this);
        databaseRef = FirebaseDatabase.getInstance().getReference(MESSAGE_REFERENCE);

        databaseRef.limitToLast(1).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Message message = dataSnapshot.getValue(Message.class);

                if (message != null && isFirebaseInitDone) {
                    TextSpeaker.speak(textToSpeech, message.getMessage());
                } else {
                    isFirebaseInitDone = true;
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onInit(int status) {
        textToSpeech.setLanguage(Locale.US);
    }
}
