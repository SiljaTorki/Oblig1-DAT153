# Oblig1-DAT153
The Name Quiz app

Activities:

Main Activity - Home page where user can navigate to the other activities.

Quiz Activity - The app randomly selects a photo from the database, and shows it on the                   screen. The user to enter the name of the person on the photo, and submit                 the name to the app. After submission, the app indicates if the name was                 correct or not.

Database Activity - Shows all names & pictures in the quiz app, it is alsow poisble to                         delete an add a new object (cat).

Add Activity -  The user can add a new photo and the associated name to the quiz.


Write test-cases using Espresso for your app! At least have the following test cases: 

* clicking a button in the main-menu takes you to the right activity (testing one button is enough);

* is the score updated correctly in the quiz (submit right/wrong answer and check if the score is correct afterwards);

* a test that checks that the number of registered pictures/persons is correct after adding/deleting an entry. 

Use Gradle either from the command line or through Android Studio's Gradle tool window to install and run the test-cases, 
so that we can closely observe the steps that are necessary. 

Which APKs are used during testing? 

- classes.dex
- clases2.dex
- AndroidManifest.xml
- resources.arsc

What are their contents?

The DEX file contains references to any classes or methods used within the app. Essentially, any Activity, Object or Fragment used in the codebase will be transformed into bytes that can be run as an Android app.

AndroidManifest.xml contains essential information about the app to the Android build tools, the Android operating system and Google Play. The AndroidManifest.xml declerares following:
- The app's package name, which usually matches the code's namespace.
- The Android build tools use this to determine the location of code entities when building our project.
- The Gradle build files, which is used as the unique app identifier on the system and on Google Play.
- The components of the app, which include all activities, services, broadcast receivers, and content providers. 
- The permissions that the app needs in order to access protected parts of the system or other apps. 
- The hardware and software features the app requires, which affects which devices can install the app from Google Play.

resources.arsc allows us to see he configuration-specific values such as language translations for a string resource. But in our case it is empty.
