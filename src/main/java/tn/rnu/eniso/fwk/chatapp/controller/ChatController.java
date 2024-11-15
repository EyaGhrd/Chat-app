package tn.rnu.eniso.fwk.chatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.rnu.eniso.fwk.chatapp.model.MessageDTO;
import tn.rnu.eniso.fwk.chatapp.service.ChatService;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody MessageDTO message) {
        chatService.sendMessage(message);
    }

    @GetMapping("/receive/{receiver}")
    public MessageDTO receiveMessage(@PathVariable String receiver, @RequestParam(defaultValue = "60") long timeoutInSeconds) throws InterruptedException {
        return chatService.receiveMessage(receiver, timeoutInSeconds);
    }
}
