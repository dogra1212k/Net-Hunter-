# Termux + NetHunter command guide

This guide separates **plain Termux commands** from commands that run **inside Kali NetHunter**.

## 1. First Termux commands

```bash
termux-setup-storage
```
Grants Termux access to shared Android storage.

```bash
pkg update && pkg upgrade
```
Refreshes Termux package lists and upgrades installed Termux packages.

```bash
pkg install wget git curl nano
```
Installs common utilities: `wget` downloads files, `git` handles repositories, `curl` transfers/tests HTTP data, and `nano` edits text.

## 2. NetHunter Rootless install flow

Use the current official Kali NetHunter Rootless instructions. The documented flow is:

```bash
termux-setup-storage
pkg install wget
wget -O install-nethunter-termux https://offs.ec/2MceZWr
chmod +x install-nethunter-termux
./install-nethunter-termux
```

Meaning: enable storage, install the downloader, download the installer, make it executable, then run it.

## 3. Starting NetHunter

`nethunter` starts Kali CLI. `nh` is a shorter alias where available.
`nethunter -r` starts the NetHunter environment with root identity inside the proot environment.
`nethunter <command>` runs one command in NetHunter.

## 4. KeX desktop

```bash
nethunter kex passwd
nethunter kex &
nethunter kex stop
```
These set the KeX password, start the GUI session, and stop it.

## 5. Basic Linux navigation

`pwd` shows the current directory.
`ls` lists files; `ls -la` includes hidden files and details.
`cd folder`, `cd ..`, and `cd ~` move around directories.
`mkdir lab` creates a folder; `touch notes.txt` creates an empty file.
`cp source dest` copies. `mv old new` moves/renames. `rm file` removes a file. `rm -r folder` recursively removes a folder, with no magical Android recycle bin to rescue enthusiasm.

## 6. Reading and editing

`cat file` prints a file. `less file` opens a scrollable viewer. `nano file` edits it.
`head file` and `tail file` show the beginning/end. `tail -f logfile` follows updates live.

## 7. Finding things

```bash
find . -name "*.txt"
grep -R "text" .
which python
```
These find files, search text recursively, and show an executable path.

## 8. Permissions

`chmod +x script.sh` makes a script executable. `ls -l` shows Unix permissions.

## 9. Package management inside Kali

```bash
sudo apt update
sudo apt full-upgrade -y
apt search <name>
sudo apt install <package>
sudo apt remove <package>
```
Run these after entering NetHunter. Updating first keeps dependency metadata current.

## 10. Networking basics

```bash
ip addr
ip route
ss -tulpn
ping -c 4 127.0.0.1
curl -I https://example.com
```
`ip addr` shows interfaces/addresses. `ip route` shows routes. `ss` shows sockets where permissions allow. `ping` checks reachability. `curl -I` fetches HTTP headers.

## 11. Processes and storage

```bash
ps aux
df -h
du -sh *
free -h
```
These show processes, filesystem space, directory usage, and memory. Android/proot can limit some output.

## 12. Git workflow

```bash
git clone https://github.com/OWNER/REPO.git
cd REPO
git status
git pull
```

## 13. Safe shell habits

- Read a script before running it.
- Prefer official package repositories.
- Avoid piping unknown internet content directly into a shell.
- Keep backups before large upgrades.
- Test security tools only in your own lab or with explicit permission.