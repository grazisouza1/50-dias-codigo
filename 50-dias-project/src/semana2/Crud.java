package semana2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Crud {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();
    public void criarTarefa(){
        Tarefa tarefa = new Tarefa();

        System.out.print("Digite o título da tarefa: ");
        tarefa.titulo = scanner.nextLine().trim();

        if (tarefa.titulo.isEmpty()) {
            System.out.println("\nO título não pode ser vazio");
            return;
        }

        tarefa.id = tarefas.size() + 1;

        tarefas.add(tarefa);
        System.out.print("\nTarefa " + tarefa.titulo + " adicionada!\n");
    }

    public void buscarTarefa(){
        System.out.println("A lista tem " + tarefas.size() + " itens");
        System.out.print("Insira o ID da tarefa que deseja buscar: ");
        String idEntrada = scanner.nextLine();

        try {
            int idTarefa = Integer.parseInt(idEntrada);

            if(idTarefa > tarefas.size()) {
                System.out.println("\nO número ultrapassa a quantidade de tarefas");
                return;
            }

            if(idTarefa < 1) {
                System.out.println("\nO número deve ser maior que 0");
                return;
            }

            Tarefa tarefa = tarefas.get(idTarefa - 1);

            System.out.println("\n__________________________");
            System.out.println("ID: " + tarefa.id);
            System.out.println("Título: " + tarefa.titulo);

            String status = (tarefa.status) ? "Concluída" : "Pendente";
            System.out.println("Status: " + status);
            System.out.println("\n__________________________");

        } catch (NumberFormatException e) {
            System.out.println("\nFormato inválido");
        }

    }

    public void editarTarefa() {
        System.out.print("Selecione o ID da tarefa que deseja atualizar: ");
        String idEntrada = scanner.nextLine();

        try {
            int idTarefa = Integer.parseInt(idEntrada);

            if(idTarefa > tarefas.size()) {
                System.out.println("\nNenhuma tarefa existente com esse ID");
                return;
            }

            if(idTarefa <= 0){
                System.out.println("\nO número deve ser maior que 0");
                return;
            }

            Tarefa tarefa = tarefas.get(idTarefa - 1);
            tarefa.status = !tarefa.status;

            System.out.println("\nTarefa " + idTarefa + " atualizada");
        } catch (NumberFormatException e) {
            System.out.println("\nO ID deve ser um número");
        }
    }

    public void deletarTarefa() {
        System.out.print("Insira o ID da tarefa que deseja remover: ");
        String idEntrada = scanner.nextLine();

        try {
            int idDeletar = Integer.parseInt(idEntrada);

            if(idDeletar <= 0){
                System.out.println("\nO número deve ser maior que 0");
                return;
            }

            if(idDeletar > tarefas.size()) {
                System.out.println("\nNenhuma tarefa existente com esse ID");
                return;
            }

            Tarefa tarefa = tarefas.get(idDeletar);

            tarefas.remove(idDeletar - 1);

            for (int i = 0; i < tarefas.size(); i++) {
                tarefa = tarefas.get(i);

                tarefa.id = i + 1;
            }

            System.out.println("\nTarefa número" + tarefa.id + "' Removida!\n");
        } catch (NumberFormatException e) {
            System.out.println("\nA entrada deve ser um número");
        }
    }

    public static void list (String[] args) {
        Crud listaTarefas = new Crud();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("\n======= Lista de Tarefas =======\n");
            System.out.println("1. Criar nova tarefa");
            System.out.println("2. Ver tarefas");
            System.out.println("3. Atualizar tarefa");
            System.out.println("4. Deletar tarefa");
            System.out.println("5. Sair\n");
            System.out.println("================================\n");

            System.out.print("Qual opção deseja selecionar? ");
            String entrada = scanner.nextLine().trim();

            try {
                int opcao = Integer.parseInt(entrada);

                switch (opcao) {
                    case 1:
                        System.out.println("\nVocê selecionou 'Criar nova tarefa'\n");
                        listaTarefas.criarTarefa();
                        break;
                    case 2:
                        System.out.println("\nVocê selecionou 'Ver tarefas'\n");
                        listaTarefas.buscarTarefa();
                        break;
                    case 3:
                        System.out.println("\nVocê selecionou 'Editar tarefa'\n");
                        listaTarefas.editarTarefa();
                        break;
                    case 4:
                        System.out.println("\nVocê selecionou 'Deletar tarefa'\n");
                        listaTarefas.deletarTarefa();
                        break;
                    case 5:
                        System.out.println("\nVocê selecionou 'Sair'");
                        scanner.close();
                        break;
                    default:
                        System.out.println("\nEntrada inválida\n");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("\nA opção deve ser um número");
            }
        }
    }
}
