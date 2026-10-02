# Packet Analysis — tcpdump + Wireshark

## Install

```bash
sudo apt install tcpdump wireshark
```

GUI availability आपके NetHunter setup पर निर्भर करेगी.

## Interfaces देखें

```bash
ip link
```

tcpdump interfaces:

```bash
tcpdump -D
```

## Help

```bash
tcpdump --help
man tcpdump
```

## Safe local practice

Loopback traffic देखने के लिए पहले एक terminal में:

```bash
sudo tcpdump -i lo
```

दूसरे terminal में:

```bash
ping -c 4 127.0.0.1
```

Capture रोकने के लिए `Ctrl+C`.

## File में capture

```bash
sudo tcpdump -i lo -w lab.pcap
```

फिर थोड़ी local traffic generate करें और capture रोकें.

Read back:

```bash
tcpdump -r lab.pcap
```

Wireshark उपलब्ध हो तो उसी `.pcap` को GUI में खोलें.

## क्या सीखें

Ethernet/interface context, IP addresses, protocol, source/destination, TCP flags, DNS/ICMP basics.

केवल वही traffic capture करें जिसे देखने की अनुमति है.
