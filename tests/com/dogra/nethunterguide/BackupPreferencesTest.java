package com.dogra.nethunterguide;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/** Standalone regression test: no Android runtime or third-party dependencies. */
public final class BackupPreferencesTest {
    private static int checks;

    public static void main(String[] args) {
        Map<String, Object> valid = new LinkedHashMap<>();
        valid.put("completed_commands.txt", true);
        valid.put("favorite_commands.txt", false);
        valid.put("note_commands.txt", "Hindi notes\nsecond line");
        valid.put("quiz_best_score", 5);
        valid.put("quiz_last_score", -1);
        valid.put("last_asset", "commands.txt");
        valid.put("last_title", "Incorrect imported title");
        Map<String, Object> restored = validate(1, valid);
        check(restored.get("last_title").equals("Command Lessons"));
        check(restored.get("note_commands.txt").equals("Hindi notes\nsecond line"));
        check(valid.get("last_title").equals("Incorrect imported title"));
        reject(2, valid);
        reject("1", valid);
        reject(null, valid);
        reject(1, Map.of("completed_commands.txt", "true"));
        reject(1, Map.of("favorite_commands.txt", 1));
        reject(1, Map.of("note_commands.txt", false));
        reject(1, Map.of("quiz_best_score", "5"));
        reject(1, Map.of("quiz_best_score", 5.0));
        reject(1, Map.of("quiz_best_score", 2147483648L));
        reject(1, Map.of("quiz_best_score", -1));
        reject(1, Map.of("quiz_last_score", 6));
        reject(1, Map.of("last_asset", "../commands.txt"));
        reject(1, Map.of("last_title", "Missing asset"));
        reject(1, Map.of("completed_unknown.txt", true));
        reject(1, Map.of("unexpected", "value"));
        Map<String, Object> partial = new LinkedHashMap<>();
        partial.put("note_commands.txt", "must not be partially applied");
        partial.put("quiz_best_score", "bad");
        reject(1, partial);
        check(partial.size() == 2);
        System.out.println("Passed " + checks + " backup regression checks");
    }

    private static Map<String, Object> validate(Object version, Map<String, Object> input) {
        return BackupPreferences.validate(version, input,
                Arrays.asList("commands.txt"), Arrays.asList("Command Lessons"));
    }

    private static void reject(Object version, Map<String, Object> input) {
        try {
            validate(version, input);
            throw new AssertionError("Invalid backup accepted: " + input);
        } catch (IllegalArgumentException expected) {
            checks++;
        }
    }

    private static void check(boolean condition) {
        if (!condition) throw new AssertionError("Regression check failed");
        checks++;
    }
}
