package com.hotelalura.util;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Objects;

public class JpaUtil {

    private static final String PERSISTENCE_UNIT_NAME = "hotel-unit";
    private static EntityManagerFactory entityManagerFactory;

    public static EntityManager getEntityManager() {
        if (Objects.isNull(entityManagerFactory)) {
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return entityManagerFactory.createEntityManager();
    }

    public static void closeEntityManagerFactory() {
        if (Objects.nonNull(entityManagerFactory)) {
            entityManagerFactory.close();
        }
    }
}