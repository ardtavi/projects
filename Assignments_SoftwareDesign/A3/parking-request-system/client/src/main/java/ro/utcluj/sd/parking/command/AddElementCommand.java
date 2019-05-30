package ro.utcluj.sd.parking.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ro.utcluj.sd.RequestToServer;
import ro.utcluj.sd.dto.RequestDTO;
import ro.utcluj.sd.parking.viewmodel.MyRequestViewModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AddElementCommand implements Command<String> {

    private final MyRequestViewModel vm;
   // private final int id;
    private String year;
    private String name;

    public AddElementCommand(MyRequestViewModel vm, String year) {
        this.vm = vm;
        //this.id = id;
      this.year = year;
      this.name=name;
    }

    public AddElementCommand(MyRequestViewModel vm){
        this.vm = vm;
    }

    @Override
    public String execute() {
        RequestToServer myRequest = new RequestToServer();
        //myRequest.getParams().put("id",Integer.valueOf(id).toString());
        myRequest.getParams().put("year",year);
        myRequest.getParams().put("name",name);
        myRequest.getParams().put("command","create");

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(myRequest);

        try (Socket clientSocket = new Socket("127.0.0.1",1997)){
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println(json);
            String fromServer = in.readLine();
            System.out.println(fromServer);
            RequestDTO requestDTO = gson.fromJson(fromServer,RequestDTO.class);
            System.out.println(requestDTO);
            if(requestDTO == null) {
                return null;
            }
            else return "request"+year+name;

        } catch (IOException e1) {
            e1.printStackTrace();
        }


        vm.addNewElement();
        return null;
    }

    @Override
    public String undo() {
        vm.removeElement();
        return null;
    }
}
