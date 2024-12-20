package dev.edgaritzak.taskmaster.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.edgaritzak.taskmaster.entity.Task;
import dev.edgaritzak.taskmaster.repository.TaskRepository;

@Service
public class TaskService {

  private final TaskRepository taskRepository;

  @Autowired
  public TaskService(TaskRepository taskRepository){
    this.taskRepository = taskRepository;
  }

  public List<Task> getAllTasks(){
    return taskRepository.findAll();
  }

  public Optional<Task> getTaskById(Long id){
    return taskRepository.findById(id);
  }

  public Task addTask(Task task){
    return taskRepository.save(task);
  }

  public void deleteTask(Long id){
    taskRepository.deleteById(id);
  }
}
