#!/usr/bin/env bash
set -euo pipefail

# Compatibility target:
# - Android Studio Panda (2022.1.1)
# - Node 18 LTS
# - JDK 17

if ! command -v node >/dev/null 2>&1; then
  echo "Node.js manquant"; exit 1
fi
if ! command -v npm >/dev/null 2>&1; then
  echo "npm manquant"; exit 1
fi

npm install
npm run sync:web

if [ ! -d "android" ]; then
  npx cap add android
fi

npx cap sync android
(
  cd android
  ./gradlew assembleDebug
)

echo "APK généré: android/app/build/outputs/apk/debug/app-debug.apk"
