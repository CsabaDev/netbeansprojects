/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import entities.Baba;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Korhaz;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import persistence.exceptions.NonexistentEntityException;
import persistence.exceptions.RollbackFailureException;

/**
 *
 * @author Czinéné Kertész Orsolya
 */
public class BabaJpaController implements Serializable {

    public BabaJpaController(){
        this(Persistence.createEntityManagerFactory("CKOEEVizsgaPU"));
    }
    
    public BabaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Baba baba) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Korhaz korhaz = baba.getKorhaz();
            if (korhaz != null) {
                Long korhazId = korhaz.getId();
                korhaz = em.getReference(korhaz.getClass(), korhazId);
                baba.setKorhaz(korhaz);
            }
            em.persist(baba);
            if (korhaz != null) {
                korhaz.getBabak().add(baba);
                korhaz = em.merge(korhaz);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Baba baba) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Baba persistentBaba = em.find(Baba.class, baba.getId());
            Korhaz korhazOld = persistentBaba.getKorhaz();
            Korhaz korhazNew = baba.getKorhaz();
            if (korhazNew != null) {
                korhazNew = em.getReference(korhazNew.getClass(), korhazNew.getId());
                baba.setKorhaz(korhazNew);
            }
            baba = em.merge(baba);
            if (korhazOld != null && !korhazOld.equals(korhazNew)) {
                korhazOld.getBabak().remove(baba);
                korhazOld = em.merge(korhazOld);
            }
            if (korhazNew != null && !korhazNew.equals(korhazOld)) {
                korhazNew.getBabak().add(baba);
                korhazNew = em.merge(korhazNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = baba.getId();
                if (findBaba(id) == null) {
                    throw new NonexistentEntityException("The baba with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

//    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            Baba baba;
//            try {
//                baba = em.getReference(Baba.class, id);
//                baba.getId();
//            } catch (EntityNotFoundException enfe) {
//                throw new NonexistentEntityException("The baba with id " + id + " no longer exists.", enfe);
//            }
//            Korhaz korhaz = baba.getKorhaz();
//            if (korhaz != null) {
//                korhaz.getBabak().remove(baba);
//                korhaz = em.merge(korhaz);
//            }
//            em.remove(baba);
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            try {
//                em.getTransaction().rollback();
//            } catch (Exception re) {
//                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
//            }
//            throw ex;
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }

    public List<Baba> findBabaEntities() {
        return findBabaEntities(true, -1, -1);
    }

    public List<Baba> findBabaEntities(int maxResults, int firstResult) {
        return findBabaEntities(false, maxResults, firstResult);
    }

    private List<Baba> findBabaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Baba.class));
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

    public Baba findBaba(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Baba.class, id);
        } finally {
            em.close();
        }
    }

    public int getBabaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Baba> rt = cq.from(Baba.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Baba> findMaiBabaEntities() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Baba> query =  em.createNamedQuery(Baba.QUERY_MAI_BABAK, Baba.class);
            List<Baba> maiBabak = query.getResultList();
            return maiBabak;
        } finally {
            em.close();
        }
    }
    
}
