package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private final List<Integer> subtaskIdsList = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
        this.type = TaskType.EPIC;
    }

    public List<Integer> getSubtaskIdsList() {
        return subtaskIdsList;
    }

    public void addSubtaskIdsList(int subtaskId, int epicId) {
        if (subtaskId != epicId) {
            subtaskIdsList.add(subtaskId);
        } else {
            System.out.println("Епик не может быть в списке подзадач");
        }
    }

    public void removeSubtask(int subtaskId) {
        subtaskIdsList.remove(subtaskId);
    }

    @Override
    public String toString() {
        return "Epic{" + super.toString() +
                "subtaskIdsList=" + subtaskIdsList +
                '}';
    }
}