package com.marijandroid.android_things.text_to_speech;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Marijan on 02/03/2017.
 */
public class TextToSpeechActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    public static final String TTS_TAG = "speech_tag";

    private static final SimpleDateFormat hour = new SimpleDateFormat("HH", Locale.getDefault());
    private static final SimpleDateFormat minute = new SimpleDateFormat("mm", Locale.getDefault());

    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        textToSpeech = new TextToSpeech(this, this);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Timestamp current = new Timestamp(System.currentTimeMillis());
                textToSpeech.speak("It's " + minute.format(current) + "minutes past" + hour.format(current) + "o'clock", TextToSpeech.QUEUE_FLUSH, null, TTS_TAG);
            }
        }, 0, 60 * 1000);
    }

    @Override
    public void onInit(int status) {
        textToSpeech.setLanguage(Locale.US);
    }
}
