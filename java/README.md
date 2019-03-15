# Lab Setup
We’ll be pushing applications and creating services in Pivotal Cloud Foundry (PCF).  This workshop will use Pivotal Web Services, an instance of PCF managed by Pivotal.

1. Login or sign up for a free Pivotal Web Services account
2. Click the Tools link and
  a. Download and install the CLI matching your operating system
  b. Login using CF CLI (cf login -a api.run.pivotal.io)
3. Clone or Download the git repo
https://github.com/msegvich/pas-dev-workshop.git

## Lab 1 – Push and Scale an Application
### Push
1. Change to application directory
`$ cd java/cf-simple-app`
2. Package the app
`$ ./mvnw clean package`
3. Push the application!
`$ cf push`
4. Ensure the application is in “running” state
5. List all applications
`$ cf apps`
6. Access the application using the URL.

### Scale
1. Scale the application vertically (increase memory)
`$ cf scale simple-java -m 1G`
2. Restart the app and validate the new Memory Limit
3. Scale the application horizontally (increase number of instances)
`$ cf scale simple-java -i 2`
4. Refresh the web browser multiple times and observe the instance index.

### Auto-Scale
Configure AutoScaling
1. Access/Login to Apps Manager at https://run.pivotal.io
2. Navigate to Marketplace.
3. Select App Autoscaler service
4. Select the Standard plan
5. Provide an Instance Name (can be anything you like, e.g. myapp-autoscaler), Add to your existing Space and Bind to App simple-java.  Click Create.
6. Navigate to Services tab and select App Autoscaler service.
7. Select simple-java within bound apps list.
8. Activate Autoscaling using toggle switch and select Manage Autoscaling.
9. Add a scaling rule using HTTP Throughput., e.g.
  a. Scale down if less than 5/s
  b. Scale up if more than 10/s
10. Set minimum of 1 and maximum of 3 instances and Apply Changes.
  a. If you have a load testing tool, you can exercise the autoscaling rule and view the events to confirm, e.g.
  b. `$ ab -n 5000 -c 5 http://simple-java-<your-url>.cfapps.io/`
  c. `$ cf events simple-java`

## Lab 2 – High Availability
1. Identify the GUID of the application
`$ cf app simple-java –guid`
2. Delete an app instance
`$ cf curl /v2/apps/[GUID]/instances/0 -X ‘DELETE’`
3. Observe the platform resurrect the app instance by repeatedly executing
`$ cf app simple-java`

## Lab 3 – Application Observability
1. Navigate to “Logs” tab within the application on Apps Manager (https://run.pivotal.io). The platform aggregates logs from all instances of the application.
2. Navigate to “View in PCF Metrics” from the application view (under Overview tab)
  * Explore metrics for myapp
3. Look at logs from the CLI
`$ cf logs simple-java --recent`
4. Look at events from the CLI
`$ cf events simple-java`

## Lab 4 – Creating and Binding to PCF Services
1. Change to a different lab
`$ cd java/spring-music`
2. View the Marketplace
`$ cf marketplace`
3. Create the MySQL service
`$ cf create-service cleardb spark music-db`
4. Ensure DB service instance is successfully created.
`$ cf services`
5. You can view the service in the Services Tab in Apps Manager
6. Package the Java Application
`$ ./gradlew clean assemble`
7. Now push the Java Application
`$ cf push`
8. Access the application using the url.
9. View the DB instance binding properties in the environment
`$ cf env spring-music`
10. (Optional) Install CF CLI MySQL plugin
https://github.com/andreasf/cf-mysql-plugin
  * Use the plugin to securely connect and view your DB using mysql CLI
`$ cf mysql music-db`
`mysql> show tables`
`mysql> describe album`
`mysql> select artist from album;`
