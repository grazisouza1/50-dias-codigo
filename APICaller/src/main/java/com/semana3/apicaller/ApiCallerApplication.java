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

        //Gerando Frame
        JFrame frame = new JFrame("Pilots List");
        frame.setLayout(new BorderLayout());
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);

        //Gerando Painéis
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        ButtonGroup radioGroup = new ButtonGroup();

        JPanel results = new JPanel();
        results.setLayout(new BoxLayout(results, BoxLayout.Y_AXIS));
        results.setPreferredSize(new Dimension(300, 500));

        JScrollPane scrollPane = new JScrollPane(results);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //Gerando componentes
        JLabel title = new JLabel("Selecione o tipo de filtro:");

        JRadioButton radioName = new JRadioButton("Nome");
        JRadioButton radioTeam = new JRadioButton("Equipe");
        JRadioButton radioCountry = new JRadioButton("País");

        JButton pesquisarButton = new JButton("Pesquisar");;

        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(300, 20));
        searchField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));

        //Adições do radioGroup
        radioGroup.add(radioName);
        radioGroup.add(radioTeam);
        radioGroup.add(radioCountry);


        pesquisarButton.addActionListener(e -> {
            if (radioGroup.getSelection() == null) {
                JOptionPane.showMessageDialog(null, "Selecione uma opção");
            } else {
                if (radioName.isSelected()) {
                    
                } else if (radioCountry.isSelected()) {
                    
                } else if (radioTeam.isSelected()) {

                }
            }
        });

        //Adições do panel
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(searchField);
        panel.add(title);
        panel.add(radioName);
        panel.add(radioTeam);
        panel.add(radioCountry);
        panel.add(Box.createVerticalStrut(10));
        panel.add(pesquisarButton);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new PilotCard(apiService.getPilotName("Max")));



        //Adições do frame
        frame.add(scrollPane);
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
