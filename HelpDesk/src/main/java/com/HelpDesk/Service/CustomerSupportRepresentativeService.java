package com.HelpDesk.Service;

import java.util.List;
import java.util.Scanner;

import com.HelpDesk.Entity.CustomerSupportRepresentative;
import com.HelpDesk.Entity.Feedback;
import com.HelpDesk.Exceptions.DuplicateDataException;
import com.HelpDesk.Exceptions.NoRecordFound;
import com.HelpDesk.Exceptions.SomethingWentWrong;

public interface CustomerSupportRepresentativeService {

	void addCustomerSupportRepresentative(CustomerSupportRepresentative csr) throws DuplicateDataException;

	void logIn(String username, String password ) throws NoRecordFound;

	void viewStatus() throws SomethingWentWrong;

	void viewIssue() throws SomethingWentWrong;

	void assignToSelf() throws SomethingWentWrong;

	void assignToOther(int id) throws SomethingWentWrong;

	void replyToIssue(int id, Scanner sc) throws SomethingWentWrong;

	void closeIssue(int id) throws SomethingWentWrong;

	List<Feedback> viewFeedback() throws SomethingWentWrong, NoRecordFound;

	void deleteAccount() throws SomethingWentWrong, NoRecordFound;

	void deleteIssue(int id) throws SomethingWentWrong, NoRecordFound;



}
