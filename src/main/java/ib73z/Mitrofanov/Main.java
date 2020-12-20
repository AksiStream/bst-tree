package ib73z.Mitrofanov;

import java.util.Random;
import java.util.Scanner;


public class Main {


    private static int scanInt(Scanner menu) {
        while (!menu.hasNextInt()) menu.next();
        return menu.nextInt();
    }

    public static void main(String[] args) {
        System.out.println("Контрольная работа по САОДИСС. Вариант №8. Митрофанов И.С., группа ИБ-73з, №1710098.");

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        BinarySearchTree<Integer> bst2 = new BinarySearchTree<>();

        randomFill(bst, 5, 10, 20);
        randomFill(bst2, 5, 15, 25);

        Scanner menu = new Scanner(System.in);

        while (true) {
            System.out.println(Menu.show());
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
                    System.out.println("Объединение двух поддеревьев.");
                    System.out.println("Первое дерево:");
                    bst.print();
                    System.out.println("Второе дерево:");
                    bst2.print();
                    BinarySearchTree<Integer> unionTree = new BinarySearchTree<>();
                    unionTree.union(bst);
                    unionTree.union(bst2);
                    System.out.println("Результат объединения:");
                    unionTree.print();
                    break;
                case 9:
                    //вывод структуры дерева
                    bst.print();
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
        }
    }

    public static void randomFill(BinarySearchTree<Integer> bst, int count, int min, int max) {
        int range = max - min;
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int tempKey = random.nextInt(range)+min;
            while (bst.findNode(tempKey) != null) {
                tempKey = random.nextInt(range)+min;
            }
            bst.addNode(tempKey);
        }
    }

    public static void defaultFill(BinarySearchTree<Integer> bst) {
        bst.addNode(50);
        bst.addNode(25);
        bst.addNode(15);
        bst.addNode(30);
        bst.addNode(75);
        bst.addNode(85);
    }

}
