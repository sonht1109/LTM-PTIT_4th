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
import entity.MessageDetail;
import entity.Reaction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author phamthainb
 */
public class ReactionDAO implements Serializable {

    public ReactionDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reaction reaction) {
        if (reaction.getMessageDetailCollection() == null) {
            reaction.setMessageDetailCollection(new ArrayList<MessageDetail>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<MessageDetail> attachedMessageDetailCollection = new ArrayList<MessageDetail>();
            for (MessageDetail messageDetailCollectionMessageDetailToAttach : reaction.getMessageDetailCollection()) {
                messageDetailCollectionMessageDetailToAttach = em.getReference(messageDetailCollectionMessageDetailToAttach.getClass(), messageDetailCollectionMessageDetailToAttach.getId());
                attachedMessageDetailCollection.add(messageDetailCollectionMessageDetailToAttach);
            }
            reaction.setMessageDetailCollection(attachedMessageDetailCollection);
            em.persist(reaction);
            for (MessageDetail messageDetailCollectionMessageDetail : reaction.getMessageDetailCollection()) {
                Reaction oldReactionIdOfMessageDetailCollectionMessageDetail = messageDetailCollectionMessageDetail.getReactionId();
                messageDetailCollectionMessageDetail.setReactionId(reaction);
                messageDetailCollectionMessageDetail = em.merge(messageDetailCollectionMessageDetail);
                if (oldReactionIdOfMessageDetailCollectionMessageDetail != null) {
                    oldReactionIdOfMessageDetailCollectionMessageDetail.getMessageDetailCollection().remove(messageDetailCollectionMessageDetail);
                    oldReactionIdOfMessageDetailCollectionMessageDetail = em.merge(oldReactionIdOfMessageDetailCollectionMessageDetail);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reaction reaction) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reaction persistentReaction = em.find(Reaction.class, reaction.getId());
            Collection<MessageDetail> messageDetailCollectionOld = persistentReaction.getMessageDetailCollection();
            Collection<MessageDetail> messageDetailCollectionNew = reaction.getMessageDetailCollection();
            Collection<MessageDetail> attachedMessageDetailCollectionNew = new ArrayList<MessageDetail>();
            for (MessageDetail messageDetailCollectionNewMessageDetailToAttach : messageDetailCollectionNew) {
                messageDetailCollectionNewMessageDetailToAttach = em.getReference(messageDetailCollectionNewMessageDetailToAttach.getClass(), messageDetailCollectionNewMessageDetailToAttach.getId());
                attachedMessageDetailCollectionNew.add(messageDetailCollectionNewMessageDetailToAttach);
            }
            messageDetailCollectionNew = attachedMessageDetailCollectionNew;
            reaction.setMessageDetailCollection(messageDetailCollectionNew);
            reaction = em.merge(reaction);
            for (MessageDetail messageDetailCollectionOldMessageDetail : messageDetailCollectionOld) {
                if (!messageDetailCollectionNew.contains(messageDetailCollectionOldMessageDetail)) {
                    messageDetailCollectionOldMessageDetail.setReactionId(null);
                    messageDetailCollectionOldMessageDetail = em.merge(messageDetailCollectionOldMessageDetail);
                }
            }
            for (MessageDetail messageDetailCollectionNewMessageDetail : messageDetailCollectionNew) {
                if (!messageDetailCollectionOld.contains(messageDetailCollectionNewMessageDetail)) {
                    Reaction oldReactionIdOfMessageDetailCollectionNewMessageDetail = messageDetailCollectionNewMessageDetail.getReactionId();
                    messageDetailCollectionNewMessageDetail.setReactionId(reaction);
                    messageDetailCollectionNewMessageDetail = em.merge(messageDetailCollectionNewMessageDetail);
                    if (oldReactionIdOfMessageDetailCollectionNewMessageDetail != null && !oldReactionIdOfMessageDetailCollectionNewMessageDetail.equals(reaction)) {
                        oldReactionIdOfMessageDetailCollectionNewMessageDetail.getMessageDetailCollection().remove(messageDetailCollectionNewMessageDetail);
                        oldReactionIdOfMessageDetailCollectionNewMessageDetail = em.merge(oldReactionIdOfMessageDetailCollectionNewMessageDetail);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = reaction.getId();
                if (findReaction(id) == null) {
                    throw new NonexistentEntityException("The reaction with id " + id + " no longer exists.");
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
            Reaction reaction;
            try {
                reaction = em.getReference(Reaction.class, id);
                reaction.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reaction with id " + id + " no longer exists.", enfe);
            }
            Collection<MessageDetail> messageDetailCollection = reaction.getMessageDetailCollection();
            for (MessageDetail messageDetailCollectionMessageDetail : messageDetailCollection) {
                messageDetailCollectionMessageDetail.setReactionId(null);
                messageDetailCollectionMessageDetail = em.merge(messageDetailCollectionMessageDetail);
            }
            em.remove(reaction);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reaction> findReactionEntities() {
        return findReactionEntities(true, -1, -1);
    }

    public List<Reaction> findReactionEntities(int maxResults, int firstResult) {
        return findReactionEntities(false, maxResults, firstResult);
    }

    private List<Reaction> findReactionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reaction.class));
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

    public Reaction findReaction(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reaction.class, id);
        } finally {
            em.close();
        }
    }

    public int getReactionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reaction> rt = cq.from(Reaction.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
