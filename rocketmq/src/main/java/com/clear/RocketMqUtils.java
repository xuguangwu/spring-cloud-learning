package com.clear;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * 类说明
 *
 * @author 栩岛
 * @date 2019-06-03 07:48
 */
public class RocketMqUtils {

	public static DefaultMQProducer producerInstance() {
		DefaultMQProducer producer = new DefaultMQProducer("xudao");
		producer.setNamesrvAddr(Constant.NAME_SERVER);
		return producer;
	}

	public static Message messageInstance() {
		Message message = new Message("XudaoTest", "Hello".getBytes());
		return message;
	}

}
