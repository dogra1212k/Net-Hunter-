package com.dogra.nethunterguide;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.json.JSONObject;

public class MainActivity extends Activity {

    private static final String PREFS_NAME = "lesson_state";
    private static final String PREF_LAST_TITLE = "last_title";
    private static final String PREF_LAST_ASSET = "last_asset";
    private static final int REQ_EXPORT_BACKUP = 1001;
    private static final int REQ_IMPORT_BACKUP = 1002;

    private final List<Button> lessonButtons = new ArrayList<>();
    private final List<String> lessonAssets = new ArrayList<>();
    private final List<String> lessonTitles = new ArrayList<>();

    private Button continueButton;
    private Button favoritesFilterButton;
    private Button notesFilterButton;
    private Button incompleteFilterButton;
    private TextView progressText;
    private TextView favoritesText;
    private TextView notesText;
    private TextView visibleLessonsText;
    private EditText searchInput;
    private boolean favoritesOnly = false;
    private boolean notesOnly = false;
    private boolean incompleteOnly = false;
    private String currentQuery = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] lessonButtonIds = {
                R.id.btn_commands,
                R.id.btn_install,
                R.id.btn_tools,
                R.id.btn_linux,
                R.id.btn_network,
                R.id.btn_web,
                R.id.btn_forensics,
                R.id.btn_roadmap,
                R.id.btn_categories,
                R.id.btn_reference,
                R.id.btn_troubleshooting,
                R.id.btn_labs,
                R.id.btn_about
        };

        for (int i = 0; i < lessonButtonIds.length; i++) {
            bindLesson(lessonButtonIds[i], LessonCatalog.TITLES[i], LessonCatalog.ASSETS[i]);
        }

        Button quizButton = findViewById(R.id.btn_quiz);
        quizButton.setOnClickListener(v -> startActivity(new Intent(this, QuizActivity.class)));

        continueButton = findViewById(R.id.btn_continue);
        progressText = findViewById(R.id.progress_text);
        favoritesText = findViewById(R.id.favorites_text);
        notesText = findViewById(R.id.notes_text);
        visibleLessonsText = findViewById(R.id.visible_lessons_text);
        favoritesFilterButton = findViewById(R.id.btn_filter_favorites);
        notesFilterButton = findViewById(R.id.btn_filter_notes);
        incompleteFilterButton = findViewById(R.id.btn_filter_incomplete);

        Button resetProgressButton = findViewById(R.id.btn_reset_progress);
        Button exportBackupButton = findViewById(R.id.btn_export_backup);
        Button importBackupButton = findViewById(R.id.btn_import_backup);
        Button clearFiltersButton = findViewById(R.id.btn_clear_filters);

        resetProgressButton.setOnClickListener(v -> confirmResetProgress());
        exportBackupButton.setOnClickListener(v -> exportBackup());
        importBackupButton.setOnClickListener(v -> importBackup());
        clearFiltersButton.setOnClickListener(v -> clearFilters());

        favoritesFilterButton.setOnClickListener(v -> {
            favoritesOnly = !favoritesOnly;
            updateFavoritesUi();
            applyFilters();
        });

        notesFilterButton.setOnClickListener(v -> {
            notesOnly = !notesOnly;
            updateNotesUi();
            applyFilters();
        });

        incompleteFilterButton.setOnClickListener(v -> {
            incompleteOnly = !incompleteOnly;
            updateIncompleteUi();
            applyFilters();
        });

        updateContinueButton();
        updateProgress();
        updateFavoritesUi();
        updateNotesUi();
        updateIncompleteUi();
        updateLessonButtonLabels();

        TextView appMeta = findViewById(R.id.app_meta);
        appMeta.setText(getString(R.string.app_meta_format, getAppVersionName(), lessonButtons.size()));

        searchInput = findViewById(R.id.search_lessons);
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                currentQuery = s == null ? "" : s.toString();
                applyFilters();
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    private String getAppVersionName() {
        try {
            return getPackageManager()
                    .getPackageInfo(getPackageName(), 0)
                    .versionName;
        } catch (Exception e) {
            return getString(R.string.unknown_version);
        }
    }

    private void bindLesson(int buttonId, String title, String assetName) {
        Button button = findViewById(buttonId);
        lessonButtons.add(button);
        lessonAssets.add(assetName);
        lessonTitles.add(title);
        button.setOnClickListener(v -> openLesson(title, assetName, true));
    }

    private void openLesson(String title, String assetName, boolean remember) {
        if (remember) {
            getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                    .edit()
                    .putString(PREF_LAST_TITLE, title)
                    .putString(PREF_LAST_ASSET, assetName)
                    .apply();
            updateContinueButton();
        }

        Intent intent = new Intent(this, LessonActivity.class);
        intent.putExtra(LessonActivity.EXTRA_TITLE, title);
        intent.putExtra(LessonActivity.EXTRA_ASSET, assetName);
        startActivity(intent);
    }

    private void updateContinueButton() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String title = prefs.getString(PREF_LAST_TITLE, null);
        String asset = prefs.getString(PREF_LAST_ASSET, null);

        if (title == null || asset == null) {
            continueButton.setVisibility(View.GONE);
            continueButton.setOnClickListener(null);
            return;
        }

        continueButton.setText(getString(R.string.continue_lesson_format, title));
        continueButton.setVisibility(View.VISIBLE);
        continueButton.setOnClickListener(v -> openLesson(title, asset, false));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (progressText != null) {
            updateProgress();
        }
        if (continueButton != null) {
            updateContinueButton();
        }
        if (favoritesText != null && favoritesFilterButton != null) {
            updateFavoritesUi();
        }
        if (notesText != null && notesFilterButton != null) {
            updateNotesUi();
        }
        if (incompleteFilterButton != null) {
            updateIncompleteUi();
        }
        if (favoritesText != null && favoritesFilterButton != null
                && notesText != null && notesFilterButton != null
                && incompleteFilterButton != null) {
            updateLessonButtonLabels();
            applyFilters();
        }
    }

    private void clearFilters() {
        favoritesOnly = false;
        notesOnly = false;
        incompleteOnly = false;
        currentQuery = "";

        if (searchInput != null) {
            searchInput.setText("");
        }

        updateFavoritesUi();
        updateNotesUi();
        updateIncompleteUi();
        applyFilters();
    }

    private void exportBackup() {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/json");
        intent.putExtra(Intent.EXTRA_TITLE, "net-hunter-backup.json");
        startActivityForResult(intent, REQ_EXPORT_BACKUP);
    }

    private void importBackup() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/json");
        startActivityForResult(intent, REQ_IMPORT_BACKUP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK || data == null || data.getData() == null) {
            return;
        }

        Uri uri = data.getData();

        if (requestCode == REQ_EXPORT_BACKUP) {
            writeBackup(uri);
        } else if (requestCode == REQ_IMPORT_BACKUP) {
            readBackup(uri);
        }
    }

    private void writeBackup(Uri uri) {
        try {
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            JSONObject root = new JSONObject();
            root.put("format", "net-hunter-backup");
            root.put("version", 1);

            JSONObject values = new JSONObject();
            for (Map.Entry<String, ?> entry : prefs.getAll().entrySet()) {
                Object value = entry.getValue();
                if (value instanceof String
                        || value instanceof Boolean
                        || value instanceof Integer
                        || value instanceof Long
                        || value instanceof Float) {
                    values.put(entry.getKey(), value);
                }
            }

            root.put("preferences", values);

            try (java.io.OutputStream output = getContentResolver().openOutputStream(uri)) {
                if (output == null) {
                    throw new IllegalStateException("Could not open backup file.");
                }
                output.write(root.toString(2).getBytes(java.nio.charset.StandardCharsets.UTF_8));
            }

            Toast.makeText(this, R.string.backup_exported, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, R.string.backup_export_failed, Toast.LENGTH_LONG).show();
        }
    }

    private void readBackup(Uri uri) {
        try {
            StringBuilder text = new StringBuilder();

            try (java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(
                            getContentResolver().openInputStream(uri),
                            java.nio.charset.StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    text.append(line);
                }
            }

            JSONObject root = new JSONObject(text.toString());
            if (!"net-hunter-backup".equals(root.optString("format"))) {
                throw new IllegalArgumentException("Unsupported backup format.");
            }

            JSONObject values = root.getJSONObject("preferences");
            SharedPreferences.Editor editor =
                    getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();

            java.util.Iterator<String> keys = values.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                Object value = values.get(key);

                if (value instanceof Boolean) {
                    editor.putBoolean(key, (Boolean) value);
                } else if (value instanceof Integer) {
                    editor.putInt(key, (Integer) value);
                } else if (value instanceof Long) {
                    editor.putLong(key, (Long) value);
                } else if (value instanceof Double) {
                    double number = (Double) value;
                    if (number == Math.rint(number)
                            && number >= Integer.MIN_VALUE
                            && number <= Integer.MAX_VALUE) {
                        editor.putInt(key, (int) number);
                    } else {
                        editor.putFloat(key, (float) number);
                    }
                } else {
                    editor.putString(key, String.valueOf(value));
                }
            }

            editor.apply();
            refreshHomeState();
            Toast.makeText(this, R.string.backup_imported, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, R.string.backup_import_failed, Toast.LENGTH_LONG).show();
        }
    }

    private void refreshHomeState() {
        updateContinueButton();
        updateProgress();
        updateFavoritesUi();
        updateNotesUi();
        updateIncompleteUi();
        updateLessonButtonLabels();
        applyFilters();
    }

    private void confirmResetProgress() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.reset_progress_title)
                .setMessage(R.string.reset_progress_message)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.reset, (dialog, which) -> resetProgress())
                .show();
    }

    private void resetProgress() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        for (String asset : lessonAssets) {
            editor.remove("completed_" + asset);
        }
        editor.apply();
        updateProgress();
        updateLessonButtonLabels();
    }

    private void updateProgress() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int completed = 0;
        for (String asset : lessonAssets) {
            if (prefs.getBoolean("completed_" + asset, false)) {
                completed++;
            }
        }
        progressText.setText(getString(R.string.progress_format, completed, lessonAssets.size()));
    }

    private void updateFavoritesUi() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int favorites = 0;
        for (String asset : lessonAssets) {
            if (prefs.getBoolean("favorite_" + asset, false)) {
                favorites++;
            }
        }

        favoritesText.setText(getString(R.string.favorites_format, favorites));
        favoritesFilterButton.setText(
                favoritesOnly ? R.string.show_all_lessons : R.string.show_favorites_only
        );
    }

    private void updateNotesUi() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int notes = 0;

        for (String asset : lessonAssets) {
            String note = prefs.getString("note_" + asset, "");
            if (note != null && !note.trim().isEmpty()) {
                notes++;
            }
        }

        notesText.setText(getString(R.string.notes_format, notes));
        notesFilterButton.setText(
                notesOnly ? R.string.show_all_notes : R.string.show_notes_only
        );
    }

    private void updateIncompleteUi() {
        incompleteFilterButton.setText(
                incompleteOnly ? R.string.show_all_progress : R.string.show_incomplete_only
        );
    }

    private void updateLessonButtonLabels() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        int visibleCount = 0;

        for (int i = 0; i < lessonButtons.size(); i++) {
            String title = lessonTitles.get(i);
            String asset = lessonAssets.get(i);

            boolean completed = prefs.getBoolean("completed_" + asset, false);
            boolean favorite = prefs.getBoolean("favorite_" + asset, false);

            StringBuilder label = new StringBuilder();
            if (completed) {
                label.append("✓ ");
            }
            if (favorite) {
                label.append("★ ");
            }
            label.append(title);

            lessonButtons.get(i).setText(label.toString());
        }
    }

    private void applyFilters() {
        String normalized = currentQuery.trim().toLowerCase(Locale.ROOT);
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        for (int i = 0; i < lessonButtons.size(); i++) {
            String title = lessonTitles.get(i);
            String asset = lessonAssets.get(i);

            boolean matchesQuery = normalized.isEmpty()
                    || title.toLowerCase(Locale.ROOT).contains(normalized);
            boolean matchesFavorite = !favoritesOnly
                    || prefs.getBoolean("favorite_" + asset, false);

            String note = prefs.getString("note_" + asset, "");
            boolean hasNote = note != null && !note.trim().isEmpty();
            boolean matchesNote = !notesOnly || hasNote;
            boolean completed = prefs.getBoolean("completed_" + asset, false);
            boolean matchesIncomplete = !incompleteOnly || !completed;

            boolean visible = matchesQuery
                    && matchesFavorite
                    && matchesNote
                    && matchesIncomplete;

            lessonButtons.get(i).setVisibility(visible ? View.VISIBLE : View.GONE);
            if (visible) {
                visibleCount++;
            }
        }

        if (visibleLessonsText != null) {
            visibleLessonsText.setText(
                    getString(R.string.visible_lessons_format, visibleCount, lessonButtons.size())
            );
        }
    }
}
