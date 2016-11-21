# Capstone-Project, Stage 2
Project 8 of the Android Nanodegree course bu Udacity. In Stage 1, you designed and planned your Capstone app. Now, it's time to build it!

###Why this Project?
In this project, you will demonstrate the skills you've learned in your Nanodegree journey, and apply them to creating a unique app experience of your own. By the end of this project, you will have an app that you can submit to the Google Play Store for distribution.

##Rubric

####Common Project Requirements
- App conforms to common standards found in the Android Nanodegree General Project Guidelines

####Core Platform Development
- App integrates a third-party library.
- App validates all input from servers and users. If data does not exist or is in the wrong format, the app logs this fact and does not crash.
- App includes support for accessibility. That includes content descriptions, navigation using a D-pad, and, if applicable, non-audio versions of audio cues.
- App keeps all strings in a strings.xml file and enables RTL layout switching on all layouts.
- App provides a widget to provide relevant information to the user on the home screen.

####Google Play Services
- App integrates two or more Google services
- Each service imported in the build.gradle is used in the app.
- If Location is used, the app customizes the user’s experience by using the device's location.
- If Admob is used, the app displays test ads. If Admob was not used, student meets specifications.
- If Analytics is used, the app creates only one analytics instance. If Analytics was not used, student meets specifications.
- If Maps is used, the map provides relevant information to the user. If Maps was not used, student meets specifications.
- If Identity is used, the user’s identity influences some portion of the app. If Identity was not used, student meets specifications.

####Material Design
- App theme extends AppCompat.
- App uses an app bar and associated toolbars.
- App uses standard and simple transitions between activities.

####Building
- App builds from a clean repository checkout with no additional configuration.
- App builds and deploys using the installRelease Gradle task.
- App is equipped with a signing configuration, and the keystore and passwords are included in the repository. Keystore is referred to by a relative path.
- All app dependencies are managed by Gradle.

####Data Persistence
- App implements a ContentProvider to access locally stored data.
- Must implement at least one of the three:

> - If it regularly pulls or sends data to/from a web service or API app updates data in its cache at regular intervals using a SyncAdapter.

> - If it needs to pull or send data to/from a web service or API only once, or on a per request basis (such as a search application), app uses an IntentService to do so.

> - If it performs short duration, on-demand requests (such as search), app uses an AsyncTask.

- App uses a Loader to move its data to its views.

