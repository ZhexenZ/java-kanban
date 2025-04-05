package model;

public class Subtask extends Task {
    private int epicId;


    public Subtask(String name, String description, int epicId) {
        super(name, description);
        this.epicId = epicId;
    }

    public void setEpicId(int epicId) {
        if (epicId == this.getId()) {
            throw new IllegalArgumentException("Subtask не может быть своим же Epic!");
        }
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }
}
