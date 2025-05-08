 Bosta Android Task 

This is a simple Android application built as part of the Bosta Android Engineer technical task.  
The app fetches a list of cities and their districts from Bosta's API and displays them in an expandable list using modern Android development tools.
--------------------------------------------------------------------------------
 Features :

- Fetches cities and their districts from a remote API.
- Displays cities in a vertical `LazyColumn`.
- Clicking a city expands a nested `LazyColumn` with its districts.
- Search functionality to filter cities and Districts by name (English).
- Toggle to hide/show the entire city list.
- Built using a clean **MVVM architecture** with **StateFlow**, **State Hoisting**, and **Jetpack Compose**.
-----------------------------------------------
 Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **MVVM Architecture**
- **Retrofit2** (Networking)
- **Coroutines** (Async calls)
- **StateFlow** (Reactive state management)
- **Material3** (UI components)
--------------------------------------------------------

 API Used
```http
GET https://stg-app.bosta.co/api/v2/cities/getAllDistricts?countryId=60e4482c7cb7d4bc4849c4d5
