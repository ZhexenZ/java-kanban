package manager;

import model.Task;
import java.util.List;

import java.util.List;

public interface HistoryManager {
    void addHistory(Task task);

    void remove(int id);

    List<Task> getHistory();
}