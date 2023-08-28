package com.HelpDesk.DOA;

import java.util.List;

import com.HelpDesk.Entity.Customer;
import com.HelpDesk.Entity.Feedback;
import com.HelpDesk.Entity.Issues;
import com.HelpDesk.Entity.LoggedIn;
import com.HelpDesk.Exceptions.DuplicateDataException;
import com.HelpDesk.Exceptions.NoRecordFound;
import com.HelpDesk.Exceptions.SomethingWentWrong;
import com.HelpDesk.UtilEMF.EMUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class CustomerDAOImpl implements CustomerDAO{

	@Override
	public void addCustomer(Customer customer) throws DuplicateDataException, SomethingWentWrong {
		
		EntityManager em = null;
		
		try {
			em = EMUtils.createConection();
			Query query = em.createQuery(" SELECT Count(c) From Customer c WHERE name = :name");
							query.setParameter("name", customer.getName());
			
			
			if((long) query.getSingleResult()>0) {
				throw new DuplicateDataException("************ Customer already exists ************");
			}
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.persist(customer);
			et.commit();
		
		}
		catch(PersistenceException pe) {
			throw new SomethingWentWrong(pe.getMessage());
		}
		finally {
			if(em!=null) {
				em.close();
			}
		}
		
	}

	@Override
	public void logIn(String name, String password) throws NoRecordFound {
		
		EntityManager em = null;
		
		try {
			em = EMUtils.createConection();
			
			Query query = em.createQuery("SELECT c.id FROM Customer c WHERE name = :name AND password = :password");
			
			query.setParameter("name", name);
			query.setParameter("password", password);
			
			List<Integer> list = query.getResultList();
			
			if(list.size() == 0) {
				throw new NoRecordFound("************** User not found,Register as new customer **************");
			}
			LoggedIn.userid = list.get(0);
		}catch(PersistenceException pe) {
			System.out.println(pe.getMessage());
		}finally {
			if(em!= null) {
				em.close();
			}
		}
		
	}


	@Override
	public void raiseIssue(String issue) throws SomethingWentWrong {
		
		EntityManager em = null;
		
		try {
			em = EMUtils.createConection();
			
			Customer cust = em.find(Customer.class, LoggedIn.userid);
			
			Issues newIssue = new Issues(issue, Status.OPEN,cust,null,null);
			
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(newIssue);
		et.commit();
			
			
		}catch(PersistenceException pe) {
			throw new SomethingWentWrong(pe.getMessage());
		}
		finally {
			if(em!=null) {
				em.close();
			}
		}
		
	}

	
	@Override
	public void giveFeedback(int id, String feedback, int rating) throws SomethingWentWrong {
		
		EntityManager em = null;
		EntityTransaction et = null;
		try {
			em = EMUtils.createConection();
			
			Issues issue = em.find(Issues.class, id);
			Feedback fb = new Feedback(feedback,rating,issue);
			
			et = em.getTransaction();
			
			et.begin();
			em.persist(fb);
			et.commit();
			
			
		}catch(PersistenceException pe) {
			throw new SomethingWentWrong(pe.getMessage());
		}finally {
			if(em!=null) {
				em.close();
			}
		}
		
	}

	@Override
	public void deleteAccount() throws SomethingWentWrong {
		
		EntityManager em = null;
		EntityTransaction et = null;
		
		try {
			em = EMUtils.createConection();
			Customer cust = em.find(Customer.class, LoggedIn.userid);
			
			et = em.getTransaction();
			et.begin();
			em.remove(cust);
			et.commit();
					
			
		}catch(PersistenceException e) {
			throw new SomethingWentWrong(e.getMessage());
		}finally {
			em.close();
		}
		
	}


	

}
