package com.dogra.nethunterguide;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class LessonActivity extends Activity {

    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_ASSET = "asset";

    private static final String PREFS_NAME = "lesson_state";

    private String assetName;
    private Button completeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        Button backButton = findViewById(R.id.btn_back);
        completeButton = findViewById(R.id.btn_complete);
        TextView title = findViewById(R.id.lesson_title);
        TextView content = findViewById(R.id.lesson_content);

        backButton.setOnClickListener(v -> finish());

        String lessonTitle = getIntent().getStringExtra(EXTRA_TITLE);
        assetName = getIntent().getStringExtra(EXTRA_ASSET);

        title.setText(lessonTitle == null ? getString(R.string.app_name) : lessonTitle);
        content.setText(readAsset(assetName));

        updateCompleteButton();
        completeButton.setOnClickListener(v -> toggleComplete());
    }

    private void toggleComplete() {
        if (assetName == null) {
            return;
        }

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String key = "completed_" + assetName;
        boolean next = !prefs.getBoolean(key, false);
        prefs.edit().putBoolean(key, next).apply();
        updateCompleteButton();
    }

    private void updateCompleteButton() {
        if (assetName == null) {
            completeButton.setEnabled(false);
            completeButton.setText(R.string.mark_complete);
            return;
        }

        boolean completed = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                .getBoolean("completed_" + assetName, false);
        completeButton.setText(completed ? R.string.completed : R.string.mark_complete);
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
