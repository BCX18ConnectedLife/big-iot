# Start-Up Guide for BIG IoT Android Consumer App Developers

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

### 3. Step: Create new Consumer instance

- Click on `MyConsumers` 
- Click on `+Consumer`
- Enter a name for your new Consumer instance (e.g. "SmartCityDashboard")

### 4. Step: Find Offerings

Create a new Offering Query
- Click on `MyConsumers` and select your new Consumer instance
- Click on `+OfferingQuery` and give your OfferingQuery a name
- Define a semantic _Category_ (and if applicable also sub-categories)
- Optionally: define a _Region_, a _Time Period_, a _License_, a _Price_, etc. 
- Save the OfferingQuery
- Scroll down - to the bottom of your OfferingQuery. There you will see all the matching Offerings
  - NOTE: You might need to `refresh` your browser or `reload` the page to get the update
- Explore the Offerings

### 5. Download Android Example Project

`git clone https://github.com/BCX18ConnectedLife/big-iot.git`

### 6. Import the Project into your IDE 

- For Android Studio IDE:
  - Go to `Import project (Gradle, ...)`
  - Select the Android consumer example project directory: `big-iot/AndroidExampleConsumer`
  - Import the project

### 7. Edit the Example Consumer Java application 

- Open the Example Consumer Java source file: `./src/main/java/android/bigiot/org/androidexampleconsumer/MainActivity..java`

- Update the Consumer ID and SECRET

NOTE: You can copy the Consumer ID and SECRET when you open your newly created Consumer instance on the Web Portal, and then click on `Copy ID to Clipboard` and `Load Consumer Secret` followed by `Copy Secret to Clipbard` (see top right).

- Update the `Offering ID` here:
```java
consumer.subscribeByTask("TestOrganization-TestProvider-RandomNumberOffering", this);
```

NOTE: You can copy an Offering ID when you open the Offering on the Web Portal, and then click on `Copy ID to Clipboard` (see top right).

### 8. Run the Android Example Consumer application 

- From Android Studio: Go to `Run` -> `Run` and then select your MainActivity


## Developer Guide 

### How to develop a BIG IoT Consumer for Android?

- A **_detailed_ developer tutorial** for a Consumer can be found [here](https://big-iot.github.io/consumerPerspective/)

- **Simple Android Consumer Example for accessing a known Offering** (full example code is available [here](https://github.com/BCX18ConnectedLife/big-iot/blob/master/AndroidExampleConsumer/app/src/main/java/android/bigiot/org/androidexampleconsumer/MainActivity.java)):
```java
// Initialize Consumer with Consumer ID and marketplace URL
Consumer consumer = new Consumer(CONSUMER_ID, MARKETPLACE_URI);

// Authenticate the consumer instance 
consumer.authenticateByTask(CONSUMER_SECRET, this);
    ...
    // The authenticateByTask() method calls onAuthenticate() upon success or failure. 
    @Override
    public void onAuthenticate(String result) {
        if (result.equals(IAuthenticationHandler.AUTHENTICATION_OK)) {\
        ...
        }
    }
...

// Subscribe to Offering by OfferingId
consumer.subscribeByTask("TestOrganization-TestProvider-RandomNumberOffering", this);
    ...
    // The subscribeByTask() method calls onSubscriptionResponse() upon success or failure. 
    @Override
    public void onSubscriptionResponse(OfferingDescription offeringDescription, OfferingCore offering) {
        if (offering != null) {     
            ...
        }
    }
...

// Define Input Data as access parameters
AccessParameters accessParameters = AccessParameters.create();
       // e.g. .addRdfTypeValue("schema:latitude", 42.0).addRdfTypeValue("schema:longitude", 9.0);

// Access Offering one-time with Access Parameters (input data) --> response includes JSON results
consumer.accessByTask(this.offering, accessParameters, this);
    ...
    // The accessByTask() method calls onAccessResponse() upon success or failure. 
    @Override
    public void onAccessResponse(OfferingCore offeringCore, AccessResponse accessResponse) {
        if (accessResponse != null) {
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(accessResponse.getBody());
        }
    }
...
```

- **To get stated**, you can **use** our [**Android Example Consumer project**](https://github.com/BCX18ConnectedLife/big-iot/tree/master/AndroidExampleConsumer) **as template** for your own project. It is part of the [GitHub example project](https://github.com/BCX18ConnectedLife/big-iot) mentioned above and **contains everything** to get started!


### Download SDK

Information on how to [download](https://big-iot.github.io/download/) and use our SDK directly in your project is provided [here](https://big-iot.github.io/download/).


## Background Information

Background information and details on the BIG IoT architecture and vision are available [here](https://big-iot.github.io/tutorial/).

