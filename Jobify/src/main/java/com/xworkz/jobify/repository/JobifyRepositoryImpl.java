package com.xworkz.jobify.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.transaction.Transaction;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.xworkz.jobify.dto.JobifyEntity;

@Repository
public class JobifyRepositoryImpl implements JobifyRepository {

	@PersistenceUnit
	private EntityManagerFactory emf;
	public boolean updateLoginAttemptByEmail;

	@Override
	@Transactional
	public boolean save(JobifyEntity entity) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();
			entity = em.merge(entity);
			em.persist(entity);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();

		}
		System.out.println(entity);

		return false;
	}

	@Override
	public JobifyEntity findByEmail(String email) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<JobifyEntity> foundData = em.createNamedQuery("findByEmail", JobifyEntity.class);
		foundData.setParameter("eq", email);
		try {
			return foundData.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean updateLoginAttemptByEmail(int loginAttempts, String email) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();
			TypedQuery<JobifyEntity> dataUpdate = em.createNamedQuery("updateLoginAttemptByEmail", JobifyEntity.class);
			dataUpdate.setParameter("up", loginAttempts);
			dataUpdate.setParameter("email", email);

			int updatedRows = dataUpdate.executeUpdate();
			transaction.commit();

			return updatedRows > 0;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		} finally {
			em.close();
		}

	}

}
