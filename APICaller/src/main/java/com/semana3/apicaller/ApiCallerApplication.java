package com.semana3.apicaller;

import com.semana3.apicaller.components.PilotCard;
import com.semana3.apicaller.dto.PilotDto;
import com.semana3.apicaller.service.APIService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

@SpringBootApplication
public class ApiCallerApplication {
    APIService apiService = new APIService();
    PilotDto pilot;

    JFrame frame;

    JPanel panel;
    JPanel results;

    JTextField searchField;

    JButton pesquisarButton;

    ButtonGroup radioGroup;

    JLabel title;

    JRadioButton radioName;
    JRadioButton radioTeam;
    JRadioButton radioCountry;

    String searchText = searchField.getText().replaceAll("\\s+", "").toLowerCase();

    void gerarCards(String filter) {
        try {
            switch (filter) {
                case "name":
                    pilot = apiService.getPilotName(searchText);
                    panel.add(new PilotCard(pilot));
                    break;
                default:
                    System.out.println("Algo não deu certo");
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ApiCallerApplication() {
        //Gerando Frame
        frame = new JFrame("Pilots List");
        frame.setLayout(new BorderLayout());
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);

        //Gerando Painéis
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        radioGroup = new ButtonGroup();

        results = new JPanel();
        results.setLayout(new BoxLayout(results, BoxLayout.Y_AXIS));
        results.setPreferredSize(new Dimension(300, 500));

        //Gerando componentes
        title = new JLabel("Selecione o tipo de filtro:");

        radioName = new JRadioButton("Nome");
        radioTeam = new JRadioButton("Equipe");
        radioCountry = new JRadioButton("País");

        pesquisarButton = new JButton("Pesquisar");;

        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(300, 20));
        searchField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));

        //Adições do radioGroup
        radioGroup.add(radioName);
        radioGroup.add(radioTeam);
        radioGroup.add(radioCountry);

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

        pesquisarButton.addActionListener(e -> {
            if (radioGroup.getSelection() == null) {
                JOptionPane.showMessageDialog(null, "Selecione uma opção");
            } else {
                if (radioName.isSelected()) {
                    System.out.println(searchText);

                } else if (radioCountry.isSelected()) {
                    System.out.println(searchText);
                } else if (radioTeam.isSelected()) {
                    System.out.println(searchText);
                }
            }
        });

        //Adições do frame
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new ApiCallerApplication();
    }

}
