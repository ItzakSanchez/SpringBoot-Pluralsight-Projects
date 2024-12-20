package dev.edgaritzak.taskmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.edgaritzak.taskmaster.entity.Project;

public interface ProjectRepository extends JpaRepository<Project,Long>{

}
