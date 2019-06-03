package com.clear.producer;

import com.clear.RocketMqUtils;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * 类说明
 *
 * @author 栩岛
 * @date 2019-06-03 07:47
 */
public class AsyncProducer {

	public static void main(String[] args) {
		DefaultMQProducer producer = null;
		try {
			producer = RocketMqUtils.producerInstance();

			producer.start();

			producer.setRetryTimesWhenSendAsyncFailed(0);

			Message message = RocketMqUtils.messageInstance();

			producer.send(message, new SendCallback() {
				@Override
				public void onSuccess(SendResult sendResult) {
					System.out.printf("%s%n", sendResult);
				}

				@Override
				public void onException(Throwable throwable) {
					System.out.printf("%s%n", throwable.getLocalizedMessage());
				}
			});


		} catch (Exception e) {

		} finally {
			producer.shutdown();
		}
	}
}
