package com.dogra.nethunterguide;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class LessonActivity extends Activity {

    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_ASSET = "asset";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        Button backButton = findViewById(R.id.btn_back);
        TextView title = findViewById(R.id.lesson_title);
        TextView content = findViewById(R.id.lesson_content);

        backButton.setOnClickListener(v -> finish());

        String lessonTitle = getIntent().getStringExtra(EXTRA_TITLE);
        String assetName = getIntent().getStringExtra(EXTRA_ASSET);

        title.setText(lessonTitle == null ? getString(R.string.app_name) : lessonTitle);
        content.setText(readAsset(assetName));
    }

    private String readAsset(String assetName) {
        if (assetName == null || assetName.contains("..") || assetName.contains("/")) {
            return getString(R.string.lesson_load_error);
        }

        StringBuilder out = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getAssets().open(assetName), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line).append('\n');
            }
            return out.toString();
        } catch (Exception e) {
            return getString(R.string.lesson_load_error);
        }
    }
}
