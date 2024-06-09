# Popular Movies

This project is a mobile application developed in Kotlin that allows users to browse popular movies. The application fetches data from an API and displays it in a user-friendly interface.

## Project Structure

The project follows a clean architecture and is divided into the following packages:

- `ui`: This is the presentation layer contains all the UI components and ViewModels. All UIs are written using Jetpack Compose.
- `domain`: This layer contains all the use cases and repository interfaces.
- `data`: This layer is responsible for data fetching and manipulation. It contains repositoriesImpl, datasources and data models.


## Architecture

The project follows the MVVM (Model-View-ViewModel) architecture. This architecture allows for a separation of concerns, improves testability, and allows the UI to remain largely unaffected by the underlying business logic.

- `Model`: Represents the data and business logic of the app.
- `View`: Represents the UI of the app.
- `ViewModel`: Acts as a bridge between the Model and the View.

## Libraries Used

- `Kotlin Coroutines`: Used for handling asynchronous tasks.
- `Hilt`: Used for dependency injection.
- `Paging 3`: Used for handling pagination of the movie list.
- `MockK`: Used for mocking objects in tests.
- `kotlinx-coroutines-test`: Used for testing coroutines.
- `coil`: Used for asynchronous image loading and caching.

## Setup

To run the project, you need to have Android Studio installed. Open the project in Android Studio and run it on an emulator or a real device.

## Testing

Unit tests are located in the `src/test` directory of each module. They can be run using the `./gradlew test` command.

## Contributing

Contributions are welcome. Please open an issue or submit a pull request.

## Sample Videos

### Main Scenario

The user opens the app and navigates to the main screen. Then scroll down to load more items

https://github.com/aminshabani94/PopularMovies/assets/30406446/c42627e8-d97f-42bf-86ed-29eb8ba6c52d

### Error in loading more items Scenario

The user opens the app and navigates to the main screen. Then scroll down to load more items but get an error

https://github.com/aminshabani94/PopularMovies/assets/30406446/f1df4904-ee24-4801-b589-8ec6d22e0142

### Error in loading Movies at first

The user opens the app but navigates to the Error screen due to an error happened in fetching APIs. For example: Network Error

https://github.com/aminshabani94/PopularMovies/assets/30406446/e9ccd234-e491-4641-b2f0-9e50f1e21d0a

### Light Theme

![Light Theme Error Screen](https://github.com/aminshabani94/PopularMovies/assets/30406446/de5f1b4b-cbea-4de1-bde3-fbbb05c114a5)

![Light Theme Main Screen](https://github.com/aminshabani94/PopularMovies/assets/30406446/116b36cb-2471-4c27-a59f-b4088efcef72)

