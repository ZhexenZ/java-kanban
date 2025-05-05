package model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private final List<Integer> subtaskIdsList = new ArrayList<>();
    private Duration duration = Duration.ZERO;
    private LocalDateTime startDateTime;
    private LocalDateTime endTime;

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
            System.out.println("Эпик не может быть в списке подзадач");
        }
    }

    public void removeSubtask(int subtaskId) {
        subtaskIdsList.remove(Integer.valueOf(subtaskId));
    }

    // Геттеры и сеттеры для новых полей
    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startDateTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    // 🔽 Метод пересчёта времени
    public void updateTimeFields(List<Subtask> subtasks) {
        if (subtasks == null || subtasks.isEmpty()) {
            startDateTime = null;
            endTime = null;
            duration = Duration.ZERO;
            return;
        }

        LocalDateTime earliest = null;
        LocalDateTime latest = null;
        Duration totalDuration = Duration.ZERO;

        for (Subtask subtask : subtasks) {
            if (subtask.getStartDateTime() == null || subtask.getDuration() == null) {
                continue;
            }

            LocalDateTime subStart = subtask.getStartDateTime();
            LocalDateTime subEnd = subtask.getEndTime();

            if (earliest == null || subStart.isBefore(earliest)) {
                earliest = subStart;
            }

            if (latest == null || subEnd.isAfter(latest)) {
                latest = subEnd;
            }

            totalDuration = totalDuration.plus(subtask.getDuration());
        }

        this.startDateTime = earliest;
        this.endTime = latest;
        this.duration = totalDuration;
    }

    @Override
    public String toString() {
        return "Epic{" + super.toString() +
                ", subtaskIdsList=" + subtaskIdsList +
                ", duration=" + duration +
                ", startTime=" + startDateTime +
                ", endTime=" + endTime +
                '}';
    }
}