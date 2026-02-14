package com.semana4.onepiecesearch.menu;

import com.semana4.onepiecesearch.service.*;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.ObjectWriter;

import java.util.Scanner;

@Component
public class Menu {

    private final CharacterService characterService;
    private final CrewService crewService;
    private final EpisodeService episodeService;
    private final FruitService fruitService;
    private final SagaService sagaService;

    private final Scanner scanner = new Scanner(System.in);

    public Menu (CharacterService characterService, CrewService crewService, EpisodeService episodeService, FruitService fruitService, SagaService sagaService) {
        this.characterService = characterService;
        this.crewService = crewService;
        this.episodeService = episodeService;
        this.fruitService = fruitService;
        this.sagaService = sagaService;
    }

    public void startApplication() {

        int selectedNumInt = 0;

        while (selectedNumInt !=6){
            System.out.println("\n======= Banco de informações One Piece =======\n");
            System.out.println("1. Pesquisar personagem");
            System.out.println("2. Pesquisar tripulação");
            System.out.println("3. Pesquisar episódio");
            System.out.println("4. Pesquisar fruta");
            System.out.println("5. Pesquisar saga");
            System.out.println("6. Sair");

            System.out.print("\nDigite o número da ação que deseja realizar: ");
            String selectedNum = scanner.nextLine();

            try {
                selectedNumInt = Integer.parseInt(selectedNum);

                if (selectedNumInt > 6 || selectedNumInt < 1) {
                    System.out.println("\n⚠ Selecione uma opção válida (de 1 à 6) ⚠\n");
                    continue;
                }

                switch (selectedNumInt) {
                    case 1:
                        showCharacter();
                        break;
                    case 2:
                        showCrew();
                        break;
                    case 3:
                        showEpisode();
                        break;
                    case 4:
                        System.out.println("\nNúmero 4 selecionado");
                        break;
                    case 5:
                        System.out.println("\nNúmero 5 selecionado");
                        break;
                    case 6:
                        System.out.println("\nVocê selecionou 'Sair'");
                        break;
                    default:
                        System.out.println("\nEntrada inválida");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n⚠ A opção deve ser um número ⚠\n");
            }
        }
        return;
    }

    public void showCharacter() {
        System.out.print("Digite o nome do personagem que deseja buscar (Lembre-se de digitar com as primeiras letras maiúsculas(Ex.: 'Roronoa Zoro')): ");
        String characterName = scanner.nextLine().trim();

        String result = characterService.searchByName(characterName);

        System.out.println(result);
    }

    public void showCrew() {
        System.out.println("\n======== ID's de cada tripulação ========");
        System.out.println("1 - Chapéus de Palha    | 2 - Piratas do Ruivo");
        System.out.println("4 - Piratas do Buggy    | 5 - Piratas Roger ");
        System.out.println("10 - Piratas Arlong     | 16 - Piratas Barba Branca");
        System.out.println("21 - Piratas Donquixote | 22 - Piratas Barba Negra");
        System.out.println("32 - Piratas do Coração | 33 - Piratas do Kid");
        System.out.println("34 - Piratas Hawkins    | 33 - Piratas Big Mom\n");
        System.out.print("Selecione o ID de uma tripulação: ");
        String selectedCrewId = scanner.nextLine().trim();
        try {
            int selectedCrewIdFormated = Integer.parseInt(selectedCrewId);
            String result = crewService.searchCrewById(selectedCrewIdFormated);
            System.out.println(result);
        } catch (NumberFormatException e){
            System.out.println("\nInsira um formato válido\n");
        }
    }

    public void showEpisode(){
        System.out.print("Selecione o número do episódio que deseja pesquisar: ");
        String selectedEpisode = scanner.nextLine().trim();

        try {
            int selectedEpisodeFormated = Integer.parseInt(selectedEpisode);
            String result = episodeService.searchEpisodeByNumber(selectedEpisodeFormated);

            System.out.println(result);
        } catch (NumberFormatException e) {
            System.out.println("O valor deve ser um número");
        }
    }
}
