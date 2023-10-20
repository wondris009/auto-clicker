# TotalBattleApp
Allows doing automatic mouse operations. Currently, there are two functions. Autoclicking (eg: for opening tons of daily jobs). Auto crypter for **common** and **epic** crypts (rare needs additional click for opening, will implement in future).

# Donation

If the app helped you, feel free to donate.

### $$$

[![Donate with PayPal USD](pic/pp.png)](https://www.paypal.com/donate/?hosted_button_id=Z9RYF3N2UTQWQ)

### €€€

[![Donate with PayPal USD](pic/pp.png)](https://www.paypal.com/donate/?hosted_button_id=GQTGJMF2CR592)

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

## Crypt Hunter Screenshot
![Alt text](pic/crypter.png)

## How to use the application?

### Download

Download application [from](https://github.com/wondris009/auto-clicker/tree/main/downloads)

### Run application
Run command line / bash / terminal and run there command:
> java -jar TotalBattleApp-all.jar

Note to say that screenshots are from MacOS. On windows it looks a little bit different.

Application store the points where it clicks on file system. It expects te be under **HOME_DIRECTORY/tmp/tbapp**. So be sure you have this directory created. Applications always stores all the points before exit to the **coords.txt** file.

At the end it should look like:

> /Users/SomeUserName/tmp/tbapp/coords.txt

> /Users/SomeUserName/tmp/tbapp/TotalBattleApp-all.jar

or prefixed with c:/ on windows

### HotKeys

**CTRL + F1** | exit application

**CTRL + ALT + left mouse click** | add new click point for crypting

## [How to use the application for hunting common and epic crypts?](https://github.com/wondris009/auto-clicker/wiki/Crypt-hunter)

# Planned features / upgrades
* be able to hunt also rare crypts
* moving map just by keys
* hunt citadels