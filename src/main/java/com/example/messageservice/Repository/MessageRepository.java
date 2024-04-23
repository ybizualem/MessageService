package com.example.messageservice.Repository;
import org.springframework.data.repository.CrudRepository;
import com.example.messageservice.Entries.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message,Integer> {
    List<Message> findBySenderOrReceiver(String sender, String receiver);

}