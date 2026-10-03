# Play Store Release Guide

This project can build Play Store-ready artifacts after a dedicated upload key is configured.

## 1. Create and protect an upload key

Create the upload keystore on a trusted computer and keep the original private. Do not commit a keystore, passwords, service-account credentials, or signing files to Git.

## 2. Configure GitHub repository secrets

Add:

- `PLAY_UPLOAD_KEYSTORE_B64`: base64-encoded upload keystore
- `PLAY_UPLOAD_STORE_PASSWORD`: keystore password
- `PLAY_UPLOAD_KEY_ALIAS`: upload-key alias
- `PLAY_UPLOAD_KEY_PASSWORD`: upload-key password

The workflow restores the key only inside the GitHub Actions runner.

## 3. Build

Push an Android-app change to `main` or run the Android workflow manually.

The workflow runs lint first. With signing secrets present it builds a signed release APK and signed release AAB. Without signing secrets it builds an installable debug APK plus an unsigned release AAB for testing/preparation.

## 4. Play Console preparation

Before production submission:

1. Create the app in Play Console.
2. Use package name `com.dogra.nethunterguide`.
3. Enroll in Play App Signing as required by Google Play.
4. Upload the signed AAB from the latest successful GitHub Actions run/release.
5. Complete store listing, content rating, target audience, ads declaration and data-safety forms.
6. Add screenshots, feature graphic, app icon and support/contact details.
7. Test through an internal testing track before production.

## 5. Data-safety notes for this app

The current app is designed as an offline learning guide. Its lesson progress and last-opened lesson are stored locally with Android SharedPreferences. The source code does not currently declare network permissions or implement analytics, advertising, account login, remote tracking or cloud sync.

Verify these statements again before every Play submission, because future code changes can alter the data-safety answers. Humans have historically enjoyed changing code after filling out forms.

## 6. Versioning

Increase both `versionCode` and `versionName` in `app/build.gradle` before a new Play release. Google Play requires each uploaded release to use a higher versionCode than the previous one.
