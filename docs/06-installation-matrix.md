# Kali / NetHunter Tool Installation Matrix

This page helps you decide **where a tool belongs** and how to install it without mixing Termux and Kali package sources.

> Use security tools only on systems you own or have explicit permission to test.

## 1. Termux vs Kali

| Task | Run in Termux | Run in Kali / NetHunter |
|---|---:|---:|
| Basic shell commands | Yes | Yes |
| Git, Python, curl, wget | Yes | Yes |
| Android-side file work | Yes | Sometimes |
| Kali metapackages | No | Yes |
| Kali security tooling | Usually no | Yes |
| Root-only wireless features | Device/kernel dependent | NetHunter-specific |

Do **not** add Kali APT repositories directly to plain Termux. They use different package ecosystems.

## 2. Prepare Termux

```bash
pkg update
pkg upgrade
pkg install git curl wget python openssh
termux-setup-storage
```

What these do:

- `pkg update`: refreshes package indexes.
- `pkg upgrade`: upgrades installed Termux packages.
- `git`: clones repositories and tracks changes.
- `curl` / `wget`: downloads or inspects web resources.
- `python`: runs Python scripts and local practice servers.
- `openssh`: provides SSH client/server tools.
- `termux-setup-storage`: asks Android for shared-storage access.

## 3. Prepare Kali / NetHunter

Inside the Kali environment:

```bash
sudo apt update
sudo apt full-upgrade
apt search '^kali-tools-'
```

Inspect a metapackage before installing it:

```bash
apt show kali-tools-top10
apt-cache depends kali-tools-top10
df -h
```

## 4. Recommended learning progression

Install only the category you are studying.

```bash
sudo apt install kali-tools-top10
sudo apt install kali-tools-information-gathering
sudo apt install kali-tools-vulnerability
sudo apt install kali-tools-web
sudo apt install kali-tools-forensics
sudo apt install kali-tools-reporting
```

Useful additional categories can be discovered with:

```bash
apt search '^kali-tools-'
```

Avoid installing `kali-linux-everything` on a phone unless you actually need it. It can consume a very large amount of storage and bandwidth.

## 5. Learn a tool before using it

For any new command:

```bash
which TOOL
TOOL --help
man TOOL
apt show PACKAGE
```

Then answer four questions:

1. What problem does this tool solve?
2. What input does it expect?
3. What output should you expect?
4. What is a safe local or authorized lab target?

## 6. Safe starter tools

These are good first tools because they teach fundamentals rather than button-mashing.

| Tool | Purpose | Safe first exercise |
|---|---|---|
| `ip` | Interfaces and routing | Inspect your own device |
| `ss` | Listening sockets | List local services |
| `ping` | Connectivity testing | Ping your router or lab host |
| `dig` | DNS queries | Query public DNS records |
| `curl` | HTTP requests | Inspect example.com headers |
| `nmap` | Network discovery | Scan localhost or your lab |
| `tcpdump` | Packet capture | Observe your own interface |
| `file` | File identification | Inspect a sample file |
| `strings` | Printable strings | Analyze a copied sample |
| `sha256sum` | File hashing | Verify a test file |

## 7. Storage check before large installs

```bash
df -h
du -sh ~
apt-cache depends PACKAGE
```

Phones run out of storage with impressive enthusiasm, so check first.
