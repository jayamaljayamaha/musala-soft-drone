How to run the API

I have added a docker file to the project, so only you need to run the docker demon in your machine in order to run this,
nothing more. There are some ways to run the application

You can run below command in order to create the image (Should run command within the project folder)
./gradlew clean build
docker build -t musala-drone-app .
docker run -p 8080:8080 musala-drone-app


You can run it as a normal java project via your IDE, just run the main class via IDE

You can run via gradle by executing following gradle wrapper command
gradlew bootRun

Now the service is running on your machine on port 8080, I have added the swagger so after you start the application
you can access the swagger API doc from following link
http://localhost:8080/swagger-ui.html