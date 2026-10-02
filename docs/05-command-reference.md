# 5. Command Reference

## Termux package management

```bash
pkg search NAME
pkg install NAME
pkg uninstall NAME
pkg update
pkg upgrade
```

## Kali package management

```bash
apt search NAME
apt show NAME
sudo apt install NAME
sudo apt remove NAME
sudo apt update
sudo apt full-upgrade
```

## Navigation/files

```bash
pwd
ls -la
cd PATH
mkdir DIR
touch FILE
cp SRC DST
mv SRC DST
rm FILE
cat FILE
less FILE
```

## Information

```bash
whoami
id
uname -a
df -h
free -h
ps aux
which COMMAND
COMMAND --help
man COMMAND
```

## Networking

```bash
ip addr
ip route
ip link
ss -tuln
ping -c 4 HOST
dig DOMAIN
curl -I URL
```

## Pipes and redirection

```bash
COMMAND > output.txt
COMMAND >> output.txt
COMMAND | less
COMMAND | grep TEXT
```

`>` replaces output file content; `>>` appends. A pipe sends one command's output into another.

## Learning rule

For a new tool:

```bash
which TOOL
TOOL --help
man TOOL
apt show PACKAGE
```

Then practice on a local/authorized target, record the result, and explain what each option changed. Avoid blindly copying giant command lists.
