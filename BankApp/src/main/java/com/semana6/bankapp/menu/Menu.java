package com.semana6.bankapp.menu;

import com.semana6.bankapp.dao.CustomerDao;
import com.semana6.bankapp.dto.CustomerDto;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {
    Scanner scanner = new Scanner(System.in);

    public Integer processInputMenu(String input) {
        if (input.isBlank()){
            System.out.println("O campo não pode ser vazio");
            return null;
        }

        try {
            int inputInt = Integer.parseInt(input);

            if(inputInt > 5 || inputInt < 0){
                System.out.println("\nA opção deve ser um número existente no menu de opções\n");
                return null;
            }

            return inputInt;
        } catch (NumberFormatException e){
            System.out.println("\nA opção deve ser um número\n");
            return null;
        }
    }

    public void consultarSaldo() {
    }

    public void depositar() {

    }

    public void sacar() {

    }

    public void transferir() {

    }



    public void criarCadastro(){
        System.out.print("Digite seu primeiro nome: ");
        String typedName = scanner.nextLine().trim();

        if (typedName.isBlank()){
            System.out.println("O campo não pode estar vazio");
            return;
        }

        if(typedName.length() > 30){
            System.out.println("O primeiro nome deve ter, no máximo, 30 letras");
            return;
        }

        System.out.print("Digite seu sobrenome: ");
        String typedLastName = scanner.nextLine().trim();

        if (typedLastName.isBlank()){
            System.out.println("O campo não pode estar vazio");
            return;
        }

        if(typedLastName.length() > 30){
            System.out.println("O sobrenome deve ter, no máximo, 30 letras");
            return;
        }

        System.out.print("Digite seu email: ");
        String typedEmail = scanner.nextLine().trim();

        if (typedEmail.isBlank()){
            System.out.println("O campo não pode estar vazio");
            return;
        }

        if(typedEmail.length() > 50){
            System.out.println("O email deve ter, no máximo, 50 caract*eres");
            return;
        }

        CustomerDto objCustomer = new CustomerDto();
        objCustomer.setFirstName(typedName);
        objCustomer.setLastName(typedLastName);
        objCustomer.setEmail(typedEmail);

        CustomerDao objCustomerDao = new CustomerDao();
        objCustomerDao.cadastrarCliente(objCustomer);
    }


    public void startApplication() {
        boolean running = true;

        while(running == true) {
            System.out.println("\n========= BEM VINDO AO SEU BANCO =========");
            System.out.println("1. Consultar saldo  | 2. Depositar   ");
            System.out.println("3. Sacar            | 4. Transferir   ");
            System.out.println("5. Criar cadastro   | 6. Sair\n");

            System.out.print("Insira o número da ação que deseja realizar: ");
            String menuInput = scanner.nextLine();

            try {
                Integer menuOption = processInputMenu(menuInput);

                if(menuOption == null) {
                    continue;
                }

                switch (menuOption) {
                    case 1:
                        System.out.println("Consultar saldo selecionado");
                        break;
                    case 2:
                        System.out.println("Depositar selecionado");
                        break;
                    case 3:
                        System.out.println("Sacar selecionado");
                        break;
                    case 4:
                        System.out.println("Transferir selecionado");
                        break;
                    case 5:
                        criarCadastro();
                        break;
                    case 6:
                        running = false;
                        break;
                    default:
                        System.out.println("Entrada inválida");
                        break;
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }
}
