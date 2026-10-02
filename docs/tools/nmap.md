# Nmap — Beginner Lesson

## कहाँ चलाएँ

Kali / NetHunter environment.

## Install

```bash
sudo apt update
sudo apt install nmap
```

Verify:

```bash
nmap --version
```

## क्यों इस्तेमाल होता है

Nmap host और network services की discovery के लिए है. सीखने का पहला लक्ष्य flags याद करना नहीं, बल्कि यह समझना है कि **port**, **service**, **TCP**, और **state** क्या बताते हैं.

## Help

```bash
nmap --help
man nmap
```

## Safe first scan: अपना device

```bash
nmap 127.0.0.1
```

यह localhost को scan करता है.

Specific ports:

```bash
nmap -p 22,80,443 127.0.0.1
```

Service detection on your own lab host:

```bash
nmap -sV 127.0.0.1
```

## Output समझें

Common states:
- `open`: कोई service connection accept कर रही है.
- `closed`: host reachable है, लेकिन उस port पर service नहीं सुन रही.
- `filtered`: firewall/filtering के कारण Nmap निश्चित result नहीं दे पाया.

## Practice

1. `ss -tuln` से अपने listening ports देखें.
2. `nmap 127.0.0.1` चलाएँ.
3. दोनों outputs compare करें.
4. Notes में लिखें कि कौन सा port किस service से जुड़ा है.

Only scan systems you own or are explicitly authorized to test.
