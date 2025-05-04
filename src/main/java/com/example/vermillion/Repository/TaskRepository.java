package com.example.vermillion.Repository;

import com.example.vermillion.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Long> {
}
