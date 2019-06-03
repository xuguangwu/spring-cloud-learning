package com.clear.producer;

import com.clear.RocketMqUtils;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class SyncProducer {

	public static void main(String[] args) {
		DefaultMQProducer producer = null;
		try {
			producer = RocketMqUtils.producerInstance();
			producer.start();

			Message message = RocketMqUtils.messageInstance();

			SendResult sendResult = producer.send(message);
			System.out.printf("%s%n", sendResult);

		} catch (MQClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.shutdown();
		}
	}
}
