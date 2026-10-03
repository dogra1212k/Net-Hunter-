# Play Store Release Guide

This project can build Play Store-ready artifacts after a dedicated upload key is configured.

## 1. Generate a dedicated upload key

On Termux:

```bash
pkg install openjdk-17
bash scripts/create-play-upload-key.sh
```

On Linux/macOS with a JDK installed:

```bash
bash scripts/create-play-upload-key.sh
```

The helper creates:

- `play-signing/net-hunter-upload.jks`
- `play-signing/net-hunter-upload.jks.b64`

The files are ignored by Git. Back up the `.jks` file and passwords somewhere private. Losing the upload key can make future release management much more annoying than any human deserves.

The default key alias created by the script is:

```text
net-hunter-upload
```

## 2. Verify the keystore

```bash
bash scripts/verify-play-upload-key.sh
```

This prints the certificate information and confirms that the expected alias exists.

## 3. Configure GitHub repository secrets

In the GitHub repository, open **Settings → Secrets and variables → Actions** and create all four secrets:

- `PLAY_UPLOAD_KEYSTORE_B64`: contents of `play-signing/net-hunter-upload.jks.b64`
- `PLAY_UPLOAD_STORE_PASSWORD`: the keystore password
- `PLAY_UPLOAD_KEY_ALIAS`: optional when the keystore contains exactly one key; otherwise set the alias explicitly
- `PLAY_UPLOAD_KEY_PASSWORD`: the key password

Do not paste these values into source files, issues, commits, README files, screenshots, or chat messages.

The workflow validates signing at runtime. If the keystore secret is missing or malformed, it emits a warning and falls back to test artifacts instead of breaking the entire Android build. A production Play release still requires a valid upload keystore, store password and key password. The alias is auto-detected when the keystore contains exactly one entry.

## 4. Build

Push an Android-app change to `main` or run the Android workflow manually.

The workflow:

1. validates the signing-secret configuration;
2. runs Android lint;
3. restores and verifies the upload keystore when signing values are present;
4. auto-detects the alias for a single-entry keystore when `PLAY_UPLOAD_KEY_ALIAS` is omitted;
5. builds a signed release APK and signed release AAB only when signing validation succeeds;
6. otherwise builds an installable debug APK plus an unsigned release AAB;
7. uploads artifacts and publishes a GitHub Release.

## 5. Play Console preparation

Before production submission:

1. Create the app in Play Console.
2. Use package name `com.dogra.nethunterguide`.
3. Enroll in Play App Signing as required by Google Play.
4. Upload the signed AAB from the latest successful GitHub Actions run/release.
5. Complete store listing, content rating, target audience, ads declaration and data-safety forms.
6. Add screenshots, feature graphic, app icon and support/contact details.
7. Test through an internal testing track before production.

## 6. Data-safety notes for this app

The current app is designed as an offline learning guide. Lesson progress, favorites, personal notes, last-opened lesson, and quiz score history are stored locally with Android SharedPreferences.

The source code currently does not declare network permissions or implement analytics, advertising, account login, remote tracking, or cloud sync.

Verify these statements again before every Play submission because future code changes can alter the data-safety answers.

## 7. Versioning

Increase both `versionCode` and `versionName` in `app/build.gradle` before each new Play release. Google Play requires each uploaded release to use a higher `versionCode` than the previous one.
