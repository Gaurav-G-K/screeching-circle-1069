package com.HelpDesk.DOA;

import java.util.List;
import java.util.Scanner;

import com.HelpDesk.Entity.CustomerSupportRepresentative;
import com.HelpDesk.Entity.Feedback;
import com.HelpDesk.Entity.Issues;
import com.HelpDesk.Entity.LoggedIn;
import com.HelpDesk.Exceptions.DuplicateDataException;
import com.HelpDesk.Exceptions.NoRecordFound;
import com.HelpDesk.Exceptions.SomethingWentWrong;
import com.HelpDesk.Service.CustomerSupportRepresentativeService;
import com.HelpDesk.Service.CustomerSupportRepresentativeServiceImpl;
import com.HelpDesk.UI.UI;
import com.HelpDesk.UtilEMF.EMUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class CustomerSupportRepresentativeDAOImpl implements CustomerSupportRepresentativeDAO{

	@Override

	public void addCustomerSupportRepresentative(CustomerSupportRepresentative customerSupportRepresentative) throws DuplicateDataException {
	    EntityManager em = null;
	    
	    try {
	        em = EMUtils.createConection();
	        Query query = em.createQuery("SELECT c FROM CustomerSupportRepresentative c WHERE name = :name");
	        query.setParameter("name", customerSupportRepresentative.getName());
	        
	        List<CustomerSupportRepresentative> results = query.getResultList();
	        
	        if (!results.isEmpty()) {
	            throw new DuplicateDataException("An account with the same name already exists.");
	        }
	        
	        EntityTransaction et = em.getTransaction();
	        et.begin();
	        em.persist(customerSupportRepresentative);
	        et.commit();
	    } catch (PersistenceException e) {
	        throw new DuplicateDataException(e.getMessage());
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }
	}

	@Override
	public void logIn(String name, String password) throws NoRecordFound {
		EntityManager em = null;
		
		try{
			
			em = EMUtils.createConection();
			
			Query query = em.createQuery("SELECT c.id FROM CustomerSupportRepresentative c WHERE name = :name AND password = :password");
			query.setParameter("name", name);
			query.setParameter("password", password);
			List<Integer> list = query.getResultList();
			
			if(list.size()==0) {
				throw new NoRecordFound("***************** Account does not exists *******************");
			}
			
			LoggedIn.userid = list.get(0);
			
			
		}catch(PersistenceException pe) {
			throw new NoRecordFound(pe.getMessage());
		}finally {
			if(em!=null) {
				em.close();
			}
		}
		
		
	}

	@Override
	public List<Issues> viewIssue() throws SomethingWentWrong {
		
		EntityManager em = null;
		List<Issues> list = null;
		try {
			em = EMUtils.createConection();
			Query query = em.createQuery("SELECT i FROM Issues i");
			list = query.getResultList();
			
			if(list.size()==0) {
				System.out.println("**************** Issue list is empty *************");
			}
			return list;
			
		}catch(PersistenceException pe) {
			System.out.println(pe.getMessage());
		}
		finally {
			if(em != null) {
				em.close();
			}
		}
		
		return list;
	}

	@Override
	public void assignToSelf() throws SomethingWentWrong {
		
		EntityManager em = null;
		EntityTransaction et = null;
		try {
			em = EMUtils.createConection();
			CustomerSupportRepresentative representative = em.find(CustomerSupportRepresentative.class,LoggedIn.userid);
			CustomerSupportRepresentativeService service = new CustomerSupportRepresentativeServiceImpl();
			
			service.viewIssue();
			
			Issues issue = em.find(Issues.class, LoggedIn.userid);
			if(issue != null) {
				System.out.println();
				et = em.getTransaction();
				
				et.begin();
				issue.setCustomerSupportRepresentativeService(representative);
				et.commit();
			}
			
		}catch(PersistenceException pe) {
			throw new SomethingWentWrong(pe.getMessage());
		}
		finally {
			if(em != null) {
				em.close();
			}
		}
	}

	@Override
	public void assignToOther(int id) throws SomethingWentWrong {
		
		EntityManager em = null;
		EntityTransaction et = null;
		Scanner sc = new Scanner(System.in);
		
		try {
			em = EMUtils.createConection();
			
			CustomerSupportRepresentative representative = em.find(CustomerSupportRepresentative.class,LoggedIn.userid);
			CustomerSupportRepresentativeService service = new CustomerSupportRepresentativeServiceImpl();
			
			service.viewIssue();
			Issues issue = em.find(Issues.class, id);
			
			if(issue != null) {
				System.out.println();
				et = em.getTransaction();
				
				et.begin();
				issue.setCustomerSupportRepresentativeService(representative);
				et.commit();
			}
			
			
			
		}catch(PersistenceException e) {
			throw new SomethingWentWrong(e.getMessage());
		}finally {
			em.close();
		}
		
	}

	
	@Override
	public void replyToIssue(int id, String reply) throws SomethingWentWrong {
		
		EntityManager em = null;
		EntityTransaction et = null;
		
		
		try {
			em = EMUtils.createConection();
			
			Issues issue = em.find(Issues.class, id);
			if(issue!= null) {
				System.out.println();
				et = em.getTransaction();
				
				et.begin();
				issue.setReply(reply);
				et.commit();
			}
			
		}catch(PersistenceException e) {
			throw new SomethingWentWrong(e.getMessage());
		}finally {
			em.close();
		}
		
	}


	@Override
	public void closeIssue(int id) throws SomethingWentWrong {
	
		EntityManager em = null;
		EntityTransaction et = null;
		try {
			
			em = EMUtils.createConection();
			
			Issues issue = em.find(Issues.class, id);
			
			if(issue != null) {
				System.out.println();
				et = em.getTransaction();
				
				et.begin();
				issue.setStatus(Status.CLOSED);
				et.commit();
			}
			
			
		}catch(PersistenceException pe) {
			throw new SomethingWentWrong(pe.getMessage());
		}finally {
			if(em!=null) {
				em.close();
			}
		}
		
	}


	@Override
	public List<Feedback> viewFeedback() throws SomethingWentWrong, NoRecordFound{
		
		EntityManager em = null;
		EntityTransaction et = null;
		List<Feedback> list = null;
		try {
			em = EMUtils.createConection();
			Query query = em.createQuery("SELECT f From Feedback f");
			list = query.getResultList();
			if(list.size()==0) {
				throw new NoRecordFound("================= Feedback not available =================");
			}
		}catch(PersistenceException pe) {
			throw new SomethingWentWrong(pe.getMessage());
		}finally {
			if(em != null) {
				em.close();
			}
		}
		
		
		return list;
	}

	@Override
	public void deleteAccount() throws SomethingWentWrong, NoRecordFound {
		
		EntityManager em = null;
		EntityTransaction et = null;
		
		try {
			
			em = EMUtils.createConection();
			CustomerSupportRepresentative sp = em.find(CustomerSupportRepresentative.class, LoggedIn.userid); 
			
			et = em.getTransaction();
			et.begin();
			em.remove(sp);
			et.commit();
			
			
		}catch(PersistenceException e) {
			throw new SomethingWentWrong(e.getMessage());
		}
		finally {
			em.close();
		}
		
		
	}


	@Override
	public void deleteIssue(int id) throws SomethingWentWrong, NoRecordFound {
		
		EntityManager em = null;
		EntityTransaction et = null;
		
		try {
			
			em = EMUtils.createConection();
			Issues issue = em.find(Issues.class, id); 
			
			if(issue!=null) {
				et = em.getTransaction();
				et.begin();
				em.remove(issue);
				et.commit();
			}else {
				throw new NoRecordFound("Issue is not found for given id");
			}
			
			
		}catch(PersistenceException e) {
			throw new SomethingWentWrong(e.getMessage());
		}
		finally {
			em.close();
		}
	}

}
