# DNS Recon — dig + whois

## कहाँ चलाएँ

Kali / NetHunter. `dig` Termux में भी package availability के अनुसार मिल सकता है.

## Install in Kali

```bash
sudo apt install dnsutils whois
```

## dig क्यों

`dig` DNS records पूछता है. DNS समझे बिना recon tools चलाना ऐसे है जैसे address book पढ़े बिना शहर ढूँढना.

Basic query:

```bash
dig example.com
```

A record:

```bash
dig A example.com
```

MX record:

```bash
dig MX example.com
```

Nameservers:

```bash
dig NS example.com
```

Short output:

```bash
dig +short example.com
```

## whois क्यों

`whois` publicly available registration/RIR information query करता है.

```bash
whois example.com
```

Results registry/privacy service पर निर्भर कर सकते हैं और हमेशा complete नहीं होते.

## Practice

`example.com` जैसे documentation domain पर A, MX और NS queries compare करें. Result में TTL, answer section और nameserver names पहचानें.
