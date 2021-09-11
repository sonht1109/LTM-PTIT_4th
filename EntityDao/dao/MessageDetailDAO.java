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
import entity.Message;
import entity.MessageDetail;
import entity.Reaction;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author phamthainb
 */
public class MessageDetailDAO implements Serializable {

    public MessageDetailDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MessageDetail messageDetail) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Message messageId = messageDetail.getMessageId();
            if (messageId != null) {
                messageId = em.getReference(messageId.getClass(), messageId.getId());
                messageDetail.setMessageId(messageId);
            }
            Reaction reactionId = messageDetail.getReactionId();
            if (reactionId != null) {
                reactionId = em.getReference(reactionId.getClass(), reactionId.getId());
                messageDetail.setReactionId(reactionId);
            }
            em.persist(messageDetail);
            if (messageId != null) {
                messageId.getMessageDetailCollection().add(messageDetail);
                messageId = em.merge(messageId);
            }
            if (reactionId != null) {
                reactionId.getMessageDetailCollection().add(messageDetail);
                reactionId = em.merge(reactionId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MessageDetail messageDetail) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MessageDetail persistentMessageDetail = em.find(MessageDetail.class, messageDetail.getId());
            Message messageIdOld = persistentMessageDetail.getMessageId();
            Message messageIdNew = messageDetail.getMessageId();
            Reaction reactionIdOld = persistentMessageDetail.getReactionId();
            Reaction reactionIdNew = messageDetail.getReactionId();
            if (messageIdNew != null) {
                messageIdNew = em.getReference(messageIdNew.getClass(), messageIdNew.getId());
                messageDetail.setMessageId(messageIdNew);
            }
            if (reactionIdNew != null) {
                reactionIdNew = em.getReference(reactionIdNew.getClass(), reactionIdNew.getId());
                messageDetail.setReactionId(reactionIdNew);
            }
            messageDetail = em.merge(messageDetail);
            if (messageIdOld != null && !messageIdOld.equals(messageIdNew)) {
                messageIdOld.getMessageDetailCollection().remove(messageDetail);
                messageIdOld = em.merge(messageIdOld);
            }
            if (messageIdNew != null && !messageIdNew.equals(messageIdOld)) {
                messageIdNew.getMessageDetailCollection().add(messageDetail);
                messageIdNew = em.merge(messageIdNew);
            }
            if (reactionIdOld != null && !reactionIdOld.equals(reactionIdNew)) {
                reactionIdOld.getMessageDetailCollection().remove(messageDetail);
                reactionIdOld = em.merge(reactionIdOld);
            }
            if (reactionIdNew != null && !reactionIdNew.equals(reactionIdOld)) {
                reactionIdNew.getMessageDetailCollection().add(messageDetail);
                reactionIdNew = em.merge(reactionIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = messageDetail.getId();
                if (findMessageDetail(id) == null) {
                    throw new NonexistentEntityException("The messageDetail with id " + id + " no longer exists.");
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
            MessageDetail messageDetail;
            try {
                messageDetail = em.getReference(MessageDetail.class, id);
                messageDetail.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The messageDetail with id " + id + " no longer exists.", enfe);
            }
            Message messageId = messageDetail.getMessageId();
            if (messageId != null) {
                messageId.getMessageDetailCollection().remove(messageDetail);
                messageId = em.merge(messageId);
            }
            Reaction reactionId = messageDetail.getReactionId();
            if (reactionId != null) {
                reactionId.getMessageDetailCollection().remove(messageDetail);
                reactionId = em.merge(reactionId);
            }
            em.remove(messageDetail);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MessageDetail> findMessageDetailEntities() {
        return findMessageDetailEntities(true, -1, -1);
    }

    public List<MessageDetail> findMessageDetailEntities(int maxResults, int firstResult) {
        return findMessageDetailEntities(false, maxResults, firstResult);
    }

    private List<MessageDetail> findMessageDetailEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MessageDetail.class));
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

    public MessageDetail findMessageDetail(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MessageDetail.class, id);
        } finally {
            em.close();
        }
    }

    public int getMessageDetailCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MessageDetail> rt = cq.from(MessageDetail.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
