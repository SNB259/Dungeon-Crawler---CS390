# Dungeon-Crawler: Revamped---CS390
## Final Project for Object Oriented Programming
### General Concept
This simple platformer will combine procedural level generation with RPG-style combat. The hero will traverse the dungeon one room at a time, encountering a random amount of enemies in each different room. At the end of the level, there will be a dungeon boss, which is a more resilient and skillfull enemy. The hero must explore the dungeon and clear every room in their way. Upon successfully defeating the boss, the player wins the game. Upon failure, the dungeon will reset entirely and the player must start again.

### Features
Procedural Level Generation:
* Inspired on "The Binding of Isaac".
* The algorithm creates a 5x5 (customizable in code) grid that is filled with interconnected rooms. These are generated at random every time the player starts the game.
* A clear path is always ensured from entrance to exit, so the player can never be stuck due to a game design flaw.
* Enemies will be randomly allocated along the level, with varying amounts.
* Overall controlled randomness for level generation.

Controls:
* Use arrow keys to move around.
* Use SPACE to start game and enter a different room (prompt screen will indicate this anyway).
* Press S to attack. (Remember! Enemies are stunned every time they are hit, so you must wait a couple seconds before hitting it again if you want to hurt it).


