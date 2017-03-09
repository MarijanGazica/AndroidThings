package com.marijandroid.android_things.text_to_speech;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.marijandroid.android_things.R;

import java.util.Locale;

/**
 * Created by Marijan on 02/03/2017.
 */
public class TextToSpeechActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private static final String MESSAGE_REFERENCE = "/message";
    private static final String TAG = "PiTag";

    TextToSpeech textToSpeech;
    DatabaseReference databaseRef;

    ListView listView;
    MessagesAdapter adapter;

    boolean isFirebaseInitDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        textToSpeech = new TextToSpeech(this, this);
        databaseRef = FirebaseDatabase.getInstance().getReference(MESSAGE_REFERENCE);

        listView = (ListView) findViewById(R.id.list_view);
        adapter = new MessagesAdapter(this);

        listView.setAdapter(adapter);

        loadRemoteMessages();
        setDatabaseListener();
    }

    private void loadRemoteMessages() {
        databaseRef.getRef().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Message message = dataSnapshot.getValue(Message.class);
                adapter.addMessage(message);
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

    private void setDatabaseListener() {
        databaseRef.limitToLast(1).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Log.d(TAG, "message received");

                Message message = dataSnapshot.getValue(Message.class);

                if (message != null && isFirebaseInitDone) {
                    TextSpeaker.speak(textToSpeech, message.getMessage());
                    Log.d(TAG, "message spoken");

                } else {
                    isFirebaseInitDone = true;
                    Log.d(TAG, "message not spoken");
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
