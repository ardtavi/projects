package ro.utcluj.view.controller.command;

import java.util.Date;


import ro.utcluj.view.model.parking.Request;
import ro.utcluj.view.model.users.Register;

public class AddRequestCommand implements Command<Register>{
    private final String request1;
    private final String name;
    private final String date;
    private final String    status;
    
    public AddRequestCommand(String request,String name, String date, String status)
    {
    	this.request1=request;
    	this.name=name;
    	this.date=date;
    	this.status=status;
    }
    
	@Override
	public Register execute() {
		  Request request= new Request(request1, name, date, status);
		 // model.addRequest(request);
		return null;
	}
	@Override
	public Register undo() {
		// TODO Auto-generated method stub
		return null;
	}
    

}
