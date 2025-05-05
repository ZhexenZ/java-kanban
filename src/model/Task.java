package model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Task {
    private String name;
    private String description;
    private TaskStatus status;
    protected TaskType type;
    private int id;
    protected Duration duration;
    protected LocalDateTime startDateTime;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.status = TaskStatus.NEW;
        this.type = TaskType.TASK;
    }

    public String getName() {

        return name;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public int getId() {
        return id;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {

        this.status = status;
    }

    public void setId(int id) {

        this.id = id;
    }

    public TaskType getType() {

        return type;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndTime() {
        if (startDateTime != null && duration != null) {
            return startDateTime.plus(duration);
        } else {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Task task = (Task) o;
        return id == task.id
                && Objects.equals(name, task.name)
                && Objects.equals(description, task.description)
                && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, status, id);
    }

    @Override
    public String toString() {
        return "Задача{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    public String toCsvString() {
        return String.join(",",
                String.valueOf(getId()),
                getType().toString(),
                getName(),
                getStatus().toString(),
                getDescription(),
                startDateTime != null ? startDateTime.toString() : "null",
                duration != null ? duration.toString() : "null"
        );
    }
}