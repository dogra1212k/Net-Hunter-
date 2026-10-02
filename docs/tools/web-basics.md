# Web Basics — curl + WhatWeb

## Install

```bash
sudo apt install curl whatweb
```

## curl क्यों

`curl` HTTP request/response समझने के लिए बेहतरीन basic tool है.

Headers देखें:

```bash
curl -I https://example.com
```

Verbose connection details:

```bash
curl -v https://example.com/
```

Response body save करें:

```bash
curl https://example.com/ -o page.html
```

फिर:

```bash
file page.html
head page.html
```

## क्या सीखें

- HTTP status codes
- request/response headers
- redirects
- content type
- TLS का basic role

## WhatWeb

WhatWeb web technology fingerprints दिखा सकता है.

Help:

```bash
whatweb --help
```

Documentation domain पर basic example:

```bash
whatweb https://example.com
```

Output को proof नहीं मानें. Fingerprinting guesses गलत भी हो सकती हैं; browser/devtools या server config से verify करना सीखें.

Automated testing केवल अपनी site, lab, या explicitly authorized target पर करें.
