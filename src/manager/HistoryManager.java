package manager;

import model.Task;

public interface HistoryManager {
    void addHistory(Task task);

    void printHistory();
}
