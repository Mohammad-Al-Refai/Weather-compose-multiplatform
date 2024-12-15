This is a Kotlin Multiplatform project targeting Android, iOS.

Using:
- Android Studio Ladybug | 2024.2.1 Patch 2
- JDK: Corretto-17.0.6.10.1


https://github.com/user-attachments/assets/1f2471b9-7b7b-42b5-ad02-f28dc284cc5e




* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
