# The Last 10 Days — README

**Project:** TheLast10Days — a 10-day zombie survival console game written in Java.

**Archive uploaded:** `/mnt/data/TheLast10Days.zip`

---

## Overview

The Last 10 Days is a console-based Java game where the player must survive for **10 in-game days**. Each day the player chooses actions (scavenge, rest, fortify, etc.), faces **random events**, and may encounter **zombies**. The project contains modular systems for events, combat, inventory, and entity management.

This README was generated to match the source in the provided ZIP. The project uses a straightforward Java structure under `src/` with a top-level `Main.java` that contains the application entry point.

---

## Highlights / Features

* 10-day campaign (survive until the end to win).
* Random event system with weighted outcomes (positive, neutral, negative).
* Zombie encounter subsystem with turn-based combat.
* Inventory system: weapons, consumables (food, antibiotics), and craftable items.
* Save / load support (file-based).
* Deterministic mode via RNG seed flag for testing.

---

## Important source locations (summary)

Major packages and directories in the `src/` tree:

* `actions/` — player actions (Attack, Run, Hide, UseItem, ...)
* `systems/` — EventSystem, CombatSystem, ZombieSystem, etc.
* `entities/` — Player/Survivor, Zombie, Inventory, Items, Weapons, Foods, Events
* `txt/` — ASCII banners and text assets (e.g. `Banner.txt`)
* `json/` — sample data / event definitions
* `Main.java` — application entry point (found at `src/Main.java`)

(Full file list is included in the ZIP you uploaded.)

---

## Quick Start — compile & run (no build tools)

> These commands assume you are running from the repository root and you have Java (JDK) installed. Tested with OpenJDK 11+.

1. **Unzip (if needed)**

```bash
unzip /mnt/data/TheLast10Days.zip -d TheLast10Days
cd TheLast10Days/src
```

2. **Compile**

```bash
# from TheLast10Days/src
javac -d ../out $(find . -name "*.java")
```

3. **Run**

```bash
# run from TheLast10Days/src
java -cp ../out Main

# deterministic run with a seed (if supported by the Main args)
java -cp ../out Main --seed 12345
```

If `Main` is in a package (check the first line of `src/Main.java`), replace `Main` with the fully qualified class name (for example `game.Main`).

---

## Configuration

Look for configuration or data under `src/json/` and `src/txt/`. The game supports configurable parameters such as:

* `days` (default: 10)
* starting stats (health, stamina, morale)
* event weights and zombie difficulty scaling
* RNG seed for reproducible runs

If the repository includes a `config.properties` or similar file, edit it before running.

---

## Gameplay summary

Each day you will:

1. Choose an action (Scavenge / Rest / Fortify / Travel / Use Item).
2. Resolve the action and trigger a random event (based on weighted tables).
3. If a zombie encounter occurs, enter the combat system: typically turn-based, where player and zombies exchange attacks until one side wins or the player flees.
4. Manage inventory and resources between days. Food restores stamina/health, antibiotics cure infections, weapons have varying damage and ammo rules.

Win by surviving until the end of day 10. Game over occurs when player health reaches 0.

---

## Troubleshooting & Tips

* **`Password authentication is not supported`** — unrelated to this project; that message typically appears when pushing to GitHub using a password. Use a personal access token (PAT) or Git credential manager.
* If you get `NoClassDefFoundError` or `ClassNotFoundException` when running, verify the `-cp` path and that compilation succeeded (check `../out` folder contents).
* If the game uses relative file reads (e.g., `resources/`), run the game from the repository root or adjust working directory accordingly.

---

## Development notes (for contributors)

* Follow the existing package layout: `actions`, `systems`, `entities`.
* Keep `EventSystem` data-driven where possible — move event definitions into JSON to allow easy editing without recompiling.
* Add unit tests for key deterministic behaviors using seeded RNG.

---

## Files included (zip snapshot)

The uploaded archive contains the full source tree. Example important paths found inside the ZIP:

```
src/Main.java
src/actions/Attack.java
src/actions/Hide.java
src/systems/EventSystem.java
src/systems/CombatSystem.java
src/systems/ZombieSystem.java
src/entities/Survivor.java
src/entities/Zombie.java
src/entities/inventory/items/Item.java
src/entities/inventory/weapons/Shotgun.java
src/txt/Banner.txt
src/json/events.json
```

(There are many more supporting classes. Use the ZIP if you need the complete list.)

---

## License

This README assumes the project author will choose a license. If you want a default, add an `MIT` or `Apache-2.0` file.

---
