package manager;
import model.Task;
import java.util.List;

public interface HistoryManager {
    void addHistory(Task task);

    //void printHistoryId();
    void remove(int id);

    List<Task> getHistory();


}
