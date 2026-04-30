# ✈️ FastTripPlanner

Android app for quick trip planning.

## Features

- Enter trip data: destination, number of days, and daily budget
- Select accommodation type: Economy, Comfort, or Luxury
- Select additional services: Transport, Food, and Tours
- Automatic calculation of the total trip cost
- Navigation between screens via explicit Intents
- State preservation on screen rotation

## Screens

**Screen 1 — Trip Data**
Fields for destination, number of days, and daily budget with data validation before proceeding.

**Screen 2 — Trip Options**
Accommodation selection via radio buttons and additional services via checkboxes. Calculate and Back buttons.

**Screen 3 — Trip Summary**
Displays all entered data, the total cost calculation, and a button to restart the planning.

## Calculation Rules

```
baseCost = days × dailyBudget × accommodationMultiplier

Accommodation multipliers:
  Economy → 1.0
  Comfort → 1.5
  Luxury  → 2.2

Extras:
  Transport → + R$ 300.00 (fixed)
  Food      → + R$ 50.00 per day
  Tours     → + R$ 120.00 per day

total = basecost + extras
```

## Technologies

- Kotlin
- Jetpack Compose
- Material Design 3
- Minimum Android SDK: API 26 (Android 8.0)

## How to run

1. Clone the repository
2. Open the project in Android Studio
3. Run on an emulator or device with Android 8.0 or higher
