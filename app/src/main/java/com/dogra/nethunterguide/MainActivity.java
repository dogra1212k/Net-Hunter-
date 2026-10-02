package com.dogra.nethunterguide;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends Activity {

    private static final String REPO_URL =
            "https://github.com/dogra1212k/Net-Hunter-";
    private static final String COMMAND_GUIDE_URL =
            "https://github.com/dogra1212k/Net-Hunter-/blob/main/docs/07-termux-kali-command-guide.md";
    private static final String INSTALL_GUIDE_URL =
            "https://github.com/dogra1212k/Net-Hunter-/blob/main/docs/06-installation-matrix.md";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindLink(R.id.btn_commands, COMMAND_GUIDE_URL);
        bindLink(R.id.btn_install, INSTALL_GUIDE_URL);
        bindLink(R.id.btn_repo, REPO_URL);
    }

    private void bindLink(int buttonId, String url) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });
    }
}
