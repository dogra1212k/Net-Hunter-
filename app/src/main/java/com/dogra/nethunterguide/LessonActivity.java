package com.dogra.nethunterguide;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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
    private static final String PREF_LAST_TITLE = "last_title";
    private static final String PREF_LAST_ASSET = "last_asset";

    private String lessonTitle;
    private String assetName;
    private Button completeButton;
    private Button favoriteButton;
    private Button previousButton;
    private Button nextButton;
    private EditText noteInput;
    private TextView titleView;
    private TextView contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        Button backButton = findViewById(R.id.btn_back);
        completeButton = findViewById(R.id.btn_complete);
        favoriteButton = findViewById(R.id.btn_favorite);
        previousButton = findViewById(R.id.btn_previous_lesson);
        nextButton = findViewById(R.id.btn_next_lesson);
        Button saveNoteButton = findViewById(R.id.btn_save_note);
        titleView = findViewById(R.id.lesson_title);
        contentView = findViewById(R.id.lesson_content);
        noteInput = findViewById(R.id.lesson_note);

        backButton.setOnClickListener(v -> finish());

        lessonTitle = getIntent().getStringExtra(EXTRA_TITLE);
        assetName = getIntent().getStringExtra(EXTRA_ASSET);

        showCurrentLesson();

        completeButton.setOnClickListener(v -> toggleComplete());
        favoriteButton.setOnClickListener(v -> toggleFavorite());
        saveNoteButton.setOnClickListener(v -> saveNote());
        previousButton.setOnClickListener(v -> moveLesson(-1));
        nextButton.setOnClickListener(v -> moveLesson(1));
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (assetName != null && noteInput != null) {
            persistNote(noteInput.getText().toString());
        }
    }

    private void showCurrentLesson() {
        titleView.setText(lessonTitle == null ? getString(R.string.app_name) : lessonTitle);
        contentView.setText(readAsset(assetName));

        updateCompleteButton();
        updateFavoriteButton();
        loadNote();
        updateNavigationButtons();
        rememberCurrentLesson();
    }

    private void moveLesson(int direction) {
        persistNote(noteInput.getText().toString());

        int currentIndex = LessonCatalog.indexOfAsset(assetName);
        int nextIndex = currentIndex + direction;

        if (currentIndex < 0 || nextIndex < 0 || nextIndex >= LessonCatalog.ASSETS.length) {
            return;
        }

        lessonTitle = LessonCatalog.TITLES[nextIndex];
        assetName = LessonCatalog.ASSETS[nextIndex];
        showCurrentLesson();
    }

    private void updateNavigationButtons() {
        int index = LessonCatalog.indexOfAsset(assetName);

        boolean hasPrevious = index > 0;
        boolean hasNext = index >= 0 && index < LessonCatalog.ASSETS.length - 1;

        previousButton.setVisibility(hasPrevious ? View.VISIBLE : View.INVISIBLE);
        nextButton.setVisibility(hasNext ? View.VISIBLE : View.INVISIBLE);

        if (hasPrevious) {
            previousButton.setText(getString(
                    R.string.previous_lesson_format,
                    LessonCatalog.TITLES[index - 1]
            ));
        }

        if (hasNext) {
            nextButton.setText(getString(
                    R.string.next_lesson_format,
                    LessonCatalog.TITLES[index + 1]
            ));
        }
    }

    private void rememberCurrentLesson() {
        if (assetName == null || lessonTitle == null) {
            return;
        }

        getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                .edit()
                .putString(PREF_LAST_TITLE, lessonTitle)
                .putString(PREF_LAST_ASSET, assetName)
                .apply();
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

        noteInput.setEnabled(true);
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
