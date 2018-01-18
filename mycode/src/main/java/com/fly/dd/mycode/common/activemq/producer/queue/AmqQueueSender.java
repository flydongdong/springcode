package com.fly.dd.mycode.common.activemq.producer.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * 队列消息生产者，发送消息到队列
 *
 * @author zhuyd
 * @version $Revision: 1.0 $, $Date: 2016年12月21日 下午3:29:28 $
 */
//@Component
public class AmqQueueSender {

    @Autowired
    @Qualifier("jmsQueueTemplate")
    private JmsTemplate jmsTemplate;

    /**
     * 发送一条消息到指定的队列（目标）
     *
     * @param queueName
     *            队列名称
     * @param message
     *            消息内容
     */
    public void send(String queueName, final String message) {

        jmsTemplate.send(queueName, new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }

}
