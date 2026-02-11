package com.semana4.onepiecesearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class OnePieceSearchApplication {
    public void showCharacter() {

    }

    public void showCrew() {

    }

    public void showEpisode() {

    }

    public void showFruit() {

    }

    public void showSaga() {

    }

    public static void startApplication(){
        Scanner scanner = new Scanner(System.in);

        int selectedNumInt = 0;

        while (selectedNumInt != 6) {
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

                if(selectedNumInt > 6 || selectedNumInt < 1){
                    System.out.println("\n⚠ Selecione uma opção válida (de 1 à 6) ⚠\n");
                    continue;
                }

                switch (selectedNumInt) {
                    case 1:
                        System.out.println("\nNúmero 1 selecionado");
                        break;
                    case 2:
                        System.out.println("\nNúmero 2 selecionado");
                        break;
                    case 3:
                        System.out.println("\nNúmero 3 selecionado");
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

            scanner.close();
        }

    }

    public static void main(String[] args) {
        startApplication();
    }

}
