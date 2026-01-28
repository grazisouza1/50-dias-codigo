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
        tarefa.titulo = scanner.nextLine();
        tarefa.id = tarefas.size() + 1;

        tarefas.add(tarefa);
        System.out.print("\nTarefa " + tarefa.titulo + " adicionada!\n");
    }

    public void verTarefas(){
        System.out.println("======== TAREFAS ========\n");
        for (int i = 0; i < tarefas.size(); i++) {
            Tarefa tarefa = tarefas.get(i);
            System.out.println("ID: " + tarefa.id);
            System.out.println("Título: " + tarefa.titulo);

            String status = (tarefa.status) ? "Concluída" : "Pendente";
            System.out.println("Status: " + status);
            System.out.println("\n__________________________\n");

        }
    }

    public void editarTarefa(){

    }

    public void deletarTarefa() {
        verTarefas();

        System.out.print("Insira o ID da tarefa que deseja remover: ");
        int idDeletar = scanner.nextInt();
        Tarefa tarefa = tarefas.get(idDeletar);

        tarefas.remove(idDeletar - 1);

        for (int i = 0; i < tarefas.size(); i++) {
            tarefa = tarefas.get(i);

            tarefa.id = i + 1;
        }

        System.out.println("\nTarefa número" + tarefa + "' Removida!\n");
    }

    public static void main (String[] args) {
        Crud listaTarefas = new Crud();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("======= Lista de Tarefas =======\n");
            System.out.println("1. Criar nova tarefa");
            System.out.println("2. Ver tarefas");
            System.out.println("3. Editar tarefa");
            System.out.println("4. Deletar tarefa");
            System.out.println("5. Sair\n");
            System.out.println("================================\n");

            System.out.print("Qual opção deseja selecionar? ");
            String entrada = scanner.nextLine();

            try {
                int opcao = Integer.parseInt(entrada);
                if (opcao == 5) {
                    System.out.println("\nVocê selecionou 'Sair'\n");
                    scanner.close();
                }

                switch (opcao) {
                    case 1:
                        System.out.println("\nVocê selecionou 'Criar nova tarefa'\n");
                        listaTarefas.criarTarefa();
                        break;
                    case 2:
                        System.out.println("\nVocê selecionou 'Ver tarefas'\n");
                        listaTarefas.verTarefas();
                        break;
                    case 3:
                        System.out.println("\nVocê selecionou 'Editar tarefa'\n");
                        listaTarefas.editarTarefa();
                        break;
                    case 4:
                        System.out.println("\nVocê selecionou 'Deletar tarefa'\n");
                        listaTarefas.deletarTarefa();
                        break;
                    default:
                        System.out.println("\nEntrada inválida\n");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("\nA opção deve ser um número\n");
            }



        }
    }
}
