package org.example;

import com.github.javafaker.Faker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestione_eventi");

    public static void main(String[] args) {
        Random rndm = new Random();
        Faker faker = new Faker();

        List<Eventi> list = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        try {
            Date d1 = new Date(2020, 01, 01);
            EventoDAO sd = new EventoDAO(em);
            Supplier<Eventi> eventSupplier = () -> new Eventi(faker.artist().name(), d1, "ciao", tipoEvento.randomTipoEvento(), rndm.nextInt(100, 1000));
            System.out.println("ciao");
            for (int i = 0; i < 5; i++) {
                list.add(eventSupplier.get());
            }
            System.out.println(list.size());
            for (int i = 0; i < list.size(); i++) {
                sd.save(list.get(i));
            }

            Eventi e = sd.findById(2);
            System.out.println(e);

            sd.findByIdAndDelete(5);

        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            // Ricordiamoci alla fine di tutto di chiudere sia entitymanager che entitymanager factory
            em.close();
            emf.close();
        }
    }
}
