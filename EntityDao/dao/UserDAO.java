/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.IllegalOrphanException;
import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Notification;
import java.util.ArrayList;
import java.util.Collection;
import entity.Messenger;
import entity.Friend;
import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author phamthainb
 */
public class UserDAO implements Serializable {

    public UserDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User user) {
        if (user.getNotificationCollection() == null) {
            user.setNotificationCollection(new ArrayList<Notification>());
        }
        if (user.getMessengerCollection() == null) {
            user.setMessengerCollection(new ArrayList<Messenger>());
        }
        if (user.getFriendCollection() == null) {
            user.setFriendCollection(new ArrayList<Friend>());
        }
        if (user.getFriendCollection1() == null) {
            user.setFriendCollection1(new ArrayList<Friend>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Notification> attachedNotificationCollection = new ArrayList<Notification>();
            for (Notification notificationCollectionNotificationToAttach : user.getNotificationCollection()) {
                notificationCollectionNotificationToAttach = em.getReference(notificationCollectionNotificationToAttach.getClass(), notificationCollectionNotificationToAttach.getId());
                attachedNotificationCollection.add(notificationCollectionNotificationToAttach);
            }
            user.setNotificationCollection(attachedNotificationCollection);
            Collection<Messenger> attachedMessengerCollection = new ArrayList<Messenger>();
            for (Messenger messengerCollectionMessengerToAttach : user.getMessengerCollection()) {
                messengerCollectionMessengerToAttach = em.getReference(messengerCollectionMessengerToAttach.getClass(), messengerCollectionMessengerToAttach.getId());
                attachedMessengerCollection.add(messengerCollectionMessengerToAttach);
            }
            user.setMessengerCollection(attachedMessengerCollection);
            Collection<Friend> attachedFriendCollection = new ArrayList<Friend>();
            for (Friend friendCollectionFriendToAttach : user.getFriendCollection()) {
                friendCollectionFriendToAttach = em.getReference(friendCollectionFriendToAttach.getClass(), friendCollectionFriendToAttach.getId());
                attachedFriendCollection.add(friendCollectionFriendToAttach);
            }
            user.setFriendCollection(attachedFriendCollection);
            Collection<Friend> attachedFriendCollection1 = new ArrayList<Friend>();
            for (Friend friendCollection1FriendToAttach : user.getFriendCollection1()) {
                friendCollection1FriendToAttach = em.getReference(friendCollection1FriendToAttach.getClass(), friendCollection1FriendToAttach.getId());
                attachedFriendCollection1.add(friendCollection1FriendToAttach);
            }
            user.setFriendCollection1(attachedFriendCollection1);
            em.persist(user);
            for (Notification notificationCollectionNotification : user.getNotificationCollection()) {
                User oldUserIdOfNotificationCollectionNotification = notificationCollectionNotification.getUserId();
                notificationCollectionNotification.setUserId(user);
                notificationCollectionNotification = em.merge(notificationCollectionNotification);
                if (oldUserIdOfNotificationCollectionNotification != null) {
                    oldUserIdOfNotificationCollectionNotification.getNotificationCollection().remove(notificationCollectionNotification);
                    oldUserIdOfNotificationCollectionNotification = em.merge(oldUserIdOfNotificationCollectionNotification);
                }
            }
            for (Messenger messengerCollectionMessenger : user.getMessengerCollection()) {
                User oldUserIdOfMessengerCollectionMessenger = messengerCollectionMessenger.getUserId();
                messengerCollectionMessenger.setUserId(user);
                messengerCollectionMessenger = em.merge(messengerCollectionMessenger);
                if (oldUserIdOfMessengerCollectionMessenger != null) {
                    oldUserIdOfMessengerCollectionMessenger.getMessengerCollection().remove(messengerCollectionMessenger);
                    oldUserIdOfMessengerCollectionMessenger = em.merge(oldUserIdOfMessengerCollectionMessenger);
                }
            }
            for (Friend friendCollectionFriend : user.getFriendCollection()) {
                User oldUserId1OfFriendCollectionFriend = friendCollectionFriend.getUserId1();
                friendCollectionFriend.setUserId1(user);
                friendCollectionFriend = em.merge(friendCollectionFriend);
                if (oldUserId1OfFriendCollectionFriend != null) {
                    oldUserId1OfFriendCollectionFriend.getFriendCollection().remove(friendCollectionFriend);
                    oldUserId1OfFriendCollectionFriend = em.merge(oldUserId1OfFriendCollectionFriend);
                }
            }
            for (Friend friendCollection1Friend : user.getFriendCollection1()) {
                User oldUserId2OfFriendCollection1Friend = friendCollection1Friend.getUserId2();
                friendCollection1Friend.setUserId2(user);
                friendCollection1Friend = em.merge(friendCollection1Friend);
                if (oldUserId2OfFriendCollection1Friend != null) {
                    oldUserId2OfFriendCollection1Friend.getFriendCollection1().remove(friendCollection1Friend);
                    oldUserId2OfFriendCollection1Friend = em.merge(oldUserId2OfFriendCollection1Friend);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(User user) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User persistentUser = em.find(User.class, user.getId());
            Collection<Notification> notificationCollectionOld = persistentUser.getNotificationCollection();
            Collection<Notification> notificationCollectionNew = user.getNotificationCollection();
            Collection<Messenger> messengerCollectionOld = persistentUser.getMessengerCollection();
            Collection<Messenger> messengerCollectionNew = user.getMessengerCollection();
            Collection<Friend> friendCollectionOld = persistentUser.getFriendCollection();
            Collection<Friend> friendCollectionNew = user.getFriendCollection();
            Collection<Friend> friendCollection1Old = persistentUser.getFriendCollection1();
            Collection<Friend> friendCollection1New = user.getFriendCollection1();
            List<String> illegalOrphanMessages = null;
            for (Friend friendCollectionOldFriend : friendCollectionOld) {
                if (!friendCollectionNew.contains(friendCollectionOldFriend)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Friend " + friendCollectionOldFriend + " since its userId1 field is not nullable.");
                }
            }
            for (Friend friendCollection1OldFriend : friendCollection1Old) {
                if (!friendCollection1New.contains(friendCollection1OldFriend)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Friend " + friendCollection1OldFriend + " since its userId2 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Notification> attachedNotificationCollectionNew = new ArrayList<Notification>();
            for (Notification notificationCollectionNewNotificationToAttach : notificationCollectionNew) {
                notificationCollectionNewNotificationToAttach = em.getReference(notificationCollectionNewNotificationToAttach.getClass(), notificationCollectionNewNotificationToAttach.getId());
                attachedNotificationCollectionNew.add(notificationCollectionNewNotificationToAttach);
            }
            notificationCollectionNew = attachedNotificationCollectionNew;
            user.setNotificationCollection(notificationCollectionNew);
            Collection<Messenger> attachedMessengerCollectionNew = new ArrayList<Messenger>();
            for (Messenger messengerCollectionNewMessengerToAttach : messengerCollectionNew) {
                messengerCollectionNewMessengerToAttach = em.getReference(messengerCollectionNewMessengerToAttach.getClass(), messengerCollectionNewMessengerToAttach.getId());
                attachedMessengerCollectionNew.add(messengerCollectionNewMessengerToAttach);
            }
            messengerCollectionNew = attachedMessengerCollectionNew;
            user.setMessengerCollection(messengerCollectionNew);
            Collection<Friend> attachedFriendCollectionNew = new ArrayList<Friend>();
            for (Friend friendCollectionNewFriendToAttach : friendCollectionNew) {
                friendCollectionNewFriendToAttach = em.getReference(friendCollectionNewFriendToAttach.getClass(), friendCollectionNewFriendToAttach.getId());
                attachedFriendCollectionNew.add(friendCollectionNewFriendToAttach);
            }
            friendCollectionNew = attachedFriendCollectionNew;
            user.setFriendCollection(friendCollectionNew);
            Collection<Friend> attachedFriendCollection1New = new ArrayList<Friend>();
            for (Friend friendCollection1NewFriendToAttach : friendCollection1New) {
                friendCollection1NewFriendToAttach = em.getReference(friendCollection1NewFriendToAttach.getClass(), friendCollection1NewFriendToAttach.getId());
                attachedFriendCollection1New.add(friendCollection1NewFriendToAttach);
            }
            friendCollection1New = attachedFriendCollection1New;
            user.setFriendCollection1(friendCollection1New);
            user = em.merge(user);
            for (Notification notificationCollectionOldNotification : notificationCollectionOld) {
                if (!notificationCollectionNew.contains(notificationCollectionOldNotification)) {
                    notificationCollectionOldNotification.setUserId(null);
                    notificationCollectionOldNotification = em.merge(notificationCollectionOldNotification);
                }
            }
            for (Notification notificationCollectionNewNotification : notificationCollectionNew) {
                if (!notificationCollectionOld.contains(notificationCollectionNewNotification)) {
                    User oldUserIdOfNotificationCollectionNewNotification = notificationCollectionNewNotification.getUserId();
                    notificationCollectionNewNotification.setUserId(user);
                    notificationCollectionNewNotification = em.merge(notificationCollectionNewNotification);
                    if (oldUserIdOfNotificationCollectionNewNotification != null && !oldUserIdOfNotificationCollectionNewNotification.equals(user)) {
                        oldUserIdOfNotificationCollectionNewNotification.getNotificationCollection().remove(notificationCollectionNewNotification);
                        oldUserIdOfNotificationCollectionNewNotification = em.merge(oldUserIdOfNotificationCollectionNewNotification);
                    }
                }
            }
            for (Messenger messengerCollectionOldMessenger : messengerCollectionOld) {
                if (!messengerCollectionNew.contains(messengerCollectionOldMessenger)) {
                    messengerCollectionOldMessenger.setUserId(null);
                    messengerCollectionOldMessenger = em.merge(messengerCollectionOldMessenger);
                }
            }
            for (Messenger messengerCollectionNewMessenger : messengerCollectionNew) {
                if (!messengerCollectionOld.contains(messengerCollectionNewMessenger)) {
                    User oldUserIdOfMessengerCollectionNewMessenger = messengerCollectionNewMessenger.getUserId();
                    messengerCollectionNewMessenger.setUserId(user);
                    messengerCollectionNewMessenger = em.merge(messengerCollectionNewMessenger);
                    if (oldUserIdOfMessengerCollectionNewMessenger != null && !oldUserIdOfMessengerCollectionNewMessenger.equals(user)) {
                        oldUserIdOfMessengerCollectionNewMessenger.getMessengerCollection().remove(messengerCollectionNewMessenger);
                        oldUserIdOfMessengerCollectionNewMessenger = em.merge(oldUserIdOfMessengerCollectionNewMessenger);
                    }
                }
            }
            for (Friend friendCollectionNewFriend : friendCollectionNew) {
                if (!friendCollectionOld.contains(friendCollectionNewFriend)) {
                    User oldUserId1OfFriendCollectionNewFriend = friendCollectionNewFriend.getUserId1();
                    friendCollectionNewFriend.setUserId1(user);
                    friendCollectionNewFriend = em.merge(friendCollectionNewFriend);
                    if (oldUserId1OfFriendCollectionNewFriend != null && !oldUserId1OfFriendCollectionNewFriend.equals(user)) {
                        oldUserId1OfFriendCollectionNewFriend.getFriendCollection().remove(friendCollectionNewFriend);
                        oldUserId1OfFriendCollectionNewFriend = em.merge(oldUserId1OfFriendCollectionNewFriend);
                    }
                }
            }
            for (Friend friendCollection1NewFriend : friendCollection1New) {
                if (!friendCollection1Old.contains(friendCollection1NewFriend)) {
                    User oldUserId2OfFriendCollection1NewFriend = friendCollection1NewFriend.getUserId2();
                    friendCollection1NewFriend.setUserId2(user);
                    friendCollection1NewFriend = em.merge(friendCollection1NewFriend);
                    if (oldUserId2OfFriendCollection1NewFriend != null && !oldUserId2OfFriendCollection1NewFriend.equals(user)) {
                        oldUserId2OfFriendCollection1NewFriend.getFriendCollection1().remove(friendCollection1NewFriend);
                        oldUserId2OfFriendCollection1NewFriend = em.merge(oldUserId2OfFriendCollection1NewFriend);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = user.getId();
                if (findUser(id) == null) {
                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User user;
            try {
                user = em.getReference(User.class, id);
                user.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Friend> friendCollectionOrphanCheck = user.getFriendCollection();
            for (Friend friendCollectionOrphanCheckFriend : friendCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Friend " + friendCollectionOrphanCheckFriend + " in its friendCollection field has a non-nullable userId1 field.");
            }
            Collection<Friend> friendCollection1OrphanCheck = user.getFriendCollection1();
            for (Friend friendCollection1OrphanCheckFriend : friendCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Friend " + friendCollection1OrphanCheckFriend + " in its friendCollection1 field has a non-nullable userId2 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Notification> notificationCollection = user.getNotificationCollection();
            for (Notification notificationCollectionNotification : notificationCollection) {
                notificationCollectionNotification.setUserId(null);
                notificationCollectionNotification = em.merge(notificationCollectionNotification);
            }
            Collection<Messenger> messengerCollection = user.getMessengerCollection();
            for (Messenger messengerCollectionMessenger : messengerCollection) {
                messengerCollectionMessenger.setUserId(null);
                messengerCollectionMessenger = em.merge(messengerCollectionMessenger);
            }
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
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

    public User findUser(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<User> rt = cq.from(User.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
