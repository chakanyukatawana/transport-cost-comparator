# 🚗 Transport Cost Comparator

An Android application designed to help users calculate, track, and understand their daily and monthly transport expenses.

The application allows users to enter their transport information, calculate transport costs, classify their monthly expenses, receive cost-saving recommendations, and save previous calculations locally.

## 📱 About the Project

Transport Cost Comparator was developed as an Android application using Java and XML.

The application calculates transport expenses based on the user's:

- Mode of transport
- Transport type
- Distance travelled per day
- Cost per kilometre
- Selected travel dates

The application automatically determines the number of travel days and generates daily and monthly transport cost information.

## ✨ Features

- 🚘 Enter transport information
- 📅 Select travel dates using DatePicker
- 🧮 Calculate daily transport cost
- 💰 Calculate monthly transport cost
- 🛣️ Calculate monthly distance travelled
- 📊 Classify monthly transport expenses
- 💡 Generate transport cost-saving recommendations
- 💾 Save transport reports locally using SQLite
- 📋 View previously saved transport details
- 📈 View saved transport reports
- 🔊 Play transport-related audio using MediaPlayer
- ✅ Input validation and error handling
- 🏠 Multi-screen navigation
- 🚀 Splash screen on application startup
- ❓ Help screen

## 🧮 Calculations

The application performs the following calculations:

### Daily Transport Cost

Daily Transport Cost = Distance Travelled per Day × Cost per Kilometre

### Monthly Transport Cost

Monthly Transport Cost = Daily Transport Cost × Number of Travel Days

### Monthly Distance

Monthly Distance = Distance Travelled per Day × Number of Travel Days

### Example

If a user travels **30 km per day** at a cost of **R4.00 per kilometre** for **20 days**:

Daily Transport Cost:

30 × R4.00 = R120.00

Monthly Transport Cost:

R120.00 × 20 = R2400.00

Monthly Distance:

30 km × 20 = 600 km

The application then determines the appropriate transport cost category and provides a recommendation.

## 💰 Transport Cost Categories

| Monthly Transport Cost | Category |
|---|---|
| Below R1,000 | Low Cost |
| R1,000 – R1,999 | Moderate |
| R2,000 – R2,999 | High |
| R3,000 – R4,999 | Very High |
| R5,000 and above | Excessive |

## 🛠️ Technologies Used

- Java
- Android Studio
- XML
- SQLite
- Android SDK

### Android Components

- Activities
- Intents
- DatePickerDialog
- Spinner
- EditText
- TextView
- Buttons
- Toast messages
- MediaPlayer
- SQLiteOpenHelper

## 📱 Application Screens

The application contains the following screens:

### Splash Screen
Displays the application logo and name when the application starts.

### Home Screen
Provides access to the main application features, including starting a calculation and playing transport-related audio.

### Transport Input Screen
Allows users to enter transport information, select travel dates, and choose their transport type.

### Results Screen
Displays:

- Mode of transport
- Transport type
- Daily transport cost
- Monthly transport cost
- Monthly distance travelled
- Cost category
- Recommendation

### Details Screen
Displays previously saved transport information.

### Reports Screen
Retrieves and displays saved transport reports from the SQLite database.

### Help Screen
Provides additional guidance to users.

## 🗄️ Local Data Storage

The application uses **SQLite** for offline data storage.

Transport calculations are stored locally and can be retrieved through the Details and Reports screens.

The database stores information such as:

- Mode of transport
- Transport type
- Distance travelled
- Cost per kilometre
- Travel days
- Daily cost
- Monthly cost
- Monthly distance
- Cost category
- Recommendation

## 📂 Project Structure

```text
app/
└── src/
    └── main/
        ├── java/com/example/transportcostcomparator/
        │   ├── DatabaseHelper.java
        │   ├── DetailsActivity.java
        │   ├── HelpActivity.java
        │   ├── InputActivity.java
        │   ├── MainActivity.java
        │   ├── Recommendation.java
        │   ├── ReportsActivity.java
        │   ├── ResultsActivity.java
        │   ├── SplashActivity.java
        │   ├── Transport.java
        │   └── TransportCalculator.java
        │
        ├── res/
        │   ├── drawable/
        │   ├── layout/
        │   ├── raw/
        │   ├── values/
        │   └── xml/
        │
        └── AndroidManifest.xml
