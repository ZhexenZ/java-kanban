package manager;
import model.*;

import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    private HashMap<Integer, Task> taskMap = new HashMap<>();
    private HashMap<Integer, Epic> epicMap = new HashMap<>();
    private HashMap<Integer, Subtask> subtaskMap = new HashMap<>();
    private int nextId = 1;


    //Работа чисто по Task-у
    public void createTask(Task task) {
        task.setId(nextId++);
        taskMap.put(task.getId(), task);
    }

    public Map<Integer, Task> printAllTask() {
        return taskMap;
    }

    public void printTaskById(int id) {
        System.out.println(taskMap.get(id));
    }

    public void removeTaskById(int id) {
        System.out.println("Задача под ID " + id + " удалена");
        taskMap.remove(id);
    }

    public void removeAllTask() {
        taskMap.clear();
    }

    public void updateStatusTask(int id, TaskStatus status) {
        taskMap.get(id).setStatus(status);
    }

    //Работа чисто по Epic-у
    public void createEpic(Epic epic) {
        epic.setId(nextId++);
        epicMap.put(epic.getId(), epic);
    }

    public Map<Integer, Epic> printAllEpic() {
        return epicMap;
    }

    public void printEpicById(int id) {
        System.out.println(epicMap.get(id));
    }

    public void removeAllEpic() {
        epicMap.clear();
    }

    public void removeEpicById(int id) {
        Epic epic = epicMap.get(id);
        if (epic != null) {
            for (int subtaskId : epic.getSubtaskIdsList()) {
                subtaskMap.remove(subtaskId);
            }
            epic.getSubtaskIdsList().clear();
        }
        epicMap.remove(id);
    }

    //Работа чисто по subtask-у
    public void createSubtask( int epicId, Subtask subtask) {
        if (!epicMap.containsKey(epicId)) {
            System.out.println("Эпика под таким ID " + epicId + " нету");
        } else {
            subtask.setId(nextId++);
            subtaskMap.put(subtask.getId(), subtask);
            epicMap.get(epicId).addSubtaskIdsList(subtask.getId());
            updateEpicStatus(epicId);

            String message = String.format("Эпик ID: %d\nДобавлена подзадача ID: %d - %s",
                    epicId, subtask.getId(), subtask.getName());
            System.out.println(message);
        }
    }

    public void printEpicWithSub(Epic epic) {
        System.out.println(epic);
        for (int i : epic.getSubtaskIdsList()){
            System.out.println(subtaskMap.get(i));
        }
    }

    public Map<Integer, Subtask> printAllSubtask() {
        return subtaskMap;
    }

    public void updateSubtaskStatus(int id, TaskStatus status) {
        if (!subtaskMap.containsKey(id)) {
            System.out.println("Подзадачи с таким ID " + id + " нет.");
            return;
        }
        Subtask subtask = subtaskMap.get(id);
        subtask.setStatus(status);
        updateEpicStatus(subtask.getEpicId());
    }

    private void updateEpicStatus(int epicId) {
        Epic epic = epicMap.get(epicId);
        if (epic == null) return;
        if (epic.getSubtaskIdsList().isEmpty()) {
            epic.setStatus(TaskStatus.NEW);
            return;
        }

        boolean allNew = true;
        boolean allDone = true;
        for (int subtaskId : epic.getSubtaskIdsList()) {
            Subtask subtask = subtaskMap.get(subtaskId);
            if (subtask == null) continue;
            if (subtask.getStatus() != TaskStatus.NEW) allNew = false;
            if (subtask.getStatus() != TaskStatus.DONE) allDone = false;
        }
        if (allDone) {
            epic.setStatus(TaskStatus.DONE);
        } else if (allNew) {
            epic.setStatus(TaskStatus.NEW);
        } else {
            epic.setStatus(TaskStatus.IN_PROGRESS);
        }
    }

    public void removeAllSubtasks() {
        subtaskMap.clear();
        for (Epic epic : epicMap.values()) {
            epic.getSubtaskIdsList().clear();
            epic.setStatus(TaskStatus.NEW);
        }
    }


}
