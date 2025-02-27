class Subtask extends Task {
    private int epicId;


    public Subtask(String name, String description, int epicId) {
        super(name, description);
    }

    public int getEpicId() {
        return epicId;
    }


}
