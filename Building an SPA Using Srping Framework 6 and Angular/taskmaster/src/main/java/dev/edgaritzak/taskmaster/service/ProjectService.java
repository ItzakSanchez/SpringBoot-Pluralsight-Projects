package dev.edgaritzak.taskmaster.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.edgaritzak.taskmaster.entity.Project;
import dev.edgaritzak.taskmaster.repository.ProjectRepository;

@Service
public class ProjectService {

  private final ProjectRepository projectRepository;

  @Autowired
  public ProjectService(ProjectRepository projectRepository){
    this.projectRepository = projectRepository;
  }

  public List<Project> getAllProjects(){
    return projectRepository.findAll();
  }

  public Optional<Project> getProjectById(Long id){
    return projectRepository.findById(id);
  }

  public Project addProject(Project project){
    return projectRepository.save(project);
  }

  public void deleteProject(Long id){
    projectRepository.deleteById(id);
  }
}
