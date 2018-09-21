# Tic Tac Toe

Java RMI implementation of the popular game Tic Tac Toe.

[![Java](https://img.shields.io/badge/java-10-blue.svg)](https://www.oracle.com/technetwork/java/javase/10-relnote-issues-4108729.html)
[![License](https://img.shields.io/github/license/mashape/apistatus.svg)](https://opensource.org/licenses/MIT)

## The Game

<img src="http://toytheater.com/wp-content/uploads/tic_tac_toe.gif" alt="Tic Tac Toe board" width="250" height="250">

The board consists of three rows and three columns. Each player must position their "piece" (X or O).

To win, the player must fill any row, column or diagonal with three of their "pieces".

## Usage

After setting up the server, the client applications must be initialized just like the example below:

```bash
java Client 127.0.0.1 Arthur
```

The IP above should be replaced by the IP of the host where the server application is running. The second argument of the application is simply the player's name.
