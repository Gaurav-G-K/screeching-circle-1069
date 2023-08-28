package com.HelpDesk.Service;

import com.HelpDesk.Entity.Customer;
import com.HelpDesk.Exceptions.DuplicateDataException;
import com.HelpDesk.Exceptions.NoRecordFound;
import com.HelpDesk.Exceptions.SomethingWentWrong;

public interface CustomerService {

	void addCustomer(Customer customer) throws DuplicateDataException, SomethingWentWrong;

	void logIn(String username, String password) throws NoRecordFound;

	void raiseIssue(String issue) throws SomethingWentWrong;

	void giveFeedback() throws SomethingWentWrong;

	void deleteAccount() throws SomethingWentWrong;

}
