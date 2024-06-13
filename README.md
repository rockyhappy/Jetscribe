# Jetscribe App

## Overview

This is a simple Android application that serves as a blog reading app. The app retrieves blog data from an API, displays it in an organized manner, and allows users to read individual blog posts using WebView. The project demonstrates the application of clean architecture principles, Jetpack Compose for UI, and MVVM architecture for maintainability.

## Features

- **List of Blogs**: Displays a list of blog posts retrieved from the API.
- **Individual Blog Content**: Allows users to read individual blog posts using WebView.
- **Clean Architecture**: The project is structured following clean architecture principles, ensuring separation of concerns and maintainability.
- **MVVM Architecture**: Implements the MVVM architecture pattern.
- **Jetpack Compose**: Uses Jetpack Compose for building the user interface.
- **Smooth Navigation**: Ensures smooth navigation between the list of blogs and individual blog posts.
- **Git for Code Management**: The project is managed using Git.

### Optional Features

- **SQLite Caching**: Caches blog data using SQLite for offline access.
- **Offline Handling**: Handles scenarios when the device is offline.
- **Efficient Data Loading**: Implements efficient data loading techniques.
- **Pagination**: Supports pagination as the user scrolls through the blog list.

## Architecture

The application is built using a clean architecture approach with the MVVM (Model-View-ViewModel) pattern. The project is divided into the following layers:

- **Presentation Layer**: Contains UI components built using Jetpack Compose and ViewModels that interact with the domain layer.
- **Domain Layer**: Contains use cases and repository interfaces.
- **Data Layer**: Contains repository implementations and data sources (e.g., remote data source for API calls, local data source for SQLite).

## Getting Started

### Prerequisites

- Android Studio
- Kotlin
- Git

### Installation

### Apk 
  https://drive.google.com/file/d/1r9NOsBSGanXrBx5y5znLvWC--Vd8G-tg/view?usp=sharing


1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/Jetscribe.git
2. Open Android Studio
3. Sync the project with Gradle files.

Run the app on an emulator or physical device.

## Usage
- Launch the app to view a list of blogs.
- Tap on a blog post to read the full content using WebView.
## Project Structure
- data: Contains repository implementations and data sources.
- domain: Contains use cases and repository interfaces.
- presentation: Contains UI components and ViewModels.
- di: Contains dependency injection setup using Dagger/Hilt.
- utils: Contains utility classes and extensions.

## Screenshots

<img src="https://github.com/rockyhappy/Jetscribe/assets/115190222/fd9e89e2-bbde-458e-b397-b3b5d2d31fdc" alt="News App Screenshot 1" width="250" >
<img src="https://github.com/rockyhappy/Jetscribe/assets/115190222/a1e551d2-ac04-4c38-9641-561687652787" alt="News App Screenshot 1" width="250" >
<img src="https://github.com/rockyhappy/Jetscribe/assets/115190222/83a381a8-60f8-43f0-a95e-d48ef9a20a5e" alt="News App Screenshot 1" width="250" >

## Video Demonstration


https://github.com/rockyhappy/Jetscribe/assets/115190222/8a859ca0-954e-4319-ae0f-188fa704d704



## Contributing
Contributions are welcome! Please fork the repository and submit a pull request for any enhancements or bug fixes.
