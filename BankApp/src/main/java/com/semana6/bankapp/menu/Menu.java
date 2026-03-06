package com.semana6.bankapp.menu;

import com.semana6.bankapp.dao.CustomerDao;
import com.semana6.bankapp.dto.AccountDto;
import com.semana6.bankapp.dao.AccountDao;
import com.semana6.bankapp.dto.CustomerDto;
import com.semana6.bankapp.validator.InputValidator;
import org.springframework.stereotype.Component;


import java.sql.*;
import java.util.Scanner;

@Component
public class Menu {
    //Inicialização de variáveis
    InputValidator validator = new InputValidator();
    Scanner scanner = new Scanner(System.in);

    private CustomerDto loggedInUser;
    private AccountDto loggedInAccount;

    private final AccountDao accountDao;
    private final CustomerDao customerDao;

    public Menu(AccountDao accountDao, CustomerDao customerDao) {
        this.accountDao = accountDao;
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
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    createRegistration();
                    break;
                case 6:
                    System.exit(1);
                    break;
                default:
                    System.out.println("\nEntrada inválida\n");
                    break;
            }
        }
        while (erro != null);
    }

    public Integer processRegisterInput(String input) {
        if (input.isBlank()) {
            System.out.println("O campo não pode ser vazio");
            return null;
        }

        try {
            int inputInt = Integer.parseInt(input);

            if (inputInt > 2 || inputInt < 0) {
                System.out.println("\nA opção deve ser um número existente no menu de opções\n");
                return null;
            }

            return inputInt;
        } catch (NumberFormatException e) {
            System.out.println("\nA opção deve ser um número\n");
            return null;
        }
    }

    public void login() throws SQLException {
        CustomerDto customer;
        String erro;
        String loginEmail;
        String loginSenha;

        System.out.println("\n===== Faça seu login =====\n");

        //Vai repetir o/os loops até o email e a senha serem válidos
        do {
            //Existe um do para email e um para senha, para que, caso o email esteja errado, o usuário não precise por a senha
            do {
                System.out.print("Digite seu email: ");
                loginEmail = scanner.nextLine().trim();

                erro = validator.isEmailValid(loginEmail);

                if (erro != null) {
                    System.out.println("\nEmail inválido, insira novamente\n");
                }
            } while (erro != null);

            do {
                System.out.print("Digite sua senha: ");
                loginSenha = scanner.nextLine().trim();

                erro = validator.isPasswordValid(loginSenha);

                if (erro != null) {
                    System.out.println("\nSenha inválida, insira novamente\n");
                }
            } while (erro != null);

            customer = customerDao.authenticate(loginEmail, loginSenha);

            if (customer == null) {
                System.out.println("\nEmail ou senha inválidos\n");
            }

        } while (customer == null);

        this.loggedInUser = customer;
        this.loggedInAccount = accountDao.searchAccountByCustomerId(customer.getId());

        System.out.println("\n====== Entrada realizada com sucesso! ======\n");
        System.out.println("Bem vindo(a), " + customer.getFirstName());
    }

    public void checkBalance() {
        System.out.println("===== Seu saldo atual é: =====");
        System.out.println("R$" + loggedInAccount.getBalance());
    }

    public void deposit() throws SQLException {
        String erro;
        float floatDeposit;

        do {
            System.out.print("Insira o valor que deseja depositar: ");
            String deposito = scanner.nextLine();

            erro = validator.isDepositValid(deposito);

            if (erro != null) {
                System.out.println(erro);
            }

            floatDeposit = Float.parseFloat(deposito);
        } while (erro != null);

        float newBalance = loggedInAccount.getBalance() + floatDeposit;

        loggedInAccount.setBalance(newBalance);
        accountDao.updateBalance(loggedInAccount.getId(), newBalance);

        System.out.println("\nO valor de R$" + floatDeposit + " foi adicionado à sua conta\n");
    }

    public void withdraw() throws SQLException {
        String erro;
        Float floatWithdraw;

        do {
            System.out.print("\nDigite o valor que deseja withdraw: ");
            String withdraw = scanner.nextLine();

            erro = validator.isWithdrawlValid(withdraw, loggedInAccount);

            if (erro != null) {
                System.out.println(erro);
            }

            floatWithdraw = Float.parseFloat(withdraw);
        } while (erro != null);

        float newWithdraw = loggedInAccount.getBalance() - floatWithdraw;

        loggedInAccount.setBalance(newWithdraw);
        accountDao.updateBalance(loggedInAccount.getId(), newWithdraw);

        System.out.println("\nSaque de R$" + floatWithdraw + " realizado\n");

    }

    public void transfer() throws SQLException {
        String transferAnswer;

        try {
            System.out.print("\nInsira o telefone da conta para qual deseja transferir: ");
            String phone = scanner.nextLine();

            //Busca o customer pelo número do telefone (Coluna da tabela customers)
            CustomerDto beneficiaryData = customerDao.searchCustomerByPhone(phone);
            //Pega o id do customer encontrado pelo telefone, e usa para achar a conta associada a ele
            AccountDto beneficiaryAccount = accountDao.searchAccountByCustomerId(beneficiaryData.getId());

            //Confirmação de transferência
            System.out.print("\nDeseja transferir para: " + beneficiaryData.getFirstName() + " " + beneficiaryData.getLastName() + "? (s/n)\n");
            transferAnswer = scanner.nextLine().trim();

            if (!transferAnswer.equals("s") && !transferAnswer.equals("n")) {
                System.out.print("Selecione uma opção válida (s ou n)");
                return;
            }

            if (transferAnswer.equals("s")) {
                System.out.print("\nDigite o valor que deseja transferir: ");
                String transferValue = scanner.nextLine();

                try {
                    Float transferValueFormated = Float.parseFloat(transferValue);

                    Float newWithdraw = beneficiaryAccount.getBalance() + transferValueFormated;
                    beneficiaryAccount.setBalance(newWithdraw);
                    accountDao.updateBalance(beneficiaryAccount.getId(), newWithdraw);

                    System.out.println("\nValor de R$" + transferValueFormated + " transferido para " + beneficiaryData.getFirstName() + " " + beneficiaryData.getLastName() + "\n");
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            } else if (transferAnswer.equals("n")) {
                return;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createRegistration() throws SQLException {
        String typedName;
        String typedLastName;
        String typedEmail;
        String typedPhoneNumber;
        String typedCpfCnpj;
        String typedPassword;

        String erro;

        //Um loop do while para cada campo que o usuário digitar

        do {
            System.out.print("Digite seu primeiro nome: ");
            typedName = scanner.nextLine().trim();

            erro = validator.isNameValid(typedName);

            if (erro != null) {
                System.out.println(erro);
            }

        } while (erro != null);

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

            if (customerDao.emailAlreadyExists(typedEmail)) {
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

            if (erro != null) {
                System.out.println(erro);
            }
        } while (erro != null);

        do {
            System.out.print("Digite seu CPF/CNPJ (tudo junto, sem caracteres especiais): ");
            typedCpfCnpj = scanner.nextLine();

            erro = validator.isCpfCnpjValid(typedCpfCnpj);

            if (erro != null) {
                System.out.println(erro);
                continue;
            }

            if (customerDao.cpfAlreadyExists(typedCpfCnpj)) {
                System.out.println("\nCPF/CNPJ já existente\n");
                erro = "cpf/cnpj existente";
            } else {
                erro = null;
            }
        } while (erro != null);

        do {
            System.out.print("Digite sua senha (Deve ter pelo menos 6 caracteres): ");
            typedPassword = scanner.nextLine();

            erro = validator.isPasswordValid(typedPassword);

            if (erro != null) {
                System.out.println(erro);
            }

        } while (erro != null);

        //Cria um objeto customer com todos os dados cadastrados
        CustomerDto objCustomer = new CustomerDto();
        objCustomer.setFirstName(typedName);
        objCustomer.setLastName(typedLastName);
        objCustomer.setEmail(typedEmail);
        objCustomer.setPhoneNumber(typedPhoneNumber);
        objCustomer.setCpfCnpj(typedCpfCnpj);
        objCustomer.setPassHash(typedPassword);

        this.loggedInUser = objCustomer;

        try {
            customerDao.registerCustomer(objCustomer);
            System.out.println("Cadastro realizado.");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("CPF já cadastrado.");
        }

        AccountDto objAccount = new AccountDto();
        objAccount.setCustomer_id(loggedInUser.getId());
        objAccount.setAccount_type(AccountDto.AccountType.CORRENTE);
        objAccount.setBalance(0);
        objAccount.setStatus(AccountDto.AccountStatus.ATIVO);

        accountDao.registerAccount(objAccount);

        System.out.println("\nVocê foi cadastrado!\n");

        login();
    }

    public void startApplication() throws SQLException {
        String registerChoice;

        while (true) {
            if (loggedInUser == null) {
                System.out.println("\n========= BEM VINDO AO BANK APP =========");
                System.out.println("-------- Você já é cadastrado? --------");
                System.out.println("1. Sim       | 2. Não         ");

                System.out.print("\nEscolha uma opção: ");
                String registerInput = scanner.nextLine();

                try {
                    Integer registerIntInput = processRegisterInput(registerInput);

                    if (registerIntInput == null) {
                        continue;
                    }

                    switch (registerIntInput) {
                        case 1:
                            login();
                            break;
                        case 2:
                            do {
                                System.out.print("Deseja fazer o cadastro? [s/n]: ");
                                registerChoice = scanner.nextLine().trim();

                                if (!registerChoice.equals("s") && !registerChoice.equals("n")) {
                                    System.out.println("\nSelecione uma opção válida (s ou n)\n");
                                }
                            } while (!registerChoice.equals("s") && !registerChoice.equals("n"));

                            if (registerChoice.equals("n")) {
                                System.out.println("\nSaindo da aplicação\n");
                                System.exit(0);
                            }

                            createRegistration();

                            break;
                        default:
                            System.out.println("Entrada inválida");
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nA opção selecionada deve ser um número\n");
                    return;
                }
            } else {
                displayMenu();
            }
        }


    }
}
