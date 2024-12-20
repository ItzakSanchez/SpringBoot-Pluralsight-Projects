package dev.edgaritzak.taskmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.edgaritzak.taskmaster.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
