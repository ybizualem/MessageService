package com.example.messageservice.Service;

import com.example.messageservice.Entries.Message;

import java.util.List;

public interface services {
    Message findById(Integer id);
    List<Message> findAll();
    Message updateMessage(Integer id, Message messageDetails);
    boolean deleteById(Integer id);
}
