import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Integer> subtaskIdsList = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
    }

    public List<Integer> getSubtaskIdsList() {
        return subtaskIdsList;
    }

    public void addSubtaskIdsList(int subtaskId) {
        subtaskIdsList.add(subtaskId);
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
