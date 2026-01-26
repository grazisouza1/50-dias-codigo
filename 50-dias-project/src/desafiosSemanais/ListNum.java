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

        return result;
    }

    public double media() {
        double result = 0;
        for (int i = 0; i < nums.size(); i++){
            result += this.nums.get(i);
        }

        int qnt = this.nums.size();

        result = result / qnt;

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
       return result;
    }

    public static void main (String[] args) {
        ListNum user1 = new ListNum();

        //Criando o principal, um frame
        JFrame frame = new JFrame("Calculadora de Lista");
        frame.setLayout(new BorderLayout());

        //Configurando seu tamanho
        frame.setSize(500, 600);

        //Deixando ele no centro
        frame.setLocationRelativeTo(null);

        //Criando e configurando o tamanho dos paineis, que vão dentro do frame
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        JPanel painelControle = new JPanel();
        JPanel painelQntNums = new JPanel();
        JPanel painelAddNums = new JPanel();
        JPanel painelResults = new JPanel();


        painelControle.setLayout(new BoxLayout(painelControle, BoxLayout.Y_AXIS));
        painelControle.setSize(500, 300);
        painelQntNums.setLayout(new FlowLayout(FlowLayout.LEFT));
        painelAddNums.setLayout(new GridLayout(0, 4, 5, 5));
        painelResults.setLayout(new BoxLayout(painelResults, BoxLayout.Y_AXIS));

        //Colocando um titulo no painel
        TitledBorder borderTitle = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Calcule sua lista de números!",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                Font.getFont("Arial"),
                Color.black
        );

        painelControle.setBorder(borderTitle);

        //ELEMENTOS
        JLabel qntLabel = new JLabel("Quantos números deseja na lista? ");
        JTextField qntTF = new JTextField(5);
        JButton botaoAdd = new JButton("Adicionar");

        List<JTextField> campos = new ArrayList<>();

        JLabel somaLabel = new JLabel();
        JLabel mediaLabel = new JLabel();
        JLabel maiorLabel = new JLabel();
        JLabel menorLabel = new JLabel();

        JButton botaoCalcular = new JButton("Calcular");

        botaoAdd.addActionListener(e -> {
            painelAddNums.removeAll();
            campos.clear();

            int qntValue = Integer.parseInt(qntTF.getText().trim());

            for (int i = 0; i < qntValue; i++) {
                JTextField tf = new JTextField(10);
                campos.add(tf);

                JPanel linha = new JPanel(new FlowLayout(FlowLayout.LEFT));
                linha.setPreferredSize(new Dimension(30, 30));
                linha.add(tf);

                painelAddNums.add(linha);
            }

            painelControle.revalidate();
            painelControle.repaint();
        });

        botaoCalcular.addActionListener(e -> {
            user1.nums.clear();

            for (JTextField tf : campos) {
                double valor = Double.parseDouble(tf.getText().trim());
                user1.nums.add(valor);
            }

            double soma = user1.soma();
            double media = user1.media();
            double maior = user1.maior();
            double menor = user1.menor();

            somaLabel.setText("A soma dos números é: " + soma);
            mediaLabel.setText("A média dos números é: " + media);
            maiorLabel.setText("O maior dos números é: " + maior);
            menorLabel.setText("O menor dos números é: " + menor);
        });

        //Adicionando elementos ao painel e adicionando o painel ao frame no final
        painelQntNums.add(qntLabel);
        painelQntNums.add(qntTF);
        painelQntNums.add(botaoAdd);

        painelResults.add(somaLabel);
        painelResults.add(mediaLabel);
        painelResults.add(maiorLabel);
        painelResults.add(menorLabel);

        painelControle.add(painelQntNums);
        painelControle.add(painelAddNums);
        painelControle.add(painelResults);
        painelControle.add(botaoCalcular);

        painelPrincipal.add(painelControle, BorderLayout.NORTH);

        //Configurando o fechamento de aba e deixando o painel visível
        frame.setContentPane(painelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        /*
            //Se a entrada não for um número, o erro informará que esse é o problema
            if (!scanner.hasNextInt()) {
                System.out.println("\nA entrada deve ser um número, digite novamente\n");
                scanner.next();
                continue;
            }

            //Se o número for menor que 1, o erro também informará isso ao usuário
            if(qntNums < 1) {
                System.out.println("\nO número deve ser maior do que 0, digite novamente\n");
            }
         */

    }
}
