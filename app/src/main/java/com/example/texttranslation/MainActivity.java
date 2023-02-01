package com.example.texttranslation;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner from_spinner, to_spinner;
    TextView textView;
    EditText edt;
    Button btn;

    String from, to;
    TranslatorOptions options;

    List<String> languagesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        edt = findViewById(R.id.edt);
        btn = findViewById(R.id.translate_btn);
        from_spinner = findViewById(R.id.fromSpinner);
        to_spinner = findViewById(R.id.toSpinner);
        languagesList = new ArrayList<>();

        languagesList.add("English");
        languagesList.add("Urdu");
        languagesList.add("French");
        languagesList.add("Hindi");
        languagesList.add("Arabic");
        languagesList.add("Spanish");
        languagesList.add("Chinese");
        languagesList.add("Persian");
        languagesList.add("Bangali");
        languagesList.add("Russian");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, languagesList);

        from_spinner.setAdapter(adapter);
        to_spinner.setAdapter(adapter);

        from_spinner.setSelection(0);
        to_spinner.setSelection(1);

        from_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (languagesList.get(position))
                {
                    case "English":
                        from = TranslateLanguage.ENGLISH;
                        break;
                    case "Urdu":
                        from = TranslateLanguage.URDU;
                        break;
                    case "French":
                        from = TranslateLanguage.FRENCH;
                        break;
                    case "Hindi":
                        from = TranslateLanguage.HINDI;
                        break;
                    case "Arabic":
                        from = TranslateLanguage.ARABIC;
                        break;
                    case "Spanish":
                        from = TranslateLanguage.SPANISH;
                        break;
                    case "Chinese":
                        from = TranslateLanguage.CHINESE;
                        break;
                    case "Persian":
                        from = TranslateLanguage.PERSIAN;
                        break;
                    case "Bangali":
                        from = TranslateLanguage.BENGALI;
                        break;
                    case "Russian":
                        from = TranslateLanguage.RUSSIAN;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        to_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (languagesList.get(position))
                {
                    case "English":
                        to = TranslateLanguage.ENGLISH;
                        break;
                    case "Urdu":
                        to = TranslateLanguage.URDU;
                        break;
                    case "French":
                        to = TranslateLanguage.FRENCH;
                        break;
                    case "Hindi":
                        to = TranslateLanguage.HINDI;
                        break;
                    case "Arabic":
                        to = TranslateLanguage.ARABIC;
                        break;
                    case "Spanish":
                        to = TranslateLanguage.SPANISH;
                        break;
                    case "Chinese":
                        to = TranslateLanguage.CHINESE;
                        break;
                    case "Persian":
                        to = TranslateLanguage.PERSIAN;
                        break;
                    case "Bangali":
                        to = TranslateLanguage.BENGALI;
                        break;
                    case "Russian":
                        to = TranslateLanguage.RUSSIAN;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                options = new TranslatorOptions.Builder()
                        .setSourceLanguage(from)
                        .setTargetLanguage(to)
                        .build();

            Translator englishUrduTranslator =
                    Translation.getClient(options);
                    getLifecycle().addObserver(englishUrduTranslator);

            DownloadConditions conditions = new DownloadConditions.Builder()
                    .requireWifi()
                    .build();

                englishUrduTranslator.downloadModelIfNeeded(conditions)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void v) {
                                englishUrduTranslator.translate(edt.getText().toString())
                                        .addOnSuccessListener(new OnSuccessListener<String>() {
                                            @Override
                                            public void onSuccess(String s) {
                                                textView.setText(s);
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.e(TAG, "onFailure: text translation failed..."+e.getMessage());
                                                e.printStackTrace();
                                            }
                                        });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e(TAG, "onFailure: Model downloaded failed..."+e.getMessage());
                                e.printStackTrace();
                            }
                        });
            }
        });
    }


}