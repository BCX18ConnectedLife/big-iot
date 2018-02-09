# BIG IoT Example Code and Documentation

This repository includes documentation and example code for using the BIG IoT Marketplace and SDK.


## Why use BIG IoT?

1. The [BIG IoT Marketplace](https://market.big-iot.org/) gives you: ![logo]()
   * **access to many relevant [data sources]()**
   * **allows you to monetize your data/results** (i.e. this could be part of your business model for your solution)
2. The BIG IoT SDKs allow you to offer or consume data from other stakeholder **with a few lines of code** (see examples below).

**Related technologies:**
- Any data from IoT devicde connected to Bosch IoT Things can be directly offered on the BIG IoT Marketplace using the [Bosch Things-Marketplace Connector service]().
- [FlowHub]() provides a ready to use building block to offer data from IoT devices (including those connected to the Bosch IoT Hub) on the Marketplace.


## BIG IoT Marketplace 

- **Public BIG IoT Marketplace: [https://market.big-iot.org/](https://market.big-iot.org/)**
- **BCX Marketplace: [https://to-be-defined/]()**


## Developer Guide 

**Java Example project for [download](https://github.com/BIG-IoT/example-projects):** This includes both, a BIG IoT Data Provider and Data Consumer project in Java, including all build files to build and run it direclty (`gradle run`) or import it into your IDE.

### How to develop a BIG IoT Consumer?

- **Simple Consumer example** (full example is available [here](https://github.com/BIG-IoT/example-projects/blob/master/more-java-examples/src/main/java/org/eclipse/bigiot/lib/examples/ExampleConsumerSubscriptionById.java)):
```java
// Initialize Consumer with Consumer ID and marketplace URL
Consumer consumer = new Consumer("Your Consumer ID - get it from Marketplace", "https://market.big-iot.org")
       .authenticate("Your Consumer SECRET - get it from Marketplace");

// Subscribe to Offering by OfferingId
Offering offering = consumer.subscribeByOfferingId("OfferingId - find it on Marketplace").get();

// Define Input Data as access parameters
AccessParameters accessParameters = AccessParameters.create();
       // e.g. .addRdfTypeValue("schema:latitude", 42.0)
       //      .addRdfTypeValue("schema:longitude", 9.0);

// Access Offering one-time with Access Parameters (input data) --> response includes JSON results
AccessResponse response = offering.accessOneTime(accessParameters).get();
```
- **[Java Example Consumer](https://github.com/BIG-IoT/example-projects/tree/master/java-example-consumer):** This is part of the example project above. 
- Detailed [Java developer tutorial for a Consumer](https://big-iot.github.io/provPerspective/) application

### How to develop a BIG IoT Provider?

- **Simple Provider example** (full example is available [here](https://github.com/BIG-IoT/example-projects/blob/master/more-java-examples/src/main/java/org/eclipse/bigiot/lib/examples/ExampleProviderWithMarketplaceOfferingDescription.java)):
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
- **[Java Example Provider](https://github.com/BIG-IoT/example-projects/tree/master/java-example-provider):** This is part of the example project above. 
- Detailed [Java developer tutorial for a Provider](https://big-iot.github.io/consumerPerspective/) application

Further Java example applications for consumers and providers are available [here](https://github.com/BIG-IoT/example-projects/tree/master/more-java-examples/src/main/java/org/eclipse/bigiot/lib/examples).

### Download SDK

Information on how to [download](https://big-iot.github.io/download/) and us our SDK is provided [here](https://big-iot.github.io/download/).


## Background Information

Background information and details on the BIG IoT architecture and vision are available [here](https://big-iot.github.io/tutorial/).
