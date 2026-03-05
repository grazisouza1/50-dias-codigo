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
    InputValidator validator = new InputValidator();
    Scanner scanner = new Scanner(System.in);

    private CustomerDto usuarioLogado;
    private AccountDto contaLogada;

    private final AccountDao accountDao;
    private final CustomerDao customerDao;

    public Menu(AccountDao accountDao, CustomerDao customerDao) {
        this.accountDao = accountDao;
        this.customerDao = customerDao;
    }

    public void displayMenu() throws SQLException {
        String erro;
        do {
            System.out.println("========= SELECIONE UMA DAS OPÇẼS =========");
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
                    consultarSaldo();
                    break;
                case 2:
                    depositar();
                    break;
                case 3:
                    sacar();
                    break;
                case 4:
                    transferir();
                    break;
                case 5:
                    criarCadastro();
                    break;
                case 6:
                    System.exit(1);
                    break;
                default:
                    System.out.println("\nEntrada inválida\n");
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

    public void login() throws SQLException {
        CustomerDto cliente;
        String erro;
        String loginEmail;
        String loginSenha;

        do {
            do {
                System.out.print("Digite seu email: ");
                loginEmail = scanner.nextLine().trim();

                erro = validator.isEmailValid(loginEmail);

                if (erro != null){
                    System.out.println("\nEmail inválido, insira novamente\n");
                }
            } while (erro != null);

            do {
                System.out.print("Digite sua senha: ");
                loginSenha = scanner.nextLine().trim();

                erro = validator.isPasswordValid(loginSenha);

                if (erro != null){
                    System.out.println("\nSenha inválida, insira novamente\n");
                }
            } while (erro != null);

            cliente = customerDao.autenticar(loginEmail, loginSenha);

            if (cliente == null) {
                System.out.println("\nEmail ou senha inválidos\n");
            }

        } while (cliente == null);

        this.usuarioLogado = cliente;
        this.contaLogada = accountDao.buscarContaPorClienteId(cliente.getId());

        System.out.println("\n====== Entrada realizada com sucesso! ======\n");
        System.out.println("Bem vindo(a), " + cliente.getFirstName());
    }

    public void consultarSaldo() {
        System.out.println("===== Seu saldo atual é: =====");
        System.out.println("R$" + contaLogada.getBalance());
    }

    public void depositar() throws SQLException{
        String erro;
        float depositoFloat;

        do {
            System.out.print("Insira o valor que deseja depositar: ");
            String deposito = scanner.nextLine();

            erro = validator.isDepositValid(deposito);

            if (erro != null){
                System.out.println(erro);
            }

            depositoFloat = Float.parseFloat(deposito);
        } while (erro != null);

        float novoSaldo = contaLogada.getBalance() + depositoFloat;

        contaLogada.setBalance(novoSaldo);

        accountDao.atualizarSaldo(contaLogada.getId(), novoSaldo);

        System.out.println("\nO valor de R$" + depositoFloat + " foi adicionado à sua conta\n");
    }

    public void sacar() throws SQLException {
        String erro;
        Float saqueFloat;

        do {
            System.out.print("\nDigite o valor que deseja sacar: ");
            String saque = scanner.nextLine();

            erro = validator.isWithdrawalValid(saque, contaLogada);

            if(erro != null) {
                System.out.println(erro);
            }

            saqueFloat = Float.parseFloat(saque);
        } while (erro != null);

        float novoSaldo = contaLogada.getBalance() - saqueFloat;

        contaLogada.setBalance(novoSaldo);
        accountDao.atualizarSaldo(contaLogada.getId(), novoSaldo);

        System.out.println("\nSaque de R$" + saqueFloat + " realizado\n");

    }

    public void transferir() throws SQLException{
        String transferAnswer;

        try {
            System.out.print("\nInsira o telefone da conta para qual deseja transferir: ");
            String phone = scanner.nextLine();

            CustomerDto beneficiaryData = customerDao.buscarCustomerPorPhone(phone);
            AccountDto beneficiaryAccount = accountDao.buscarContaPorCustomerId(beneficiaryData.getId());

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

                        Float novoSaldo = beneficiaryAccount.getBalance() + transferValueFormated;
                        beneficiaryAccount.setBalance(novoSaldo);
                        accountDao.atualizarSaldo(beneficiaryAccount.getId(), novoSaldo);

                        System.out.println("\nValor de R$" + transferValueFormated + " transferido para " + beneficiaryData.getFirstName() + " " + beneficiaryData.getLastName() + "\n");
                    } catch (NumberFormatException e){
                        System.out.println(e.getMessage());
                    }
                } else if (transferAnswer.equals("n")) {
                    return;
                }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
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

        this.usuarioLogado = objCustomer;

        try {
            customerDao.cadastrarCliente(objCustomer);
            System.out.println("Cadastro realizado.");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("CPF já cadastrado.");
        }

        AccountDto objAccount = new AccountDto();
        objAccount.setCustomer_id(usuarioLogado.getId());
        objAccount.setAccount_type(AccountDto.AccountType.CORRENTE);
        objAccount.setBalance(0);
        objAccount.setStatus(AccountDto.AccountStatus.ATIVO);

        accountDao.cadastrarConta(objAccount);

        System.out.println("\nVocê foi cadastrado!\n");
    }

    public void startApplication() throws SQLException {
        String escolhaCadastrar;
        while(true) {
            if (usuarioLogado == null) {
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
                            do {
                                System.out.print("Deseja fazer o cadastro? [s/n]: ");
                                escolhaCadastrar = scanner.nextLine().trim();

                                if (!escolhaCadastrar.equals("s") && !escolhaCadastrar.equals("n")) {
                                    System.out.println("\nSelecione uma opção válida (s ou n)\n");
                                }
                            } while (!escolhaCadastrar.equals("s") && !escolhaCadastrar.equals("n"));

                                if (escolhaCadastrar.equals("n")) {
                                    System.out.println("\nSaindo da aplicação\n");
                                    System.exit(0);
                                }

                                if (escolhaCadastrar.equals("s")) {
                                    criarCadastro();
                                }

                                break;
                                default:
                                    System.out.println("Entrada inválida");
                                    break;
                            }
                    } catch(NumberFormatException e){
                        System.out.println("\nA opção selecionada deve ser um número\n");
                        return;
                    }
                } else{
                    displayMenu();
                }
            }


    }
}
