package service;

import model.*;
import repository.*;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;

    public Message envoyerMessage(Message msg) {
        // 1. Sauvegarder le message
        Message savedMsg = messageRepository.save(msg);

        // 2. Mettre à jour la date du dernier message dans la conversation
        Conversation conv = msg.getConversation();
        conv.setLastMessageDate(LocalDateTime.now());
        conversationRepository.save(conv);

        return savedMsg;
    }

    public List<Message> chargerDiscussion(Long conversationId) {
        return messageRepository.findByConversationIdOrderBySentAtAsc(conversationId);
    }
}
