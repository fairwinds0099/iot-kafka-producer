package com.challenge.app.producer.production;

import com.challenge.app.producer.dto.IoTDeviceMessage;
import com.challenge.app.producer.utilities.ConfigurationReader;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;


public class IoTDataProducer {

    private static final Logger logger = Logger.getLogger(IoTDataProducer.class);

    public static void main(String[] args) throws Exception {

        //read config file
        Properties config = ConfigurationReader.readPropertyFile();
        final String bootstrapServer = config.getProperty("com.challenge.app.producer.bootstrapserver");
        final String topic = config.getProperty("com.challenge.app.producer.topic");
        final String keySerializer = config.getProperty("com.challenge.app.producer.keyserializer");
        final String valueSerializer = config.getProperty("com.challenge.app.producer.valueserializer");

        logger.info("Bootstrap server=" + bootstrapServer + " | Topic=" + topic +
                        " | Key Serializer=" + keySerializer + " | Value Serializer=" + valueSerializer);

        //producer properties
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer); //props.put("metadata.broker.list", brokerList);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,valueSerializer);

        //TODO add code to handle connection / reconnect attempts / failure

        KafkaProducer producer = new KafkaProducer(props);

        String key = "simulation";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //non blocking separate thread for console input
        new Thread(() -> {
            try {
                Thread.sleep(10_000);
                if(!(br.readLine().isEmpty())){
                    System.exit(0);}
            } catch (InterruptedException|IOException e) {
                logger.error("Input thread exception: " + e.toString());
            }
        }).start();

        System.out.print("Sending messages - press any key to quit:");

        do {
            IoTDeviceMessage message = generateMessage();

            if (message != null) {
                ProducerRecord<String, IoTDeviceMessage> record = new ProducerRecord<>(topic, key, message);
                producer.send(record);
                Thread.sleep(1000);
            }

        } while (true);
    }

    private static IoTDeviceMessage generateMessage() {
        try {
            //List of 3 Generated UUIDs
            List<String> deviceIds = Arrays.asList("67ff9ef1-a7e7-4730-9ff0-8acf4418d8ed", "8ba4ac7f-1025-4456-b818-a0e76895651b", "edbe46c4-aa7f-4acd-8f07-09b280c85c55");
            //pick 1 of 3 devices to send message
            int deviceNum = ThreadLocalRandom.current().nextInt(0, 3);
            long unixTime = Instant.now().getEpochSecond();
            //generate a temp between -150C and 150C
            int temp = ThreadLocalRandom.current().nextInt(-150, 151);
            //generate latitude & longitude
            double lat = ThreadLocalRandom.current().nextDouble(-90.0, 90.0);
            double lon = ThreadLocalRandom.current().nextDouble(-180.0, 180.0);

            IoTDeviceMessage message = new IoTDeviceMessage();
            IoTDeviceMessage.Data data = new IoTDeviceMessage.Data();
            IoTDeviceMessage.Data.Location loc = new IoTDeviceMessage.Data.Location();
            loc.setLatitude(lat);
            loc.setLongitude(lon);
            data.setDeviceId(deviceIds.get(deviceNum));
            data.setTemperature(temp);
            data.setLocation(loc);
            data.setTime(unixTime);
            message.setData(data);
            return message;
        }
        catch(Exception e){
            logger.error("ERROR:  generateEventData | " + e.toString());
            return null;
        }
    }
}
