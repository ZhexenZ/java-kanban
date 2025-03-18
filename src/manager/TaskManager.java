package manager;

import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;

import java.util.List;
import java.util.Map;

public interface TaskManager {
    //Работа чисто по Task-у
    void createTask(Task task);

    Map<Integer, Task> printAllTask();

    void printTaskById(int id);

    void removeTaskById(int id);

    void removeAllTask();

    void updateStatusTask(int id, TaskStatus status);

    //Работа чисто по Epic-у
    void createEpic(Epic epic);

    Map<Integer, Epic> printAllEpic();

    void printEpicById(int id);

    void removeAllEpic();

    void removeEpicById(int id);

    //Работа чисто по subtask-у
    void createSubtask(int epicId, Subtask subtask);

    void printEpicWithSub(Epic epic);

    Map<Integer, Subtask> printAllSubtask();

    void updateSubtaskStatus(int id, TaskStatus status);

    void removeAllSubtasks();

    void getHistory();

}
