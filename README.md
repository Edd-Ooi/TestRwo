# Habit Tracker — Traduction HTML vers Android natif (Kotlin/XML)

Le HTML fourni a été converti vers une base **native Android Studio** orientée écrans + navigation locale.

## Ce qui est traduit en natif
- Shell applicatif Android avec navigation bas de page + FAB (`activity_main.xml`)
- Écrans séparés en Fragments:
  - Home (`HomeFragment`) avec progression + liste
  - Garden (`GardenFragment`)
  - Reminders (`RemindersFragment`)
  - Profile (`ProfileFragment`)
- Flow d'ajout via dialogue natif `AddHabitDialogFragment`
- Persistance locale 100% téléphone via `HabitStorage` (SharedPreferences JSON)

## Fichiers clés
- `android-native/app/src/main/java/com/habittracker/nativeapp/MainActivity.kt`
- `android-native/app/src/main/java/com/habittracker/nativeapp/ui/HomeFragment.kt`
- `android-native/app/src/main/java/com/habittracker/nativeapp/ui/AddHabitDialogFragment.kt`
- `android-native/app/src/main/res/layout/activity_main.xml`
- `android-native/app/src/main/res/menu/menu_bottom_nav.xml`

## Important
- Aucun serveur
- Aucune synchronisation cloud
- Tout fonctionne localement sur l'appareil
