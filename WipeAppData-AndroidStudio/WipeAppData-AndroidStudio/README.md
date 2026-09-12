# Wipe App Data — Android Studio

A small Kotlin + Jetpack Compose Android Studio project with a **Wipe App Data** button.

## What it does

The button clears data owned by this application:

- App-private files
- App cache
- External app cache
- The sample app's `SharedPreferences`

It does **not**:

- Factory-reset the phone
- Erase another application's data
- Erase the user's device-wide storage
- Bypass Android security restrictions

## Open in Android Studio

1. Extract the ZIP.
2. Open the `WipeAppData` folder in Android Studio.
3. Let Gradle sync.
4. Run the `app` configuration on an emulator or Android device.

## Important behavior

After app data is cleared, Android may recreate required directories automatically. A real production app should also clear its own databases, DataStore files, or other app-specific state as appropriate.

For example:

```kotlin
deleteDatabase("my_database.db")
```

Use this project only for resetting data belonging to your own application.
