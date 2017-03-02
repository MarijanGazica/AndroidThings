package com.marijandroid.android_things.text_to_speech;

import android.speech.tts.TextToSpeech;

public class TextSpeaker {

    private static final String UTTERANCE_ID = "com.marijandroid.thingstts.UTTERANCE_ID";

    public void speakReady(TextToSpeech tts, String text) {
        tts.speak(text, TextToSpeech.QUEUE_ADD, null, UTTERANCE_ID);
    }
}
