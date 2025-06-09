package com.evaluacion;

import java.util.Scanner;

import com.evaluacion.alquiler.model.Cliente;
import com.evaluacion.alquiler.model.Pelicula;
import com.evaluacion.alquiler.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class App {

    /** Pequeña pausa para ver el estado de la BD en cada paso */
    private static void pausar(Scanner sc) {
        sc.nextLine();
    }

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {

            // 1. Obtener EntityManager
            EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
            EntityTransaction tx = em.getTransaction();

            /*
             * ------------------------------------------------------------------
             * CREATE – 3 clientes de prueba
             * ------------------------------------------------------------------
             */
            tx.begin();
            // Usamos constructor sin id, JPA asignará automáticamente el idCliente
            Cliente c1 = new Cliente(1L, "Camila", "camila.rojas@gmail.com");
            Cliente c2 = new Cliente(2L, "Luis", "luis.mendoza@gmail.com");
            Cliente c3 = new Cliente(5L, "Natalia", "natalia.perez@gmail.com");
            Cliente c4 = new Cliente(4L, "Andrés", "andres.lopez@gmail.com");

            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            em.persist(c4);
            tx.commit();
            System.out.println("\nClientes creados:");
            System.out.println(c1);
            System.out.println(c2);
            System.out.println(c3);
            System.out.println(c4);

            pausar(sc);

            /*
             * ------------------------------------------------------------------
             * CREATE – 3 películas de prueba
             * ------------------------------------------------------------------
             */
            tx.begin();
            // Usamos constructor sin id, JPA asignará automáticamente el idPelicula
            Pelicula p1 = new Pelicula("Interstellar", "Ciencia Ficción", 5, 18.99);
            Pelicula p2 = new Pelicula("Coco", "Animación", 4, 11.50);
            Pelicula p3 = new Pelicula("El Conjuro", "Terror", 10, 9.00);
            Pelicula p4 = new Pelicula("Zootopia", "Animación", 6, 7.50);

            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.persist(p4);
            tx.commit();

            System.out.println("\nPelículas creadas:");
            System.out.println(p1);
            System.out.println(p2);
            System.out.println(p3);
            System.out.println(p4);

            pausar(sc);

            em.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cierra el factory creado en JPAUtil
            JPAUtil.shutdown();
            System.out.println("\n>>> APLICACIÓN FINALIZADA <<<");
        }
    }
}
