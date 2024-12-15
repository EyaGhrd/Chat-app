package tn.rnu.eniso.fwk.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.rnu.eniso.fwk.chatapp.entity.Message;
import tn.rnu.eniso.fwk.chatapp.entity.User;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByReceiver(User receiver);
    List<Message> findByReceiver_Username(String receiver);
}