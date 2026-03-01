package com.semana6.bankapp.menu;

import com.semana6.bankapp.dao.CustomerDao;
import com.semana6.bankapp.dto.CustomerDto;
import com.semana6.bankapp.validator.InputValidator;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class Menu {
    InputValidator validator = new InputValidator();
    Scanner scanner = new Scanner(System.in);

    private final CustomerDao customerDao;

    public Menu(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void displayMenu() throws SQLException {
        String erro;
        do {
            System.out.println("\n========= SELECIONE UMA DAS OPÇẼS =========");
            System.out.println("1. Consultar saldo  | 2. Depositar   ");
            System.out.println("3. Sacar            | 4. Transferir   ");
            System.out.println("5. Criar cadastro   | 6. Sair\n");

            System.out.print("Insira o número da ação que deseja realizar: ");
            String menuInput = scanner.nextLine();

            erro = validator.isMenuInputValid(menuInput);

            if (erro != null) {
                System.out.println(erro);
            }

            int menuOption = Integer.parseInt(menuInput);

            switch (menuOption) {
                case 1:
                    System.out.println();
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
                    System.exit(1);
                    break;
                default:
                    System.out.println("Entrada inválida");
                    break;
            }
        }
        while (erro != null) ;
    }

    public Integer processInputCadastro(String input) {
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

    public void criarCadastro() throws SQLException {
        String typedName;
        String typedLastName;
        String typedEmail;
        String typedPhoneNumber;
        String typedCpfCnpj;
        String typedPassword;

        String erro;

        do {
            System.out.print("Digite seu primeiro nome: ");
            typedName = scanner.nextLine().trim();

            erro = validator.isNameValid(typedName);

            if (erro != null){
                System.out.println(erro);
            }

        } while (erro !=null);

        do {
            System.out.print("Digite seu sobrenome: ");
            typedLastName = scanner.nextLine().trim();

            erro = validator.isNameValid(typedLastName);

            if (erro != null) {
                System.out.println(erro);
            }
        } while (erro != null);

        do {
            System.out.print("Digite seu email: ");
            typedEmail = scanner.nextLine().trim();

            erro = validator.isEmailValid(typedEmail);

            if (erro != null) {
                System.out.println(erro);
                continue;
            }

            if (customerDao.emailJaExiste(typedEmail)) {
                System.out.println("\nEmail já cadastrado.\n");
                erro = "email existente";
            } else {
                erro = null;
            }

        } while (erro != null);

        do {
            System.out.print("Digite seu telefone (tudo junto, sem caracteres especiais): ");
            typedPhoneNumber = scanner.nextLine();

            erro = validator.isPhoneNumberValid(typedPhoneNumber);

            if(erro != null) {
                System.out.println(erro);
            }
        } while (erro != null);

        do {
            System.out.print("Digite seu CPF/CNPJ (tudo junto, sem caracteres especiais): ");
            typedCpfCnpj = scanner.nextLine();

            erro = validator.isCpfCnpjValid(typedCpfCnpj);

            if (erro != null){
                System.out.println(erro);
                continue;
            }

            if(customerDao.cpfJaExiste(typedCpfCnpj)) {
                System.out.println("\nCPF/CNPJ já existente\n");
                erro = "cpf/cnpj existente";
            } else{
                erro = null;
            }
        } while (erro != null);

        do {
            System.out.print("Digite sua senha (Deve ter pelo menos 6 caracteres): ");
            typedPassword = scanner.nextLine();

            erro = validator.isPasswordValid(typedPassword);

            if(erro != null) {
                System.out.println(erro);
            }

        } while (erro != null);

        CustomerDto objCustomer = new CustomerDto();
        objCustomer.setFirstName(typedName);
        objCustomer.setLastName(typedLastName);
        objCustomer.setEmail(typedEmail);
        objCustomer.setPhoneNumber(typedPhoneNumber);
        objCustomer.setCpfCnpj(typedCpfCnpj);
        objCustomer.setPassHash(typedPassword);

        try {
            customerDao.cadastrarCliente(objCustomer);
            System.out.println("Cadastro realizado.");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("CPF já cadastrado.");
        }

        System.out.println("\nVocê foi cadastrado!\n");
        System.out.println("\nFaça seu login abaixo!\n");

        login();
    }

    private CustomerDto usuarioLogado;

    public void login() throws SQLException {
        CustomerDto cliente;

        do {
            System.out.print("Digite seu email: ");
            String loginEmail = scanner.nextLine().trim();

            System.out.print("Digite sua senha: ");
            String loginSenha = scanner.nextLine().trim();

            cliente = customerDao.autenticar(loginEmail, loginSenha);

            if (cliente == null) {
                System.out.println("\nEmail ou senha inválidos\n");
            }

        } while (cliente == null);

        this.usuarioLogado = cliente;

        System.out.println("\n====== Entrada realizada com sucesso! ======\n");
        System.out.println("Bem vindo(a), " + cliente.getFirstName());

        displayMenu();
    }


    public void startApplication() throws SQLException {
        boolean running = true;

        while(running) {
            System.out.println("\n========= BEM VINDO AO BANK APP =========");
            System.out.println("-------- Você já é cadastrado? --------");
            System.out.println("1. Sim       | 2. Não         ");

            System.out.print("\nEscolha uma opção: ");
            String cadastroInput = scanner.nextLine();

            try {
                Integer cadastroIntInput = processInputCadastro(cadastroInput);

                if (cadastroIntInput == null) {
                    continue;
                }

                switch (cadastroIntInput) {
                    case 1:
                        login();
                        break;
                    case 2:
                        System.out.print("Deseja fazer o cadastro? [s/n]: ");
                        String escolhaCadastrar = scanner.nextLine().trim();

                        if(!escolhaCadastrar.equals("s") && !escolhaCadastrar.equals("n")){
                            System.out.println("\nSelecione uma opção válida\n");
                            return;
                        }

                        if (escolhaCadastrar.equals("n")){
                            System.out.println("\nSaindo da aplicação\n");
                            running = false;
                        }

                        if (escolhaCadastrar.equals("s")) {
                            criarCadastro();
                        }

                        break;
                    default:
                        System.out.println("Entrada inválida");
                        break;
                }
            }catch (NumberFormatException e){
                System.out.println("\nA opção selecionada deve ser um número\n");
                return;
            }
        }


    }
}
