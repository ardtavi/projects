package ro.utcluj.sd;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ro.utcluj.sd.business.TransactionScript;
import ro.utcluj.sd.business.factory.TSFactory;
import ro.utcluj.sd.dao.RequestDao;
import ro.utcluj.sd.dto.Dto;
import ro.utcluj.sd.dto.RequestDTO;
import ro.utcluj.sd.entities.Car;
import ro.utcluj.sd.entities.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {

    private static Server instance;

    private final Gson gson;
    private List<Socket> observers;


    private Server() {
        observers = Collections.synchronizedList(new ArrayList<>());
        gson = new GsonBuilder().create();
    }

    public static Server getInstance() {
        if (instance == null) instance = new Server();
        return instance;
    }

    public void start() {
        System.out.println("Listening for connections");
        try (ServerSocket serverSocket = new ServerSocket(1997)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Thread thread = new Thread(() -> serveRequest(clientSocket));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void serveRequest(Socket clientSocket) {
        System.out.println("Serving Request");
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            //receive request data
            String jsonFromClient = in.readLine(); // if you use pretty print, you need to read multiple lines
            System.out.println("KAPTAM "+jsonFromClient);
            RequestToServer requestToServer = gson.fromJson(jsonFromClient, RequestToServer.class);

            String command = requestToServer.getParams().get("command");
            if ("subscribeToNotification".equals(command)) { //special case
                System.out.println("--------------------Command");
                observers.add(clientSocket);
                return;
            }

            if("create".equals(command))
            {
                System.out.println("EEEEEEEEEEEE    "+requestToServer.getParams().get("year"));

                Request request = new Request();
                request.setYear(requestToServer.getParams().get("year"));
//                request.setCar(new Car());
                RequestDao requestDao = new RequestDao();
                requestDao.save(request);
                System.out.println(jsonFromClient);
                //send response to client
                out.println(jsonFromClient);
                clientSocket.close();
                return;
            }

            // process request
            TransactionScript transactionScript = TSFactory.create(requestToServer);
            Dto result = transactionScript.execute();

            //send response to client
            out.println(gson.toJson(result));
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Socket> getObservers() {
        return observers;
    }
}
