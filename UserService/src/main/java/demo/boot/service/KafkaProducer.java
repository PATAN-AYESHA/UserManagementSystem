package demo.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import demo.boot.model.User;

@Service
public class KafkaProducer {

    @Autowired
    KafkaTemplate<List<User>, List<User>> kafkaTemplate;
    public void sendMsgToTopic(List<User> user) {
        kafkaTemplate.send("codeDecode_Topic", user);
        
    }
}
