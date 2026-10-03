#!/usr/bin/env bash
set -euo pipefail

KEYSTORE="${1:-play-signing/net-hunter-upload.jks}"
ALIAS="${2:-net-hunter-upload}"

if ! command -v keytool >/dev/null 2>&1; then
  echo "keytool is required."
  exit 1
fi

if [ ! -f "$KEYSTORE" ]; then
  echo "Keystore not found: $KEYSTORE"
  exit 1
fi

echo "Checking alias '$ALIAS' in $KEYSTORE"
keytool -list -v -keystore "$KEYSTORE" -alias "$ALIAS"
