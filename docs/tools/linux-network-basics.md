# Linux & Network Basics Tools

Before advanced tools, learn these:

## ip

```bash
ip addr
ip route
ip link
```

Use: inspect interfaces, addresses, links, and routing.

## ss

```bash
ss -tuln
```

Use: see listening TCP/UDP sockets.

## ping

```bash
ping -c 4 127.0.0.1
```

Use: basic reachability test.

## curl

```bash
curl -I http://127.0.0.1:8000
```

Use: inspect HTTP responses.

## dig

```bash
dig example.com
```

Use: inspect DNS answers for public domains.

Learn these before automated scanners. Otherwise a tool can produce fifty lines of output and all fifty may as well be decorative wallpaper.
