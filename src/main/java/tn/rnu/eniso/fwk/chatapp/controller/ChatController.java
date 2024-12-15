package tn.rnu.eniso.fwk.chatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.rnu.eniso.fwk.chatapp.entity.Message;
import tn.rnu.eniso.fwk.chatapp.entity.User;
import tn.rnu.eniso.fwk.chatapp.service.ChatService;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/send")
    public void sendMessage(@RequestBody Message message) {
        chatService.sendMessage(message);
    }

    @GetMapping("/receive/{receiver}")
    public List<Message> receiveMessages(@PathVariable String receiver) {
        return chatService.receiveMessages(receiver);
    }
    @PostMapping("/users/{username}")
    public User createUser(@PathVariable String username) {
        return chatService.createUser(username);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return chatService.getAllUsers();
    }

    @GetMapping("/users/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return chatService.getUserByUsername(username);
    }
}
