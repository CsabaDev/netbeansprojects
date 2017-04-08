/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Baba;
import entities.Korhaz;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import persistence.exceptions.NonexistentEntityException;
import persistence.exceptions.RollbackFailureException;

/**
 *
 * @author User
 */
public class KorhazJpaController implements Serializable {

    public KorhazJpaController(){
        this(Persistence.createEntityManagerFactory("CKOEEVizsgaPU"));
    }
    
    public KorhazJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public KorhazJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Korhaz korhaz) throws RollbackFailureException, Exception {
        if (korhaz.getBabak() == null) {
            korhaz.setBabak(new ArrayList<Baba>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Baba> attachedBabak = new ArrayList<Baba>();
            for (Baba babakBabaToAttach : korhaz.getBabak()) {
                babakBabaToAttach = em.getReference(babakBabaToAttach.getClass(), babakBabaToAttach.getId());
                attachedBabak.add(babakBabaToAttach);
            }
            korhaz.setBabak(attachedBabak);
            em.persist(korhaz);
            for (Baba babakBaba : korhaz.getBabak()) {
                Korhaz oldKorhazOfBabakBaba = babakBaba.getKorhaz();
                babakBaba.setKorhaz(korhaz);
                babakBaba = em.merge(babakBaba);
                if (oldKorhazOfBabakBaba != null) {
                    oldKorhazOfBabakBaba.getBabak().remove(babakBaba);
                    oldKorhazOfBabakBaba = em.merge(oldKorhazOfBabakBaba);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
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

    public void edit(Korhaz korhaz) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Korhaz persistentKorhaz = em.find(Korhaz.class, korhaz.getId());
            List<Baba> babakOld = persistentKorhaz.getBabak();
            List<Baba> babakNew = korhaz.getBabak();
            List<Baba> attachedBabakNew = new ArrayList<Baba>();
            for (Baba babakNewBabaToAttach : babakNew) {
                babakNewBabaToAttach = em.getReference(babakNewBabaToAttach.getClass(), babakNewBabaToAttach.getId());
                attachedBabakNew.add(babakNewBabaToAttach);
            }
            babakNew = attachedBabakNew;
            korhaz.setBabak(babakNew);
            korhaz = em.merge(korhaz);
            for (Baba babakOldBaba : babakOld) {
                if (!babakNew.contains(babakOldBaba)) {
                    babakOldBaba.setKorhaz(null);
                    babakOldBaba = em.merge(babakOldBaba);
                }
            }
            for (Baba babakNewBaba : babakNew) {
                if (!babakOld.contains(babakNewBaba)) {
                    Korhaz oldKorhazOfBabakNewBaba = babakNewBaba.getKorhaz();
                    babakNewBaba.setKorhaz(korhaz);
                    babakNewBaba = em.merge(babakNewBaba);
                    if (oldKorhazOfBabakNewBaba != null && !oldKorhazOfBabakNewBaba.equals(korhaz)) {
                        oldKorhazOfBabakNewBaba.getBabak().remove(babakNewBaba);
                        oldKorhazOfBabakNewBaba = em.merge(oldKorhazOfBabakNewBaba);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = korhaz.getId();
                if (findKorhaz(id) == null) {
                    throw new NonexistentEntityException("The korhaz with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Korhaz korhaz;
            try {
                korhaz = em.getReference(Korhaz.class, id);
                korhaz.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The korhaz with id " + id + " no longer exists.", enfe);
            }
            List<Baba> babak = korhaz.getBabak();
            for (Baba babakBaba : babak) {
                babakBaba.setKorhaz(null);
                babakBaba = em.merge(babakBaba);
            }
            em.remove(korhaz);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
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

    public List<Korhaz> findKorhazEntities() {
        return findKorhazEntities(true, -1, -1);
    }

    public List<Korhaz> findKorhazEntities(int maxResults, int firstResult) {
        return findKorhazEntities(false, maxResults, firstResult);
    }

    private List<Korhaz> findKorhazEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Korhaz.class));
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

    public Korhaz findKorhaz(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Korhaz.class, id);
        } finally {
            em.close();
        }
    }

    public int getKorhazCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Korhaz> rt = cq.from(Korhaz.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
