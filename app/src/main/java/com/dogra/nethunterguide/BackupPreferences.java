package com.dogra.nethunterguide;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Validates a whole backup before any preferences are changed. */
final class BackupPreferences {
    static final int MAX_BACKUP_CHARS = 1_000_000;

    static Map<String, Object> validate(Object version, Map<String, Object> input,
                                       List<String> assets, List<String> titles) {
        if (!(version instanceof Integer) || ((Integer) version) != 1) {
            throw new IllegalArgumentException("Unsupported backup version");
        }
        Map<String, Object> result = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : input.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (key.equals("last_asset") || key.equals("last_title")) {
                require(value instanceof String);
            } else if (key.equals("quiz_best_score") || key.equals("quiz_last_score")) {
                require(value instanceof Integer);
                int score = (Integer) value;
                require(score >= (key.equals("quiz_last_score") ? -1 : 0) && score <= 5);
            } else if (key.equals("lesson_text_size")) {
                require(value instanceof Number);
                float size = ((Number) value).floatValue();
                require(Float.isFinite(size) && size >= 12f && size <= 24f);
                value = size;
            } else if (key.startsWith("scroll_")) {
                require(assets.contains(key.substring(7)));
                require(value instanceof Integer && (Integer) value >= 0);
            } else if (key.startsWith("completed_") || key.startsWith("favorite_")) {
                require(assets.contains(key.substring(key.indexOf('_') + 1)));
                require(value instanceof Boolean);
            } else if (key.startsWith("note_")) {
                require(assets.contains(key.substring(5)));
                require(value instanceof String);
            } else {
                throw new IllegalArgumentException("Unknown preference");
            }
            result.put(key, value);
        }
        if (result.containsKey("last_asset") || result.containsKey("last_title")) {
            int index = assets.indexOf(result.get("last_asset"));
            require(index >= 0);
            result.put("last_title", titles.get(index));
        }
        return result;
    }

    private static void require(boolean condition) {
        if (!condition) {
            throw new IllegalArgumentException("Invalid backup preference");
        }
    }

    private BackupPreferences() { }
}
