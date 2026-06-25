# 🏨 Smart Room Booking System

Welcome to the Smart Room Booking System repository! This guide will walk you through how to download the project, open it correctly in VS Code, run the Java code, and collaborate using Git.

---

## 🚀 1. Clone the Repository

To get started, you need to download (clone) the code to your local machine.

1. Open your Command Prompt (Windows) or Terminal (Mac/Linux).
2. Navigate to the folder where you want to save the project (e.g., your Documents):
   ```bash
   cd Documents
   ```
3. Clone the repository using this command *(make sure to replace the URL with our actual GitHub repo link)*:
   ```bash
   git clone https://github.com/shelingng/Meeting-Room-Reservation-System/tree/main
   ```

---

## 💻 2. Open the Project in VS Code

**⚠️ CRITICAL STEP:** To avoid Java package errors like "cannot find symbol", you must open the main project folder, not the `smartroom` folder directly.

1. Open Visual Studio Code.
2. Go to **File > Open Folder**.
3. Select the main repository folder that you just cloned (the folder that *contains* the `src` folder).

---

## ▶️ 3. How to Compile and Run the System

Since this project uses Java packages (`package smartroom;`), we must compile and run the files from outside the `smartroom` folder.

1. Open the integrated terminal in VS Code by pressing **`` Ctrl + ` ``** (Control + Backtick).
2. Navigate into the `src` folder:
   ```bash
   cd src
   ```
3. **Compile all Java files** in the `smartroom` package together:
   ```bash
   javac smartroom/*.java
   ```
4. **Run the main program**:
   ```bash
   java smartroom.BookingTest
   ```

---

## 🤝 4. How to Collaborate (Pull & Push)

When working together, it is extremely important to keep your local code updated and share your changes properly so we don't overwrite each other's work.

### Step A: Get the Latest Code (PULL)
Always do this **before** you start coding! This fetches any updates your teammates have made.
```bash
git pull origin main
```

### Step B: Share Your Updates (PUSH)
Once you have made changes and saved your files (`Ctrl + S`), run these three commands one by one in the VS Code terminal to upload your work:

1. **Stage your changes:**
   ```bash
   git add .
   ```
2. **Commit your changes:** *(Write a short, clear message explaining what you did)*
   ```bash
   git commit -m "Briefly describe what you fixed or added here"
   ```
3. **Push to GitHub:**
   ```bash
   git push origin main
   ```
