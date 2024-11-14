# BloodRelay Plugin

BloodRelay is a simple Minecraft plugin that lets players transfer health to each other.

![Minecraft](https://img.shields.io/badge/Minecraft-1.21-blue.svg)
![Spigot](https://img.shields.io/badge/Spigot-1.21--R0.1--SNAPSHOT-orange.svg)

## Features
- Transfer health between players
- Transfer max health between players
- Help command for a list of all commands

See, just simple.

## Requirements
- Minecraft Server version 1.21 or higher
- Spigot API version 1.21-R0.1-SNAPSHOT

## Installation
1. Download the `BloodRelay.jar` file<!-- from the [latest release](https://github.com/username/repository/releases)-->.
2. Place the `BloodRelay.jar` file in the `plugins` folder of your Minecraft server directory.
3. Start or restart your Minecraft server.

## Usage
This plugin provides several commands to transfer health between players. Here’s a list of available commands:

### Main Command
- `/BloodRelay` or `/br` - Main command for the BloodRelay plugin.

### Sub-Commands
- `/br transfer <targetPlayer> <amount>` - Transfer your health to another player.
- `/br transferMaxHealth <targetPlayer> <amount>` - Transfer your max health to another player.
- `/br help` - Display the help message with a list of available commands.

### Examples
- Transfer 5 units of health to player "Steve":
  ```plaintext
  /br transfer Steve 5
  ```

- Transfer 2 units of max health to player "Alex":
  ```plaintext
  /br transferMaxHealth Alex 2
  ```

### Permissions
This plugin requires the following permissions to access various features:
- `br.admin` - Permission to access the main BloodRelay command.
- `br.transferHealth` - Permission to transfer health.
- `br.transferMaxHealth` - Permission to transfer max health.

## Contributing
Contributions are welcome! Feel free to open an Issue to report bugs or request new features.