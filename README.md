# 🎵 Quackify — Music Playlist Application

A fully functional music player application built in Java from scratch, using custom-implemented linear data structures. No Java `util` collections were used — every data structure powering this app was hand-built.

---

## Overview

Quackify is a playlist management system built on two custom data structures:

- **`MyLinkedList`** — A circular singly linked list that manages the playlist with O(1) add/remove at the front and back
- **`ArrayDeque`** — A circular array-based double-ended queue that powers undo/redo history and palindrome checking

The project was built as part of Georgia Tech's CS 1332 (Data Structures & Algorithms) coursework.

---

## Features

| Feature | Description |
|---|---|
| ▶️ Play / ⏹ Stop | Start or stop playlist playback |
| ⏭ Next Song | Advance to the next track in O(1) |
| ➕ Add Song | Add a track to the top of the playlist in O(1) |
| ➖ Remove Song | Remove the first occurrence of a track in O(n) |
| 🔀 Random Song | Jump to a random track using seeded `Random` |
| 🔁 Reverse | Reverse the playlist in O(n) time, O(1) auxiliary space |
| 🔤 Palindrome Check | Detect if the playlist order is a palindrome using the ArrayDeque |
| ↩️ Undo | Undo the most recent structural change (add/remove) |
| ↪️ Redo | Redo the most recently undone change; clears on new modification |

---

## Data Structures

### `MyLinkedList` — Circular Singly Linked List
- Maintains both a `head` and `tail` pointer, with `tail.next` always pointing back to `head`
- Enables O(1) `addFirst`, `addLast`, and `removeFirst`
- Supports in-place `reverse()` in O(n) time with O(1) auxiliary space
- Custom iterator wraps around the list endlessly

### `ArrayDeque` — Circular Array Deque
- Initial capacity of 11; doubles when full
- Uses a `front` pointer and modular arithmetic for O(1) front and back operations
- Powers three parallel stacks for undo/redo: operation type, song name, and index
- Also used for the palindrome check — loads songs front-to-back, then compares ends inward

---

## Undo / Redo System

Structural changes (`addSong`, `removeSong`) are tracked using three parallel `ArrayDeque` stacks:
- **Type** — `"ADD"` or `"REMOVE"`
- **Song** — the song name affected
- **Index** — the position in the playlist (used to restore exact order on undo)

Performing any new structural change clears the redo history, maintaining correctness.

---

## Project Structure

```
├── Main.java                        # Entry point; factory methods for instances
├── apply/
│   ├── StaticQuackify.java          # Interface defining all player operations (DO NOT MODIFY)
│   └── MyQuackify.java              # Concrete implementation of the music player
├── refactor/
│   ├── StaticEndlessLinkedList.java # Abstract circular linked list (DO NOT MODIFY)
│   └── MyLinkedList.java            # Concrete circular linked list implementation
└── implement/
    └── ArrayDeque.java              # Circular array deque implementation
```

---

## Getting Started

### Prerequisites
- Java 11 or higher
- JUnit 5 (for running tests)

### Compile & Run

```bash
# Clone the repository
git clone https://github.com/YOUR_USERNAME/quackify.git
cd quackify

# Compile all files
javac -d out $(find . -name "*.java")

# Run
java -cp out Main
```

---

## Author

Biruk Tensae — Computer Science Student at Georgia Institute of Technology/ linkedin: https://www.linkedin.com/in/biruk-tensae-509621377/
