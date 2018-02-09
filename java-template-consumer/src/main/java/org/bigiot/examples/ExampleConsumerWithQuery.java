/*
 *	Copyright (c) 2017 by Contributors of the BIG IoT Project Consortium (see below).
 *	All rights reserved. 
 *		
 *	This source code is licensed under the MIT license found in the
 * 	LICENSE file in the root directory of this source tree.
 *		
 *	Contributor:
 *	- Robert Bosch GmbH 
 *	    > Stefan Schmid (stefan.schmid@bosch.com)
 */

package org.bigiot.examples;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import org.eclipse.bigiot.lib.Consumer;
import org.eclipse.bigiot.lib.exceptions.AccessToNonActivatedOfferingException;
import org.eclipse.bigiot.lib.exceptions.AccessToNonSubscribedOfferingException;
import org.eclipse.bigiot.lib.feed.AccessFeed;
import org.eclipse.bigiot.lib.misc.BridgeIotProperties;
import org.eclipse.bigiot.lib.offering.AccessParameters;
import org.eclipse.bigiot.lib.offering.AccessResponse;
import org.eclipse.bigiot.lib.offering.Offering;
import org.eclipse.bigiot.lib.offering.SubscribableOfferingDescription;

import org.joda.time.Duration;

public class ExampleConsumerWithQuery {
		
	public static void main(String args[]) throws InterruptedException, ExecutionException, IOException, AccessToNonSubscribedOfferingException, AccessToNonActivatedOfferingException {
	    
	    // Load example properties file
        BridgeIotProperties prop = BridgeIotProperties.load("example.properties");

		// Initialize consumer with Consumer ID and Marketplace URL
		Consumer consumer = Consumer.create(prop.CONSUMER_ID, prop.MARKETPLACE_URI)
		                            .authenticate(prop.CONSUMER_SECRET);
		
		// Discover matching offerings (by Query ID) based on a pre-defined Query on the Marketplace 
        List<SubscribableOfferingDescription> offeringDescriptionList = 
                consumer.discoverById("TestOrganization-TestConsumer-RandomNumberQuery").get();

        if (offeringDescriptionList.isEmpty()) {
            System.out.println("Couldn't find any matching offering that is active!");
            System.exit(1);
        }
        
        // Select 1st offering in list
        SubscribableOfferingDescription offeringDescription = offeringDescriptionList.get(0);
       
        // Subscribe to the selected offering
        Offering offering = offeringDescription.subscribe().get();
			
		// Check if offering exists
		if (offering != null) {
		
    		// Define Input Data as access parameters
    		AccessParameters accessParameters = AccessParameters.create();
    		       // e.g. .addRdfTypeValue("schema:latitude", 42.0).addRdfTypeValue("schema:longitude", 9.0);
    
    	// EXAMPLE 1: ONE-TIME ACCESS to the Offering
    		// Access Offering one-time with Access Parameters (input data) --> response includes JSON results
    		AccessResponse response = offering.accessOneTime(accessParameters).get();
            System.out.println("Received data: " + response.asJsonNode().toString());
                       
        // EXAMPLE 2: CONTINUOUS ACCESS to the Offering 
            // Create an Access Feed with callbacks for the received results		
    		Duration feedDuration = Duration.standardHours(2);
    		Duration feedInterval = Duration.standardSeconds(2);
    		AccessFeed accessFeed = offering.accessContinuous(accessParameters, 
    									feedDuration.getMillis(), 
    									feedInterval.getMillis(), 
    									(f,r) -> {
    										System.out.println("Received data: " + r.asJsonNode().toString());
    									},
    									(f,r) -> {
    										System.out.println("Feed operation failed");
    										f.stop();
    									});
    			
    		// Run until user presses the ENTER key
    		System.out.println(">>>>>>  Terminate ExampleConsumer by pressing ENTER  <<<<<<");
    		Scanner keyboard = new Scanner(System.in);
    		keyboard.nextLine();
    
    		// Stop Access Feed
    		accessFeed.stop();
    		
    		// Unsubscribe the Offering
    		offering.unsubscribe();	
		}
		else {
	        System.out.println("Offering not found or not active!!!");
		}
			
		// Terminate consumer instance
		consumer.terminate();
			
	}

}
