package main;

import manager.TaskManager;
import model.*;


public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        System.out.println("🟢 Тест 1: Создание задачи");
        Task task = new Task("Test Task", "Description");
        taskManager.createTask(task);
        assert taskManager.printAllTask().size() == 1 : "Ошибка: задача не была добавлена";
        System.out.println("✅ Тест пройден!\n");

        System.out.println("🟢 Тест 2: Удаление задачи");
        taskManager.removeTaskById(1);
        assert taskManager.printAllTask().size() == 0 : "Ошибка: задача не была удалена";
        System.out.println("✅ Тест пройден!\n");

        System.out.println("🟢 Тест 3: Обновление статуса задачи");
        taskManager.createTask(new Task("New Task", "Desc"));
        taskManager.updateStatusTask(2, TaskStatus.DONE);
        assert taskManager.printAllTask().get(2).getStatus() == TaskStatus.DONE : "Ошибка: статус задачи не обновился";
        System.out.println("✅ Тест пройден!\n");

        System.out.println("🟢 Тест 4: Создание эпика");
        Epic epic = new Epic("Test Epic", "Description");
        taskManager.createEpic(epic);
        assert taskManager.printAllEpic().size() == 1 : "Ошибка: эпик не был добавлен";
        System.out.println("✅ Тест пройден!\n");

        System.out.println("🟢 Тест 5: Удаление эпика");
        taskManager.removeEpicById(3);
        assert taskManager.printAllEpic().size() == 0 : "Ошибка: эпик не был удалён";
        System.out.println("✅ Тест пройден!\n");

        System.out.println("🟢 Тест 6: Добавление подзадачи к эпику");
        Epic epic2 = new Epic("Epic 2", "Description");
        taskManager.createEpic(epic2);
        Subtask subtask = new Subtask("Subtask 1", "Description", 4);
        taskManager.createSubtask(4, subtask);
        assert taskManager.printAllSubtask().size() == 1 : "Ошибка: подзадача не добавилась";
        assert taskManager.printAllEpic().get(4).getStatus() == TaskStatus.NEW : "Ошибка: статус эпика не обновился";
        System.out.println("✅ Тест пройден!\n");

        System.out.println("🟢 Тест 7: Обновление статуса подзадачи и проверка статуса эпика");
        taskManager.updateSubtaskStatus(5, TaskStatus.IN_PROGRESS);
        assert taskManager.printAllEpic().get(4).getStatus() == TaskStatus.IN_PROGRESS : "Ошибка: статус эпика должен быть IN_PROGRESS";
        taskManager.updateSubtaskStatus(5, TaskStatus.DONE);
        assert taskManager.printAllEpic().get(4).getStatus() == TaskStatus.DONE : "Ошибка: статус эпика должен быть DONE";
        System.out.println("✅ Тест пройден!\n");

        System.out.println("🟢 Тест 8: Удаление всех подзадач");
        taskManager.removeAllSubtasks();
        assert taskManager.printAllSubtask().size() == 0 : "Ошибка: подзадачи не удалены";
        assert taskManager.printAllEpic().get(4).getStatus() == TaskStatus.NEW : "Ошибка: статус эпика должен быть NEW";
        System.out.println("✅ Тест пройден!\n");

        System.out.println("🟢 Все тесты успешно пройдены! 🎉");
    }
}
