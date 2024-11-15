package tn.rnu.eniso.fwk.chatapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.rnu.eniso.fwk.chatapp.dal.MessageDao;
import tn.rnu.eniso.fwk.chatapp.model.MessageDTO;

import java.util.concurrent.TimeUnit;
@Service
public class ChatService {
    private final MessageDao messageDao;

    @Autowired
    public ChatService(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public void sendMessage(MessageDTO message) {
        messageDao.addMessage(message);
    }

    public MessageDTO receiveMessage(String receiver, long timeoutInSeconds) throws InterruptedException {
        return messageDao.getMessageForRecipient(receiver, timeoutInSeconds, TimeUnit.SECONDS);
    }
}
