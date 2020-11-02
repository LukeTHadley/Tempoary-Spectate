# Tempory Spectate
###### A Spigot Minecraft plugin for 1.15.1. Allows players to temporarily be placed into spectator mode until they choose to come out again

---

The plugin stores the location of the player, and switches them into spetator mode untill they choose to come out of it again, teleporting them back to there last known surival coordinates.

## Install

The plugin can be downloaded [here](https://github.com/leapmotion/leapsimple), or you can compile it yourself :)

## Commands

```/spec``` or ```/spectate``` toggles a players gamemode between 'Survival' and 'Spectator'.

![alt text](/readmefiles/LoginMessage.png "Login message")

The plugin stores the location in the world that the player was in before switching to spectator mode.

![alt text](/readmefiles/surToSpec.gif "Login message")

Once the player runs the command to come out of spectator mode, the plugin will teleport the player to there last 'survival' coordinates in the world.

![alt text](/readmefiles/specToSur.gif "Login message")


If a player attempts to logout, or there connection gets forcibly removed from the server, the plugin will teleport them to there last known survival coordinates and switch them back to survival.




## To Do

* Add permission nodes for commands.
* Update plugin to use the latest Spigot API (otherwies it will throw errors in console, it will still run however.)
