package com.kafkaTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.embrace.Consumer.KafkaConsumerDemo;

public class KafkaClientConsumerTest {
	public static void main(String[] args) throws IOException {

		Properties prop = new Properties();
		InputStream is = new FileInputStream("src/main/resources/kafka.properties");
		prop.load(is);

		KafkaConsumerDemo con = new KafkaConsumerDemo(prop.getProperty("TOPIC"));
		con.start();
	}
}
