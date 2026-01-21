package desafiosSemanais;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListNum {
    List<Double> nums = new ArrayList<Double>();

    public double soma() {
        double result = 0;
        for (int i = 0; i < nums.size(); i++){
            result += this.nums.get(i);
        }
        return result;
    }

    public double media() {
        double result = this.soma();
        int qnt = this.nums.size();

        result = result / qnt;

        return result;
    }

    public double maior() {
        double result = this.nums.get(0);

        for(int i = 0; i < this.nums.size(); i++) {
            if (this.nums.get(i) > result) {
                result = this.nums.get(i);
            }
        }

        return result;
    }

    public double menor() {
       double result = this.nums.get(0);

       for(int i = 0; i < this.nums.size(); i++) {
           if(this.nums.get(i) < result) {
               result = this.nums.get(i);
           }
       }

       return result;
    }

    public static void main (String[] args) {
        ListNum user1 = new ListNum();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite quantos números deseja adicionar: ");
        int qntNums = scanner.nextInt();

        for (int i = 0; i < qntNums; i++) {
            System.out.print("Digite o " + (i + 1) + "º número: ");
            user1.nums.add(scanner.nextDouble());
        }

        System.out.println(user1.nums);
        System.out.println(user1.soma());
        System.out.println(user1.media());
        System.out.println(user1.maior());
        System.out.println(user1.menor());

        scanner.close();
    }
}
