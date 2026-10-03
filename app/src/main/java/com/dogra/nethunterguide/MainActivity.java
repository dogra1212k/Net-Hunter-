package com.dogra.nethunterguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends Activity {

    private final List<Button> lessonButtons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindLesson(R.id.btn_commands, "Command Lessons", "commands.txt");
        bindLesson(R.id.btn_install, "Installation Guide", "install.txt");
        bindLesson(R.id.btn_tools, "Kali Tools Notes", "kali-tools.txt");
        bindLesson(R.id.btn_linux, "Linux Basics", "linux-basics.txt");
        bindLesson(R.id.btn_network, "Network Basics", "network-basics.txt");
        bindLesson(R.id.btn_web, "Web Basics", "web-basics.txt");
        bindLesson(R.id.btn_forensics, "Forensics Basics", "forensics-basics.txt");
        bindLesson(R.id.btn_roadmap, "Learning Roadmap", "learning-roadmap.txt");
        bindLesson(R.id.btn_categories, "Tool Categories", "tool-categories.txt");
        bindLesson(R.id.btn_reference, "Command Reference", "command-reference.txt");
        bindLesson(R.id.btn_troubleshooting, "Troubleshooting", "troubleshooting.txt");
        bindLesson(R.id.btn_labs, "Practice Labs", "practice-labs.txt");
        bindLesson(R.id.btn_about, "About & Safety", "about-safety.txt");

        TextView appMeta = findViewById(R.id.app_meta);
        appMeta.setText(getString(R.string.app_meta_format, getAppVersionName(), lessonButtons.size()));

        EditText search = findViewById(R.id.search_lessons);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterLessons(s == null ? "" : s.toString());
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
        button.setTag(title.toLowerCase(Locale.ROOT));
        lessonButtons.add(button);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, LessonActivity.class);
            intent.putExtra(LessonActivity.EXTRA_TITLE, title);
            intent.putExtra(LessonActivity.EXTRA_ASSET, assetName);
            startActivity(intent);
        });
    }

    private void filterLessons(String query) {
        String normalized = query.trim().toLowerCase(Locale.ROOT);
        for (Button button : lessonButtons) {
            String searchable = String.valueOf(button.getTag());
            button.setVisibility(normalized.isEmpty() || searchable.contains(normalized)
                    ? View.VISIBLE
                    : View.GONE);
        }
    }
}
