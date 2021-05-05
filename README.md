# FitPlus

This mobile android application which is related to fitness developed using java language. Users can view the locations of gyms, check their BMI, watch different kind of exercise videos, know about the fitness events that are going to happen. For using this application users has to Sign up using their email address. After that user can login into application and access all the features.


Functionalities:

Firestore database: We have used firestore database for user to sign up and for storing the events information that will be shown to the user.
Google map api: Google map api iis used for displaying the various locations of the gyms.
Web View: Web view is used for calculating the BMI ratio of the user.As the user will click on the BMI icon, the application will direct the user to the link "https://www.diabetes.ca/managing-my-diabetes/tools---resources/body-mass-index-(bmi)-calculator" where users can enter their information and get to know their BMI.
Json rest API: We have creaed a json file with videos and images url. Fitness videos links are placed in json file.For displaying the videos we have implemented recycler view in the application.
Shared preferences: Shared preferences are used in login activity, so that users need not to enter their login credentials again and again.
