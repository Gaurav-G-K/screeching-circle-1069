package com.HelpDesk.UI;

import java.util.List;
import java.util.Scanner;

import com.HelpDesk.Entity.Customer;
import com.HelpDesk.Entity.CustomerSupportRepresentative;
import com.HelpDesk.Entity.Feedback;
import com.HelpDesk.Exceptions.DuplicateDataException;
import com.HelpDesk.Exceptions.NoRecordFound;
import com.HelpDesk.Exceptions.SomethingWentWrong;
import com.HelpDesk.Service.CustomerService;
import com.HelpDesk.Service.CustomerServiceImpl;
import com.HelpDesk.Service.CustomerSupportRepresentativeService;
import com.HelpDesk.Service.CustomerSupportRepresentativeServiceImpl;

public class UI 
{
    public static void main( String[] args )
    {
      Scanner sc = new Scanner(System.in); 
    	int choice;
    	
    	do {
    		System.out.println("=====================================================");
    		System.out.println("| Press 1 to register customer                      |");
        	System.out.println("| Press 2 to login customer                         |");
        	System.out.println("| Press 3 to Customer support representative        |");
        	System.out.println("| Press 4 to login support representative           |");
        	System.out.println("| Press 0 to Exit                                   |");
        	System.out.println("=====================================================");
        	
        	System.out.println("Enter your choice : ");
        	choice = sc.nextInt();
        	switch(choice) {
        	case 1:
        		addCustomer(sc);
        		break;
        	case 2:
        		loginCustomer(sc);
        		break;
        	case 3:
        		addCustomerSupportRepresentative(sc);
        		break;
        	case 4: 
        		loginCSR(sc);
        		break;
        	case 0:
        		System.out.println("=====================================================");
        		System.out.println("             Thankyou visit again");
        		System.out.println("=====================================================");
        		break;
        	default:
        		System.out.println("=====================================================");
        		System.out.println("             Please enter valid choice");
        		System.out.println("=====================================================");
        		
        	}
    	}while(choice!=0);
    }

