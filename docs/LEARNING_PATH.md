# NetHunter learning path

## Stage 1: Terminal foundations

Learn `pwd`, `ls`, `cd`, files/directories, permissions, pipes/redirection, `grep`, `find`, package management and Git. Practice with harmless files in `~/lab`.

## Stage 2: Networking foundations

Learn IPv4/IPv6, subnets, DNS, TCP/UDP, ports, HTTP/HTTPS and routing.

Safe commands: `ip addr`, `ip route`, `ss -tulpn`, `ping -c 4 127.0.0.1`, `curl -I https://example.com`.

## Stage 3: Scripting

Learn Bash variables, arguments, loops, conditionals and exit codes. Then learn Python files, JSON, HTTP requests, parsing and small automation scripts.

## Stage 4: Security fundamentals

Learn CIA triad, authentication vs authorization, least privilege, vulnerability vs exploit, hashing/encryption/signing, logging, patching and threat modeling.

## Stage 5: Discovery in your lab

Learn host discovery, ports, service identification, DNS and web headers using your own machine or an intentionally vulnerable lab target.

## Stage 6: Web security

Learn requests/responses, cookies, sessions, access control, common input-validation failures and secure-development fixes using purpose-built training applications.

## Stage 7: Traffic analysis

Use packet captures you generated yourself or public training datasets. Learn filters, protocols and suspicious patterns.

## Stage 8: Vulnerability assessment

Run scanners in a controlled lab, manually verify results, record false positives and document remediation.

## Stage 9: Exploitation labs

Only after understanding networking, Linux and the vulnerability. Use CTFs or intentionally vulnerable VMs/containers. For each lab write the root cause, prerequisites, evidence, impact and fix.

## Stage 10: Forensics and incident response

Practice on sample logs and disk images: hashes, timelines, process/network evidence, persistence clues and reporting.

## Weekly routine

Day 1: Linux + Termux
Day 2: networking
Day 3: Bash/Python
Day 4: one Kali tool category
Day 5: legal lab exercise
Day 6: write a short report
Day 7: review commands and concepts

## Golden rule

Progress is not the number of tools installed. It is whether you can explain what a tool does, why you chose it, what its output means, what could make the result wrong, and how a defender would fix the finding.