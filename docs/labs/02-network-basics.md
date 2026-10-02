# Lab 2: Networking Basics

## Goal
Understand your own device interfaces, routes, ports, and localhost.

## Step 1: Interfaces

Inside Kali:

```bash
ip addr
```

Look for:
- interface names
- IPv4/IPv6 addresses
- loopback address `127.0.0.1`

## Step 2: Routes

```bash
ip route
```

This shows where traffic is sent.

## Step 3: Listening sockets

```bash
ss -tuln
```

- `-t`: TCP
- `-u`: UDP
- `-l`: listening
- `-n`: numeric addresses/ports

## Step 4: Test localhost

```bash
ping -c 4 127.0.0.1
```

This tests your own loopback interface.

## Exercise

Write down:
1. your loopback address,
2. one network interface name,
3. your default route if shown,
4. any listening port you recognize.

Do not scan neighboring devices unless they are yours or part of an authorized lab.
