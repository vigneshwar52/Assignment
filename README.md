#  Pokemon Jetpack Compose App

A simple Android app that displays a list of Pokémon and their details using Jetpack Compose. This app follows the MVVM architecture and uses Retrofit for network requests.

## Features

- Display a list of Pokémon fetched from the PokéAPI.
- Navigate to a detail screen to view more information about a selected Pokémon.
- Support for both portrait and landscape orientations.

## Screenshots
ListView:
<img src="https://github.com/user-attachments/assets/25346877-e4a3-4df9-8b78-684395ab018b" alt="listView" width="400"/>

DetailedView:
<img src="https://github.com/user-attachments/assets/59e65dfe-7a3a-4dfd-850e-d1246e4e5e87" alt="detailedView" width="400"/>


## Build APK Link:
https://drive.google.com/file/d/19i62eYvYEgPOEX5WO4gtcPERCn7KrWpn/view?usp=drive_link

## Architecture

This app follows the Model-View-ViewModel (MVVM) architecture:

- **Model**: Data classes representing the Pokémon data.
- **View**: Composables for the UI.
- **ViewModel**: Fetches data from the API and exposes it to the View.

## Libraries Used

- [Jetpack Compose](https://developer.android.com/jetpack/compose) for the UI.
- [Retrofit](https://square.github.io/retrofit/) for network requests.
- [Gson](https://github.com/google/gson) for JSON parsing.
- [Coil](https://coil-kt.github.io/coil/) for image loading.
- [Navigation Component](https://developer.android.com/jetpack/compose/navigation) for navigation.

## Setup

1. Clone the repository:
    ```sh
    git clone git@github.com:vigneshwar52/Assignment.git
    ```
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Run the app on an emulator or a physical device.

## API

This app uses the [PokéAPI](https://pokeapi.co/) to fetch Pokémon data:

- Endpoint for Pokémon list: `https://pokeapi.co/api/v2/pokemon`
- Endpoint for Pokémon details: `https://pokeapi.co/api/v2/pokemon/{id}`

