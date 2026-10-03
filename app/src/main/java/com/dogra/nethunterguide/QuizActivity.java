package com.dogra.nethunterguide;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    private final int[] explanations = {
            R.string.quiz_explain_pwd, R.string.quiz_explain_localhost,
            R.string.quiz_explain_http, R.string.quiz_explain_sockets,
            R.string.quiz_explain_hash
    };

    private int selectedAnswer = -1;
    private int questionIndex = 0;
    private int score = 0;
    private boolean answered = false;

    private TextView questionText;
    private TextView scoreText;
    private TextView historyText;
    private TextView feedbackText;
    private Button[] optionButtons;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Button backButton = findViewById(R.id.btn_quiz_back);
        historyText = findViewById(R.id.quiz_history);
        questionText = findViewById(R.id.quiz_question);
        scoreText = findViewById(R.id.quiz_score);
        nextButton = findViewById(R.id.btn_quiz_next);
        feedbackText = findViewById(R.id.quiz_feedback);

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
        if (savedInstanceState != null) {
            questionIndex = savedInstanceState.getInt("question_index", 0);
            score = savedInstanceState.getInt("score", 0);
            selectedAnswer = savedInstanceState.getInt("selected_answer", -1);
            answered = selectedAnswer >= 0;
        }
        showQuestion();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("question_index", questionIndex);
        outState.putInt("score", score);
        outState.putInt("selected_answer", selectedAnswer);
        super.onSaveInstanceState(outState);
    }

    private void showQuestion() {
        questionText.setText((questionIndex + 1) + ". " + questions[questionIndex]);
        scoreText.setText(getString(R.string.quiz_score, score, questions.length));
        nextButton.setVisibility(View.GONE);
        feedbackText.setVisibility(View.GONE);

        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i].setEnabled(!answered);
            optionButtons[i].setText(options[questionIndex][i]);
        }
        if (answered) {
            showAnswerFeedback();
        }
    }

    private void answer(int selected) {
        if (answered) {
            return;
        }

        answered = true;
        selectedAnswer = selected;
        if (selected == correctAnswers[questionIndex]) {
            score++;
        }
        if (questionIndex == questions.length - 1) {
            saveQuizResult();
            updateHistory();
        }
        showAnswerFeedback();
    }

    private void showAnswerFeedback() {
        int result = selectedAnswer == correctAnswers[questionIndex]
                ? R.string.quiz_correct : R.string.quiz_incorrect;
        feedbackText.setText(getString(R.string.quiz_feedback_format,
                getString(result), options[questionIndex][correctAnswers[questionIndex]],
                getString(explanations[questionIndex])));
        feedbackText.setVisibility(View.VISIBLE);
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
            questionIndex = 0;
            score = 0;
        } else {
            questionIndex++;
        }

        answered = false;
        selectedAnswer = -1;
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
