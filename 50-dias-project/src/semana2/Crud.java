package semana2;

import java.util.Scanner;

public class Crud {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("======= Lista de Tarefas =======\n");
            System.out.println("1. Criar nova tarefa");
            System.out.println("2. Ver tarefas");
            System.out.println("3. Editar tarefa");
            System.out.println("4. Deletar tarefa");
            System.out.println("5. Sair\n");
            System.out.println("================================\n");


            System.out.println("Qual opção deseja? ");

            String entrada = scanner.nextLine();

            try {
                int opcao = Integer.parseInt(entrada);
                System.out.println("\nOpção escolhida: " + opcao + "\n");
            } catch (NumberFormatException e) {
                System.out.println("\nEntrada inválida\n");
            }

            //Tratamento de erros de entrada
            //if (opcao < 1 || opcao > 5) {
              //  System.out.println("\nNúmero de opção inválido\n");
                //continue;
            //}

        }
    }
}
