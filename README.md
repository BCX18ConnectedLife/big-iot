# BIG IoT Documentation, Startup Guide and Example Code


## Why use BIG IoT?

1. The [BIG IoT Marketplace](https://market.big-iot.org/) gives you:
   * **easy access to many relevant data sources - see [here](https://github.com/BIG-IoT/BIG-IoT.github.io/blob/master/bcxDataOfferings.md)**
   * **allows you to monetize your data/results** (i.e. this could be part of your business model for your solution)
      * Disclaimer: Charging and billing functionality is not yet implemented :smiley:
2. The BIG IoT SDKs allow you to **offer and consume data** from other stakeholder **with a few lines of code** (see examples below).

### Overall Picture

![BIG-IoT Overall Picture](/images/BIG-IoT_OverallPicture.png)

### Related technologies:

- Any data from IoT devicde connected to Bosch IoT Things can be directly offered on the BIG IoT Marketplace using the [Bosch Things-Marketplace Connector service]().
- [FlowHub]() provides a ready to use building block to offer data from IoT devices (including those connected to the Bosch IoT Hub) on the Marketplace.


## BIG IoT Marketplace 

- **Public BIG IoT Marketplace** URI: [https://market.big-iot.org/](https://market.big-iot.org/)
- **BCX Marketplace** URI: [https://market.internal-big-iot.org/](https://market.internal-big-iot.org/)


## Startup Guides (with step-by-step instructions)

- For a BIG IoT [**Consumer application** (Java)](/StartUp-Guide-Consumer.md)
- For a BIG IoT [**Provider application** (Java)](/StartUp-Guide-Provider.md)


## Developer Guide 

**Java Example project for [download](https://github.com/BIG-IoT/example-projects):** This includes both, a BIG IoT Data Provider and Data Consumer project in Java, including all build files to build and run it direclty (`gradlew run`) or import it into your IDE.

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
- **To get stated**, you can **use** our [**Java Example Consumer project**](https://github.com/BCX18ConnectedLife/big-iot/tree/master/java-template-consumer) **as template** for your own project. It is part of the [GitHub example project](https://github.com/BCX18ConnectedLife/big-iot) mentioned above and **contains everything** to get started!
- A _detailed_ Java **developer tutorial** for a Consumer can be found [here](https://big-iot.github.io/consumerPerspective/)


### How to develop a BIG IoT Provider?

- **Simple Provider example - register a new Offering on the Marketplace** (full example code is available [here](https://github.com/BIG-IoT/example-projects/blob/master/more-java-examples/src/main/java/org/eclipse/bigiot/lib/examples/ExampleProviderWithMarketplaceOfferingDescription.java)):
```java
// Initialize provider with provider id and Marketplace URI
ProviderSpark provider = ProviderSpark.create("Your Provider ID - get it from Marketplace", 
                                              "https://market.big-iot.org", "IP address of your node", 6789)
        .authenticate("Your Consumer SECRET - get it from Marketplace");

// Create an Offering Description based on a stored descripton on the Marketplace
RegistrableOfferingDescription offeringDescription = 
        provider.createOfferingDescriptionFromOfferingId("OfferingId - get it from Marketplace");

// Define an Endpoint for your Offering
Endpoints endpoints = Endpoints.create(offeringDescription)
        // provide an AccessRequestHandler - it is called each time a Consmer accesses your offering
        .withAccessRequestHandler(accessCallback);

// Register the offering - from now on it will be discoverable, subscribable and accessible to consumers
provider.register(offeringDescription, endpoints);
```
- **To get stated**, you can **use** our [**Java Example Provider project**](https://github.com/BCX18ConnectedLife/big-iot/tree/master/java-template-provider) **as template** for your own project. It is part of the [GitHub example project](https://github.com/BCX18ConnectedLife/big-iot) mentioned above and **contains everything** to get started!
- A _detailed_ Java **developer tutorial** for a Provider can be found [here](https://big-iot.github.io/providerPerspective/)

Further Java example applications for consumers and providers are available [here](https://github.com/BIG-IoT/example-projects/tree/master/more-java-examples/src/main/java/org/eclipse/bigiot/lib/examples).

### Download SDK

Information on how to [download](https://big-iot.github.io/download/) and use our SDK directly in your porject is provided [here](https://big-iot.github.io/download/).


## Background Information

Background information and details on the BIG IoT architecture and vision are available [here](https://big-iot.github.io/tutorial/).
