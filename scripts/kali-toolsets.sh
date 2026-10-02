#!/usr/bin/env bash
set -euo pipefail

cat <<'EOF'
Kali learning toolset helper

Run this INSIDE Kali/NetHunter, not plain Termux.

Suggested progression:
  1) kali-tools-top10
  2) kali-tools-information-gathering
  3) kali-tools-vulnerability
  4) kali-tools-web
  5) kali-tools-forensics
  6) kali-tools-reporting

Inspect before installing:
  apt show <package>
  apt-cache depends <package>
  df -h

Install manually with:
  sudo apt update
  sudo apt install <metapackage>

Discover more:
  apt search '^kali-tools-'

Very large Kali metapackages can consume substantial storage.
EOF
