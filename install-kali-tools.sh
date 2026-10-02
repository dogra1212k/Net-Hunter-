#!/usr/bin/env bash
set -euo pipefail

if ! command -v apt >/dev/null 2>&1; then
  echo "This script must be run inside Kali/NetHunter, not plain Termux."
  exit 1
fi

if [ "$(id -u)" -eq 0 ]; then
  SUDO=""
else
  SUDO="sudo"
fi

help_text() {
  cat <<'EOF'
Usage: ./install-kali-tools.sh PROFILE

Profiles:
  core         kali-linux-core
  headless     kali-linux-headless
  default      kali-linux-default
  arm          kali-linux-arm
  nethunter    kali-linux-nethunter
  top10        kali-tools-top10
  info         kali-tools-information-gathering
  vuln         kali-tools-vulnerability
  web          kali-tools-web
  database     kali-tools-database
  passwords    kali-tools-passwords
  wireless     kali-tools-wireless
  reverse      kali-tools-reverse-engineering
  exploitation kali-tools-exploitation
  social       kali-tools-social-engineering
  sniffing     kali-tools-sniffing-spoofing
  post         kali-tools-post-exploitation
  forensics    kali-tools-forensics
  reporting    kali-tools-reporting
  everything   kali-linux-everything
  help         show this help

Recommended on Android: nethunter, arm, top10, or selected categories.
EOF
}

profile="${1:-help}"
case "$profile" in
  core) pkg="kali-linux-core" ;;
  headless) pkg="kali-linux-headless" ;;
  default) pkg="kali-linux-default" ;;
  arm) pkg="kali-linux-arm" ;;
  nethunter) pkg="kali-linux-nethunter" ;;
  top10) pkg="kali-tools-top10" ;;
  info) pkg="kali-tools-information-gathering" ;;
  vuln) pkg="kali-tools-vulnerability" ;;
  web) pkg="kali-tools-web" ;;
  database) pkg="kali-tools-database" ;;
  passwords) pkg="kali-tools-passwords" ;;
  wireless) pkg="kali-tools-wireless" ;;
  reverse) pkg="kali-tools-reverse-engineering" ;;
  exploitation) pkg="kali-tools-exploitation" ;;
  social) pkg="kali-tools-social-engineering" ;;
  sniffing) pkg="kali-tools-sniffing-spoofing" ;;
  post) pkg="kali-tools-post-exploitation" ;;
  forensics) pkg="kali-tools-forensics" ;;
  reporting) pkg="kali-tools-reporting" ;;
  everything) pkg="kali-linux-everything" ;;
  help|-h|--help) help_text; exit 0 ;;
  *) echo "Unknown profile: $profile"; help_text; exit 2 ;;
esac

if [ "$profile" = "everything" ]; then
  echo "WARNING: kali-linux-everything is very large and can consume substantial storage."
  echo "Press Ctrl+C now if this is a phone with limited space."
  sleep 5
fi

echo "[1/2] Updating package lists..."
$SUDO apt update

echo "[2/2] Installing $pkg ..."
$SUDO apt install -y "$pkg"

echo "Done: $pkg"