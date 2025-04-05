package main;

import manager.TaskManager;
import model.*;
import util.ManagerUtil;


public class Main {
        public static void main(String[] args) {
            TaskManager taskManager = ManagerUtil.getDefault();

            // 1️⃣ Добавление задач
            Task task1 = new Task("Task 1", "Description 1");
            Task task2 = new Task("Task 2", "Description 2");

            taskManager.createTask(task1);
            taskManager.createTask(task2);

            System.out.println("Все задачи после добавления:");
            taskManager.printAllTask();  // ✅ Должны отобразиться две задачи

            // 2️⃣ Получение задачи по ID
            int task1Id = task1.getId();
            System.out.println(String.format("\nПолученная задача по ID %d:", task1Id));
            taskManager.printTaskById(task1Id); // ✅ Должна быть найдена

            // 3️⃣ Удаление задачи
            taskManager.removeTaskById(task1Id);
            System.out.println("\nВсе задачи после удаления первой:");
            taskManager.printAllTask();  // ✅ Должна остаться только task2

            // 4️⃣ Добавление эпика и подзадачи
            Epic epic = new Epic("Epic 1", "Big Task");
            taskManager.createEpic(epic);

            Subtask subtask1 = new Subtask("Subtask 1", "Part of Epic", epic.getId());
            Subtask subtask2 = new Subtask("Subtask 2", "Part of Epic", epic.getId());

            taskManager.createSubtask(epic.getId(), subtask1);
            taskManager.createSubtask(epic.getId(), subtask2);

            System.out.println("\nЭпик с подзадачами:");
            taskManager.printEpicWithSub(epic);  // ✅ Должен вывести эпик и две подзадачи

            // 5️⃣ Обновление статусов
            System.out.println("\nОбновление статуса подзадачи...");
            taskManager.updateSubtaskStatus(subtask1.getId(), TaskStatus.DONE);
            taskManager.printEpicWithSub(epic); // ✅ Эпик должен измениться в статусе


            // 7️⃣ Очистка данных
            taskManager.removeAllTask();
            taskManager.removeAllEpic();
            taskManager.removeAllSubtasks();

            System.out.println("\nДанные после очистки:");
            taskManager.printAllTask();  // ✅ Должно быть пусто
            taskManager.printAllEpic();
            taskManager.printAllSubtask();


            Task task3 = new Task("task3","task3");
            Task task4 = new Task("task4","task4");
            Task task5 = new Task("task5","task5");
            Task task6 = new Task("task6","task6");

            taskManager.createTask(task3);
            taskManager.createTask(task4);
            taskManager.createTask(task5);
            taskManager.createTask(task6);

            taskManager.printTaskById(task5.getId());
            taskManager.printTaskById(task2.getId());
            taskManager.printTaskById(task5.getId());
            taskManager.printTaskById(task4.getId());


            System.out.println("---------------------");
            taskManager.getHistory();

    }
}
