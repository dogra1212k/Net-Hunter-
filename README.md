# Net-Hunter-

Beginner-friendly Kali NetHunter + Termux learning repository for Android.

> Use security tools only on systems you own or have explicit permission to test.

## Start here

1. Read [Termux Setup](docs/01-termux-setup.md)
2. Read [Kali / NetHunter Setup](docs/02-nethunter-setup.md)
3. Browse [Tool Categories](docs/03-tool-categories.md)
4. Follow the [Learning Roadmap](docs/04-learning-roadmap.md)
5. Keep the [Command Reference](docs/05-command-reference.md) nearby
6. Use the [Installation Matrix](docs/06-installation-matrix.md) before installing Kali toolsets
7. Follow the [Termux + Kali Command Guide](docs/07-termux-kali-command-guide.md) for step-by-step command practice

## Important: Termux is not Kali

Termux uses its own Android package repositories. Kali tools should normally be installed **inside a Kali NetHunter/rootless Kali environment**, not by pasting Kali APT sources into Termux.

## Quick Termux foundation

```bash
pkg update
pkg upgrade
pkg install git curl wget python openssh
termux-setup-storage
```

These commands prepare the Android-side shell for Git, downloads, Python practice, SSH, and shared storage.

## Kali tool sets

Inside Kali, inspect available metapackages:

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

Large installs such as `kali-linux-default` or especially `kali-linux-everything` consume substantial storage and bandwidth. Install categories as you learn them.

## Repository goal

Each lesson explains:

- what a command or tool does;
- why you use it;
- where it runs: Termux or Kali;
- what the important options mean;
- a safe practice exercise using localhost, your own systems, or an explicitly authorized lab.

The goal is understanding, not blindly pasting giant command lists.

## Tool lessons

Start with [docs/tools/README.md](docs/tools/README.md):

- Linux/network basics
- Nmap basics
- DNS reconnaissance fundamentals
- Web/HTTP basics
- Packet analysis basics
- Forensics basics
- John the Ripper basics for owned/test hashes

## Hands-on labs

Practice safely with your own phone, localhost, or an authorized lab:

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

It shows recommended tool categories, inspection commands, and safe installation guidance without automatically dumping a giant install onto your phone.
