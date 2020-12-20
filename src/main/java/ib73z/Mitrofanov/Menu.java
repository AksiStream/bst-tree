package ib73z.Mitrofanov;

import java.util.Arrays;
import java.util.List;

public class Menu {
    private static final List<String> menu = Arrays.asList(
            "Выход из программы",
            "Опрос размера дерева",
            "Очистка дерева",
            "Проверка дерева на пустоту",
            "Поиск элемента с заданным ключом",
            "Включение нового элемента с заданным ключом",
            "Удаление элемента с заданным ключом",
            "Обход дерева по схеме LRN (обратный обход):",
            "Объединение двух поддеревьев (рекурсивная форма)",
            "Вывод структуры дерева на экран",
            "Установка на корень дерева",
            "Проверка конца дерева",
            "Доступ к данным текущего элемента дерева",
            "Переход к следующему по значению ключа элементу дерева",
            "Переход к предыдущему по значению ключа элементу дерева"
    );

    public static String show() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<menu.size(); i++){
            sb.append(i).append(" - ").append(menu.get(i)).append("\n");
        }
        return sb.toString();
    }
}
