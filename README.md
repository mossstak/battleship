<h1> Batttleship </h1>

#### This is a mini project using Object Oriented Design Pattern in Java taken inspiration from one of my childhood game I played at school called Battleships.

## Background & Core Concept

#### Battleship is a classic, turn-based guessing game traditionally played by two players on ruled grid sheets.

- The Setup: Each player secretly places a fleet of ships on their own grid.
- The Gameplay: Players take turns calling out "shots" (coordinates) on the opponent's grid. The opponent must announce whether the shot was a Hit or a Miss.
- The Goal: The first player to sink all of the opponent's ships wins the game. A ship is sunk when every square it occupies has been hit.

## Project Requirements
#### To keep the project manageable, I will start with a Minimum Viable Product (MVP), then look at potential expansions.

### Functional Requirements (MVP)
- Two Players: Can be Player vs. Computer (AI) or Player vs. Player (pass-and-play).
- The Grid: A $10 \times 10$ coordinate system (typically rows A–J and columns 1–10).
- The Fleet: Each player gets 5 ships of varying lengths:
    - Carrier (5 spaces)
    - Battleship (4 spaces)
    - Cruiser (3 spaces)
    - Submarine (3 spaces)
    - Destroyer (2 spaces)
- Ship Placement: Allow ships to be placed horizontally or vertically without overlapping or going out of bounds.
- Turn Logic: Alternating turns, tracking hits/misses, and preventing a player from shooting the same coordinate twice.
- Win Condition: Game automatically ends and declares a winner when a player’s total ship points (17 hits in total) reach zero.

### Technical Requirements
- Written entirely in Java.
- For the basic version, a Command Line Interface (CLI) using standard input/output (Scanner and System.out).
- Exception handling to catch invalid user inputs (e.g., entering "Z25" or a non-integer).

## The Basics: Core Logic & Mechanics
#### Before writing classes, understand how the data will be tracked in code:
- The Grids: You will need two types of grids per player:
    1. Ocean Grid: Tracks where the player's own ships are placed and where the enemy has shot.
    2. Target Grid: Tracks the player's own shots fired at the enemy (tracking hits and misses).
- Representation: A 2D array (char[][] or int[][]) is perfect for this.
- ~ = Water / Unexplored
- S = Ship
- M = Miss
- H = Hit

## Object-Oriented Design (OOD)
- [x] ShipType.java - Create Enum for the Each Ship Type.
- [x] Ship.java - racks the state of an individual ship.
- [x] Grid.java - Manages the $10 \times 10$ board state.
- [x] Player.java - Represents a player (can be extended for a human or a computer AI).
- [x] GameEngine.java - Controls the flow, rules, and state of the overall game.
- [x] App.java - The entry point of your application that simply instantiates the GameEngine and starts it.