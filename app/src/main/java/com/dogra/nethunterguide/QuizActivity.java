package com.dogra.nethunterguide;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {

    private static final String PREFS_NAME = "lesson_state";
    private static final String PREF_QUIZ_BEST = "quiz_best_score";
    private static final String PREF_QUIZ_LAST = "quiz_last_score";

    private final String[] questions = {
            "Which command shows the current directory?",
            "Which address always refers to localhost?",
            "Which HTTP status code means Not Found?",
            "Which command shows listening TCP/UDP sockets?",
            "Which command can calculate a SHA-256 hash?"
    };

    private final String[][] options = {
            {"pwd", "ls", "cd", "whoami"},
            {"8.8.8.8", "127.0.0.1", "192.168.1.1", "1.1.1.1"},
            {"200", "301", "404", "500"},
            {"ss -tulpn", "df -h", "free -h", "uname -a"},
            {"sha256sum", "strings", "file", "mkdir"}
    };

    private final int[] correctAnswers = {0, 1, 2, 0, 0};

    private int questionIndex = 0;
    private int score = 0;
    private boolean answered = false;

    private TextView questionText;
    private TextView scoreText;
    private TextView historyText;
    private Button[] optionButtons;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Button backButton = findViewById(R.id.btn_quiz_back);
        questionText = findViewById(R.id.quiz_question);
        scoreText = findViewById(R.id.quiz_score);
        historyText = findViewById(R.id.quiz_history);
        nextButton = findViewById(R.id.btn_quiz_next);

        optionButtons = new Button[]{
                findViewById(R.id.btn_option_1),
                findViewById(R.id.btn_option_2),
                findViewById(R.id.btn_option_3),
                findViewById(R.id.btn_option_4)
        };

        backButton.setOnClickListener(v -> finish());
        nextButton.setOnClickListener(v -> moveNext());

        for (int i = 0; i < optionButtons.length; i++) {
            final int selected = i;
            optionButtons[i].setOnClickListener(v -> answer(selected));
        }

        updateHistory();
        showQuestion();
    }

    private void showQuestion() {
        answered = false;
        questionText.setText((questionIndex + 1) + ". " + questions[questionIndex]);
        scoreText.setText(getString(R.string.quiz_score, score, questions.length));
        nextButton.setVisibility(View.GONE);

        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i].setEnabled(true);
            optionButtons[i].setText(options[questionIndex][i]);
        }
    }

    private void answer(int selected) {
        if (answered) {
            return;
        }

        answered = true;
        boolean correct = selected == correctAnswers[questionIndex];
        if (correct) {
            score++;
            Toast.makeText(this, R.string.quiz_correct, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.quiz_incorrect, Toast.LENGTH_SHORT).show();
        }

        scoreText.setText(getString(R.string.quiz_score, score, questions.length));
        for (Button button : optionButtons) {
            button.setEnabled(false);
        }

        nextButton.setVisibility(View.VISIBLE);
        nextButton.setText(questionIndex == questions.length - 1
                ? R.string.quiz_restart
                : R.string.quiz_next);
    }

    private void moveNext() {
        if (!answered) {
            return;
        }

        if (questionIndex == questions.length - 1) {
            saveQuizResult();
            questionIndex = 0;
            score = 0;
            updateHistory();
        } else {
            questionIndex++;
        }

        showQuestion();
    }

    private void saveQuizResult() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int best = prefs.getInt(PREF_QUIZ_BEST, 0);
        int newBest = Math.max(best, score);

        prefs.edit()
                .putInt(PREF_QUIZ_LAST, score)
                .putInt(PREF_QUIZ_BEST, newBest)
                .apply();
    }

    private void updateHistory() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int last = prefs.getInt(PREF_QUIZ_LAST, -1);
        int best = prefs.getInt(PREF_QUIZ_BEST, 0);

        if (last < 0) {
            historyText.setText(getString(R.string.quiz_history_empty, best, questions.length));
        } else {
            historyText.setText(getString(R.string.quiz_history, last, questions.length, best, questions.length));
        }
    }
}
