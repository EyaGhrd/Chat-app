package tn.rnu.eniso.fwk.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.rnu.eniso.fwk.chatapp.entity.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByReceiver(String receiver);
}