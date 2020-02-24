package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {

    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository repository;

    @Test
    public void shouldGetAllTasks(){
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1, "Task1", "Task1 content"));
        taskList.add(new Task(2, "Task2", "Task2 content"));
        when(repository.findAll()).thenReturn(taskList);
        //When
        List<Task> resultList = dbService.getAllTasks();
        String name = resultList.get(0).getTitle();
        String content = resultList.get(1).getContent();
        //Then
        Assert.assertEquals("Task1", name);
        Assert.assertEquals("Task2 content", content);
    }

    @Test
    public void shouldSaveTask(){
        //Given
        Task task = new Task(1, "Task1", "Task1 content");
        when(repository.save(task)).thenReturn(task);
        //When
        Task receivedTask = dbService.save(task);
        String content = receivedTask.getContent();
        //Then
        Assert.assertEquals("Task1 content", content);
    }
}
