package ro.utcluj.view.model.users;

import ro.utcluj.view.model.parking.Request;

import java.util.List;

class Employee extends User {

	public Employee(String username, String password) {
		super(username, password, User.Type.EMPLOYEE);
	}

	public List<Request> searchByRequest(String request) {
		return null;
	}

	public List<Request> searchByName(String name) {
		return null;
	}

	public List<Request> searchByStatus(int status) {
		return null;
	}

}
