# Start-Up Guide for BIG IoT Consumer Developers

## Step-by-step Instructions

### 1. Step: Login on Marketplace

- **Public BIG IoT Marketplace URI: [https://market.big-iot.org/](https://market.big-iot.org/)**
- **BCX Marketplace URI: [https://market.internal-big-iot.org/](https://market.internal-big-iot.org/)**

- Click on `Login`
- You can use your GitHub or Google account to sign in

### 2. Step: Create new Organization on Marketplace

- The first time you login on the Marketplace with your account, you can create a new Organization 
- Click on `New Organization` (bottom left)
- Enter a name for your new Organization

### 3. Step: Create new Consumer instance

- Click on `MyConsumers` 
- Click on `+Consumer`
- Enter a name for your new Consumer instance (e.g. "SmartCityDashboard")

NOTE: You can copy the Consumer ID and SECRET by when you click on the new Consumer instance and then `Copy ID to Clipboard` and `Load Consumer Secret` followed by `Copy Secret to Clipbard`)

### 4. Step: Find Offerings

Create a new Offering Query
- Click on `MyConsumers`
- Select your new Consumer instance
- Click on `+OfferingQuery`
- Give you OfferingQuery a name
- Define a semantic category
- Optional: define a region, a time period, a license, a price model, etc. 
- Save the OfferingQuery
- Scroll down - on the bottom you will see matching Offerings for your query
  - Note: You might need to `refresh` your browser or `reload` the page to get the update
- Explore the Offerings

### 5. Download Java Template Project

`git clone https://github.com/BCX18ConnectedLife/big-iot.git`

### 6. Import the Project into your IDE 

- Eclipse IDE:
  - File --> Imprt --> Gradle Project 
    NOTE: If you don't see the Gradle Project import option, you first have to install the Eclipse grade tooling (`buildship`) - see [here](http://www.vogella.com/tutorials/EclipseGradle/article.html)
  - Select the consumer template project directory: `big-iot/java-template-consumer`
  - Import the project
  
### 7. Update Properties Files 

- Update your Consumer ID and Secret in the `example.properties` file (see route directory of the template project)

### 8. Edit the Example Consumer Java application 

- Open the Example Consumer Java source file: `./src/main/java/org/bigiot/examples/ExampleConsumer.java`
- Update the `Offering ID` here:
```java
consumer.subscribeByOfferingId("... include the Offering ID that you want to subscribe to and access ...").get();
```

### 9. Execute the Consumer application 

 - From the command line: `./gradlew run` from the root directoy of the project
 - From the IDE 


## Developer Guide 

### How to develop a BIG IoT Consumer?

- **Simple Consumer example for accessing a known Offering** (full example code is available [here](https://github.com/BIG-IoT/example-projects/blob/master/more-java-examples/src/main/java/org/eclipse/bigiot/lib/examples/ExampleConsumerSubscriptionById.java)):
```java
// Initialize Consumer with Consumer ID and marketplace URL
Consumer consumer = new Consumer("Your Consumer ID - get it from Marketplace", "https://market.big-iot.org")
       .authenticate("Your Consumer SECRET - get it from Marketplace");

// Subscribe to Offering by OfferingId
Offering offering = consumer.subscribeByOfferingId("OfferingId - find it on Marketplace").get();

// Define Input Data as access parameters
AccessParameters accessParameters = AccessParameters.create();
       // e.g. .addRdfTypeValue("schema:latitude", 42.0).addRdfTypeValue("schema:longitude", 9.0);

// Access Offering one-time with Access Parameters (input data) --> response includes JSON results
AccessResponse response = offering.accessOneTime(accessParameters).get();
```

- A _detailed_ Java **developer tutorial** for a Consumer can be found [here](https://big-iot.github.io/consumerPerspective/)

- **To get stated**, you can **use** our [**Java Example Consumer project**](https://github.com/BCX18ConnectedLife/big-iot/tree/master/java-template-consumer) **as template** for your own project. It is part of the [GitHub example project](https://github.com/BCX18ConnectedLife/big-iot) mentioned above and **contains everything** to get started!


### Download SDK

Information on how to [download](https://big-iot.github.io/download/) and use our SDK directly in your porject is provided [here](https://big-iot.github.io/download/).


## Background Information

Background information and details on the BIG IoT architecture and vision are available [here](https://big-iot.github.io/tutorial/).
