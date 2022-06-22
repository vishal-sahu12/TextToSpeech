package com.example.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextToSpeech mTTS;
    private EditText formula;
    private Button mButtonSpeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS){
                    int result = mTTS.setLanguage(Locale.ENGLISH);
                    if (result==TextToSpeech.LANG_MISSING_DATA ||
                    result ==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS","Initialization Failed");
                    }
                }
                else {
                    Log.e("TTS","Initialization Failed");
                }
            }
        });
        mTTS.speak("2*2 =",TextToSpeech.QUEUE_FLUSH,null);
        formula = findViewById(R.id.edit_text);
        mButtonSpeak = findViewById(R.id.speak_Button);
        mButtonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speaks();
            }
        });}

    @Override
    protected void onDestroy() {
        if (mTTS != null){
            mTTS.stop();
            mTTS.shutdown();}
        super.onDestroy();
    }

    private void speaks() {
            String text = formula.getText().toString();
            int value = Integer.parseInt(text);
            if (value == 4){
            mTTS.speak("Correct",TextToSpeech.QUEUE_FLUSH,null);
            formula.setText("");

        }
            else{
                mTTS.speak("Incorrect",TextToSpeech.QUEUE_FLUSH,null);
                formula.setText("");
            }
    }}
