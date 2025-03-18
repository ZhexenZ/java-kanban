package manager;

import model.Task;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {
    private ArrayList<Task> getHistory = new ArrayList<>();

    @Override
    public void addHistory(Task task) {
        if(getHistory.size() >= 10) {
            getHistory.remove(0);
        }
        getHistory.add(task);
    }

    @Override
    public void printHistory() {
        for (Task task : getHistory) {
            System.out.println(task.getId() + " ");
        }
        System.out.println("В истории: " + getHistory.size() + " ID");
    }


}
