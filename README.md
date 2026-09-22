# 📚 StudyFlow

**StudyFlow** is a modern multilingual Android application designed to help students organize their studies, manage tasks, track deadlines, and stay productive.

The application is built with **Kotlin** in **Android Studio** and uses **Material 3** for a clean and modern user interface.

---

## ✨ Features

- 📋 Create and manage study tasks
- ✏️ Add task titles and descriptions
- 📂 Organize tasks by category
- 🚦 Set task priorities
- 📅 Set deadlines using a date picker
- 🔍 Search through tasks
- ✅ Mark tasks as completed
- 🗑️ Delete tasks
- 📊 Track total and completed tasks
- 💾 Local data storage with SQLite
- 🌙 Dark theme
- ☀️ Light theme
- 📱 Modern Material 3 interface
- ✨ Smooth UI animations
- 💻 Offline functionality
- 🌐 Multilingual interface

---

## 🌐 Supported Languages

StudyFlow currently supports three languages:

- 🇬🇧 English
- 🇰🇿 Қазақша
- 🇷🇺 Русский

The selected language is saved and restored when the application is opened again.

---

## 🎨 Themes

StudyFlow supports multiple appearance modes:

- ☀️ Light
- 🌙 Dark
- ⚙️ System Default

Your selected theme is automatically saved.

---

## 📱 Screens

### 🏠 Dashboard

The main dashboard provides a quick overview of your study progress.

It displays:

- Total number of tasks
- Number of completed tasks
- Quick access to My Tasks
- Quick access to Add Task
- Application settings

### ✅ My Tasks

View and manage all your study tasks.

Features include:

- Task list
- Search
- Completion status
- Categories
- Priorities
- Deadlines
- Task deletion

### ➕ Add Task

Create a new study task with:

- Title
- Description
- Category
- Priority
- Deadline

### ⚙️ Settings

Customize the application:

- Change application language
- Switch between Light and Dark themes
- Follow the system theme

---

## 🛠️ Built With

| Technology | Purpose |
|---|---|
| Kotlin | Main programming language |
| Android Studio | Development environment |
| XML | Android user interface layouts |
| Material 3 | UI components and design system |
| SQLite | Local database |
| SharedPreferences | Saving user settings |
| Gradle | Build system |

---

## 🏗️ Project Structure

```text
StudyFlow/
│
├── app/
│   └── src/
│       └── main/
│           │
│           ├── java/com/miras/studyflow/
│           │   ├── MainActivity.kt
│           │   ├── TasksActivity.kt
│           │   ├── AddEditTaskActivity.kt
│           │   ├── SettingsActivity.kt
│           │   │
│           │   ├── adapters/
│           │   │   └── TaskAdapter.kt
│           │   │
│           │   ├── database/
│           │   │   └── DatabaseHelper.kt
│           │   │
│           │   ├── models/
│           │   │   └── Task.kt
│           │   │
│           │   └── utils/
│           │       └── AppPreferences.kt
│           │
│           ├── res/
│           │   ├── drawable/
│           │   ├── layout/
│           │   ├── mipmap/
│           │   ├── values/
│           │   ├── values-kk/
│           │   └── values-ru/
│           │
│           └── AndroidManifest.xml
│
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── .gitignore
└── README.md
```

---

## 🚀 Getting Started

### Requirements

To build the project, you need:

- Android Studio
- Android SDK
- Kotlin support
- Gradle
- Android emulator or physical Android device

### Clone the Repository

```bash
git clone https://github.com/mnerst1/StudyFlow-Android.git
```

Open the project folder in **Android Studio**.

Wait for Gradle synchronization to finish and then run the application using an emulator or physical Android device.

---

## 📦 APK Installation

You can download the latest APK from the **Releases** section of this repository.

1. Open **Releases**
2. Select the latest version
3. Download `StudyFlow-v1.0.0.apk`
4. Open the APK on your Android device
5. Allow installation from unknown sources if Android requests permission
6. Install StudyFlow

---

## 🔐 Privacy

StudyFlow works locally on your device.

Task information is stored using a local SQLite database. The application does not require an internet connection for its core task-management functionality.

---

## 🗺️ Roadmap

Future versions may include:

- 📊 Advanced study statistics
- 📈 Productivity charts
- 🔔 Deadline notifications
- ⏱️ Pomodoro timer
- 🎯 Study goals
- 🏆 Achievements
- 📚 Subjects and courses
- 🗓️ Calendar view
- 🔄 Task sorting and filtering
- 👆 Swipe actions
- 🎨 Dynamic Material colors
- ☁️ Cloud synchronization
- 👤 User accounts
- 📤 Backup and restore
- 📱 Home screen widgets

---

## 📌 Version

Current release:

**StudyFlow v1.0.0**

---

## 👨‍💻 Author

Developed by **Miras**

Created as part of the **365 Days of Code** challenge.

---

## ⭐ Support

If you like StudyFlow, consider giving the repository a ⭐ on GitHub.

Feedback, ideas, and suggestions are welcome.

---

## 📄 License

This project is currently provided for educational and personal use.