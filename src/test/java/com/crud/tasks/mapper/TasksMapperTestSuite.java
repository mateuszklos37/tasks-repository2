package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TasksMapperTestSuite {

    @Autowired
    TaskMapper taskMapper;

    @Test
    public void shouldMapTaskToDto(){
        //Given
        Task task = new Task(1, "task_name", "first_task");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        String name = taskDto.getTitle();
        String content = taskDto.getContent();
        //
        Assert.assertEquals("task_name", name);
        Assert.assertEquals("first_task", content);
    }

    @Test
    public void shouldMapTaskDtoToDomain(){
        //Given
        TaskDto taskDto = new TaskDto(1, "taskDto", "some_task");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        String title = task.getTitle();
        String content = task.getContent();
        //
        Assert.assertEquals("taskDto", title);
        Assert.assertEquals("some_task", content);
    }
}
