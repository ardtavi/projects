package com.example.springdemo.RMI2;

import com.yrrhelp.grpc.User;
import com.yrrhelp.grpc.userGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GrpcClient {

    private static View view = new View();

    public static void main(String[] args){



        view.addAdaugareListener(new AdaugareListener());
        view.addAdaugareListener2(new AdaugareListener2());

        final int ONE_SECOND = 10000;

        javax.swing.Timer timer = new Timer(ONE_SECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                ManagedChannel channel =  ManagedChannelBuilder.forAddress("localhost",9090).usePlaintext().build();
                userGrpc.userBlockingStub userStub = userGrpc.newBlockingStub(channel);
                User.LoginRequest loginRequest = User.LoginRequest.newBuilder().setUsername("Paracetamol si Ampicilina").setPassword("ROM").build();
                User.APIResponse response = userStub.login(loginRequest);
                System.out.println(response.getResponsemessage());

            }
        });
        timer.start();



    }

    static class AdaugareListener implements ActionListener {



        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            ManagedChannel channel =  ManagedChannelBuilder.forAddress("localhost",9090).usePlaintext().build();
            userGrpc.userBlockingStub userStub = userGrpc.newBlockingStub(channel);
            User.LoginRequest loginRequest = User.LoginRequest.newBuilder().setUsername("Paracetamol si Ampicilina").setPassword("ROM").build();
            User.APIResponse response = userStub.login(loginRequest);
            System.out.println(response.getResponsemessage());

        }

    }

    static class AdaugareListener2 implements ActionListener {



        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            ManagedChannel channel =  ManagedChannelBuilder.forAddress("localhost",9090).usePlaintext().build();
            userGrpc.userBlockingStub userStub = userGrpc.newBlockingStub(channel);
            User.LoginRequest loginRequest = User.LoginRequest.newBuilder().setUsername(view.getText()).setPassword("ROM").build();
            User.APIResponse response = userStub.login(loginRequest);
            System.out.println(response.getResponsemessage());

        }

    }
}

