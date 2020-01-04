# Nimble Torrent
 
[LATEST VERSION APK FILE](https://github.com/Nekobitlz/Nimble_Torrent/blob/master/apks/NimbleTorrent-1.0.apk)

Android application for downloading torrents based on [TorrentStreamLibrary](https://github.com/TorrentStream/TorrentStream-Android).

## Technology stack 
The source code of the application was written in *Kotlin*. The application architecture is based on the *MVP* pattern. To download torrents, the *TorrentStreams* library was used. Information about torrents was stored using the *Room*. *RxJava 2* was used to ensure asynchronous operation. *Dagger 2* was used to implement dependency injection. Also, the *NumberProgressBar* library and *MaterialDialogs* were used for a more beautiful display.

## Screenshots
<img src="/screenshots/no_torrents.jpg" width="360" height="640"> <img src="/screenshots/downloads.jpg" width="360" height="640"> <img src="/screenshots/all_torrents.jpg" width="360" height="640">

## Setup for Developers
1. Make sure you have downloaded the latest version of [Android Studio](https://developer.android.com/sdk/index.html). It works on Linux, Windows and Mac. Download the correct version for your OS.
2. Go to [the project repo](https://github.com/Nekobitlz/Nimble_Torrent) and fork it by clicking "Fork" 
3. If you are working on Windows, download [Git Bash for Windows](https://git-for-windows.github.io/) to get a full Unix bash with Git functionality
4. Clone the repo to your desktop `git clone https://github.com/YOUR_USERNAME/Nimble_Torrent.git`
5. Open the project with Android Studio 
6. Build a 'app' application which is inside the base directory.
