package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class EventoDAO {

    private final EntityManager em;

    public EventoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Eventi e) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(e);
        transaction.commit();
        System.out.println("Nuovo evento salvato correttamente");
    }

    public Eventi findById(int id) {
        // SELECT * FROM students WHERE students.id=1
        return em.find(Eventi.class, id);
    }

    public void findByIdAndDelete(int id) {
        Eventi e = em.find(Eventi.class, id);

        if (e != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(e);
            transaction.commit();
            System.out.println("Lo studente è stato cancellato correttamente");
        } else {
            System.err.println("Lo studente con l'id " + id + " non è stato trovato");
        }


    }
}
