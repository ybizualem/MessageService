package com.example.messageservice.Service;
import com.example.messageservice.Repository.MessageRepository;
import com.example.messageservice.Entries.Message;
import com.pusher.rest.Pusher;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PusherService implements services{
    private static final Logger logger = LoggerFactory.getLogger(PusherService.class);
    private final MessageRepository messageRepository;
    private final Pusher pusher;
    private final ObjectMapper objectMapper;

    public PusherService(MessageRepository messageRepository, ObjectMapper objectMapper) {
        this.messageRepository = messageRepository;
        this.objectMapper = objectMapper;
        String app_id = "1784918";
        String key = "2d21e5ca31cdaee85787";
        String secret = "e7ff0b60aff91316a91e";
        String cluster = "us2";
        this.pusher = new Pusher(app_id, key, secret);
        pusher.setCluster(cluster);
        pusher.setEncrypted(true);
    }

    public Message saveAndSend(Message message) {
        // Convert the message object to JSON and send it through Pusher
        try {
            String messageJson = objectMapper.writeValueAsString(message);
            logger.info("Sending message through Pusher: {}", message);
            pusher.trigger("my-channel", "my-event", Collections.singletonMap("message", messageJson));
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize message", e);
        }
        // Save the message to the database
        logger.info("Saving message to database: {}", message);
        message = messageRepository.save(message);
        return message;
    }
    @Override
    public Message findById(Integer id) {
        Optional<Message> result = messageRepository.findById(id);
        return result.orElseThrow(() -> new RuntimeException("Message not found with id: " + id));
    }

    public List<Message> findMessagesForUser(String userId) {
        return messageRepository.findBySenderOrReceiver(userId, userId);
    }

    @Override
    public List<Message> findAll() {
        return (List<Message>) messageRepository.findAll();
    }

    @Override
    @Transactional
    public Message updateMessage(Integer id, Message messageDetails) {
        Message existingMessage = findById(id);
        existingMessage.setContent(messageDetails.getContent());
        existingMessage.setSender(messageDetails.getSender());
        existingMessage.setReceiver(messageDetails.getReceiver());
        return messageRepository.save(existingMessage);
    }

    @Override
    public boolean deleteById(Integer id) {
        messageRepository.deleteById(id);
        return false;
    }
}
