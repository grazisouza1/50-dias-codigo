package com.semana3.apicaller;

import com.semana3.apicaller.components.PilotCard;
import com.semana3.apicaller.dto.PilotDto;
import com.semana3.apicaller.service.APIService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.awt.*;
import java.util.Set;

@SpringBootApplication
public class ApiCallerApplication {
    //Criando um objeto para rodar os serviços da API chamados pelo APIService
    APIService apiService = new APIService();
    List<PilotDto> pilots;

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

    void gerarCards(String filter, String searchText) {
            //Confere o filtro, para direcionar o que a API deve buscar, e depois usa funções do API Service para buscar o que o user deseja
            try {
                switch (filter) {
                    case "name":
                        pilots = apiService.getPilotsByFirstName(searchText);
                        break;
                    case "country":
                        pilots = apiService.getPilotsByCountry(searchText);
                        break;
                    case "team":
                        pilots = apiService.getPilotsByTeam(searchText);
                        break;
                    default:
                        pilots = List.of();
                }

                //Limpa a área de cards
                results.removeAll();


                Set<String> seen = new HashSet<>();

                for (PilotDto p : pilots) {
                    String key = p.getFull_name();

                    if(seen.contains(key)) continue;

                    seen.add(key);
                    results.add(new PilotCard(p));
                }

                results.revalidate();
                results.repaint();

            } catch (Exception ex) {
                ex.printStackTrace();
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
                return;
            }

            String searchText = searchField.getText().trim();

            //Gera os cards de acordo com o filtro selecionado
            if (radioName.isSelected()) {
                gerarCards("name", searchText);
            } else if (radioCountry.isSelected()) {
                gerarCards("country", searchText);
            } else if (radioTeam.isSelected()) {
                gerarCards("team", searchText);
            }
        });

        JScrollPane scrollPane = new JScrollPane(results);
        panel.add(scrollPane);

        //Adições do frame
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new ApiCallerApplication();
    }

}
