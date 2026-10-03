package com.dogra.nethunterguide;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class LessonActivity extends Activity {

    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_ASSET = "asset";

    private static final String PREFS_NAME = "lesson_state";

    private String assetName;
    private Button completeButton;
    private Button favoriteButton;
    private EditText noteInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        Button backButton = findViewById(R.id.btn_back);
        completeButton = findViewById(R.id.btn_complete);
        favoriteButton = findViewById(R.id.btn_favorite);
        Button saveNoteButton = findViewById(R.id.btn_save_note);
        TextView title = findViewById(R.id.lesson_title);
        TextView content = findViewById(R.id.lesson_content);
        noteInput = findViewById(R.id.lesson_note);

        backButton.setOnClickListener(v -> finish());

        String lessonTitle = getIntent().getStringExtra(EXTRA_TITLE);
        assetName = getIntent().getStringExtra(EXTRA_ASSET);

        title.setText(lessonTitle == null ? getString(R.string.app_name) : lessonTitle);
        content.setText(readAsset(assetName));

        updateCompleteButton();
        updateFavoriteButton();
        loadNote();

        completeButton.setOnClickListener(v -> toggleComplete());
        favoriteButton.setOnClickListener(v -> toggleFavorite());
        saveNoteButton.setOnClickListener(v -> saveNote());
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (assetName != null && noteInput != null) {
            persistNote(noteInput.getText().toString());
        }
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

    private void toggleFavorite() {
        if (assetName == null) {
            return;
        }

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String key = "favorite_" + assetName;
        boolean next = !prefs.getBoolean(key, false);
        prefs.edit().putBoolean(key, next).apply();
        updateFavoriteButton();
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

    private void updateFavoriteButton() {
        if (assetName == null) {
            favoriteButton.setEnabled(false);
            favoriteButton.setText(R.string.add_favorite);
            return;
        }

        boolean favorite = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                .getBoolean("favorite_" + assetName, false);
        favoriteButton.setText(favorite ? R.string.favorite_added : R.string.add_favorite);
    }

    private void loadNote() {
        if (assetName == null) {
            noteInput.setEnabled(false);
            return;
        }

        String note = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                .getString("note_" + assetName, "");
        noteInput.setText(note);
    }

    private void saveNote() {
        if (assetName == null) {
            return;
        }

        persistNote(noteInput.getText().toString());
        Toast.makeText(this, R.string.note_saved, Toast.LENGTH_SHORT).show();
    }

    private void persistNote(String note) {
        getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                .edit()
                .putString("note_" + assetName, note)
                .apply();
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
