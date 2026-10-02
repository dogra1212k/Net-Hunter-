# Forensics Basics

## Install

```bash
sudo apt install file binutils libimage-exiftool-perl binwalk
```

## Practice file बनाएँ

```bash
printf 'NetHunter training file\n' > sample.txt
```

## file

```bash
file sample.txt
```

File type/signature पहचानने में मदद करता है.

## strings

```bash
strings sample.txt
```

Binary/file से printable text निकालता है. Context के बिना कोई string अपने-आप evidence नहीं होती.

## Hash

```bash
sha256sum sample.txt
```

फिर file बदलें और hash दोबारा compare करें. Hash integrity verification में काम आता है.

## Metadata

किसी अपनी photo/copy पर:

```bash
exiftool YOUR_IMAGE.jpg
```

Original evidence पर सीधे edit न करें; copy पर काम करें.

## binwalk

अपनी firmware/sample file पर:

```bash
binwalk YOUR_FILE
```

यह embedded signatures/data पहचानने में मदद करता है.

## Practice record

हर exercise में filename, original hash, working-copy hash, command और observation लिखें.
