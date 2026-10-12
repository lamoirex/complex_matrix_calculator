import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== КАЛЬКУЛЯТОР МАТРИЦ КОМПЛЕКСНЫХ ЧИСЕЛ ===");
        System.out.println("Каждый элемент матрицы вводится как два числа через пробел: действительная и мнимая части.");
        System.out.println("Если мнимой части нет, можно ввести одно число: например, 5 это 5 + 0i.");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Выберите пункт меню: ", 0, 5);

            try {
                if (choice == 0) {
                    running = false;
                } else if (choice == 1) {
                    ComplexMatrix a = readMatrix("A");
                    ComplexMatrix b = readMatrix("B");
                    printResult(a.add(b));
                } else if (choice == 2) {
                    ComplexMatrix a = readMatrix("A");
                    ComplexMatrix b = readMatrix("B");
                    printResult(a.multiply(b));
                } else if (choice == 3) {
                    ComplexMatrix a = readMatrix("A");
                    ComplexMatrix b = readMatrix("B");
                    printResult(a.divide(b));
                } else if (choice == 4) {
                    ComplexMatrix a = readMatrix("A");
                    printResult(a.transpose());
                } else if (choice == 5) {
                    ComplexMatrix a = readMatrix("A");
                    System.out.println("Определитель: " + a.determinant());
                }
            } catch (IllegalArgumentException error) {
                System.out.println("Ошибка: " + error.getMessage());
            } catch (ArithmeticException error) {
                System.out.println("Ошибка: " + error.getMessage());
            }
        }
        System.out.println("Программа завершена.")
    }
}