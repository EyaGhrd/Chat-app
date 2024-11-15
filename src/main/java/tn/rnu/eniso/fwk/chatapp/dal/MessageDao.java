package tn.rnu.eniso.fwk.chatapp.dal;

import org.springframework.stereotype.Repository;
import tn.rnu.eniso.fwk.chatapp.model.MessageDTO;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Repository
public class MessageDao {
    private final Map<String, LinkedBlockingQueue<MessageDTO>> messages = new ConcurrentHashMap<>();

    public void addMessage(MessageDTO message) {
        messages.computeIfAbsent(message.getReceiver(), k -> new LinkedBlockingQueue<>()).add(message);
    }

    public MessageDTO getMessageForRecipient(String receiver, long timeout, TimeUnit unit) throws InterruptedException {
        return messages.computeIfAbsent(receiver, k -> new LinkedBlockingQueue<>()).poll(timeout, unit);
    }
}
