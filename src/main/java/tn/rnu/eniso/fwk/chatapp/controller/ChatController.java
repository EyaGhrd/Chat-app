package tn.rnu.eniso.fwk.chatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.rnu.eniso.fwk.chatapp.entity.User;
import tn.rnu.eniso.fwk.chatapp.model.MessageDTO;
import tn.rnu.eniso.fwk.chatapp.repository.UserRepository;
import tn.rnu.eniso.fwk.chatapp.service.ChatService;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody MessageDTO message) {
        chatService.sendMessage(message);
    }

    @GetMapping("/receive/{receiver}")
    public List<MessageDTO> receiveMessages(@PathVariable String receiver) {
        return chatService.receiveMessages(receiver);
    }
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return chatService.createUser(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return chatService.getAllUsers();
    }

    @GetMapping("/users/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return chatService.getUserByUsername(username);
    }
//    @Autowired
//    private final ChatService chatService;
//

//    public ChatController(ChatService chatService) {
//        this.chatService = chatService;
//    }
//
//    @PostMapping("/send")
//    public void sendMessage(@RequestBody MessageDTO message) {
//        chatService.sendMessage(message);
//    }
//
//    @GetMapping("/receive/{receiver}")
//    public MessageDTO receiveMessage(@PathVariable String receiver, @RequestParam(defaultValue = "60") long timeoutInSeconds) throws InterruptedException {
//        return chatService.receiveMessage(receiver, timeoutInSeconds);
//    }
}
