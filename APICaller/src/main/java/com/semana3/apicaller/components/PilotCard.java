package com.semana3.apicaller.components;

import com.semana3.apicaller.dto.PilotDto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

//Criando card dos pilotos
public class PilotCard extends JPanel {
    //Função vai retornar um tipo PilotDto, pois cada card retorna apenas 1 piloto
    public PilotCard(PilotDto pilot) throws IOException {
        //Ajustando layout do card com java swing
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(450, 120));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));

        JPanel pilotInfos = new JPanel();
        pilotInfos.setLayout(new BoxLayout(pilotInfos, BoxLayout.Y_AXIS));

        //O PilotCard recebe um piloto, e a partir disso, se cria um objeto PilotDto que contém os getters e setters necessários
        JLabel pilotName = new JLabel("Name: " + pilot.getFull_name());
        JLabel pilotTeam = new JLabel("Team: " + pilot.getTeam_name());
        JLabel pilotAcro = new JLabel("Acronym: " + pilot.getName_acronym());
        JLabel pilotCountry = new JLabel("Country: " + pilot.getCountry_code());

        JPanel colorPanel = new JPanel();
        colorPanel.setPreferredSize(new Dimension(450, 10));
        colorPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 10));

        String color = pilot.getTeam_colour();

        if (color == null || color.isBlank()) {
            colorPanel.setBackground(Color.GRAY);
        } else {
            colorPanel.setBackground(Color.decode("#" + color));
        }

        pilotInfos.add(pilotName);
        pilotInfos.add(pilotTeam);
        pilotInfos.add(pilotAcro);
        pilotInfos.add(pilotCountry);
        pilotInfos.add(colorPanel);

        //Criando padding interno
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true), new EmptyBorder(10,10,10,10)));

        add(pilotInfos);
    }
}
