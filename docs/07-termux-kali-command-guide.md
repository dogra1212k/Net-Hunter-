# Termux and Kali Command Guide

This is a beginner reference for the commands you will repeatedly use while learning NetHunter.

## 1. Navigation

```bash
pwd
ls
ls -la
cd DIRECTORY
cd ..
cd ~
```

- `pwd`: shows your current directory.
- `ls -la`: includes hidden files and details.
- `cd ..`: moves one directory up.
- `cd ~`: returns to your home directory.

## 2. Files and folders

```bash
mkdir lab
touch notes.txt
cp notes.txt notes-copy.txt
mv notes-copy.txt lab/
cat notes.txt
less notes.txt
rm notes.txt
```

Be careful with `rm`. Linux does not provide a magical Android-style undo button because apparently consequences are educational.

## 3. Package management

### Termux

```bash
pkg search NAME
pkg install NAME
pkg uninstall NAME
pkg update
pkg upgrade
```

### Kali

```bash
apt search NAME
apt show NAME
sudo apt install NAME
sudo apt remove NAME
sudo apt update
sudo apt full-upgrade
```

## 4. System information

```bash
whoami
id
uname -a
df -h
free -h
ps aux
which COMMAND
```

Use these when troubleshooting installation or permission problems.

## 5. Networking basics

```bash
ip addr
ip route
ip link
ss -tuln
ping -c 4 127.0.0.1
```

These teach interfaces, routes, ports, and basic connectivity.

## 6. DNS basics

```bash
dig example.com
dig A example.com
dig AAAA example.com
dig MX example.com
```

Use DNS tools for public records or domains you are authorized to investigate.

## 7. HTTP basics

```bash
curl https://example.com
curl -I https://example.com
curl -v https://example.com
```

- `-I`: fetches response headers.
- `-v`: shows verbose connection details.

## 8. Local practice web server

In a test directory:

```bash
python -m http.server 8000
```

Then, in another shell:

```bash
curl http://127.0.0.1:8000
```

This gives you a safe local HTTP target.

## 9. Safe Nmap practice

Run against localhost or a lab host you control:

```bash
nmap 127.0.0.1
nmap -sV 127.0.0.1
```

- Basic scan: checks common TCP ports.
- `-sV`: asks Nmap to identify service versions.

Do not scan networks you do not own or have permission to test.

## 10. Pipes and redirection

```bash
COMMAND > output.txt
COMMAND >> output.txt
COMMAND | less
COMMAND | grep TEXT
```

- `>`: replaces a file.
- `>>`: appends.
- `|`: sends one command's output into another command.

## 11. Process control

```bash
ps aux
jobs
Ctrl+C
Ctrl+Z
fg
bg
```

Use `Ctrl+C` to stop a foreground process. This is particularly useful after you start a server and then realize you also need your terminal back. Humanity survives another shell session.

## 12. Troubleshooting routine

When a command fails:

```bash
which COMMAND
COMMAND --help
echo $PATH
pwd
whoami
df -h
```

Then check whether you are in **Termux** or **Kali**. Many beginner errors come from running the right command in the wrong environment.
