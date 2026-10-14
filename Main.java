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
        System.out.println("Программа завершена.");
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("Что делаем?");
        System.out.println("1. Сложить две матрицы (A + B)");
        System.out.println("2. Перемножить две матрицы (A * B)");
        System.out.println("3. Разделить две матрицы (A / B)");
        System.out.println("4. Транспонировать матрицу");
        System.out.println("5. Вычислить определитель матрицы");
        System.out.println("0. Выход");
    }

    private static void printResult(ComplexMatrix result) {
        System.out.println("Результат:");
        System.out.print(result);
    }

    private static int readInt(String message, int min, int max) {
        while (true) {
            System.out.print(message);
            String line = scanner.nextLine().trim();
            try {
                int number = Integer.parseInt(line);
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println("Число должно быть от " + min + " до " + max + ".");
            } catch (NumberFormatException error) {
                System.out.println("Это не целое число, попробуйте еще.");
            }
        }
    }

    private static Complex readComplex(String message) {
        while (true) {
            System.out.print(message);
            String line = scanner.nextLine().trim().replace(",", ".");
            String[] parts = line.split("\\s+");
            try {
                if (parts.length == 1) {
                    return new Complex(Double.parseDouble(parts[0]), 0);
                } else if (parts.length == 2) {
                    double re = Double.parseDouble(parts[0]);
                    double im = Double.parseDouble(parts[1]);
                    return new Complex(re, im);
                }
                System.out.println("Нужно ввести одно или два числа");
            } catch (NumberFormatException error) {
                System.out.println("Не получилось прочитать число, попробуйте еще");
            }
        }
    }

    private static ComplexMatrix readMatrix(String name) {
        System.out.println();
        System.out.println("Ввод матрицы " + name);
        int rows = readInt("Количество строк (от 1 до 10): ", 1, 10);
        int cols = readInt("Количество столбцов (от 1 до 10): ", 1, 10);
    
        ComplexMatrix matrix = new ComplexMatrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String message = name + "[" + (i + 1) + "][" + (j + 1) + "] = ";
                matrix.set(i, j, readComplex(message));
            }
        }
        return matrix;
    }
}
