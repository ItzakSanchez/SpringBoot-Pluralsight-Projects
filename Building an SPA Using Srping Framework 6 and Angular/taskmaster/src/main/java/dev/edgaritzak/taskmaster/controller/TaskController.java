package dev.edgaritzak.taskmaster.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.edgaritzak.taskmaster.entity.Task;
import dev.edgaritzak.taskmaster.service.TaskService;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

  private final TaskService taskService;

  @Autowired
  public TaskController(TaskService taskService){
    this.taskService = taskService;
  }
  
  @GetMapping("")
  public List<Task> getAllTasks() {
      return taskService.getAllTasks();
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<Task> getTaskById(@PathVariable("id") Long id) {
    Optional<Task> task = taskService.getTaskById(id);
    if (task.isPresent()){
      return ResponseEntity.ok(task.get());
    } else{
      return ResponseEntity.notFound().build();
    }
  }
  
  @PostMapping("")
  public Task saveTask(@RequestBody Task task) {
    //task.setId(0L);
    return taskService.addTask(task);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody Task task) {
    Optional<Task> optionalTask = taskService.getTaskById(id);

    if (optionalTask.isPresent()){
      Task currentTask = optionalTask.get();

      currentTask.setName(task.getName());
      currentTask.setDescription(task.getDescription());
      currentTask.setCompleted(task.getCompleted());
      currentTask.setDueDate(task.getDueDate());
    
      return ResponseEntity.ok(taskService.addTask(currentTask));
    } else{
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProject(@PathVariable("id") Long id){
    Optional<Task> optionalTask = taskService.getTaskById(id);
    if (optionalTask.isPresent()){
      taskService.deleteTask(id);
      return ResponseEntity.noContent().build();
    } else{
      return ResponseEntity.notFound().build();
    }
  }
  
}
