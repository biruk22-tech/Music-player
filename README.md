Quackify — Music Playlist Application
A music player application built in Java using custom linear data structures. No Java util collections were used — every data structure was hand-built.
Overview
Quackify is a playlist management system built on two custom data structures:

MyLinkedList — A circular singly linked list that manages the playlist with O(1) add/remove at the front and back
ArrayDeque — A circular array-based double-ended queue that powers undo/redo history and palindrome checking

Features

Play / Stop playlist playback
Advance to the next track in O(1)
Add a track to the top of the playlist in O(1)
Remove the first occurrence of a track in O(n)
Jump to a random track using seeded Random
Reverse the playlist in O(n) time, O(1) auxiliary space
Palindrome check on playlist order using ArrayDeque
Undo/Redo structural changes (add/remove)

Data Structures
MyLinkedList — Circular Singly Linked List: maintains head and tail pointers with tail.next always pointing back to head. Supports in-place reverse() in O(n) time with O(1) auxiliary space.
ArrayDeque — Circular Array Deque: initial capacity of 11, doubles when full. Uses a front pointer and modular arithmetic for O(1) front and back operations.
Undo / Redo System
Structural changes are tracked using three parallel ArrayDeque stacks — operation type, song name, and index — so the exact playlist order is restored on undo. Any new structural change clears the redo history.
Project Structure
├── Main.java
├── apply/
│   ├── StaticQuackify.java
│   └── MyQuackify.java
├── refactor/
│   ├── StaticEndlessLinkedList.java
│   └── MyLinkedList.java
└── implement/
    └── ArrayDeque.java
Author
Biruk Tensae — Computer Science Student at Georgia Institute of Technology/ linkedin: https://www.linkedin.com/in/biruk-tensae-509621377/
