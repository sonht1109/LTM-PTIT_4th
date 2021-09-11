/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import entity.ChatBox;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Messenger;
import java.util.ArrayList;
import java.util.Collection;
import entity.Media;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author phamthainb
 */
public class ChatBoxDAO implements Serializable {

    public ChatBoxDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ChatBox chatBox) {
        if (chatBox.getMessengerCollection() == null) {
            chatBox.setMessengerCollection(new ArrayList<Messenger>());
        }
        if (chatBox.getMediaCollection() == null) {
            chatBox.setMediaCollection(new ArrayList<Media>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Messenger> attachedMessengerCollection = new ArrayList<Messenger>();
            for (Messenger messengerCollectionMessengerToAttach : chatBox.getMessengerCollection()) {
                messengerCollectionMessengerToAttach = em.getReference(messengerCollectionMessengerToAttach.getClass(), messengerCollectionMessengerToAttach.getId());
                attachedMessengerCollection.add(messengerCollectionMessengerToAttach);
            }
            chatBox.setMessengerCollection(attachedMessengerCollection);
            Collection<Media> attachedMediaCollection = new ArrayList<Media>();
            for (Media mediaCollectionMediaToAttach : chatBox.getMediaCollection()) {
                mediaCollectionMediaToAttach = em.getReference(mediaCollectionMediaToAttach.getClass(), mediaCollectionMediaToAttach.getId());
                attachedMediaCollection.add(mediaCollectionMediaToAttach);
            }
            chatBox.setMediaCollection(attachedMediaCollection);
            em.persist(chatBox);
            for (Messenger messengerCollectionMessenger : chatBox.getMessengerCollection()) {
                ChatBox oldChatBoxIdOfMessengerCollectionMessenger = messengerCollectionMessenger.getChatBoxId();
                messengerCollectionMessenger.setChatBoxId(chatBox);
                messengerCollectionMessenger = em.merge(messengerCollectionMessenger);
                if (oldChatBoxIdOfMessengerCollectionMessenger != null) {
                    oldChatBoxIdOfMessengerCollectionMessenger.getMessengerCollection().remove(messengerCollectionMessenger);
                    oldChatBoxIdOfMessengerCollectionMessenger = em.merge(oldChatBoxIdOfMessengerCollectionMessenger);
                }
            }
            for (Media mediaCollectionMedia : chatBox.getMediaCollection()) {
                ChatBox oldChatboxIdOfMediaCollectionMedia = mediaCollectionMedia.getChatboxId();
                mediaCollectionMedia.setChatboxId(chatBox);
                mediaCollectionMedia = em.merge(mediaCollectionMedia);
                if (oldChatboxIdOfMediaCollectionMedia != null) {
                    oldChatboxIdOfMediaCollectionMedia.getMediaCollection().remove(mediaCollectionMedia);
                    oldChatboxIdOfMediaCollectionMedia = em.merge(oldChatboxIdOfMediaCollectionMedia);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ChatBox chatBox) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ChatBox persistentChatBox = em.find(ChatBox.class, chatBox.getId());
            Collection<Messenger> messengerCollectionOld = persistentChatBox.getMessengerCollection();
            Collection<Messenger> messengerCollectionNew = chatBox.getMessengerCollection();
            Collection<Media> mediaCollectionOld = persistentChatBox.getMediaCollection();
            Collection<Media> mediaCollectionNew = chatBox.getMediaCollection();
            Collection<Messenger> attachedMessengerCollectionNew = new ArrayList<Messenger>();
            for (Messenger messengerCollectionNewMessengerToAttach : messengerCollectionNew) {
                messengerCollectionNewMessengerToAttach = em.getReference(messengerCollectionNewMessengerToAttach.getClass(), messengerCollectionNewMessengerToAttach.getId());
                attachedMessengerCollectionNew.add(messengerCollectionNewMessengerToAttach);
            }
            messengerCollectionNew = attachedMessengerCollectionNew;
            chatBox.setMessengerCollection(messengerCollectionNew);
            Collection<Media> attachedMediaCollectionNew = new ArrayList<Media>();
            for (Media mediaCollectionNewMediaToAttach : mediaCollectionNew) {
                mediaCollectionNewMediaToAttach = em.getReference(mediaCollectionNewMediaToAttach.getClass(), mediaCollectionNewMediaToAttach.getId());
                attachedMediaCollectionNew.add(mediaCollectionNewMediaToAttach);
            }
            mediaCollectionNew = attachedMediaCollectionNew;
            chatBox.setMediaCollection(mediaCollectionNew);
            chatBox = em.merge(chatBox);
            for (Messenger messengerCollectionOldMessenger : messengerCollectionOld) {
                if (!messengerCollectionNew.contains(messengerCollectionOldMessenger)) {
                    messengerCollectionOldMessenger.setChatBoxId(null);
                    messengerCollectionOldMessenger = em.merge(messengerCollectionOldMessenger);
                }
            }
            for (Messenger messengerCollectionNewMessenger : messengerCollectionNew) {
                if (!messengerCollectionOld.contains(messengerCollectionNewMessenger)) {
                    ChatBox oldChatBoxIdOfMessengerCollectionNewMessenger = messengerCollectionNewMessenger.getChatBoxId();
                    messengerCollectionNewMessenger.setChatBoxId(chatBox);
                    messengerCollectionNewMessenger = em.merge(messengerCollectionNewMessenger);
                    if (oldChatBoxIdOfMessengerCollectionNewMessenger != null && !oldChatBoxIdOfMessengerCollectionNewMessenger.equals(chatBox)) {
                        oldChatBoxIdOfMessengerCollectionNewMessenger.getMessengerCollection().remove(messengerCollectionNewMessenger);
                        oldChatBoxIdOfMessengerCollectionNewMessenger = em.merge(oldChatBoxIdOfMessengerCollectionNewMessenger);
                    }
                }
            }
            for (Media mediaCollectionOldMedia : mediaCollectionOld) {
                if (!mediaCollectionNew.contains(mediaCollectionOldMedia)) {
                    mediaCollectionOldMedia.setChatboxId(null);
                    mediaCollectionOldMedia = em.merge(mediaCollectionOldMedia);
                }
            }
            for (Media mediaCollectionNewMedia : mediaCollectionNew) {
                if (!mediaCollectionOld.contains(mediaCollectionNewMedia)) {
                    ChatBox oldChatboxIdOfMediaCollectionNewMedia = mediaCollectionNewMedia.getChatboxId();
                    mediaCollectionNewMedia.setChatboxId(chatBox);
                    mediaCollectionNewMedia = em.merge(mediaCollectionNewMedia);
                    if (oldChatboxIdOfMediaCollectionNewMedia != null && !oldChatboxIdOfMediaCollectionNewMedia.equals(chatBox)) {
                        oldChatboxIdOfMediaCollectionNewMedia.getMediaCollection().remove(mediaCollectionNewMedia);
                        oldChatboxIdOfMediaCollectionNewMedia = em.merge(oldChatboxIdOfMediaCollectionNewMedia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = chatBox.getId();
                if (findChatBox(id) == null) {
                    throw new NonexistentEntityException("The chatBox with id " + id + " no longer exists.");
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
            ChatBox chatBox;
            try {
                chatBox = em.getReference(ChatBox.class, id);
                chatBox.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The chatBox with id " + id + " no longer exists.", enfe);
            }
            Collection<Messenger> messengerCollection = chatBox.getMessengerCollection();
            for (Messenger messengerCollectionMessenger : messengerCollection) {
                messengerCollectionMessenger.setChatBoxId(null);
                messengerCollectionMessenger = em.merge(messengerCollectionMessenger);
            }
            Collection<Media> mediaCollection = chatBox.getMediaCollection();
            for (Media mediaCollectionMedia : mediaCollection) {
                mediaCollectionMedia.setChatboxId(null);
                mediaCollectionMedia = em.merge(mediaCollectionMedia);
            }
            em.remove(chatBox);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ChatBox> findChatBoxEntities() {
        return findChatBoxEntities(true, -1, -1);
    }

    public List<ChatBox> findChatBoxEntities(int maxResults, int firstResult) {
        return findChatBoxEntities(false, maxResults, firstResult);
    }

    private List<ChatBox> findChatBoxEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ChatBox.class));
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

    public ChatBox findChatBox(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ChatBox.class, id);
        } finally {
            em.close();
        }
    }

    public int getChatBoxCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ChatBox> rt = cq.from(ChatBox.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
