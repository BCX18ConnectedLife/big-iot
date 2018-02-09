# Start-Up Guide for BIG IoT Provider Developers

## Step-by-step Instructions

### 1. Step: Login on Marketplace

- Select the BIG IoT Marketplace you want to use:
  - Public BIG IoT Marketplace URI: [https://market.big-iot.org/](https://market.big-iot.org/)
  - BCX Marketplace URI: [https://market.internal-big-iot.org/](https://market.internal-big-iot.org/)
- Click on `Login`
- You can use your GitHub or Google account to sign in

### 2. Step: Create new Organization on Marketplace

- The first time you login on the Marketplace with your account, you can create a new Organization 
- Click on `New Organization` (see bottom left)
- Enter a name for your new Organization

### 3. Step: Create new Provider instance

- Click on `MyProviders` 
- Click on `+Provider`
- Enter a name for your new Provider instance (e.g. "ParkingSpaceDataProvider")

### 4. Step: Create a new Offering Description

- Click on `MyProviders` and select your new Provider instance
- Click on `+Offering` and give your Offering a name
- Define a _Region_ (i.e. bounding box) for your data
  - NOTE: If the data are not associated with a geographic area, you can also leave the region empty
- Select a semantic _Category_ for the data you want to offer (try to use also sub-categories if available)
  - NOTE: If none of the semantic categories match, use the category `Miscellaneous`
- Define the _Input_ and _Output Data_ for your Offering
  - Input data are those that a Consumer, who accesses your Offering, will add to the access requst. These are usually used to filter/restrict the output data on your Provider end
  - Output data are those that your Provider will return when a Consumer accesses the Offering
  - Click on the button `+` and then select from the drop-down box a semantic `Data Type` for the output data
    - NOTE: If none of the recommended types match your needs, you can also propose a new data type; simply enter the name of the new proposed type. Once a new name is detected, the marketplace will also ask for an URI as a unique identify of your type
  - After you have defined the data type, you need to provide the JSON property name that your Provider will use in the JSON  response sent upon an access request 
  - Repeat these steps until you have defined all Input and Output data fields.
- Select a _License Type_ for your Offering  
- Optionally: define a _Time Period_, a _Price_, etc. 
- Save the Offering

### 5. Download Java Template Project

`git clone https://github.com/BCX18ConnectedLife/big-iot.git`

### 6. Import the Project into your IDE 

- For Eclipse IDE:
  - Go to `File` -> `Import` -> `Gradle Project`
    - NOTE: If you don't see the Gradle Project import option, you first have to install the Eclipse gradle tooling (`buildship`) - see [here](http://www.vogella.com/tutorials/EclipseGradle/article.html) for further information
  - Select the **Provider template** project directory: `big-iot/java-template-provider`
  - Import the project
  
### 7. Update Properties Files 

Update your Provider ID and Secret in the `example.properties` file (see root directory of the template project)

NOTE: You can copy the Provider ID and SECRET when you open your newly created Provider instance on the Web Portal, and then click on `Copy ID to Clipboard` and `Load Consumer Secret` followed by `Copy Secret to Clipbard` (see top right).

### 8. Edit the Example Provider Java application 

- Open the Example Provider Java source file: `./src/main/java/org/bigiot/examples/ExampleProvider.java` (see [here](https://github.com/BCX18ConnectedLife/big-iot/blob/master/java-template-provider/src/main/java/org/bigiot/examples/ExampleProvider.java))
- Update the `Offering ID` here:
```java
RegistrableOfferingDescription offeringDescription = 
	            provider.createOfferingDescriptionFromOfferingId("... include your Offering ID here ...");
```
- Update the `AccessRequestHandler accessCallback` based on your Offering. In the response, return a JSONArray or JSONObject that is conform to your offering description.
```java
return BigIotHttpResponse.okay().withBody(jsonArray).asJsonType();
```

NOTE: You can copy the Offering ID when you open the Offering on the Web Portal, and then click on `Copy ID to Clipboard` (see top right).

### 9. Run the Provider application 

- From the command line: Use `./gradlew run` in the root directoy of the project
- From Eclipse IDE: Go to `Run` -> `Run` and then select your provider application

### 10. Test your Provider and Offering

- Select your Provider instance and then Offering on the Web Portal. If the Provider is working correctly, the Offering will be activated (see `Activation` on the bottom)
- You can also check if your Provider runs by accessing the Offering endpoint using a Web browser:
```
https://localhost:9123/bigiot/access/<YOUR OFFERING ID>
```

## Developer Guide 

### How to develop a BIG IoT Provider?

- A **_detailed_ Java developer tutorial** for a Provider can be found [here](https://big-iot.github.io/providerPerspective/)

- **Simple Provider example - register a new Offering on the Marketplace** (full example code is available [here](https://github.com/BIG-IoT/example-projects/blob/master/more-java-examples/src/main/java/org/eclipse/bigiot/lib/examples/ExampleProviderWithMarketplaceOfferingDescription.java)):
```java
// Initialize provider with provider id and Marketplace URI
ProviderSpark provider = ProviderSpark.create("Your Provider ID - get it from Marketplace", 
                                              "https://market.big-iot.org", "IP address of your node", 6789)
        .authenticate("Your Provider SECRET - get it from Marketplace");

// Create an Offering Description based on a stored descripton on the Marketplace
RegistrableOfferingDescription offeringDescription = 
        provider.createOfferingDescriptionFromOfferingId("Your OfferingId - get it from Marketplace");

// Define an Endpoint for your Offering
Endpoints endpoints = Endpoints.create(offeringDescription)
        // provide an AccessRequestHandler - it is called each time a Consmer accesses your offering
        .withAccessRequestHandler(accessCallback);

// Register the offering - from now on it will be discoverable, subscribable and accessible to consumers
provider.register(offeringDescription, endpoints);
```
- **To get stated**, you can **use** our [**Java Example Provider project**](https://github.com/BCX18ConnectedLife/big-iot/tree/master/java-template-provider) **as template** for your own project. It is part of the [GitHub example project](https://github.com/BCX18ConnectedLife/big-iot) mentioned above and **contains everything** to get started!

Further Java example applications for consumers and providers are available [here](https://github.com/BIG-IoT/example-projects/tree/master/more-java-examples/src/main/java/org/eclipse/bigiot/lib/examples).

### Download SDK

Information on how to [download](https://big-iot.github.io/download/) and use our SDK directly in your porject is provided [here](https://big-iot.github.io/download/).


## Background Information

Background information and details on the BIG IoT architecture and vision are available [here](https://big-iot.github.io/tutorial/).
