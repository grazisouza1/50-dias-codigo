package com.semana4.onepiecesearch.menu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.semana4.onepiecesearch.dto.CharacterDto;
import com.semana4.onepiecesearch.dto.CrewDto;
import com.semana4.onepiecesearch.dto.EpisodeDto;
import com.semana4.onepiecesearch.dto.SagaDto;
import com.semana4.onepiecesearch.service.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Component
public class Menu {

    private final CharacterService characterService;
    private final CrewService crewService;
    private final EpisodeService episodeService;
    private final SagaService sagaService;

    ObjectMapper mapper = new ObjectMapper();
    Scanner scanner = new Scanner(System.in);

    public Menu(CharacterService characterService, CrewService crewService, EpisodeService episodeService, SagaService sagaService) {
        this.characterService = characterService;
        this.crewService = crewService;
        this.episodeService = episodeService;
        this.sagaService = sagaService;
    }

    public void startApplication() {
        boolean rodando = true;
        int selectedNumInt = 0;

        while (rodando == true) {
            System.out.println("\n======= Banco de informações One Piece =======\n");
            System.out.println("1. Pesquisar personagem");
            System.out.println("2. Pesquisar tripulação");
            System.out.println("3. Pesquisar episódio");
            System.out.println("4. Pesquisar saga");
            System.out.println("5. Sair");

            System.out.print("\nDigite o número da ação que deseja realizar: ");
            String selectedNum = scanner.nextLine();

            if (selectedNum.isEmpty()) {
                System.out.println("\nEsse campo não pode estar vazio\n");
            }

            try {
                selectedNumInt = Integer.parseInt(selectedNum);

                if (selectedNumInt > 5 || selectedNumInt < 1) {
                    System.out.println("\n⚠ Selecione uma opção válida (de 1 à 5) ⚠\n");
                    continue;
                }

                if (selectedNumInt == 5) {
                    System.out.println("\nVocê selecionou 'Sair'");
                    System.exit(1);
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
                        showSaga();
                        break;
                    default:
                        System.out.println("\nEntrada inválida");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n⚠ A opção deve ser um número ⚠\n");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void showCharacter() throws JsonProcessingException {
        System.out.print("Digite o nome do personagem que deseja buscar (Lembre-se de digitar com as primeiras letras maiúsculas(Ex.: 'Roronoa Zoro')): ");
        String characterName = scanner.nextLine().trim();

        if (characterName.isEmpty()) {
            System.out.println("\nEsse campo não pode estar vazio\n");
            return;
        }

        CharacterDto result = characterService.searchByName(characterName);

        if (result == null) {
            System.out.println("\nPersonagem não encontrado\n");
        } else {
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
        }

    }

    public void showCrew() {
        System.out.println("\n======== ID's de cada tripulação ========");
        System.out.println("1 - Chapéus de Palha    | 2 - Piratas do Ruivo");
        System.out.println("4 - Piratas do Buggy    | 5 - Piratas Roger ");
        System.out.println("10 - Piratas Arlong     | 16 - Piratas Barba Branca");
        System.out.println("21 - Piratas Donquixote | 22 - Piratas Barba Negra");
        System.out.println("32 - Piratas do Coração | 33 - Piratas do Kid");
        System.out.println("34 - Piratas Hawkins    | 53 - Piratas Big Mom\n");
        System.out.print("Selecione o ID de uma tripulação: ");
        List<Integer> crewIds = new ArrayList<>(Arrays.asList(1, 2, 4, 5, 10, 16, 21, 22, 32, 33, 34, 53));
        String selectedCrewId = scanner.nextLine().trim();

        if (selectedCrewId.isEmpty()) {
            System.out.println("\nEsse campo não pode estar vazio\n");
            return;
        }

        try {
            int selectedCrewIdFormated = Integer.parseInt(selectedCrewId);

            if (!crewIds.contains(selectedCrewIdFormated)) {
                System.out.println("\n⚠ Insira um número válido da lista de ID's ⚠\n");
                return;
            }

            CrewDto result = crewService.searchCrewById(selectedCrewIdFormated);
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
        } catch (NumberFormatException e) {
            System.out.println("\nInsira um formato válido\n");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void showEpisode() {
        System.out.print("Selecione o número do episódio que deseja pesquisar: ");
        String selectedEpisode = scanner.nextLine().trim();

        if (selectedEpisode.isEmpty()) {
            System.out.println("\nEsse campo não pode ser vazio");
            return;
        }

        try {
            int selectedEpisodeFormated = Integer.parseInt(selectedEpisode);

            if(selectedEpisodeFormated < 0) {
                System.out.println("\nO número deve ser maior que 0");
                return;
            }

            int existentsEps = episodeService.episodesCount();

            if(selectedEpisodeFormated > existentsEps) {
                System.out.println("\nO número selecionado é maior que o número de episódios existentes");
                return;
            }

            EpisodeDto result = episodeService.searchEpisodeByNumber(selectedEpisodeFormated);

            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
        } catch (NumberFormatException e) {
            System.out.println("\nO valor deve ser um número");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void showSaga() {
        System.out.println("===== Lista de sagas por ID =====");
        System.out.println("1. East Blue         | 2. Alabasta");
        System.out.println("3. Celestial Island  | 4. Water Seven / CP9");
        System.out.println("5. Thriller Bark     | 6. War at the top");
        System.out.println("7. Fish men island   | 8. Dressrosa / Pirate Alliance");
        System.out.println("9. Four Emperors     | 10. Final Saga\n");

        System.out.print("Selecione um dos ID's acima: ");
        String selectedId = scanner.nextLine().trim();

        if (selectedId.isEmpty()) {
            System.out.println("\nEsse campo não pode ser vazio");
            return;
        }

        try {
            int selectedIdFormated = Integer.parseInt(selectedId);

            if (selectedIdFormated > 10 || selectedIdFormated < 1) {
                System.out.println("⚠ Selecione uma opção válida (Entre 1 e 10) ⚠");
                return;
            }

            SagaDto result = sagaService.searchSagaById(selectedIdFormated);

            if (result == null) {
                System.out.println("\nSaga não encontrada\n");
            } else {
                System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
            }

        } catch (NumberFormatException e) {
            System.out.println("\nA opção deve ser um número\n");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
}
}
