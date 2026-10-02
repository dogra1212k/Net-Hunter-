# Lab 4: File & Metadata Forensics

## Goal
Learn to identify files and inspect metadata using copies of your own files.

## Step 1: Choose a sample

Use a harmless file you own. Work on a copy.

```bash
cp YOUR_FILE ~/nethunter-labs/sample
cd ~/nethunter-labs
```

## Step 2: Identify it

```bash
file sample
```

## Step 3: Hash it

```bash
sha256sum sample
```

Record the hash.

## Step 4: View strings

```bash
strings sample | head
```

This prints readable character sequences found in the file.

## Step 5: Metadata

If ExifTool is installed:

```bash
exiftool sample
```

## Exercise

Make a copy:

```bash
cp sample sample-copy
```

Hash both. They should match.

Then change only the copy and hash again. The new hash should differ.
