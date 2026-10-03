#!/usr/bin/env bash
set -euo pipefail

OUT_DIR="${1:-play-signing}"
KEYSTORE="$OUT_DIR/net-hunter-upload.jks"
B64_FILE="$OUT_DIR/net-hunter-upload.jks.b64"

mkdir -p "$OUT_DIR"
chmod 700 "$OUT_DIR"

if ! command -v keytool >/dev/null 2>&1; then
  echo "keytool is required."
  echo "Termux: pkg install openjdk-17"
  echo "Debian/Ubuntu: sudo apt install openjdk-17-jdk"
  exit 1
fi

if [ -e "$KEYSTORE" ]; then
  echo "Refusing to overwrite existing keystore: $KEYSTORE"
  exit 1
fi

echo "Creating a dedicated Play upload key."
echo "Keep the .jks file and passwords private and backed up."
echo
keytool -genkeypair   -v   -keystore "$KEYSTORE"   -alias net-hunter-upload   -keyalg RSA   -keysize 2048   -validity 10000

chmod 600 "$KEYSTORE"

if command -v base64 >/dev/null 2>&1; then
  base64 "$KEYSTORE" | tr -d '\n' > "$B64_FILE"
  chmod 600 "$B64_FILE"
  echo
  echo "Created:"
  echo "  $KEYSTORE"
  echo "  $B64_FILE"
  echo
  echo "GitHub secret names:"
  echo "  PLAY_UPLOAD_KEYSTORE_B64  <- contents of $B64_FILE"
  echo "  PLAY_UPLOAD_STORE_PASSWORD"
  echo "  PLAY_UPLOAD_KEY_ALIAS      <- net-hunter-upload"
  echo "  PLAY_UPLOAD_KEY_PASSWORD"
else
  echo "Keystore created, but base64 command was not found."
fi

echo
echo "Do NOT commit either signing file."
