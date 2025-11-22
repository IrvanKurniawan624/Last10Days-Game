# Zombie Survival — 10-Day Java Game

**Short description:**
A small single-player Java game where you survive **10 days** in a post-apocalyptic city. Each day you scavenge, manage inventory, and face **random events** including **zombie encounters**. Simple console-based gameplay, easy to extend.

---

## Features

* 10-day campaign (win by surviving day 10).
* Random events each day (good/bad outcomes).
* Randomized zombie encounters with simple combat.
* Inventory (weapons, medkits, food).
* Basic stats: health, stamina, morale.
* Save/load (file-based simple save).
* Configurable RNG seed for reproducible runs.

---

## How it plays (summary)

* Each **day** you choose actions: Scavenge / Rest / Fortify / Travel.
* After action, a **random event** may trigger (find supplies, trap, trader, storm).
* Some events spawn **zombies** — combat uses weapon damage vs zombie HP.
* If health ≤ 0 → game over. Survive through day 10 → victory.

---

## Random events & zombie encounters

* Events chosen from a weighted list. Example weights: find (40%), trader (10%), trap (15%), zombie ambush (25%), nothing (10%).
* Zombie encounter spawns 1..N zombies depending on event severity and day (day increases difficulty).
* Combat: turn-based simplified: player attacks (weapon damage ± randomness), zombies attack back. Stamina affects chance to hit/escape.
* RNG seed option: `--seed <number>` for deterministic testing.

---

## Quick install & run

Assumes source in `src/` with package `game`.

Compile:

```bash
javac -d out $(find src -name "*.java")
```

Run:

```bash
java -cp out game.Main
# optional deterministic run:
java -cp out game.Main --seed 12345
```

---

## Configuration (example)

Edit `config.properties` or pass CLI flags:

```
days=10
startingHealth=100
startingStamina=50
eventWeights=find:40,trap:15,trader:10,zombie:25,nothing:10
maxZombiesPerEncounter=3
```

---

## Project layout (recommended)

```
src/
  game/
    Main.java
    DayManager.java
    EventSystem.java
    Combat.java
    Player.java
    Zombie.java
    Inventory.java
resources/
  config.properties
README.md
```

---

## Extending ideas

* GUI (Swing/JavaFX)
* More event types, AI behaviors, stealth mechanics
* Multiplayer/co-op mode
* Difficulty levels, achievements

---

## License & credits

MIT License — feel free to fork and improve.

---

Want me to:

* generate full `Main.java` + core classes scaffold?
* or produce a ready-to-run ZIP with example `config.properties`?
