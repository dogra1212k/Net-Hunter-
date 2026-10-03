#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
test_classes=$(mktemp -d)
trap 'rm -rf "$test_classes"' EXIT
java -m jdk.compiler/com.sun.tools.javac.Main -d "$test_classes" \
  app/src/main/java/com/dogra/nethunterguide/BackupPreferences.java \
  tests/com/dogra/nethunterguide/BackupPreferencesTest.java
java -cp "$test_classes" com.dogra.nethunterguide.BackupPreferencesTest
