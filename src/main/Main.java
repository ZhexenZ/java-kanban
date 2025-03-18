package main;

import manager.TaskManager;
import model.*;
import util.ManagerUtil;


public class Main {
    public static void main(String[] args) {
        TaskManager manager = ManagerUtil.getDefault();

        Epic epic1 = new Epic("Test", "test");
        manager.createEpic(epic1);

        Subtask subtask1 = new Subtask("sfsd","sdfsfd",epic1.getId());
        Subtask subtask2 = new Subtask("2","2",epic1.getId());

        manager.createSubtask(epic1.getId(), subtask1);
        manager.createSubtask(epic1.getId(), subtask2);

//        epic1.addSubtaskIdsList(epic1.getId());


//        Task task1 = new Task("Задача1", "Description1");
//        Task task2 = new Task("Задача2", "Description2");
//        Task task3 = new Task("Задача3", "Description3");
//        Task task4 = new Task("Задача4", "Description4");
//        Task task5 = new Task("Задача5", "Description5");
//        Task task6 = new Task("Задача6", "Description6");
//        Task task7 = new Task("Задача7", "Description7");
//        Task task8 = new Task("Задача8", "Description8");
//        Task task9 = new Task("Задача9", "Description9");
//        Task task10 = new Task("Задача10", "Description10");


//        manager.createTask(task1);
//        manager.createTask(task2);
//        manager.createTask(task3);
//        manager.createTask(task4);
//        manager.createTask(task5);
//        manager.createTask(task6);
//        manager.createTask(task7);
//        manager.createTask(task8);
//        manager.createTask(task9);
//        manager.createTask(task10);
//
//        System.out.println("Здесь должен печатаь все задачи");
//        manager.printAllTask();
//
//        System.out.println("Здесь должен вызвать таск по ID");
//        manager.printTaskById(task1.getId());
//        manager.printTaskById(task4.getId());
//        manager.printTaskById(task6.getId());
//        manager.printTaskById(task2.getId());
//        manager.printTaskById(task6.getId());
//        manager.printTaskById(task5.getId());
//        manager.printTaskById(task7.getId());
//        manager.printTaskById(task8.getId());
//        manager.printTaskById(task9.getId());
//        manager.printTaskById(task10.getId());
//        manager.printTaskById(task2.getId());


//        System.out.println("Здесь должен записать как историю");
//        manager.getHistory();
        manager.printAllEpic();

    }
}
