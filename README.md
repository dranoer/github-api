# ⭐ Architecture
The project is implemented with MVVM and it's written fully with Kotlin.
<br />

# ⭐ Technologies And Decisions
In this section, I'll try to mention some of the important things I used within this project and I'll explain the reason behind some of my decisions.

## DI
I used hilt because of fewer boilerplate codes. Also generally, I think it's easier for testing usage as well.

## Coroutine and Flow
For the threading and observing, I could use technologies like Livedata with Threadpools, Rx, But I used Coroutine and Flow in this project which is my preferred way.
I can explain Why? It's hard to handle things like backpressure or debounce with Livedatas and ThreadPools.
On the other hand, Coroutine is lighter than Rx. It's native and somehow it's easier to test because Google has created some libraries for that.
*PS: I also used some codes from Google samples.*

## Data Persistence
The approach here in this app is Offline first and I made a local database with the help of Room library.

## UI
All the views made with Jetpack Compose.

## Test
I added preview testing for compose views. Also I wrote some unitTest for the only and shared viewmodel in this app. There are still so many rooms for improvement in this field.
<br />

# ⭐ Need to Improve
## Performance optimization
Pagination is one of the things I have missed in this project for increasing the total performance of the app.
