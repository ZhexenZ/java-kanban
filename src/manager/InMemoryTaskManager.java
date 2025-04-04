package manager;
import model.*;
import util.ManagerUtil;
import java.util.HashMap;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {
    private final HashMap<Integer, Task> taskMap;
    private final HashMap<Integer, Epic> epicMap;
    private final HashMap<Integer, Subtask> subtaskMap;
    private final HistoryManager historyManager;
    private int nextId = 1;

    public InMemoryTaskManager() {
        this.taskMap = new HashMap<>();
        this.epicMap = new HashMap<>();
        this.subtaskMap = new HashMap<>();
        this.historyManager = ManagerUtil.getDefaultHistory();
    }


    //Работа чисто по Task-у
    @Override
    public Task getTask(int id) {
        Task task = taskMap.get(id);
        System.out.println(task);

        return task;
    }

    @Override
    public Task addTask(Task task, int id) {
        task.setId(id);
        taskMap.put(task.getId(), task);
        return task;
    }

    @Override
    public void createTask(Task task) {
        task.setId(nextId++);
        taskMap.put(task.getId(), task);
    }

    @Override
    public Map<Integer, Task> printAllTask() {
        for (Task task : taskMap.values()) {
            System.out.println(task);
        }
        return  taskMap;
    }

    @Override
    public void printTaskById(int id) {
        System.out.println(taskMap.get(id));
        historyManager.addHistory(taskMap.get(id));
    }

    @Override
    public void removeTaskById(int id) {
        System.out.println("Задача под ID " + id + " удалена");
        taskMap.remove(id);
    }

    @Override
    public void removeAllTask() {
        taskMap.clear();
    }

    @Override
    public void updateStatusTask(int id, TaskStatus status) {
        taskMap.get(id).setStatus(status);
    }

    //Работа чисто по Epic-у
    @Override
    public void createEpic(Epic epic) {
        epic.setId(nextId++);
        epicMap.put(epic.getId(), epic);
    }

    @Override
    public Map<Integer, Epic> printAllEpic() {
        for (Epic epic : epicMap.values()) {
            System.out.println(epic);
        }
        return epicMap;
    }

    @Override
    public void printEpicById(int id) {
        System.out.println(epicMap.get(id));
        historyManager.addHistory(epicMap.get(id));
    }

    @Override
    public void removeAllEpic() {
        epicMap.clear();
    }

    @Override
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
    @Override
    public void createSubtask(int epicId, Subtask subtask) {
        if (!epicMap.containsKey(epicId)) {
            System.out.println("Эпика под таким ID " + epicId + " нету");
        } else {
            subtask.setId(nextId++);
            subtaskMap.put(subtask.getId(), subtask);
            epicMap.get(epicId).addSubtaskIdsList(subtask.getId(), epicId);
            updateEpicStatus(epicId);
            String message = String.format("Эпик ID: %d\nДобавлена подзадача ID: %d - %s",
                    epicId, subtask.getId(), subtask.getName());

            System.out.println(message);
        }
        historyManager.addHistory(subtaskMap.get(epicId));
    }

    @Override
    public void printEpicWithSub(Epic epic) {
        System.out.println(epic);
        for (int i : epic.getSubtaskIdsList()){
            System.out.println(subtaskMap.get(i));
        }
    }

    @Override
    public Map<Integer, Subtask> printAllSubtask() {
        for (Subtask subtask : subtaskMap.values()) {
            System.out.println(subtask);
        }
        return subtaskMap;
    }

    @Override
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

    @Override
    public void removeAllSubtasks() {
        subtaskMap.clear();
        for (Epic epic : epicMap.values()) {
            epic.getSubtaskIdsList().clear();
            epic.setStatus(TaskStatus.NEW);
        }
    }

    @Override
    public void getHistory() {
        historyManager.getHistory();
        //historyManager.printHistory();
    }
}
