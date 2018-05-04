# iot-kafka-producer
Java Kafka producer for generated IoT data

IoT data should be produced in the following format:

{
  “data”: {
            “deviceId”: “67ff9ef1-a7e7-4730-9ff0-8acf4418d8ed",
            “temperature”: 1,
            “location”: {
                          “latitude”: “1.0000000000000”,
                          “longitude”: “1.0000000000000”
                        },
            “time”: “2618884342”
           }
}
