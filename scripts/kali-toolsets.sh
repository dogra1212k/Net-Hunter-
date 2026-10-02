#!/usr/bin/env bash
set -euo pipefail

cat <<'EOF'
Net-Hunter Kali learning toolset helper
=======================================

Run this INSIDE Kali / NetHunter, not plain Termux.

1) Confirm your environment
---------------------------
  whoami
  cat /etc/os-release
  uname -a

2) Update package metadata
--------------------------
  sudo apt update

3) Check storage before installing
----------------------------------
  df -h
  du -sh ~

4) Discover Kali tool metapackages
----------------------------------
  apt search '^kali-tools-'

5) Inspect before installing
----------------------------
  apt show kali-tools-top10
  apt-cache depends kali-tools-top10

Suggested learning progression
------------------------------
  kali-tools-top10
  kali-tools-information-gathering
  kali-tools-vulnerability
  kali-tools-web
  kali-tools-forensics
  kali-tools-reporting

Install one category at a time:
  sudo apt install <metapackage>

Example:
  sudo apt install kali-tools-top10

Learn any installed tool:
  which <tool>
  <tool> --help
  man <tool>

Safe practice:
  - localhost
  - your own devices
  - deliberately vulnerable local labs
  - systems you have explicit permission to test

Do not add Kali APT repositories to plain Termux.
Very large metapackages can consume substantial phone storage.
EOF
