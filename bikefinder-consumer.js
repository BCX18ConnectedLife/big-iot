const bigiot = require('bigiot-js');

process.env.NODE_TLS_REJECT_UNAUTHORIZED = "0";

// Create a new consumer instance with your credentials
const consumer = new bigiot.consumer("... Your Provider ID ...", "... Your Provider Secret ...");

// Authenticate your consumer
consumer.authenticate().then(() => {
	// authentication is done
	console.log("Consumer authentication successful!");
	consumer.subscribe("VMZBerlin-vmzProvider-availablebikesharingservice")
		.then((offering) => {
			console.log("Offering subscription successful!");

        		const accessParameters = {
	   			longitude: 9.2,
	   			latitude: 42.0,
	   			radius: 10000000,
			}
    			consumer.access(offering, accessParameters).then((response) => {

					response.forEach((obj) => {
					    if (obj.freeBikeCount > 0) {
						console.log('---');
						console.log('lon:        ' + obj.longitude);
						console.log('lat:        ' + obj.latitude);
						console.log('free bikes: ' + obj.freeBikeCount);
					    }
					});

    				}); 
			});

	});


