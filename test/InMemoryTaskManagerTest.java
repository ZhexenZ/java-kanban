import manager.InMemoryTaskManager;
import manager.TaskManager;
import model.Epic;
import model.Subtask;
import model.Task;
import org.junit.jupiter.api.Test;
import util.ManagerUtil;
import static org.junit.jupiter.api.Assertions.*;

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
        epic.setId(1);

        epic.addSubtaskIdsList(epic.getId(), epic.getId());


        assertFalse(epic.getSubtaskIdsList().contains(epic.getId()),
                "Epic не должен быть своей же подзадачей");
    }

    @Test
    void testSubtaskCannotBeItsOwnEpic() {
        Subtask subtask = new Subtask("Subtask", "Test description", 1);
        subtask.setId(1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            subtask.setEpicId(1);
        });

        assertEquals("Subtask не может быть своим же Epic!", exception.getMessage());
    }

    @Test
    void testGetDefaultReturnsInitializedTaskManager() {
        TaskManager taskManager = ManagerUtil.getDefault();

        assertNotNull(taskManager, "Метод getDefault() вернул null");
        assertInstanceOf(TaskManager.class, taskManager, "getDefault() не возвращает экземпляр TaskManager");
    }

    @Test
    void testAddAndFindTaskById() {
        TaskManager taskManager = new InMemoryTaskManager();
        Task task = new Task("Test Task", "Description");
        taskManager.createTask(task);

        Task foundTask = taskManager.getTask(task.getId());

        assertNotNull(foundTask, "Задача не найдена.");
        assertEquals(task, foundTask, "Задача не совпадает.");
    }

    @Test
    void testTaskIdConflict() {
        TaskManager taskManager = new InMemoryTaskManager();
        Task taskWithCustomId = new Task("Task 1", "Custom ID Task");
        taskManager.addTask(taskWithCustomId,100);


        Task autoGeneratedTask = new Task("Task 2", "Auto-generated ID Task");
        taskManager.createTask(autoGeneratedTask);
        Task generatedId = taskManager.getTask(autoGeneratedTask.getId());


        assertEquals(taskWithCustomId, taskManager.getTask(100), "Задача с заданным ID не найдена");
        assertEquals(autoGeneratedTask, taskManager.getTask(generatedId.getId()), "Задача с авто-генерацией ID не найдена");


        assertNotEquals(taskWithCustomId.getId(), autoGeneratedTask.getId(), "ID не должны совпадать!");
    }





        @Test
        void shouldNotChangeTaskAfterAdding() {
            TaskManager taskManager = new InMemoryTaskManager();
            Task originalTask = new Task("Test Task", "Description");

            taskManager.createTask(originalTask);

            Task storedTask = taskManager.getTask(originalTask.getId());
            taskManager.createTask(storedTask);
            assertNotNull(storedTask, "Задача должна существовать в менеджере");
            assertEquals(originalTask.getId(), storedTask.getId(), "ID задачи должен оставаться неизменным");
            assertEquals(originalTask.getName(), storedTask.getName(), "Название задачи должно оставаться неизменным");
            assertEquals(originalTask.getDescription(), storedTask.getDescription(), "Описание задачи должно оставаться неизменным");
            assertEquals(originalTask.getStatus(), storedTask.getStatus(), "Статус задачи должен оставаться неизменным");

        }


}
