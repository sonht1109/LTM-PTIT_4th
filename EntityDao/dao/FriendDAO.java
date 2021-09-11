/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import entity.Friend;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author phamthainb
 */
public class FriendDAO implements Serializable {

    public FriendDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Friend friend) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User userId1 = friend.getUserId1();
            if (userId1 != null) {
                userId1 = em.getReference(userId1.getClass(), userId1.getId());
                friend.setUserId1(userId1);
            }
            User userId2 = friend.getUserId2();
            if (userId2 != null) {
                userId2 = em.getReference(userId2.getClass(), userId2.getId());
                friend.setUserId2(userId2);
            }
            em.persist(friend);
            if (userId1 != null) {
                userId1.getFriendCollection().add(friend);
                userId1 = em.merge(userId1);
            }
            if (userId2 != null) {
                userId2.getFriendCollection().add(friend);
                userId2 = em.merge(userId2);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Friend friend) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Friend persistentFriend = em.find(Friend.class, friend.getId());
            User userId1Old = persistentFriend.getUserId1();
            User userId1New = friend.getUserId1();
            User userId2Old = persistentFriend.getUserId2();
            User userId2New = friend.getUserId2();
            if (userId1New != null) {
                userId1New = em.getReference(userId1New.getClass(), userId1New.getId());
                friend.setUserId1(userId1New);
            }
            if (userId2New != null) {
                userId2New = em.getReference(userId2New.getClass(), userId2New.getId());
                friend.setUserId2(userId2New);
            }
            friend = em.merge(friend);
            if (userId1Old != null && !userId1Old.equals(userId1New)) {
                userId1Old.getFriendCollection().remove(friend);
                userId1Old = em.merge(userId1Old);
            }
            if (userId1New != null && !userId1New.equals(userId1Old)) {
                userId1New.getFriendCollection().add(friend);
                userId1New = em.merge(userId1New);
            }
            if (userId2Old != null && !userId2Old.equals(userId2New)) {
                userId2Old.getFriendCollection().remove(friend);
                userId2Old = em.merge(userId2Old);
            }
            if (userId2New != null && !userId2New.equals(userId2Old)) {
                userId2New.getFriendCollection().add(friend);
                userId2New = em.merge(userId2New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = friend.getId();
                if (findFriend(id) == null) {
                    throw new NonexistentEntityException("The friend with id " + id + " no longer exists.");
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
            Friend friend;
            try {
                friend = em.getReference(Friend.class, id);
                friend.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The friend with id " + id + " no longer exists.", enfe);
            }
            User userId1 = friend.getUserId1();
            if (userId1 != null) {
                userId1.getFriendCollection().remove(friend);
                userId1 = em.merge(userId1);
            }
            User userId2 = friend.getUserId2();
            if (userId2 != null) {
                userId2.getFriendCollection().remove(friend);
                userId2 = em.merge(userId2);
            }
            em.remove(friend);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Friend> findFriendEntities() {
        return findFriendEntities(true, -1, -1);
    }

    public List<Friend> findFriendEntities(int maxResults, int firstResult) {
        return findFriendEntities(false, maxResults, firstResult);
    }

    private List<Friend> findFriendEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Friend.class));
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

    public Friend findFriend(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Friend.class, id);
        } finally {
            em.close();
        }
    }

    public int getFriendCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Friend> rt = cq.from(Friend.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
