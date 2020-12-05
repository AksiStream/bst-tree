package ib73z.Mitrofanov;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static void showMenu() {
        System.out.println("1 - Опрос размера дерева");
        System.out.println("2 - Очистка дерева");
        System.out.println("3 - Проверка дерева на пустоту");
        System.out.println("4 - Поиск элемента с заданным ключом");
        System.out.println("5 - Включение нового элемента с заданным ключом");
        System.out.println("6 - Удаление элемента с заданным ключом");
        System.out.println("7 - Обход дерева по схеме LRN (обратный обход):");
        System.out.println("8 - Объединение двух поддеревьев (рекурсивная форма)");
        System.out.println("9 - Вывод структуры дерева на экран");
        System.out.println("10 - Опрос числа просмотренных операцией узлов дерева");
        System.out.println("11 - Установка на корень дерева");
        System.out.println("12 - Проверка конца дерева");
        System.out.println("13 - Доступ к данным текущего элемента дерева");
        System.out.println("14 - Переход к следующему по значению ключа элементу дерева");
        System.out.println("15 - Переход к предыдущему по значению ключа элементу дерева");
        System.out.println("0 - Выход из программы");
    }

/*    private static void pressAnyKeyToContinue() {
        System.out.println("Нажмите Enter, чтобы вернуться в меню.");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }*/

    private static int scanInt(Scanner menu) {
        while (!menu.hasNextInt()) menu.next();
        return menu.nextInt();
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        randomFill(bst, 20);

        BinarySearchTree<Integer> bst2 = new BinarySearchTree<>();

        defaultFill(bst2);

        Scanner menu = new Scanner(System.in);

        while (true) {
            showMenu();
            switch (scanInt(menu)) {
                case 0:
                    //выход из программы
                    System.exit(0);
                case 1:
                    //опрос размера дерева
                    System.out.println("Размер дерева: " + bst.getSize() + ".");
                    break;
                case 2:
                    //очистка дерева
                    bst.clearAllTree();
                    System.out.println("Дерево очищено.");
                    break;
                case 3:
                    //проверка дерева на пустоту
                    System.out.println("Дерево " + (bst.isEmpty() ? "" : "не ") + "пустое.");
                    break;
                case 4:
                    //поиск элемента с заданным ключом
                    System.out.print("Введите ключ: ");
                    System.out.println("Элемент с заданным ключом " + (bst.findNode(scanInt(menu)) == null ? "не " : "") + "найден.");
                    break;
                case 5:
                    //включение нового элемента с заданным ключом
                    System.out.print("Введите ключ: ");
                    int key = scanInt(menu);
                    if (bst.findNode(key) == null) {
                        bst.addNode(key);
                        System.out.println("Элемент успешно добавлен.");
                    } else System.out.println("Элемент с заданным ключом уже присутствует в дереве.");

                    break;
                case 6:
                    //удаление элемента с заданным ключом
                    System.out.print("Введите ключ: ");
                    if (bst.removeNode(scanInt(menu))) System.out.println("Элемент с заданным ключом успешно удалён.");
                    else System.out.println("Элемент с заданным ключом отсутствует.");
                    break;
                case 7:
                    //обход дерева по схеме LRN (обратный обход)
                    System.out.println("Обход дерева по схеме LRN (обратный обход):" + bst.postOrderTraverseTree());
                    break;
                case 8:
                    //объединение двух поддеревьев (рекурсивная форма)
                    BinarySearchTree bst3 = new BinarySearchTree();
                    bst3.merge(bst.root,bst2.root);
                    bst3.postOrderTraverseTree();
                    break;
                case 9:
                    //вывод структуры дерева
                    System.out.println(bst.print());
                    break;
                case 10:
                    //опрос числа просмотренных операцией узлов дерева
                    break;
                case 11:
                    //установка на корень дерева

                    break;
                case 12:
                    //проверка конца дерева
                    break;
                case 13:
                    //доступ к данным текущего элемента дерева
                    break;
                case 14:
                    //переход к следующему по значению ключа элементу дерева

                    break;
                case 15:
                    //переход к предыдущему по значению ключа элементу дерева

                    break;
                default:
                    break;
            }
            //pressAnyKeyToContinue();
        }
    }

    public static void randomFill(BinarySearchTree bst, int count){
        bst.clearAllTree();
        Random random = new Random();
        for (int i = 0; i<count; i++) bst.addNode(random.nextInt(100));
    }
    public static void defaultFill(BinarySearchTree bst){
        bst.clearAllTree();
        bst.addNode(50);
        bst.addNode(25);
        bst.addNode(15);
        bst.addNode(30);
        bst.addNode(75);
        bst.addNode(85);
    }

}
