# Kali tools guide

Kali contains hundreds of tools. Learn them by category instead of memorizing a wall of names.

Install categories with `./install-kali-tools.sh <profile>`.

## Information gathering

Metapackage: `sudo apt install kali-tools-information-gathering`

Purpose: DNS, host, service and public-information discovery.

Safe example:
```bash
nmap -sV 127.0.0.1
```
This asks Nmap to identify services exposed on your own local host.

Learn IP addresses, ports, TCP/UDP, service detection, and open/closed/filtered states.

## Vulnerability assessment

Metapackage: `sudo apt install kali-tools-vulnerability`

Purpose: identify known weaknesses, outdated services, unsafe configurations and exposed surfaces. Scanner output can contain false positives, because apparently software also enjoys paperwork.

Learning flow: inventory assets, identify versions/configuration, compare findings with advisories, verify in a lab, document remediation.

## Web application testing

Metapackage: `sudo apt install kali-tools-web`

Learn HTTP methods/status codes, headers, cookies, sessions, authentication/authorization, input validation, browser developer tools and proxy-based request inspection.

Safe first command: `curl -I http://127.0.0.1` against a local service.

## Password auditing

Metapackage: `sudo apt install kali-tools-passwords`

Use only for passwords/hashes you own or lab datasets. Learn hashing vs encryption, salts, password policy, offline auditing, rate limiting and MFA.

## Wireless

Metapackage: `sudo apt install kali-tools-wireless`

Rootless NetHunter usually cannot provide Wi-Fi injection. Full functionality depends on supported hardware, kernel, adapters and root. Start with bands, channels, WPA2/WPA3 concepts, monitor-mode theory and analysis of your own captures.

## Sniffing and spoofing

Metapackage: `sudo apt install kali-tools-sniffing-spoofing`

Use local captures or lab traffic you control. Learn DNS/TCP/TLS metadata, ARP, routing and detection of cleartext protocols.

## Exploitation

Metapackage: `sudo apt install kali-tools-exploitation`

Keep practical exploitation inside CTFs and intentionally vulnerable labs. Before using frameworks, understand the vulnerability, affected version, prerequisites, expected impact and cleanup/remediation.

## Reverse engineering

Metapackage: `sudo apt install kali-tools-reverse-engineering`

Use binaries you compiled, legal crackmes or CTF binaries. Learn ELF, assembly basics, strings/symbols, static vs dynamic analysis and debuggers.

## Forensics

Metapackage: `sudo apt install kali-tools-forensics`

Learn hashes/evidence integrity, metadata, timelines, deleted-file concepts and analysis of training disk/memory images.

## Reporting

Metapackage: `sudo apt install kali-tools-reporting`

A useful report contains scope, evidence, impact, reproduction in the authorized environment, remediation and severity rationale.

## Database testing

Metapackage: `sudo apt install kali-tools-database`

Learn SQL and database security with a local database you control.

## Social engineering

Metapackage: `sudo apt install kali-tools-social-engineering`

Keep exercises consent-based. Focus on awareness, phishing recognition, identity verification and defensive simulations.

## Post-exploitation

Metapackage: `sudo apt install kali-tools-post-exploitation`

Practice only in labs. Focus on privilege boundaries, credential storage, persistence concepts, logging, lateral-movement detection and remediation.

## Per-tool learning pattern

For any installed tool:
```bash
tool --help
man tool
apt show package-name
```
Then learn: what problem it solves, required inputs, safe lab target/data, one basic command, output interpretation, common errors, and defensive/remediation meaning.