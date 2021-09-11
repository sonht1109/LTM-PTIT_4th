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
import entity.ChatBox;
import entity.User;
import entity.Message;
import entity.Messenger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author phamthainb
 */
public class MessengerDAO implements Serializable {

    public MessengerDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Messenger messenger) {
        if (messenger.getMessageCollection() == null) {
            messenger.setMessageCollection(new ArrayList<Message>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ChatBox chatBoxId = messenger.getChatBoxId();
            if (chatBoxId != null) {
                chatBoxId = em.getReference(chatBoxId.getClass(), chatBoxId.getId());
                messenger.setChatBoxId(chatBoxId);
            }
            User userId = messenger.getUserId();
            if (userId != null) {
                userId = em.getReference(userId.getClass(), userId.getId());
                messenger.setUserId(userId);
            }
            Collection<Message> attachedMessageCollection = new ArrayList<Message>();
            for (Message messageCollectionMessageToAttach : messenger.getMessageCollection()) {
                messageCollectionMessageToAttach = em.getReference(messageCollectionMessageToAttach.getClass(), messageCollectionMessageToAttach.getId());
                attachedMessageCollection.add(messageCollectionMessageToAttach);
            }
            messenger.setMessageCollection(attachedMessageCollection);
            em.persist(messenger);
            if (chatBoxId != null) {
                chatBoxId.getMessengerCollection().add(messenger);
                chatBoxId = em.merge(chatBoxId);
            }
            if (userId != null) {
                userId.getMessengerCollection().add(messenger);
                userId = em.merge(userId);
            }
            for (Message messageCollectionMessage : messenger.getMessageCollection()) {
                Messenger oldMessengerOfMessageCollectionMessage = messageCollectionMessage.getMessenger();
                messageCollectionMessage.setMessenger(messenger);
                messageCollectionMessage = em.merge(messageCollectionMessage);
                if (oldMessengerOfMessageCollectionMessage != null) {
                    oldMessengerOfMessageCollectionMessage.getMessageCollection().remove(messageCollectionMessage);
                    oldMessengerOfMessageCollectionMessage = em.merge(oldMessengerOfMessageCollectionMessage);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Messenger messenger) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Messenger persistentMessenger = em.find(Messenger.class, messenger.getId());
            ChatBox chatBoxIdOld = persistentMessenger.getChatBoxId();
            ChatBox chatBoxIdNew = messenger.getChatBoxId();
            User userIdOld = persistentMessenger.getUserId();
            User userIdNew = messenger.getUserId();
            Collection<Message> messageCollectionOld = persistentMessenger.getMessageCollection();
            Collection<Message> messageCollectionNew = messenger.getMessageCollection();
            if (chatBoxIdNew != null) {
                chatBoxIdNew = em.getReference(chatBoxIdNew.getClass(), chatBoxIdNew.getId());
                messenger.setChatBoxId(chatBoxIdNew);
            }
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getId());
                messenger.setUserId(userIdNew);
            }
            Collection<Message> attachedMessageCollectionNew = new ArrayList<Message>();
            for (Message messageCollectionNewMessageToAttach : messageCollectionNew) {
                messageCollectionNewMessageToAttach = em.getReference(messageCollectionNewMessageToAttach.getClass(), messageCollectionNewMessageToAttach.getId());
                attachedMessageCollectionNew.add(messageCollectionNewMessageToAttach);
            }
            messageCollectionNew = attachedMessageCollectionNew;
            messenger.setMessageCollection(messageCollectionNew);
            messenger = em.merge(messenger);
            if (chatBoxIdOld != null && !chatBoxIdOld.equals(chatBoxIdNew)) {
                chatBoxIdOld.getMessengerCollection().remove(messenger);
                chatBoxIdOld = em.merge(chatBoxIdOld);
            }
            if (chatBoxIdNew != null && !chatBoxIdNew.equals(chatBoxIdOld)) {
                chatBoxIdNew.getMessengerCollection().add(messenger);
                chatBoxIdNew = em.merge(chatBoxIdNew);
            }
            if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.getMessengerCollection().remove(messenger);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                userIdNew.getMessengerCollection().add(messenger);
                userIdNew = em.merge(userIdNew);
            }
            for (Message messageCollectionOldMessage : messageCollectionOld) {
                if (!messageCollectionNew.contains(messageCollectionOldMessage)) {
                    messageCollectionOldMessage.setMessenger(null);
                    messageCollectionOldMessage = em.merge(messageCollectionOldMessage);
                }
            }
            for (Message messageCollectionNewMessage : messageCollectionNew) {
                if (!messageCollectionOld.contains(messageCollectionNewMessage)) {
                    Messenger oldMessengerOfMessageCollectionNewMessage = messageCollectionNewMessage.getMessenger();
                    messageCollectionNewMessage.setMessenger(messenger);
                    messageCollectionNewMessage = em.merge(messageCollectionNewMessage);
                    if (oldMessengerOfMessageCollectionNewMessage != null && !oldMessengerOfMessageCollectionNewMessage.equals(messenger)) {
                        oldMessengerOfMessageCollectionNewMessage.getMessageCollection().remove(messageCollectionNewMessage);
                        oldMessengerOfMessageCollectionNewMessage = em.merge(oldMessengerOfMessageCollectionNewMessage);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = messenger.getId();
                if (findMessenger(id) == null) {
                    throw new NonexistentEntityException("The messenger with id " + id + " no longer exists.");
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
            Messenger messenger;
            try {
                messenger = em.getReference(Messenger.class, id);
                messenger.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The messenger with id " + id + " no longer exists.", enfe);
            }
            ChatBox chatBoxId = messenger.getChatBoxId();
            if (chatBoxId != null) {
                chatBoxId.getMessengerCollection().remove(messenger);
                chatBoxId = em.merge(chatBoxId);
            }
            User userId = messenger.getUserId();
            if (userId != null) {
                userId.getMessengerCollection().remove(messenger);
                userId = em.merge(userId);
            }
            Collection<Message> messageCollection = messenger.getMessageCollection();
            for (Message messageCollectionMessage : messageCollection) {
                messageCollectionMessage.setMessenger(null);
                messageCollectionMessage = em.merge(messageCollectionMessage);
            }
            em.remove(messenger);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Messenger> findMessengerEntities() {
        return findMessengerEntities(true, -1, -1);
    }

    public List<Messenger> findMessengerEntities(int maxResults, int firstResult) {
        return findMessengerEntities(false, maxResults, firstResult);
    }

    private List<Messenger> findMessengerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Messenger.class));
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

    public Messenger findMessenger(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Messenger.class, id);
        } finally {
            em.close();
        }
    }

    public int getMessengerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Messenger> rt = cq.from(Messenger.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
