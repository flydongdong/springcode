package com.fly.dd.mycode.common.activemq.comsumer.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 队列消息监听器
 *
 * @author zhuyd
 * @version $Revision: 1.0 $, $Date: 2016年12月21日 下午3:49:46 $
 */
//@Component("testAmqQueueReceiver")
public class AmqQueueReceiver implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("QueueReceiver1接收到消息:" + ((TextMessage) message).getText());
        }
        catch (JMSException e) {
            e.printStackTrace();
        }
    }



}
