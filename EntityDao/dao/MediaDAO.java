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
import entity.Media;
import entity.Message;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author phamthainb
 */
public class MediaDAO implements Serializable {

    public MediaDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Media media) {
        if (media.getMessageCollection() == null) {
            media.setMessageCollection(new ArrayList<Message>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ChatBox chatboxId = media.getChatboxId();
            if (chatboxId != null) {
                chatboxId = em.getReference(chatboxId.getClass(), chatboxId.getId());
                media.setChatboxId(chatboxId);
            }
            Collection<Message> attachedMessageCollection = new ArrayList<Message>();
            for (Message messageCollectionMessageToAttach : media.getMessageCollection()) {
                messageCollectionMessageToAttach = em.getReference(messageCollectionMessageToAttach.getClass(), messageCollectionMessageToAttach.getId());
                attachedMessageCollection.add(messageCollectionMessageToAttach);
            }
            media.setMessageCollection(attachedMessageCollection);
            em.persist(media);
            if (chatboxId != null) {
                chatboxId.getMediaCollection().add(media);
                chatboxId = em.merge(chatboxId);
            }
            for (Message messageCollectionMessage : media.getMessageCollection()) {
                Media oldMediaOfMessageCollectionMessage = messageCollectionMessage.getMedia();
                messageCollectionMessage.setMedia(media);
                messageCollectionMessage = em.merge(messageCollectionMessage);
                if (oldMediaOfMessageCollectionMessage != null) {
                    oldMediaOfMessageCollectionMessage.getMessageCollection().remove(messageCollectionMessage);
                    oldMediaOfMessageCollectionMessage = em.merge(oldMediaOfMessageCollectionMessage);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Media media) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Media persistentMedia = em.find(Media.class, media.getId());
            ChatBox chatboxIdOld = persistentMedia.getChatboxId();
            ChatBox chatboxIdNew = media.getChatboxId();
            Collection<Message> messageCollectionOld = persistentMedia.getMessageCollection();
            Collection<Message> messageCollectionNew = media.getMessageCollection();
            if (chatboxIdNew != null) {
                chatboxIdNew = em.getReference(chatboxIdNew.getClass(), chatboxIdNew.getId());
                media.setChatboxId(chatboxIdNew);
            }
            Collection<Message> attachedMessageCollectionNew = new ArrayList<Message>();
            for (Message messageCollectionNewMessageToAttach : messageCollectionNew) {
                messageCollectionNewMessageToAttach = em.getReference(messageCollectionNewMessageToAttach.getClass(), messageCollectionNewMessageToAttach.getId());
                attachedMessageCollectionNew.add(messageCollectionNewMessageToAttach);
            }
            messageCollectionNew = attachedMessageCollectionNew;
            media.setMessageCollection(messageCollectionNew);
            media = em.merge(media);
            if (chatboxIdOld != null && !chatboxIdOld.equals(chatboxIdNew)) {
                chatboxIdOld.getMediaCollection().remove(media);
                chatboxIdOld = em.merge(chatboxIdOld);
            }
            if (chatboxIdNew != null && !chatboxIdNew.equals(chatboxIdOld)) {
                chatboxIdNew.getMediaCollection().add(media);
                chatboxIdNew = em.merge(chatboxIdNew);
            }
            for (Message messageCollectionOldMessage : messageCollectionOld) {
                if (!messageCollectionNew.contains(messageCollectionOldMessage)) {
                    messageCollectionOldMessage.setMedia(null);
                    messageCollectionOldMessage = em.merge(messageCollectionOldMessage);
                }
            }
            for (Message messageCollectionNewMessage : messageCollectionNew) {
                if (!messageCollectionOld.contains(messageCollectionNewMessage)) {
                    Media oldMediaOfMessageCollectionNewMessage = messageCollectionNewMessage.getMedia();
                    messageCollectionNewMessage.setMedia(media);
                    messageCollectionNewMessage = em.merge(messageCollectionNewMessage);
                    if (oldMediaOfMessageCollectionNewMessage != null && !oldMediaOfMessageCollectionNewMessage.equals(media)) {
                        oldMediaOfMessageCollectionNewMessage.getMessageCollection().remove(messageCollectionNewMessage);
                        oldMediaOfMessageCollectionNewMessage = em.merge(oldMediaOfMessageCollectionNewMessage);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = media.getId();
                if (findMedia(id) == null) {
                    throw new NonexistentEntityException("The media with id " + id + " no longer exists.");
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
            Media media;
            try {
                media = em.getReference(Media.class, id);
                media.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The media with id " + id + " no longer exists.", enfe);
            }
            ChatBox chatboxId = media.getChatboxId();
            if (chatboxId != null) {
                chatboxId.getMediaCollection().remove(media);
                chatboxId = em.merge(chatboxId);
            }
            Collection<Message> messageCollection = media.getMessageCollection();
            for (Message messageCollectionMessage : messageCollection) {
                messageCollectionMessage.setMedia(null);
                messageCollectionMessage = em.merge(messageCollectionMessage);
            }
            em.remove(media);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Media> findMediaEntities() {
        return findMediaEntities(true, -1, -1);
    }

    public List<Media> findMediaEntities(int maxResults, int firstResult) {
        return findMediaEntities(false, maxResults, firstResult);
    }

    private List<Media> findMediaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Media.class));
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

    public Media findMedia(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Media.class, id);
        } finally {
            em.close();
        }
    }

    public int getMediaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Media> rt = cq.from(Media.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
