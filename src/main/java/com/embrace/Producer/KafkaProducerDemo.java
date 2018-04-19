package com.embrace.Producer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * Kafka 生产者示例
 * 
 * @author xiaoxu
 *
 */
public class KafkaProducerDemo extends Thread {
	private Producer<String, String> producer = null;
	private String topic;
	private Properties props = new Properties();
	private final int SLEEP = 1000 * 3;

	public KafkaProducerDemo(String topic) {
		Properties prop = new Properties();
		InputStream is;
		try {
			is = new FileInputStream("src/main/resources/kafka.properties");
			prop.load(is);
			props.put("serializer.class", "kafka.serializer.StringEncoder");
			props.put("bootstrap.servers", prop.get("BROKER_LIST"));
			props.put("acks", "all");
			props.put("retries", 0);
			props.put("batch.size", 16384);
			props.put("linger.ms", 1);
			props.put("buffer.memory", 33554432);
			props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
			props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
			producer = new KafkaProducer<String, String>(props);
			this.topic = topic;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		int offsetNo = 1;
		boolean flag = true;
		while (flag) {
			String msg = new String("Message_" + offsetNo);
			System.out.println("Send->[" + msg + "]");
			producer.send(new ProducerRecord<String, String>(topic, String.valueOf(offsetNo), msg));
			offsetNo++;
			if (offsetNo == 210) {
				flag = false;
			}
			try {
				sleep(SLEEP);
			} catch (Exception ex) {
				producer.close();
				ex.printStackTrace();
			}
		}
		producer.close();
	}
}
