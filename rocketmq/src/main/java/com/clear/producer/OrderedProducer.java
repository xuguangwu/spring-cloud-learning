package com.clear.producer;

import com.clear.Constant;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

/**
 * 类说明
 *
 * @author 栩岛
 * @date 2019-06-03 08:02
 */
public class OrderedProducer {

	public static void main(String[] args) throws Exception {
		//Instantiate with a producer group name.
		MQProducer producer = new DefaultMQProducer("example_group_name");
		((DefaultMQProducer) producer).setNamesrvAddr(Constant.NAME_SERVER);
		//Launch the instance.
		producer.start();
		String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};
		for (int i = 0; i < Constant.MESSAGE_COUNT; i++) {
			int orderId = i % 10;
			//Create a message instance, specifying topic, tag and message body.
			Message msg = new Message("TopicTestjjj", tags[i % tags.length], "KEY" + i,
					("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
			SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
				@Override
				public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
					Integer id = (Integer) arg;
					int index = id % mqs.size();
					return mqs.get(index);
				}
			}, orderId);

			System.out.printf("%s%n", sendResult);
		}
		//server shutdown
		producer.shutdown();
	}
}
