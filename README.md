# MyNewsfeed

This application is an example of using modern technologies for Android Development.

Something about my approach: 
1. I used following stack: [Kotlin](https://kotlinlang.org/), [RxJava 3](https://github.com/ReactiveX/RxJava), [Room](https://developer.android.com/training/data-storage/room), [Koin](https://github.com/InsertKoinIO/koin), [Retforit](https://square.github.io/retrofit/), [Glide](https://github.com/bumptech/glide), [OkHttp](https://square.github.io/okhttp/), [Jackson](https://github.com/FasterXML/jackson), [Navigation component](https://developer.android.com/guide/navigation) and [Paging library](https://developer.android.com/topic/libraries/architecture/paging) by Google.
2. The page with sources local cache was implemented using **Room**.
3. Paging was implemented for the Articles page using **Paging library by Google**.
4. Navigation between fragments was implemented using **Navigation component** by Google.
5. **Koin** was chosen for DI instead of Dager which I used to use, and it was a valuable experience.
6. I used [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html). This approach was described by Robert Martin. The application code was divided to different layers according to this approach.
7. **MVVM pattern** was used in presentation layer, I used [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) and MVVM pattern.
8. Core classes were covered by Unit tests.
