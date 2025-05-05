package manager;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import model.*;


public class FileBackedTaskManager extends InMemoryTaskManager implements TaskManager {
    protected final File file;
    Map<Integer, Task> tasks = new HashMap<>();

    public FileBackedTaskManager(File file) {
        this.file = file;
    }


    @Override
    public void createTask(Task task) {
        super.createTask(task);
        tasks.put(task.getId(), task);
        save();
    }

    @Override
    public void createEpic(Epic epic) {
        super.createEpic(epic);
        tasks.put(epic.getId(), epic);
        save();
    }

    @Override
    public void createSubtask(int epicId, Subtask subtask) {
        super.createSubtask(epicId, subtask);
        tasks.put(subtask.getId(), subtask);
        save();
    }

    private void save() {
        try (Writer writer = new FileWriter(file, StandardCharsets.UTF_8)) {
            writer.write("id,type,name,status,description,epic\n");
            for (Task task : tasks.values()) {
                writer.write(toString(task) + "\n");
            }
            // Так же для эпиков и подзадач
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String toString(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(task.getId()).append(",")
                .append(task.getType()).append(",")
                .append(task.getName()).append(",")
                .append(task.getStatus()).append(",")
                .append(task.getDescription()).append(",");

        if (task instanceof Subtask) {
            sb.append(((Subtask) task).getEpicId());
        } else {
            sb.append("");
        }
        return sb.toString();
    }

    public Task fromString(String value) {
        String[] fields = value.split(",", -1);
        int id = Integer.parseInt(fields[0]);
        TaskType type = TaskType.valueOf(fields[1]);
        String name = fields[2];
        TaskStatus status = TaskStatus.valueOf(fields[3]);
        String description = fields[4];
        LocalDateTime startTime = !"null".equals(fields[5]) ? LocalDateTime.parse(fields[5]) : null;
        Duration duration = !"null".equals(fields[6]) ? Duration.parse(fields[6]) : Duration.ZERO;

        Task task;
        switch (type) {
            case TASK:
                task = new Task(name, description);
                break;
            case EPIC:
                task = new Epic(name, description);
                break;
            case SUBTASK:
                int epicId = Integer.parseInt(fields[7]);
                task = new Subtask(name, description, epicId);
                break;
            default:
                throw new IllegalStateException("Неизвестный тип задачи: " + type);
        }

        task.setId(id);
        task.setStatus(status);
        task.setStartDateTime(startTime);
        task.setDuration(duration);

        return task;
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                Task task = fromString(line);
                tasks.put(task.getId(), task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}