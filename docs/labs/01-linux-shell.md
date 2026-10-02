# Lab 1: Linux & Shell Basics

## Goal
Become comfortable moving around the terminal before using security tools.

## Step 1: Where am I?

```bash
pwd
```

Shows your current directory.

## Step 2: What is here?

```bash
ls
ls -la
```

`-l` shows details. `-a` includes hidden files.

## Step 3: Create a practice area

```bash
mkdir -p ~/nethunter-labs/lab1
cd ~/nethunter-labs/lab1
```

## Step 4: Create and inspect files

```bash
echo "hello lab" > note.txt
cat note.txt
wc -c note.txt
sha256sum note.txt
```

- `echo ... >` writes text to a file.
- `cat` prints it.
- `wc -c` counts bytes.
- `sha256sum` creates a file hash.

## Step 5: Search text

```bash
grep "hello" note.txt
```

## Exercise

Create `second.txt`, write one sentence into it, then:
1. list both files,
2. print both files,
3. hash both files,
4. move `second.txt` into a new directory called `archive`.
