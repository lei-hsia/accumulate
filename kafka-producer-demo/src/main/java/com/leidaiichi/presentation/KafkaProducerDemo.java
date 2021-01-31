package com.leidaiichi.presentation;

import org.apache.kafka.clients.producer.*;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author lei
 * @date 01/30/2021 4:35 PM
 */
public class KafkaProducerDemo {

    private static final String brokerList = "node1:9092,node2:9093,node3:9094";
    private static final String topic = "SOME";

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(properties);
        int count = 0;

        while (true){
            String msg = "msg_"+ new Date().getTime() + "  " + count++ ;
            ProducerRecord<String, String> producerRecord =
                    new ProducerRecord<String, String>(topic, msg);

            try {
                final int innerCount = count;

                producer.send(producerRecord, new Callback() {
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        System.out.println(innerCount);
                        System.out.print("offset: "+ recordMetadata.offset()+"   ");
                        System.out.print("topic-" + recordMetadata.topic()+"   ");
                        System.out.print("partition: " + recordMetadata.partition());
                        System.out.println();
                    }
                });
            } catch (Exception e){
                e.printStackTrace();
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
