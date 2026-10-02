# 3. Kali Tool Categories

Learn tools by purpose instead of memorizing hundreds of names.

| Category | Examples | Learn first |
|---|---|---|
| System/network basics | ip, ss, ping, traceroute | interfaces, routes, ports |
| DNS/recon | dig, whois | DNS and public metadata |
| Network discovery | nmap | your own lab hosts |
| Web inspection | curl, whatweb | HTTP requests/headers |
| Traffic analysis | tcpdump, Wireshark | packets and protocols |
| Vulnerability assessment | scanners in Kali packages | test lab findings and false positives |
| Password auditing | John, Hashcat | only hashes/accounts you own or lab data |
| Forensics | file, strings, exiftool, binwalk | copies of practice files/images |
| Reporting | notes, evidence, timestamps | reproducible findings |

## Safe Nmap starter lab

Find your own machine's addresses:

```bash
ip addr
ip route
```

Check Nmap help:

```bash
nmap --help
man nmap
```

Practice against a host/service you own or a deliberately vulnerable local lab. First understand TCP/UDP, ports and services; flags make more sense afterward.

## Web fundamentals

```bash
curl --help
curl -I https://example.com
```

`-I` retrieves response headers. Learn HTTP methods, status codes, headers, cookies and TLS before automated web scanners.

## Packet fundamentals

```bash
ip link
ss -tuln
tcpdump --help
```

Capture only networks/interfaces you are authorized to observe.

## Forensics fundamentals

```bash
file SAMPLE
strings SAMPLE
sha256sum SAMPLE
exiftool SAMPLE
```

Work on copies. Hashing helps verify that evidence did not change.

## Password auditing

Learn hashing, salts, password storage and defensive password policy first. Use auditing/cracking tools only with your own test hashes or explicit authorization.
