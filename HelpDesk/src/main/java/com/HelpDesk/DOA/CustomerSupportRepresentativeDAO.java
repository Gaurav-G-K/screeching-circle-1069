package com.HelpDesk.DOA;

import java.util.List;

import com.HelpDesk.Entity.CustomerSupportRepresentative;
import com.HelpDesk.Entity.Feedback;
import com.HelpDesk.Entity.Issues;
import com.HelpDesk.Exceptions.DuplicateDataException;
import com.HelpDesk.Exceptions.NoRecordFound;
import com.HelpDesk.Exceptions.SomethingWentWrong;
import com.HelpDesk.Service.CustomerSupportRepresentativeService;

public interface CustomerSupportRepresentativeDAO {

	void addCustomerSupportRepresentative(CustomerSupportRepresentative csr) throws DuplicateDataException;

	void logIn(String username, String password) throws NoRecordFound;

	List<Issues> viewIssue() throws SomethingWentWrong;

	void assignToSelf()throws SomethingWentWrong;

	void assignToOther(int id)throws SomethingWentWrong;

	void replyToIssue(int id, String reply) throws SomethingWentWrong;

	void closeIssue(int id) throws SomethingWentWrong;

	List<Feedback> viewFeedback() throws SomethingWentWrong,NoRecordFound;

	void deleteAccount() throws SomethingWentWrong, NoRecordFound;

	void deleteIssue(int id) throws SomethingWentWrong, NoRecordFound;

	
	

}
