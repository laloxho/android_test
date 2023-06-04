## Why use Coroutines? ##

* Help to manage long-running tasks that might otherwise block the main thread and cause your app to become unresponsive.

## Why use Flow? ##

* We can use flows in many scenarios specially when we’ve some background task is going on and it is emitting some data based on some states of the task and we need to react on those states.
* Types of flows like MutableSharedFlow, MutableStateFlow can be used for event and state update respectively for UI.

## Why use Hilt? ##

* Improves your app's performance by releasing objects in memory when they're no longer needed.
* Makes it easier to manage the dependencies between the classes in our app
* Simplifies access to shared instances.
* Easier unit and integration testing

## Why use Retrofit? ##

* Help to manages the process of receiving, sending, and creating HTTP requests and responses.
* It is easy to use and understand
* There are so many formats and libraries to support converters
* Easily manage connect timeout and read timeout just using it’s methods and also adds Interceptor.

## Why use Mockito? ##

* Help to create a dummy functionality can be added to a mock interface that can be used in unit testing.

## Why use Jetpack Compose? ##

* It simplifies and accelerates UI development on Android 
* Bringing your apps to life with less code.
* Powerful tools.
* Intuitive Kotlin APIs. 
* It makes building Android UI faster and easier.

## Why use Coil? ##

* It works well with Jetpack Compose and uses coroutines.
* Handles loading the image over a network, displaying it and automatically caching it for later use.

## Why use Room? ##

* Is now considered as a better approach for data persistence than SQLiteDatabase.
* Easily integrated with other Architecture components.
* Convenience annotations that minimize repetitive and error-prone boilerplate code.
* Compile-time verification of SQL queries.

## Why use Timber? ##

* It is not required to pass in the tag parameter in the Timber.d(...) or Timber.e(...), automatically detects the class in which logs were written.

## Why use Screen splash? ##

* Lets apps launch with animation, including an into-app motion at launch
* Splash screen showing your app icon, and a transition to your app itself. 
* A SplashScreen is a Window and therefore covers an Activity.

## Why use Navigation components? ##

* Simplified setup for common navigation patterns
* Handle the back stack which means if you go from one screen to another it will know which screen you were at before.
* Automate fragment transaction
* Typesafe arguments passing
* Handles transition animations
* Simplified deep linking
* Centralizes and visualizes navigation
