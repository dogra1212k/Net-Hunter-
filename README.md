# Net-Hunter-

A beginner-friendly Kali NetHunter + Termux learning repo for Android.

> Use these tools only on systems you own or have explicit permission to test.

## What this repo gives you

- NetHunter Rootless setup notes for Android + Termux
- Kali metapackage installer with selectable profiles
- Termux/Linux command reference with what each command does
- Kali tool categories with safe lab examples
- Beginner → intermediate → advanced learning roadmap
- Notes about Android/rootless limitations

## Start here

1. Read [Termux Commands](docs/TERMUX_COMMANDS.md)
2. Install NetHunter Rootless using the official Kali flow described there.
3. Inside Kali, update packages:
   ```bash
   sudo apt update
   sudo apt full-upgrade -y
   ```
4. Make the installer executable:
   ```bash
   chmod +x install-kali-tools.sh
   ```
5. See profiles:
   ```bash
   ./install-kali-tools.sh help
   ```
6. Recommended Android profile:
   ```bash
   ./install-kali-tools.sh nethunter
   ```

## Tool profiles

| Profile | Purpose |
|---|---|
| `core` | Minimal Kali base |
| `headless` | Useful CLI environment without a desktop |
| `default` | Kali's normal default tool set |
| `arm` | Tools suitable for ARM systems |
| `nethunter` | NetHunter-oriented tools |
| `top10` | Small popular security-tool set |
| `everything` | Kali's very large all-tools metapackage |

The `everything` profile can require a huge amount of download/storage and is usually not sensible on a phone.

## Guides

- [Termux commands and NetHunter setup](docs/TERMUX_COMMANDS.md)
- [Kali tools guide](docs/KALI_TOOLS_GUIDE.md)
- [Learning path](docs/LEARNING_PATH.md)
- [Security and legal-use note](SECURITY.md)

## Rootless limitations

NetHunter Rootless provides Kali CLI, packages and KeX, but hardware-dependent features such as Wi-Fi injection, HID attacks and some Bluetooth features require supported rooted/full NetHunter setups. Check current Kali documentation before assuming a phone feature will work.