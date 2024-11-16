package tn.rnu.eniso.fwk.chatapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.rnu.eniso.fwk.chatapp.dal.MessageDao;
import tn.rnu.eniso.fwk.chatapp.entity.Message;
import tn.rnu.eniso.fwk.chatapp.entity.User;
import tn.rnu.eniso.fwk.chatapp.model.MessageDTO;
import tn.rnu.eniso.fwk.chatapp.repository.MessageRepository;
import tn.rnu.eniso.fwk.chatapp.repository.UserRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ChatService {

    @Autowired
    private final MessageRepository messageRepository;
    @Autowired
    private final UserRepository userRepository;


    public ChatService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public void sendMessage(MessageDTO messageDTO) {
        Message message = new Message();
        message.setSender(messageDTO.getSender());
        message.setReceiver(messageDTO.getReceiver());
        message.setContent(messageDTO.getMessage());
        messageRepository.save(message);
    }

    public List<MessageDTO> receiveMessages(String receiver) {
        List<Message> messages = messageRepository.findByReceiver(receiver);
        return messages.stream().map(message -> {
            MessageDTO dto = new MessageDTO();
            dto.setSender(message.getSender());
            dto.setReceiver(message.getReceiver());
            dto.setMessage(message.getContent());
            return dto;
        }).collect(Collectors.toList());
    }
    public User createUser(User userInfo) {
        if (userRepository.findByUsername(userInfo.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists: " + userInfo.getUsername());
        }
        User user = new User();
        user.setUsername(userInfo.getUsername());
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }


//    private final MessageDao messageDao;
//
//    @Autowired
//    public ChatService(MessageDao messageDao) {
//        this.messageDao = messageDao;
//    }
//
//    public void sendMessage(MessageDTO message) {
//        messageDao.addMessage(message);
//    }
//
//    public MessageDTO receiveMessage(String receiver, long timeoutInSeconds) throws InterruptedException {
//        return messageDao.getMessageForRecipient(receiver, timeoutInSeconds, TimeUnit.SECONDS);
//    }
}
