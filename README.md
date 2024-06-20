# Prayer Times Plugin for IntelliJ IDEA

![Logo](https://private-user-images.githubusercontent.com/70816926/341532878-35e56eef-49b3-4b8c-b36f-3917e937c33d.svg?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MTg5MTA0NzMsIm5iZiI6MTcxODkxMDE3MywicGF0aCI6Ii83MDgxNjkyNi8zNDE1MzI4NzgtMzVlNTZlZWYtNDliMy00YjhjLWIzNmYtMzkxN2U5MzdjMzNkLnN2Zz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA2MjAlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwNjIwVDE5MDI1M1omWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPWEzZDYwYmQyNzhlYjI2ZmUxZTU2OGUzNDhhYzFmMThjNjhlYzBiN2IwOGM0YjllYzFjYTE4ZWE4ZDZiZWEzNTQmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.IgTbPLoxHlc7okgMyBqqRWtrUeVVfajRisT_cYJv9Iw)

## Description

This is a plugin developed for IntelliJ IDEA. It is written in Kotlin and Java, and built with Gradle. The plugin provides prayer times based on the user's location settings.

## Installation

1. Download the plugin from the [Releases](https://github.com/0xberkay/PrayerTimes/releases/tag/1.0.0).
2. Open IntelliJ IDEA.
3. Go to `File` -> `Settings` -> `Plugins` -> `Install Plugin from Disk...`.
4. Select the downloaded plugin file.
5. Restart IntelliJ IDEA.

## Usage

1. Open IntelliJ IDEA.
2. Go to `View` -> `Tool Windows` -> `Prayer Times`.
3. The plugin will automatically fetch the prayer times based on your location settings.
4. You can press `Ctrl + Shift + E` to see the prayer times.
5. It will show dialog the prayer times when time comes.

## Screenshots

![Prayer Times Plugin](https://private-user-images.githubusercontent.com/70816926/341530103-57d6331f-df6d-4495-9bd6-f591435da00b.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MTg5MDk4MjIsIm5iZiI6MTcxODkwOTUyMiwicGF0aCI6Ii83MDgxNjkyNi8zNDE1MzAxMDMtNTdkNjMzMWYtZGY2ZC00NDk1LTliZDYtZjU5MTQzNWRhMDBiLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA2MjAlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwNjIwVDE4NTIwMlomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTM3MjY5MmQ5NGRhYjVmMWJmYzYyNDg1NjUxYjdhM2YxNDJmZTI4M2ZmOWFlMDI1NWFmNGJmYjEyNjFjYzNjZmMmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.Dxjthrxq3UDC4Ui8L0Xew5K0PQrQI7yzf7vkNWJMvqk)

![Prayer Times Plugin](https://private-user-images.githubusercontent.com/70816926/341530108-4af0999d-c11b-4906-8a23-a0561390c55d.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MTg5MDk4MjIsIm5iZiI6MTcxODkwOTUyMiwicGF0aCI6Ii83MDgxNjkyNi8zNDE1MzAxMDgtNGFmMDk5OWQtYzExYi00OTA2LThhMjMtYTA1NjEzOTBjNTVkLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA2MjAlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwNjIwVDE4NTIwMlomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTgzZWM1YmE1ZGU0MjFmMjhjZjQyZjkwNDc0ZjVhNzFiMDRlMWY3MDRmODcxOGIxNzE1YThkZDI3NmRlOWNiYmMmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.whK2gugPbuUUjZl0_TkI0nNPKcfAgewEz-gyzRna1a8)

![Prayer Times Plugin](https://private-user-images.githubusercontent.com/70816926/341530093-59bf2d13-6e9e-40c3-a6c4-49cef336855b.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MTg5MDk4MjIsIm5iZiI6MTcxODkwOTUyMiwicGF0aCI6Ii83MDgxNjkyNi8zNDE1MzAwOTMtNTliZjJkMTMtNmU5ZS00MGMzLWE2YzQtNDljZWYzMzY4NTViLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA2MjAlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwNjIwVDE4NTIwMlomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTEzY2UyOWJmNTFjMmE1OWZjZDcyMjdjZDRiOTI5YzZlNmYwOTgyYzNkYWYyZWRjNjZhZjAxZTQ0M2JkYzNmNmImWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.y-DaOsS6Wava1fi87ZLcpBrzdGRe47apuinQN0YzXcg)I
