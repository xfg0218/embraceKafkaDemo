package com.kafkaTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestKafka {
	public static void main(String[] args) throws IOException {
		Properties prop = new Properties();
		InputStream is = new FileInputStream("src/main/resources/kafka.properties");
		prop.load(is);
		System.out.println(prop.getProperty("ZK.CONNECT"));
	}
}
