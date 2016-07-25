# Project 1 - Flickster

**Flickster** shows the latest movies currently playing in theaters. The app utilizes the Movie Database API to display images and basic information about these movies to the user.

Time spent: **15** hours spent in total

## User Stories

The following **required** functionality is completed:

* [X] User can **scroll through current movies** from the Movie Database API
* [X] Layout is optimized with the [ViewHolder](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView#improving-performance-with-the-viewholder-pattern) pattern.
* [X] For each movie displayed, user can see the following details:
  * [X] Title, Poster Image, Overview (Portrait mode)
  * [X] Title, Backdrop Image, Overview (Landscape mode)

The following **optional** features are implemented:

* [X] User can **pull-to-refresh** popular stream to get the latest movies.
* [X] Display a nice default [placeholder graphic](http://guides.codepath.com/android/Displaying-Images-with-the-Picasso-Library#configuring-picasso) for each image during loading.
* [X] Improved the user interface through styling and coloring.

The following **bonus** features are implemented:

* [X] Allow user to view details of the movie including ratings and popularity within a separate activity or dialog fragment.
* [X] When viewing a popular movie (i.e. a movie voted for more than 5 stars) the video should show the full backdrop image as the layout.  Uses [Heterogenous ListViews](http://guides.codepath.com/android/Implementing-a-Heterogenous-ListView) or [Heterogenous RecyclerView](http://guides.codepath.com/android/Heterogenous-Layouts-inside-RecyclerView) to show different layouts.
* [X] Allow video trailers to be played in full-screen using the YouTubePlayerView.
    * [X] Overlay a play icon for videos that can be played.
    * [X] More popular movies should start a separate activity that plays the video immediately.
    * [X] Less popular videos rely on the detail page should show ratings and a YouTube preview.
* [X] Leverages the [data binding support module](http://guides.codepath.com/android/Applying-Data-Binding-for-Views) to bind data into layout templates.
* [X] Apply the popular [Butterknife annotation library](http://guides.codepath.com/android/Reducing-View-Boilerplate-with-Butterknife) to reduce boilerplate code.
* [X] Apply rounded corners for the poster or background images using [Picasso transformations](https://guides.codepath.com/android/Displaying-Images-with-the-Picasso-Library#other-transformations)

The following **additional** features are implemented:

* [X] Toolbar hides and quick returns on the main screen to provide more real estate.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

### Portrait 

http://i.imgur.com/kb9ALcL.gif

<img src='http://i.imgur.com/kb9ALcL.gif' title='Portrait Walkthrough' width='' alt='Portrait Walkthrough' />

### Landscape

http://i.imgur.com/8sRIBsi.gif

<img src='http://i.imgur.com/8sRIBsi.gif' title='Landscape Walkthrough' width='' alt='Landscape Walkthrough' />

### Refresh & Quick Return

http://i.imgur.com/xRZC5Fg.gif

<img src='http://i.imgur.com/xRZC5Fg.gif' title='Refresh and Quick Return Walkthrough' width='' alt='Refresh and Quick Return Walkthrough' />

The GIFs resolution is a bit low, hence I added these screenshots:

### Portrait Screenshots
<img src='http://i.imgur.com/UaWzJz4.jpg' title='Portrait 1' width='' alt='Portrait 1' />

<img src='http://i.imgur.com/38Y1g5j.jpg' title='Portrait 2' width='' alt='Portrait 2' />

<img src='http://i.imgur.com/9PTB5WY.jpg' title='Portrait 3' width='' alt='Portrait 3' />

<img src='http://i.imgur.com/GcTWp5w.jpg' title='Portrait Detail' width='' alt='Portrait Detail' />

### Landscape Screenshots
<img src='http://i.imgur.com/cDXbctb.jpg' title='Landscape 1' width='' alt='Landscape 1' />

<img src='http://i.imgur.com/DzNfbAg.jpg' title='Landscape 2' width='' alt='Landscape 2' />

<img src='http://i.imgur.com/4PmQCrY.jpg' title='Landscape Detail' width='' alt='Landscape Detail' />


GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.
* Creating an elegant design & icons is time consuming.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android
- [Butterknife](http://jakewharton.github.io/butterknife/) - Field and method binding for Android views
- [Gson](https://github.com/google/gson) - A Java serialization/deserialization library that can convert Java Objects into JSON and back
- [Dagger](http://google.github.io/dagger/) - Compile-time dependency injection framework for Android

## License

    Copyright 2016 Sharath Prodduturi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.