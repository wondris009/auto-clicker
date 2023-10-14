# TotalBattleApp
Allows doing automatic mouse operations. Currently, there are two functions. Autoclicking (eg: for opening tons of daily jobs). Auto crypter for **common** and **epic** crypts (rare needs additional click for opening, will implement in future).

## Technology stack
**Kotlin, Swing, Gradle** 

## Prerequisites
Be able to run java program. To do this you must install JDK (java development kit). Yes for just running java program, JRE (java runtime environment) is enough (advanced people can try it). But because I am programmer I am using JDK and I didn't try that just with JRE.

So install JDK17. 

### MacOS

[Here](https://www.oracle.com/java/technologies/downloads/#jdk17-mac)

I would go with DMG installer. Use appropriate version based on your processor architecture. x64 vs arm64 (M1, M2 processors)

### Windows
[Here](https://www.oracle.com/java/technologies/downloads/#jdk17-windows)

When installation is successful you would be able to run command line with command below:
> java - version

And it prints correctly your new installed java version 17. Here is just sample about mine java 19:
```
openjdk version "19.0.2" 2023-01-17
OpenJDK Runtime Environment Zulu19.32+13-CA (build 19.0.2+7)
OpenJDK 64-Bit Server VM Zulu19.32+13-CA (build 19.0.2+7, mixed mode, sharing)
```

## Auto Clicker Screenshot
![Alt text](pic/auto-clicker.png)

## Crypt Maker Screenshot
![Alt text](pic/crypter.png)

## How to use the application?

### Run application
Go to the directory where you unpacked the ZIP file. Run command line / bash / terminal and run there command:
> java -jar TotalBattleApp-all.jar

### HotKeys

**CTRL + F1** | exit application

**CTRL + ALT + left mouse click** | add new click point for crypting 

### Define points for crypting

You need to select directly six points which crypter needs to work correctly. Points are selected when you hit CTRL + ALT + left mouse. After you select point it should appear in text area in the bottom part of Crypt maker tab. Try to click in the middle of the requested point / area.

Points:
1. Watchtower icon location

![Alt text](pic/cr01-watchtower.png)

2. Open watchtower and select any "Go button" with selected crypt

![Alt text](pic/cr02-go.png)

3. Crypt position on the map.

![Alt text](pic/cr03-crypt.png)

4. Explore button 

![Alt text](pic/cr04-explore.png)

5. Speedup button

![Alt text](pic/cr05-speedup.png)

6. Use speedup button

![Alt text](pic/cr06-makespeedup.png)
   

### Setup app for auto crypting

* Set Carter as first captain. 
* Set correct gear. 
* Turn archeologist (optional).
* In watchtower select crypt you want to make. When crypting starts it will open watchtower and there must be "Crypts and Arenas" tab selected.
* Go to map and select highest zoom 125%
