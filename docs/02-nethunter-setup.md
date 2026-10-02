# 2. Kali / NetHunter Setup

This repository does not replace the official Kali NetHunter installation instructions. Android models, rooting state and supported features differ.

## After entering your Kali environment

Identify the system:

```bash
whoami
uname -a
cat /etc/os-release
```

Update package metadata and packages:

```bash
sudo apt update
sudo apt full-upgrade
```

## Discover Kali metapackages

```bash
apt search '^kali-tools-'
apt show kali-tools-top10
```

Install a focused starter set:

```bash
sudo apt install kali-tools-top10
```

Or learn by category:

```bash
sudo apt install kali-tools-information-gathering
sudo apt install kali-tools-vulnerability
sudo apt install kali-tools-web
sudo apt install kali-tools-forensics
sudo apt install kali-tools-reporting
```

Before installing:

```bash
apt show PACKAGE
apt-cache depends PACKAGE
df -h
```

This lets you inspect the package and available storage first.

## Termux vs Kali

Run `pkg ...` commands in Termux. Run Kali `apt ...` commands inside the Kali/NetHunter environment. Do not mix repositories between them.

Some NetHunter hardware functions require compatible devices, kernels, root access or external adapters. A rootless environment cannot magically grant hardware capabilities Android/kernel does not expose.
