package com.semana3.apicaller;

import com.semana3.apicaller.components.PilotCard;
import com.semana3.apicaller.service.APIService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

@SpringBootApplication
public class ApiCallerApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        APIService apiService = new APIService();

        JFrame frame = new JFrame("Pilots List");
        frame.setLayout(new BorderLayout());
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        panel.add(new PilotCard(apiService.getPilotName("Max")));

        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
