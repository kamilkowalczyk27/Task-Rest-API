package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.Silent.class)
public class DbServiceTestSuite {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void getAllTasksTest() {
        //Given
        Task task1 = new Task(1L, "name1", "content1");
        Task task2 = new Task(2L, "name2", "content2");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        //When
        when(dbService.getAllTasks()).thenReturn(tasks);
        //Then
        assertEquals(2, tasks.size());
    }

    @Test
    public void saveTaskTest() {
        //Given
        Task task = new Task(1L, "name", "content");
        //When
        when(taskRepository.save(task)).thenReturn(task);
        Task savedTask = dbService.saveTask(task);
        //Then
        assertEquals(task.getId(), savedTask.getId());
        assertEquals(task.getTitle(), savedTask.getTitle());
        assertEquals(task.getContent(), savedTask.getContent());
    }

    @Test
    public void getTaskTest() {
        //Given
        Task taskTest = new Task(1L,"name1","content1");
        Long taskIdTest = taskTest.getId();
        String taskTitletest = taskTest.getTitle();
        //When
        when(dbService.getTask(taskIdTest)).thenReturn(java.util.Optional.of(taskTest));
        //Then
        assertEquals("name1", taskTitletest);
    }

    @Test
    public void deleteTaskTest() {
        //Given
        Task task1 = new Task(1L, "name1", "content1");
        Task task2 = new Task(2L, "name2", "content2");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        //When
        dbService.deleteTask(2L);
        //Then
        verify(taskRepository, times(1)).deleteById(2L);
    }

    @Test
    public void getTaskWhichDoesNotExist() {
        //Given
        Task task = new Task(1L, "title", "content");
        //When
        when(taskRepository.findById(2L)).thenReturn(Optional.empty());
        Optional<Task> result = dbService.getTask(2L);
        //Then
        assertEquals(Optional.empty(), result);
    }
}
