package com.clear.producer;

import com.clear.RocketMqUtils;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * 单向（Oneway）发送特点为只负责发送消息，不等待服务器回应且没有回调函数触发，
 * 即只发送请求不等待应答。此方式发送消息的过程耗时非常短，一般在微秒级别。
 *
 * @author 栩岛
 * @date 2019-06-03 07:57
 */
public class OnewayProducer {


	public static void main(String[] args) {

		DefaultMQProducer defaultMQProducer = null;
		try {
			defaultMQProducer = RocketMqUtils.producerInstance();
			defaultMQProducer.start();
			Message message = RocketMqUtils.messageInstance();
			defaultMQProducer.sendOneway(message);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			defaultMQProducer.shutdown();
		}


	}

}
