package com.HelpDesk.Service;

import java.util.List;
import java.util.Scanner;

import com.HelpDesk.DOA.CustomerSupportRepresentativeDAO;
import com.HelpDesk.DOA.Status;
import com.HelpDesk.DOA.CustomerSupportRepresentativeDAOImpl;
import com.HelpDesk.Entity.CustomerSupportRepresentative;
import com.HelpDesk.Entity.Feedback;
import com.HelpDesk.Entity.Issues;
import com.HelpDesk.Entity.LoggedIn;
import com.HelpDesk.Exceptions.DuplicateDataException;
import com.HelpDesk.Exceptions.NoRecordFound;
import com.HelpDesk.Exceptions.SomethingWentWrong;

public class CustomerSupportRepresentativeServiceImpl implements CustomerSupportRepresentativeService{

	@Override
	public void addCustomerSupportRepresentative(CustomerSupportRepresentative csr) throws DuplicateDataException {
		
		CustomerSupportRepresentativeDAO dao = new CustomerSupportRepresentativeDAOImpl();
		dao.addCustomerSupportRepresentative(csr);
		
	}

	@Override
	public void logIn(String username, String password) throws NoRecordFound {
		
		CustomerSupportRepresentativeDAO dao = new CustomerSupportRepresentativeDAOImpl();
		dao.logIn(username,password);
		
	}
	
	@Override
	public void viewStatus() throws SomethingWentWrong {
		
		CustomerSupportRepresentativeDAO dao = new CustomerSupportRepresentativeDAOImpl();
		List<Issues> list = dao.viewIssue();
		for(Issues i : list) {
			if(i.getCustomer().getId() == LoggedIn.userid) {
				System.out.println(i.getId()+" "+i.getIssue()+" "+i.getStatus());
			}
		}
	}


	@Override
	public void viewIssue() throws SomethingWentWrong{
		
		CustomerSupportRepresentativeDAO dao = new CustomerSupportRepresentativeDAOImpl();
		
		List<Issues> list = dao.viewIssue();
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
		if(list.size()>0) {
		for(Issues i : list) {
			if(i.getCustomerSupportRepresentative()==null) {
			System.out.println("Issue id : "+i.getId()+ "  |  " + "Customer_id : " + i.getCustomer().getId()+ "  |  "  + "Issue : " + i.getIssue()+ "  |  "  + "Status : " + i.getStatus()+ "  |  "  +"Assigned : "+ "Not Yet");
				
			}
			else {
			System.out.println("Issue id : "+i.getId()+ "  |  "  + "Customer_id : " + i.getCustomer().getId() + "  |  " + "Issue : " + i.getIssue()+ "  |  "  + "Status : " + i.getStatus()+ "  |  "  +"Assigned : "+ i.getCustomerSupportRepresentative().getId());
			
			}
		  }
			
		}
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
		
	}

	@Override
	public void assignToSelf() throws SomethingWentWrong {
		
		CustomerSupportRepresentativeDAO dao = new CustomerSupportRepresentativeDAOImpl();
		dao.assignToSelf();
		
	}

	@Override
	public void assignToOther(int id) throws SomethingWentWrong {
		
		CustomerSupportRepresentativeDAO dao = new CustomerSupportRepresentativeDAOImpl();
		dao.assignToOther(id);
		
	}

	@Override
	public void replyToIssue(int id, Scanner sc) throws SomethingWentWrong {
		
		CustomerSupportRepresentativeDAO dao = new CustomerSupportRepresentativeDAOImpl();
		List<Issues> list = dao.viewIssue();
		
		for(Issues i : list) {
			if(i.getCustomerSupportRepresentative() != null && i.getCustomerSupportRepresentative().getId() == LoggedIn.userid && i.getStatus() == Status.OPEN) {
				System.out.println(i.getId() + "  " + i.getIssue());
			}
		}
		
		System.out.println("Entery issue id to reply");
		id = sc.nextInt();
		
		sc.nextLine();
		System.out.println("Enter reply for issue");
		String reply = sc.nextLine();
		
		dao.replyToIssue(id,reply);
		
	}


	@Override
	public void closeIssue(int id) throws SomethingWentWrong {
		
		CustomerSupportRepresentativeDAO dao = new CustomerSupportRepresentativeDAOImpl();
		List<Issues> list = dao.viewIssue();
		
		for(Issues i: list) {
			if(i.getCustomerSupportRepresentative()!= null && i.getCustomerSupportRepresentative().getId() == LoggedIn.userid && i.getStatus() == Status.OPEN) {
				System.out.println("=====================================================");
				System.out.println(i.getId() + "  "+ i.getIssue());
				System.out.println("=====================================================");
			}
		}
		dao.closeIssue(id);
		
	}

	@Override
	public List<Feedback> viewFeedback() throws SomethingWentWrong,NoRecordFound {
		
		CustomerSupportRepresentativeDAO dao = new CustomerSupportRepresentativeDAOImpl();
		return dao.viewFeedback();
	}

	@Override
	public void deleteAccount() throws SomethingWentWrong, NoRecordFound {
		
		CustomerSupportRepresentativeDAO dao = new CustomerSupportRepresentativeDAOImpl();
		dao.deleteAccount();
		
	}

	@Override
	public void deleteIssue(int id) throws SomethingWentWrong, NoRecordFound {
		
		CustomerSupportRepresentativeDAO dao = new CustomerSupportRepresentativeDAOImpl();
		dao.deleteIssue(id);
		
	}

}
