package desafiosSemanais;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class listNum {

    public static void main (String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite quantos números deseja adicionar: ");
        int qntNums = scanner.nextInt();

        List<Integer> nums = new ArrayList<>();

        for (int i = 0; i < qntNums; i++) {
            System.out.print("Digite o " + (i + 1) + "º número: ");
            nums.add(scanner.nextInt());
        }

        System.out.println(nums);
    }
}
