/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Media;
import entity.Message;
import entity.Messenger;
import java.util.ArrayList;
import java.util.Collection;
import entity.MessageDetail;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author phamthainb
 */
public class MessageDAO implements Serializable {

    public MessageDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Message message) {
        if (message.getMessageCollection() == null) {
            message.setMessageCollection(new ArrayList<Message>());
        }
        if (message.getMessageDetailCollection() == null) {
            message.setMessageDetailCollection(new ArrayList<MessageDetail>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Media media = message.getMedia();
            if (media != null) {
                media = em.getReference(media.getClass(), media.getId());
                message.setMedia(media);
            }
            Message messageRel = message.getMessage();
            if (messageRel != null) {
                messageRel = em.getReference(messageRel.getClass(), messageRel.getId());
                message.setMessage(messageRel);
            }
            Messenger messenger = message.getMessenger();
            if (messenger != null) {
                messenger = em.getReference(messenger.getClass(), messenger.getId());
                message.setMessenger(messenger);
            }
            Collection<Message> attachedMessageCollection = new ArrayList<Message>();
            for (Message messageCollectionMessageToAttach : message.getMessageCollection()) {
                messageCollectionMessageToAttach = em.getReference(messageCollectionMessageToAttach.getClass(), messageCollectionMessageToAttach.getId());
                attachedMessageCollection.add(messageCollectionMessageToAttach);
            }
            message.setMessageCollection(attachedMessageCollection);
            Collection<MessageDetail> attachedMessageDetailCollection = new ArrayList<MessageDetail>();
            for (MessageDetail messageDetailCollectionMessageDetailToAttach : message.getMessageDetailCollection()) {
                messageDetailCollectionMessageDetailToAttach = em.getReference(messageDetailCollectionMessageDetailToAttach.getClass(), messageDetailCollectionMessageDetailToAttach.getId());
                attachedMessageDetailCollection.add(messageDetailCollectionMessageDetailToAttach);
            }
            message.setMessageDetailCollection(attachedMessageDetailCollection);
            em.persist(message);
            if (media != null) {
                media.getMessageCollection().add(message);
                media = em.merge(media);
            }
            if (messageRel != null) {
                messageRel.getMessageCollection().add(message);
                messageRel = em.merge(messageRel);
            }
            if (messenger != null) {
                messenger.getMessageCollection().add(message);
                messenger = em.merge(messenger);
            }
            for (Message messageCollectionMessage : message.getMessageCollection()) {
                Message oldMessageOfMessageCollectionMessage = messageCollectionMessage.getMessage();
                messageCollectionMessage.setMessage(message);
                messageCollectionMessage = em.merge(messageCollectionMessage);
                if (oldMessageOfMessageCollectionMessage != null) {
                    oldMessageOfMessageCollectionMessage.getMessageCollection().remove(messageCollectionMessage);
                    oldMessageOfMessageCollectionMessage = em.merge(oldMessageOfMessageCollectionMessage);
                }
            }
            for (MessageDetail messageDetailCollectionMessageDetail : message.getMessageDetailCollection()) {
                Message oldMessageIdOfMessageDetailCollectionMessageDetail = messageDetailCollectionMessageDetail.getMessageId();
                messageDetailCollectionMessageDetail.setMessageId(message);
                messageDetailCollectionMessageDetail = em.merge(messageDetailCollectionMessageDetail);
                if (oldMessageIdOfMessageDetailCollectionMessageDetail != null) {
                    oldMessageIdOfMessageDetailCollectionMessageDetail.getMessageDetailCollection().remove(messageDetailCollectionMessageDetail);
                    oldMessageIdOfMessageDetailCollectionMessageDetail = em.merge(oldMessageIdOfMessageDetailCollectionMessageDetail);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Message message) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Message persistentMessage = em.find(Message.class, message.getId());
            Media mediaOld = persistentMessage.getMedia();
            Media mediaNew = message.getMedia();
            Message messageRelOld = persistentMessage.getMessage();
            Message messageRelNew = message.getMessage();
            Messenger messengerOld = persistentMessage.getMessenger();
            Messenger messengerNew = message.getMessenger();
            Collection<Message> messageCollectionOld = persistentMessage.getMessageCollection();
            Collection<Message> messageCollectionNew = message.getMessageCollection();
            Collection<MessageDetail> messageDetailCollectionOld = persistentMessage.getMessageDetailCollection();
            Collection<MessageDetail> messageDetailCollectionNew = message.getMessageDetailCollection();
            if (mediaNew != null) {
                mediaNew = em.getReference(mediaNew.getClass(), mediaNew.getId());
                message.setMedia(mediaNew);
            }
            if (messageRelNew != null) {
                messageRelNew = em.getReference(messageRelNew.getClass(), messageRelNew.getId());
                message.setMessage(messageRelNew);
            }
            if (messengerNew != null) {
                messengerNew = em.getReference(messengerNew.getClass(), messengerNew.getId());
                message.setMessenger(messengerNew);
            }
            Collection<Message> attachedMessageCollectionNew = new ArrayList<Message>();
            for (Message messageCollectionNewMessageToAttach : messageCollectionNew) {
                messageCollectionNewMessageToAttach = em.getReference(messageCollectionNewMessageToAttach.getClass(), messageCollectionNewMessageToAttach.getId());
                attachedMessageCollectionNew.add(messageCollectionNewMessageToAttach);
            }
            messageCollectionNew = attachedMessageCollectionNew;
            message.setMessageCollection(messageCollectionNew);
            Collection<MessageDetail> attachedMessageDetailCollectionNew = new ArrayList<MessageDetail>();
            for (MessageDetail messageDetailCollectionNewMessageDetailToAttach : messageDetailCollectionNew) {
                messageDetailCollectionNewMessageDetailToAttach = em.getReference(messageDetailCollectionNewMessageDetailToAttach.getClass(), messageDetailCollectionNewMessageDetailToAttach.getId());
                attachedMessageDetailCollectionNew.add(messageDetailCollectionNewMessageDetailToAttach);
            }
            messageDetailCollectionNew = attachedMessageDetailCollectionNew;
            message.setMessageDetailCollection(messageDetailCollectionNew);
            message = em.merge(message);
            if (mediaOld != null && !mediaOld.equals(mediaNew)) {
                mediaOld.getMessageCollection().remove(message);
                mediaOld = em.merge(mediaOld);
            }
            if (mediaNew != null && !mediaNew.equals(mediaOld)) {
                mediaNew.getMessageCollection().add(message);
                mediaNew = em.merge(mediaNew);
            }
            if (messageRelOld != null && !messageRelOld.equals(messageRelNew)) {
                messageRelOld.getMessageCollection().remove(message);
                messageRelOld = em.merge(messageRelOld);
            }
            if (messageRelNew != null && !messageRelNew.equals(messageRelOld)) {
                messageRelNew.getMessageCollection().add(message);
                messageRelNew = em.merge(messageRelNew);
            }
            if (messengerOld != null && !messengerOld.equals(messengerNew)) {
                messengerOld.getMessageCollection().remove(message);
                messengerOld = em.merge(messengerOld);
            }
            if (messengerNew != null && !messengerNew.equals(messengerOld)) {
                messengerNew.getMessageCollection().add(message);
                messengerNew = em.merge(messengerNew);
            }
            for (Message messageCollectionOldMessage : messageCollectionOld) {
                if (!messageCollectionNew.contains(messageCollectionOldMessage)) {
                    messageCollectionOldMessage.setMessage(null);
                    messageCollectionOldMessage = em.merge(messageCollectionOldMessage);
                }
            }
            for (Message messageCollectionNewMessage : messageCollectionNew) {
                if (!messageCollectionOld.contains(messageCollectionNewMessage)) {
                    Message oldMessageOfMessageCollectionNewMessage = messageCollectionNewMessage.getMessage();
                    messageCollectionNewMessage.setMessage(message);
                    messageCollectionNewMessage = em.merge(messageCollectionNewMessage);
                    if (oldMessageOfMessageCollectionNewMessage != null && !oldMessageOfMessageCollectionNewMessage.equals(message)) {
                        oldMessageOfMessageCollectionNewMessage.getMessageCollection().remove(messageCollectionNewMessage);
                        oldMessageOfMessageCollectionNewMessage = em.merge(oldMessageOfMessageCollectionNewMessage);
                    }
                }
            }
            for (MessageDetail messageDetailCollectionOldMessageDetail : messageDetailCollectionOld) {
                if (!messageDetailCollectionNew.contains(messageDetailCollectionOldMessageDetail)) {
                    messageDetailCollectionOldMessageDetail.setMessageId(null);
                    messageDetailCollectionOldMessageDetail = em.merge(messageDetailCollectionOldMessageDetail);
                }
            }
            for (MessageDetail messageDetailCollectionNewMessageDetail : messageDetailCollectionNew) {
                if (!messageDetailCollectionOld.contains(messageDetailCollectionNewMessageDetail)) {
                    Message oldMessageIdOfMessageDetailCollectionNewMessageDetail = messageDetailCollectionNewMessageDetail.getMessageId();
                    messageDetailCollectionNewMessageDetail.setMessageId(message);
                    messageDetailCollectionNewMessageDetail = em.merge(messageDetailCollectionNewMessageDetail);
                    if (oldMessageIdOfMessageDetailCollectionNewMessageDetail != null && !oldMessageIdOfMessageDetailCollectionNewMessageDetail.equals(message)) {
                        oldMessageIdOfMessageDetailCollectionNewMessageDetail.getMessageDetailCollection().remove(messageDetailCollectionNewMessageDetail);
                        oldMessageIdOfMessageDetailCollectionNewMessageDetail = em.merge(oldMessageIdOfMessageDetailCollectionNewMessageDetail);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = message.getId();
                if (findMessage(id) == null) {
                    throw new NonexistentEntityException("The message with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Message message;
            try {
                message = em.getReference(Message.class, id);
                message.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The message with id " + id + " no longer exists.", enfe);
            }
            Media media = message.getMedia();
            if (media != null) {
                media.getMessageCollection().remove(message);
                media = em.merge(media);
            }
            Message messageRel = message.getMessage();
            if (messageRel != null) {
                messageRel.getMessageCollection().remove(message);
                messageRel = em.merge(messageRel);
            }
            Messenger messenger = message.getMessenger();
            if (messenger != null) {
                messenger.getMessageCollection().remove(message);
                messenger = em.merge(messenger);
            }
            Collection<Message> messageCollection = message.getMessageCollection();
            for (Message messageCollectionMessage : messageCollection) {
                messageCollectionMessage.setMessage(null);
                messageCollectionMessage = em.merge(messageCollectionMessage);
            }
            Collection<MessageDetail> messageDetailCollection = message.getMessageDetailCollection();
            for (MessageDetail messageDetailCollectionMessageDetail : messageDetailCollection) {
                messageDetailCollectionMessageDetail.setMessageId(null);
                messageDetailCollectionMessageDetail = em.merge(messageDetailCollectionMessageDetail);
            }
            em.remove(message);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Message> findMessageEntities() {
        return findMessageEntities(true, -1, -1);
    }

    public List<Message> findMessageEntities(int maxResults, int firstResult) {
        return findMessageEntities(false, maxResults, firstResult);
    }

    private List<Message> findMessageEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Message.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Message findMessage(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Message.class, id);
        } finally {
            em.close();
        }
    }

    public int getMessageCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Message> rt = cq.from(Message.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