    public static void addCustomer(Scanner sc) {
		
		System.out.println("Enter email");
		String email = sc.next();
		
		System.out.println("Enter username");
		String username = sc.next();
		
		System.out.println("Enter password");
		String password = sc.next();
		
		Customer customer = new Customer(email, username, password);
		CustomerService service = new CustomerServiceImpl();
		
		try {
			service.addCustomer(customer);
			System.out.println("=====================================================");
			System.out.println("        Customer registered successfully");
			System.out.println("=====================================================");
		} catch (DuplicateDataException | SomethingWentWrong e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
    public static void loginCustomer(Scanner sc) {
	
		System.out.println("Enter username");
		String username = sc.next();
		
		System.out.println("Enter password");
		String password = sc.next();
		
		CustomerService service = new CustomerServiceImpl();
		
		try {
			service.logIn(username,password);
			customerMenu(sc);
		} catch (NoRecordFound e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}	
    }
	private static void customerMenu(Scanner sc) {
		// TODO Auto-generated method stub
		
		int choice;
		do {
			System.out.println("=====================================================");
			System.out.println("| Press 1 to Raise Issue                            |");
			System.out.println("| Press 2 to view issue status                      |");
			System.out.println("| Press 3 to provide feedback                       |");
			System.out.println("| Press 0 to log_out                                |");
			System.out.println("=====================================================");
			
			System.out.println("Enter yuour choice as customer : ");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				raiseIssue(sc);
				break;
			case 2:
				viewStatus();
				break;
			case 3:
				giveFeedback();
				break;
			case 0:
				System.out.println("=====================================================");
	    		System.out.println("              Thankyou visit again");
	    		System.out.println("=====================================================");
	    		break;
	    	default:
	    		System.out.println("*****************************************************");
	    		System.out.println("     Please enter valid choice");
	    		System.out.println("*****************************************************");
			}
		}while(choice!=0);
	}
	private static void raiseIssue(Scanner sc) {
		System.out.println("Enter issue");
		sc.nextLine();
		String issue = sc.nextLine();	
		CustomerService service = new CustomerServiceImpl();	
		try {
			service.raiseIssue(issue);
			System.out.println("===========Issue is raised===============");
			} 
		catch (SomethingWentWrong e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
	private static void viewStatus() {
		CustomerSupportRepresentativeService service = new CustomerSupportRepresentativeServiceImpl();
		try {
			service.viewStatus();
			System.out.println("============Issue status================");
		} catch (SomethingWentWrong e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static void giveFeedback() {
		
		CustomerService service = new CustomerServiceImpl();
		
		try {
			service.giveFeedback();
			System.out.println("=====================================================");
			System.out.println("                 Fedback is recorded");
			System.out.println("=====================================================");
		} catch (SomethingWentWrong e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
	}
	public static void addCustomerSupportRepresentative(Scanner sc) {
		
		System.out.println("Enter email");
		String email = sc.next();
		
		System.out.println("Enter username");
		String username = sc.next();
		
		System.out.println("Enter password");
		String password = sc.next();
		CustomerSupportRepresentative CustomerSupportRepresentative = new CustomerSupportRepresentative(email, username, password);
		CustomerSupportRepresentativeService service = new CustomerSupportRepresentativeServiceImpl();
		try {
			service.addCustomerSupportRepresentative(CustomerSupportRepresentative);
			System.out.println("===================================================");
			System.out.println("Account registered successfully");
		}
		catch(DuplicateDataException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public static void loginCSR(Scanner sc) {
		
		System.out.println("Enter username");
		String username = sc.next();
		
		System.out.println("Enter password");
		String password = sc.next();
		
		CustomerSupportRepresentativeService service = new CustomerSupportRepresentativeServiceImpl();
		
		try {
			service.logIn(username,password);
			supportMenu(sc);
		}catch(NoRecordFound e) {
			System.out.println(e.getMessage());
		}
		
	}

	private static void supportMenu(Scanner sc) {
		
		
		
		int choice;
		
		do {
			System.out.println("======================================");
			System.out.println("| Press 1 to view issue              |");
			System.out.println("| Press 2 to assign issue            |");
			System.out.println("| Press 3 to Reply to issue          |");
			System.out.println("| Press 4 to Close the issue         |");
			System.out.println("| Press 5 to view feedback           |");
			System.out.println("| Press 0 to logout                  |");
			System.out.println("======================================");
			
			System.out.println("Enter your choice as CSR : ");
			choice = sc.nextInt();
			
			
			switch (choice) {
			case 1:
				viewIssue();
				break;
			case 2:
				assignIssue(sc);
				break;
			case 3:
				replyToIssues(sc);
				break;
			case 4:
				closeIssue(sc);
				break;
			case 5:
                viewFeedback();
				break;
			case 0:
				System.out.println("====================================");
				System.out.println("       Thankyou, visit again");
				System.out.println("====================================");
				break;
			default:
				System.out.println("*************************************");
				System.out.println("         Invalid input");
				System.out.println("*************************************");
				
			}
			
			
		}while(choice!=0);
		
	}
		

	private static void viewIssue() {
		
		CustomerSupportRepresentativeService service = new CustomerSupportRepresentativeServiceImpl();
		try {
			System.out.println("================================================");
			service.viewIssue();
			System.out.println("================================================");
		} catch (SomethingWentWrong e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	
	private static void assignIssue(Scanner sc) {
		
		int choice;
		do {
			System.out.println("=================================");
			System.out.println("| Press 1 to Assign to self     |");
			System.out.println("| Press 2 to Assign to other    |");
			System.out.println("| Press 0 to exit               |");
			System.out.println("=================================");
			System.out.println("Enter your choice to assign : ");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				assignToSelf();
				break;
			case 2:
				assignToOther(sc);
				break;
			case 0:
				System.out.println("==========================");
				System.out.println("         Thankyou");
				System.out.println("==========================");
				break;
			default:
				System.out.println("============================");
				System.out.println("       Invalid input");
				System.out.println("============================");
				
			}
			
		}while(choice!=0);
		
	}

	private static void assignToSelf() {
		CustomerSupportRepresentativeService service = new CustomerSupportRepresentativeServiceImpl();
		try {
			service.assignToSelf();
			System.out.println("==================================================");
			System.out.println("    Issue assigned to self successfully");
			System.out.println("==================================================");
		}catch(SomethingWentWrong e) {
			e.getMessage();
		}
		
	}
	
	private static void assignToOther(Scanner sc) {
		System.out.println("Enter id to assign : ");
		int id = sc.nextInt();
		
		CustomerSupportRepresentativeService service = new CustomerSupportRepresentativeServiceImpl();
		try {
			service.assignToOther(id);
			System.out.println("==================================================");
			System.out.println("     Issue assigned to entered id successfully");
			System.out.println("==================================================");
		}catch(SomethingWentWrong e) {
			e.getMessage();
		}
		
	}

	private static void replyToIssues(Scanner sc) {
		
		int id = 0;
		CustomerSupportRepresentativeService service = new CustomerSupportRepresentativeServiceImpl();
		
		try {
			service.replyToIssue(id,sc);
			System.out.println("=====================================================");
			System.out.println("          Reply to the issue is submitted");
			System.out.println("====================================================="); 
		} catch (SomethingWentWrong e) {
			
			e.printStackTrace();
		}
		
	}
	
	private static void closeIssue(Scanner sc) {
		System.out.println("=====================================================");
		System.out.println("Enter issue id to close");
		int id = sc.nextInt();
		CustomerSupportRepresentativeService service = new CustomerSupportRepresentativeServiceImpl();
		try {
			service.closeIssue(id);
			System.out.println("=====================================================");
			System.out.println("                 Issue is closed");
			System.out.println("=====================================================");
		} catch (SomethingWentWrong e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void viewFeedback() {
		
		CustomerSupportRepresentativeService service = new CustomerSupportRepresentativeServiceImpl();
		try {
			System.out.println("===================Feedback=====================");
			List<Feedback> list=service.viewFeedback();
			for(Feedback f :list)
				System.out.println(f);
			System.out.println("================================================");
		} catch (SomethingWentWrong | NoRecordFound e) {
			System.out.println(e.getMessage());
		}
		
	}
		
}

