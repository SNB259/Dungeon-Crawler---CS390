# Dungeon-Crawler: Revamped---CS390
## Final Project for Object Oriented Programming
### General Concept
This project expands on the Dungeon Crawler
made halfway through the semester. This simple platformer will combine procedural level generation with RPG-style combat. The hero will traverse the dungeon one room at a time, encountering different types of enemies, and weapons to fight them. At the end of every level, there will be a dungeon boss, which is a more resilient and skillfull enemy. The hero must traverse three levels successfully to win the game, but must start from the beginning upon each failure.

### Features
Procedural Level Generation:
* Inspired on Spelunky / Binding of Isaac.
* The algorithm will select pre-built room templates and allocate them along a square grid randomly.
* A clear path must be ensured from entrance to exit, so the player can never be stuck due to a game design flaw.
* Enemies will be randomly allocated but with difficulty levels according to the player's progress.
* Overall controlled randomness for level generation.

Combat:
* There will be enemies scattered along the dungeon, these will pursue the player upon detection, and attack them.
* There will be three different types of enemies:
    * Goblin: Easy. Solely melee attacks. Harms on contact.
    * Druid: Medium. Long-distance attacks (fireballs). Harms by projectiles.
    * Warden: Hard. Melee and long-distance (Sword/Bow). Harms on contact and by projectiles.
* Healing potions scattered across levels can heal the player.
