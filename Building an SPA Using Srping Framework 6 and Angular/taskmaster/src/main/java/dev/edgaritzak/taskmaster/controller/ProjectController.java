package dev.edgaritzak.taskmaster.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.edgaritzak.taskmaster.entity.Project;
import dev.edgaritzak.taskmaster.entity.Task;
import dev.edgaritzak.taskmaster.service.ProjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

  private final ProjectService projectService;

  @Autowired
  public ProjectController(ProjectService projectService){
    this.projectService = projectService;
  }
  
  @GetMapping("")
  public List<Project> getAllProjects() {
    System.out.println("CALLING GET ALL PROJECTS CONTROLLER");
      return projectService.getAllProjects();
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<Project> getProjectById(@PathVariable("id") Long id) {
    Optional<Project> project = projectService.getProjectById(id);
    if (project.isPresent()){
      return ResponseEntity.ok(project.get());
    } else{
      return ResponseEntity.notFound().build();
    }
  }
  
  @PostMapping("")
  public Project saveProject(@RequestBody Project project) {
    //project.setId(0L);
    return projectService.addProject(project);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Project> updateProject(@PathVariable("id") Long id, @RequestBody Project project) {
    Optional<Project> optionalProject = projectService.getProjectById(id);

    if (optionalProject.isPresent()){
      Project currentProject = optionalProject.get();

      currentProject.setName(project.getName());
      currentProject.setDescription(project.getDescription());
      
      currentProject.getTasks().clear();
      if(project.getTasks() != null){
        
        for (Task task : project.getTasks()) {
          currentProject.addTask(task);        
        }
      }
      return ResponseEntity.ok(projectService.addProject(currentProject));
    } else{
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProject(@PathVariable("id") Long id){
    Optional<Project> optionalProject = projectService.getProjectById(id);
    if (optionalProject.isPresent()){
      projectService.deleteProject(id);
      return ResponseEntity.noContent().build();
    } else{
      return ResponseEntity.notFound().build();
    }
  }
  
}
