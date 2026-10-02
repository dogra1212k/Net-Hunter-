# John the Ripper — Password Auditing Basics

यह lesson केवल आपके अपने test hashes / training data के लिए है.

## Install

```bash
sudo apt install john
```

Verify:

```bash
john --help
```

## Concept

John password hashes को candidate passwords से compare करता है. इससे password storage, hashing, salts और weak-password risk समझा जा सकता है.

## अपना training hash बनाएँ

Python से SHA-256 demo hash:

```bash
python3 - <<'PY'
import hashlib
print(hashlib.sha256(b"training123").hexdigest())
PY
```

यह raw hash formats और real account databases के बीच का फर्क समझने के लिए छोटा demo है. अलग hash formats के लिए John की documentation देखें:

```bash
john --list=formats | less
```

## Defensive learning goals

- plaintext password और hash का फर्क
- salted vs unsalted hashes
- fast vs slow password-hashing algorithms
- unique, long passwords क्यों बेहतर हैं
- MFA क्यों उपयोगी है

किसी दूसरे व्यक्ति के credentials, leaked databases या unauthorized account hashes पर password-cracking tools न चलाएँ.
