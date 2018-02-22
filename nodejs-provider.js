const bigiot = require('bigiot-js');
const provider = new bigiot.provider("... Your Provider ID ...", "... Your Provider Secret ...");

function createOffering() {

        // Instantiate an offering of the desired type
        const offering = new bigiot.offering("TestOffering", "urn:proposed:Miscellaneous");

        // Define the HTTP endpoint consumers should call on your service
        offering.endpoints = {
          uri: 'http://example.net/some/path',
        };

        // Define the geographical extent of your offering
        offering.extent = {
          city: 'Station, Berlin',
        };

        // Define the input parameters your offering accepts, if any
        offering.inputData = [
          {
            name: 'latitude',
            rdfUri: 'http://schema.org/latitude',
          },
          {
            name: 'longitude',
            rdfUri: 'http://schema.org/longitude',
          },
        ]

        // Define the data structure your offering returns when called
        offering.outputData = [
          {
            name: "temperature",
            rdfUri: 'http://schema.org/airTemperatureValue',
          }
        ];

        offering.price = {
                pricingModel: 'PER_ACCESS',
                money: {
                        amount: 0.001,
                        currency: 'EUR',
                },
        };

        return offering;
}

provider.authenticate()
  .then(createOffering)
        .then((offering) => {
                provider.register(offering)
                        .then(() => {
                                console.log(`Offering ${offering.id} registered`);
                                const interval = setInterval(() => provider.register(offering), 30000);
                                // clearInterval(interval);
                        });
        });


