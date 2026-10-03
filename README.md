# Net-Hunter-

Beginner-friendly Kali NetHunter + Termux learning repository for Android.

> Use security tools only on systems you own, localhost, your own lab, or systems where you have explicit permission.

## Net Hunter Guide Android App

The repository includes an offline Android learning app with in-app lesson navigation.

Current app features:

- 13 offline lessons;
- lesson search on the home screen;
- continue-last-lesson support;
- per-lesson completion tracking;
- per-lesson personal notes saved locally;
- home-screen progress counter;
- confirmed progress reset;
- Termux and Kali quick-start guidance;
- Linux, networking, web/HTTP and forensics basics;
- Kali tool notes and categories;
- learning roadmap;
- command reference;
- troubleshooting guide;
- safe practice labs;
- About & Safety page;
- app version and lesson-count display;
- in-app Back navigation;
- offline quick quiz with scoring, saved last score, best score, answer explanations and restart;
- quiz progress retained across screen rotation;
- no browser required for offline lesson reading.

Current app version: **1.6.0**
Package: **com.dogra.nethunterguide**

## Download / Build

GitHub Actions runs Android lint and then builds:

- an installable debug APK when Play signing secrets are not configured;
- a signed release APK when Play signing secrets are configured;
- a release AAB for Play Store preparation.

After a successful build, open the repository **Releases** page for the latest APK/AAB. The workflow keeps only the latest Android build running, so stale queued builds are cancelled.

## Play Store signing

For a production Play Store APK/AAB, configure these repository secrets:

- `PLAY_UPLOAD_KEYSTORE_B64`
- `PLAY_UPLOAD_STORE_PASSWORD`
- `PLAY_UPLOAD_KEY_ALIAS`
- `PLAY_UPLOAD_KEY_PASSWORD`

Without those secrets, the APK is an installable debug build and the release AAB is not production-signed.

See [Play Store Release Guide](docs/08-play-store-release.md).

## Start here

1. Read [Termux Setup](docs/01-termux-setup.md)
2. Read [Kali / NetHunter Setup](docs/02-nethunter-setup.md)
3. Browse [Tool Categories](docs/03-tool-categories.md)
4. Follow the [Learning Roadmap](docs/04-learning-roadmap.md)
5. Keep the [Command Reference](docs/05-command-reference.md) nearby
6. Use the [Installation Matrix](docs/06-installation-matrix.md)
7. Follow the [Termux + Kali Command Guide](docs/07-termux-kali-command-guide.md)
8. Use the [Play Store Release Guide](docs/08-play-store-release.md) for publishing

## Important: Termux is not Kali

Termux uses its own Android package repositories. Kali tools should normally be installed **inside a Kali NetHunter/rootless Kali environment**, not by pasting Kali APT sources into Termux.

## Quick Termux foundation

```bash
pkg update
pkg upgrade
pkg install git curl wget python openssh
termux-setup-storage
```

## Kali tool sets

Inside Kali:

```bash
sudo apt update
apt search '^kali-tools-'
apt show kali-tools-top10
```

Suggested learning progression:

```bash
sudo apt install kali-tools-top10
sudo apt install kali-tools-information-gathering
sudo apt install kali-tools-vulnerability
sudo apt install kali-tools-web
sudo apt install kali-tools-forensics
sudo apt install kali-tools-reporting
```

Large installs such as `kali-linux-default` and especially `kali-linux-everything` can consume substantial storage and bandwidth. Install categories as you learn them.

## Repository goal

Each lesson aims to explain what a command or tool does, why you use it, where it runs, what important options mean, and how to practice safely while understanding the output.

## Hands-on labs

Practice with your own phone, localhost, your own files, or an authorized lab:

- [Linux & shell basics](docs/labs/01-linux-shell.md)
- [Networking basics](docs/labs/02-network-basics.md)
- [Local HTTP lab](docs/labs/03-local-http.md)
- [File & metadata forensics](docs/labs/04-file-metadata.md)

Start from [docs/labs/README.md](docs/labs/README.md).

## Toolset helper

Run this **inside Kali / NetHunter**:

```bash
bash scripts/kali-toolsets.sh
```

It shows recommended tool categories, inspection commands, and safe installation guidance without automatically dumping a giant install onto the device.
