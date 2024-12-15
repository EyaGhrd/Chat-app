package tn.rnu.eniso.fwk.chatapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.rnu.eniso.fwk.chatapp.entity.Message;
import tn.rnu.eniso.fwk.chatapp.entity.User;
import tn.rnu.eniso.fwk.chatapp.repository.MessageRepository;
import tn.rnu.eniso.fwk.chatapp.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    @Autowired
    private  MessageRepository messageRepository;
    @Autowired
    private  UserRepository userRepository;

    public void sendMessage(Message messageDTO) {
        Message message = new Message();
        message.setSender(messageDTO.getSender());
        message.setReceiver(messageDTO.getReceiver());
        message.setContent(messageDTO.getContent());
        messageRepository.save(message);
    }

    public List<Message> receiveMessages(String receiver) {
        List<Message> messages = messageRepository.findByReceiver_Username(receiver);
        return messages.stream().map(message -> {
            Message dto = new Message();
            dto.setSender(message.getSender());
            dto.setReceiver(message.getReceiver());
            dto.setContent(message.getContent());
            return dto;
        }).collect(Collectors.toList());
    }
    public User createUser(String userName) {
        if (userRepository.findByUsername(userName).isPresent()) {
            throw new RuntimeException("User already exists: " + userName);
        }
        User user = new User();
        user.setUsername(userName);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }
}
