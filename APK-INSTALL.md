# APK Android (compatible Android Studio Panda)

## Versions recommandées
- Android Studio **Panda** (2022.1.1)
- Node.js **18 LTS**
- Java **JDK 17**
- SDK Android installé via Android Studio

## Build APK debug
```bash
./build-apk.sh
```

Sortie APK:
- `android/app/build/outputs/apk/debug/app-debug.apk`

## Ouvrir dans Android Studio Panda
```bash
npm run cap:open
```
Puis `Build > Build Bundle(s) / APK(s) > Build APK(s)`.

## Installation Samsung
1. Copier `app-debug.apk` sur le téléphone
2. Autoriser l'installation d'applications inconnues
3. Installer l'APK

## Données
- Stockage 100% local sur le téléphone (localStorage)
- Aucune synchro cloud
