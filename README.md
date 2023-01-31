# Cryptolive
## Live cryptocurrency information of different coins with complete verified data and coin market capitalization rankings.


####  Presentation

<div>
   <img src="https://user-images.githubusercontent.com/101206928/215864961-c3b5ff6b-2784-4b4e-9c13-a6864cf0243f.png" width="400">
    <img src="https://user-images.githubusercontent.com/101206928/215864735-e6bf2c8c-60f2-421a-a118-f70dacac0924.png" width="400">
</div>

#### Compose:
##### I am using Jetpack Compose for building UI, as it is very helpful for large screens and handles all different screen sizes, orientations, and form factors - adaptive layout changes based on the screen space available to it, Also used Flow coroutines in most of the Use Cases to emit multiple data over a period of time.


##### Lightweight Thread:
###### I am using coroutine as a concurrency design pattern that you simplify code that executes asynchronously, helpful in more than one field as memory leaks and background tasks executing.


##### About:
###### The app Module is the main part of the application whose dependencies have been injected by dagger-hilt classes implementation, also control how long these class will live and instance creation. also, I am using coinpaprika as a remote data source for cryptocurrency coins' live data and a retrofit library for handling all requests and responses with network connections.


##### Use Cases:
###### In this project, I am counting on Use Case as a single action with a single feature to Use a repository to access API data and then forward the information to the ViewModel.


##### Download:
[Rate app on google](https://play.google.com/store/apps/details?id=sparespark.crypto.currency) -Live

