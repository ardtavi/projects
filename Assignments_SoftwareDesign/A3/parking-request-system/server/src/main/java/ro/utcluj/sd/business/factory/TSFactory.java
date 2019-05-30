package ro.utcluj.sd.business.factory;

import ro.utcluj.sd.RequestToServer;
import ro.utcluj.sd.business.LoginTS;
import ro.utcluj.sd.business.RequestTS;
import ro.utcluj.sd.business.TransactionScript;

public class TSFactory {

    public static TransactionScript create(RequestToServer requestToServer) {
        String command = requestToServer.getParams().get("command");
        switch (command) {
            case "login":
                String username = requestToServer.getParams().get("username");
                String password = requestToServer.getParams().get("password");
                return new LoginTS(username, password);

            case "create":
                String year = requestToServer.getParams().get("year");
                return new RequestTS(year);

            default:
                return null;
        }
    }
}
