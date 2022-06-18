package io.quarkus.benchmark.cdi;

import org.hibernate.SessionFactory;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Typed;
import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Singleton
public class HibernateOrmNativeComponents {

	@PersistenceUnit
	EntityManagerFactory entityManagerFactory;

	@Singleton
	@Typed(SessionFactory.class)
	@Produces
	SessionFactory extractSessionFactory() {
		return entityManagerFactory.unwrap( SessionFactory.class );
	}

}
