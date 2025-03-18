package test;


import model.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class InMemoryTaskManagerTest {

    @Test
    void testTaskEqualityById() {
        Task task = new Task("Test", "TestTEst");
        Task task1 = new Task("Test", "TestTEst");

        task.setId(1);
        task1.setId(1);

        assertEquals(task, task1,"Задача не равны");
    }

    @Test
    void testTaskInheritanceEqualityById() {
        Epic epic1 = new Epic("Epic Task 1", "Description 1");
        Epic epic2 = new Epic("Epic Task 1", "Description 1");

        Subtask subtask1 = new Subtask("Subtask 1", "Description 1", 1);
        Subtask subtask2 = new Subtask("Subtask 1", "Description 1", 1);

        // Назначаем одинаковый id
        epic1.setId(10);
        epic2.setId(10);
        subtask1.setId(20);
        subtask2.setId(20);

        assertEquals(epic1, epic2, "Epic задачи с одинаковым ID должны быть равны");
        assertEquals(subtask1, subtask2, "Subtask задачи с одинаковым ID должны быть равны");
    }

    @Test
    void shouldNotAllowEpicToBeItsOwnSubtask() {
        Epic epic = new Epic("Epic Task", "Epic Description");
        epic.setId(1); // Устанавливаем ID

        // Попытка добавить эпик в список своих подзадач
       // epic.addSubtaskIdsList(epic.getId());

        // Проверяем, что ID эпика НЕ добавился в список его подзадач
        assertFalse(epic.getSubtaskIdsList().contains(epic.getId()),
                "Epic не должен быть своей же подзадачей");
    }
}
