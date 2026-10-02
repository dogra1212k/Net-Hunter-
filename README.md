# Net-Hunter-

Beginner-friendly Kali NetHunter + Termux learning repository for Android.

> Use security tools only on systems you own or have explicit permission to test.

## Start here

1. Read [Termux Setup](docs/01-termux-setup.md)
2. Read [Kali / NetHunter Setup](docs/02-nethunter-setup.md)
3. Browse [Tool Categories](docs/03-tool-categories.md)
4. Follow the [Learning Roadmap](docs/04-learning-roadmap.md)
5. Use [Command Reference](docs/05-command-reference.md)

## Important: Termux is not Kali

Termux uses its own Android package repositories. Kali tools should normally be installed **inside a Kali NetHunter/rootless Kali environment**, not by pasting Kali apt sources into Termux.

## Kali tool sets

Inside Kali, inspect metapackages:

```bash
apt update
apt search '^kali-tools-'
apt show kali-tools-top10
```

Common choices:

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

Each lesson explains **what a command does, why you use it, where it runs (Termux or Kali), and a safe practice exercise**.

## Tool lessons

Start with the focused lessons in [docs/tools/README.md](docs/tools/README.md):

- Nmap basics
- DNS reconnaissance fundamentals
- Web/HTTP basics
- Packet analysis basics
- Forensics basics
- John the Ripper basics for owned/test hashes

Each lesson uses localhost, your own systems, or explicitly authorized labs.
