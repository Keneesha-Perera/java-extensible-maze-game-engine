# 🧩 Java Extensible Maze Game Engine

A modular 2D puzzle/maze game engine developed in Java for **COMP3003/6007 – Software Architecture and Extensible Design**.

This project demonstrates modern software engineering concepts including:

* Plugin-based architecture
* Dynamic script loading
* Custom Domain-Specific Language (DSL)
* Internationalisation (i18n)
* Gradle multi-project builds
* Event-driven APIs
* Reflection-based plugin systems

---

# 🚀 Features

## 🎮 Core Gameplay

* 2D grid-based maze/puzzle gameplay
* Player movement system
* Hidden/visible tile mechanics
* Inventory system
* Goal-based game progression
* Obstacles requiring specific items

## 🌍 Internationalisation

* Runtime locale switching
* Translatable UI text
* Localised in-game date display
* Unicode normalisation support
* UTF-8 / UTF-16 / UTF-32 map support

## 🔌 Extensible Architecture

* Dynamic plugin loading using reflection
* Python/Jython script support
* Event callback API system
* Modular Gradle subprojects

## 📦 Plugins and Scripts Implemented

- **Teleport Plugin** – allows the player to teleport once to a random grid location.
- **Reveal Plugin** – reveals the goal and remaining hidden items when a map-related item is acquired.
- **Prize Plugin** – rewards the player after collecting/traversing enough game events.
- **Penalty Script** – adds a penalty obstacle near the player if more than 5 seconds are taken between moves.

---

# 🛠 Technologies Used

* Java
* Gradle
* ANTLR / JavaCC
* Jython
* Reflection API
* PMD

---

# 📁 Project Structure

```text
Assignment 2/
│
├── api/                    # Shared API interfaces
├── core/                   # Core game engine
├── teleport-plugin/        # Teleport plugin
├── reveal-plugin/          # Reveal plugin
├── prize-plugin/           # Prize plugin
├── scripts/                # Script-related functionality
├── build-logic/            # Shared Gradle build logic
├── demoinput.utf8.map      # Demo map file
├── build.gradle
├── settings.gradle
└── gradlew / gradlew.bat
```

---

# ▶️ Running the Project

## Windows (PowerShell)

Navigate into the project directory:

```powershell
cd "Assignment 2"
```

Run the game:

```powershell
.\gradlew.bat :core:run --args="../demoinput.utf8.map"
```

Run using another input file:

```powershell
.\gradlew.bat :core:run --args="../testinput-0.utf8.map"
```

---

# 🎯 Gameplay Controls

```text
w  -> Move Up
a  -> Move Left
s  -> Move Down
d  -> Move Right

locale   -> Change locale
teleport -> Activate teleport plugin
quit     -> Exit game
```

---

# 📚 Educational Focus

This project was developed to explore:

* Software architecture principles
* Extensible system design
* Plugin and scripting systems
* DSL parsing
* Build automation
* Internationalisation concepts

---

# 👩‍💻 Author

Makawitage Keneesha Yehaly Perera
Curtin University – Software Engineering
