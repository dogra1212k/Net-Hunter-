package com.dogra.nethunterguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends Activity {

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
    }

    private void bindLesson(int buttonId, String title, String assetName) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, LessonActivity.class);
            intent.putExtra(LessonActivity.EXTRA_TITLE, title);
            intent.putExtra(LessonActivity.EXTRA_ASSET, assetName);
            startActivity(intent);
        });
    }
}
