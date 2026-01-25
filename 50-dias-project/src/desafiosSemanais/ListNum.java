package desafiosSemanais;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListNum {
    // Cria um array de números do tipo Double
    List<Double> nums = new ArrayList<Double>();

    //Método para somar os números da lista
    public double soma() {
        double result = 0;
        for (int i = 0; i < nums.size(); i++){
            result += this.nums.get(i);
        }

        System.out.print("- A soma dos números inseridos é: ");
        return result;
    }

    public double media() {
        double result = 0;
        for (int i = 0; i < nums.size(); i++){
            result += this.nums.get(i);
        }

        int qnt = this.nums.size();

        result = result / qnt;

        System.out.print("- A média dos números inseridos é: ");
        return result;
    }

    public double maior() {
        //Seleciona o primeiro elemento do array
        double result = this.nums.get(0);

        //Percorre todo o array comparando o número do índice atual com o result. Caso encontre um número maior, ele se torna o result
        for(int i = 0; i < this.nums.size(); i++) {
            if (this.nums.get(i) > result) {
                result = this.nums.get(i);
            }
        }

        System.out.print("- O maior dos números inseridos é: ");
        return result;
    }

    public double menor() {
        //Seleciona o primeiro elemento do array
       double result = this.nums.get(0);

        //Percorre todo o array comparando o número do índice atual com o result. Caso encontre um número maior, ele se torna o result
       for(int i = 0; i < this.nums.size(); i++) {
           if(this.nums.get(i) < result) {
               result = this.nums.get(i);
           }
       }

       System.out.print("- O menor dos números inseridos é: ");
       return result;
    }

    public static void main (String[] args) {
        //Criando o principal, um frame
        JFrame frame = new JFrame("Calculadora de Lista");
        //Configurando seu tamanho
        frame.setSize(500, 600);
        //Deixando ele no centro
        frame.setLocationRelativeTo(null);

        //Criando e configurando o tamanho do painel, que vai dentro do frame
        JPanel panel = new JPanel();
        panel.setSize(500, 300);

        //Colocando um titulo no painel
        TitledBorder borderTitle = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Calcule sua lista de números!",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                Font.getFont("Arial"),
                Color.black
        );
        panel.setBorder(borderTitle);

        //ELEMENTOS
        JLabel qntLabel = new JLabel("Quantos números deseja na lista? ");
        JTextField qntTF = new JTextField(1);
        JButton botaoAdd = new JButton("Adicionar");

        JLabel numsLabel = new JLabel("Adicione os números: ");
        List<JTextField> campos = new ArrayList<>();

        JButton botaoCalcular = new JButton("Calcular");

        botaoAdd.addActionListener(e -> {
            int qntValue = Integer.parseInt(qntTF.getText());
            for (int i = 0; i < qntValue; i++) {
                JTextField tf = new JTextField();
                campos.add(tf);
                panel.add(tf);
            }
            System.out.println(qntValue);
        });

        //Adicionando elementos ao painel e adicionando o painel ao frame no final
        panel.add(qntLabel);
        panel.add(qntTF);
        panel.add(botaoAdd);
        panel.add(numsLabel);
        panel.add(botaoCalcular);
        frame.add(panel);

        //Configurando o fechamento de aba e deixando o painel visível
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        /* //Cria um objeto usuário para rodar o projeto
        ListNum user1 = new ListNum();
        int qntNums = 0;

        //Cria um objeto para aceitar entrada do usuário
        Scanner scanner = new Scanner(System.in);

        //Roda a parte do prgrama que pede números ao usuário enquanto ele adicionar entradas inválidas
        do {
            System.out.print("Digite quantos números deseja adicionar: ");

            //Se a entrada não for um número, o erro informará que esse é o problema
            if (!scanner.hasNextInt()) {
                System.out.println("\nA entrada deve ser um número, digite novamente\n");
                scanner.next();
                continue;
            }

            qntNums = scanner.nextInt();

            //Se o número for menor que 1, o erro também informará isso ao usuário
            if(qntNums < 1) {
                System.out.println("\nO número deve ser maior do que 0, digite novamente\n");
            }
        //Quando o número for maior que 1, o programa para de perguntar ao usuário, e segue para a próxima etapa
        } while (qntNums < 1);

        //Mostra mensagens diferentes caso a quantidade de números seja 1 ou maior que isso
        if (qntNums > 1) {
            System.out.println("\nA lista vai conter " + qntNums + " números \n");
            for (int i = 0; i < qntNums; i++) {
                System.out.print("Digite o " + (i + 1) + "º número: ");
                user1.nums.add(scanner.nextDouble());
            }
        } else {
            System.out.println("\nA lista vai conter 1 número");
            System.out.print("\nDigite o número: ");
            user1.nums.add(scanner.nextDouble());
        }

        //Chama todas as funções
        System.out.println(user1.soma());
        System.out.println(user1.media());
        System.out.println(user1.maior());
        System.out.println(user1.menor());

        System.out.println("\nPrograma Encerrado!");

        //Fecha o objeto de leitura de entrada do usuário
        scanner.close(); */
    }
}
