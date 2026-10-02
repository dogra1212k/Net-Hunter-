# 1. Termux Setup

## Update Termux

```bash
pkg update
pkg upgrade
```

- `pkg update`: refreshes package information.
- `pkg upgrade`: upgrades installed packages.

## Basic packages

```bash
pkg install git curl wget nano openssh python
```

Why:
- `git`: clone/version repositories.
- `curl` / `wget`: retrieve web resources.
- `nano`: simple terminal editor.
- `openssh`: SSH client/server utilities.
- `python`: scripting and learning automation.

## Storage permission

```bash
termux-setup-storage
```

This requests Android shared-storage access. Do not grant permissions an app does not need.

## Useful shell commands

```bash
pwd
ls
ls -la
cd DIRECTORY
mkdir NAME
cp SOURCE DEST
mv SOURCE DEST
rm FILE
cat FILE
clear
history
which COMMAND
COMMAND --help
man COMMAND
```

Learn `pwd`, `ls`, `cd`, file operations and help pages before security tooling. Otherwise every typo becomes an archaeological expedition.

## GitHub workflow

```bash
git clone REPOSITORY_URL
cd REPOSITORY_DIRECTORY
git status
git pull
```

Never paste unknown shell commands or scripts from random pages without reading them first.
