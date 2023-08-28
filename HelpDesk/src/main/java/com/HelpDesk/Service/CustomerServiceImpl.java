package com.HelpDesk.Service;

import java.util.List;
import java.util.Scanner;

import com.HelpDesk.DOA.CustomerDAO;
import com.HelpDesk.DOA.CustomerDAOImpl;
import com.HelpDesk.DOA.CustomerSupportRepresentativeDAO;
import com.HelpDesk.DOA.Status;
import com.HelpDesk.DOA.CustomerSupportRepresentativeDAOImpl;
import com.HelpDesk.Entity.Customer;
import com.HelpDesk.Entity.Issues;
import com.HelpDesk.Entity.LoggedIn;
import com.HelpDesk.Exceptions.DuplicateDataException;
import com.HelpDesk.Exceptions.NoRecordFound;
import com.HelpDesk.Exceptions.SomethingWentWrong;

public class CustomerServiceImpl implements CustomerService{

	@Override
	public void addCustomer(Customer customer) throws DuplicateDataException,SomethingWentWrong {
		// TODO Auto-generated method stub
		CustomerDAO dao = new CustomerDAOImpl();
		dao.addCustomer(customer);
	}

	@Override
	public void logIn(String username, String password) throws NoRecordFound {
		
		CustomerDAO dao = new CustomerDAOImpl();
		dao.logIn(username,password);
		
	}

	@Override
	public void raiseIssue(String issue) throws SomethingWentWrong {
		CustomerDAO dao = new CustomerDAOImpl();
		dao.raiseIssue(issue);
		
	}

	@Override
	public void giveFeedback() throws SomethingWentWrong {
		
		Scanner sc = new Scanner(System.in);
		CustomerDAO custDAO = new CustomerDAOImpl();
		
		CustomerSupportRepresentativeDAO csrDAO = new CustomerSupportRepresentativeDAOImpl();
		
		List<Issues> list = csrDAO.viewIssue();
		
		for(Issues i : list) {
			if(i.getStatus().equals(Status.CLOSED) && i.getCustomer().getId() == LoggedIn.userid) {
				System.out.println("================================================");
				System.out.println("Issue id : "+i.getId()+"  |  "+"Issue : "+ i.getIssue()+"  |  "+"Reply : "+i.getReply());
			}
		}
		System.out.println("================================================");
		System.out.println("Select issue by id to provide feedback");
		int id = sc.nextInt();
		
		System.out.println("Provide your feedback");
		sc.nextLine();
		String feedback = sc.nextLine();
		
		System.out.println("Enter your rating (1 to 5)");
		int rating  = sc.nextInt();
		
		custDAO.giveFeedback(id,feedback,rating);
		
		
	}

	@Override
	public void deleteAccount() throws SomethingWentWrong {
		
		CustomerDAO dao = new CustomerDAOImpl();
		dao.deleteAccount();
		
	}

}
