package com.example.vermillion.Service;

import com.example.vermillion.DTO.TaskDto;
import com.example.vermillion.Model.Project;
import com.example.vermillion.Model.Task;
import com.example.vermillion.Model.Developer;
import com.example.vermillion.Repository.TaskRepository;
import com.example.vermillion.Repository.ProjectRepository;
import com.example.vermillion.Repository.DeveloperRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final DeveloperRepository developerRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Transactional
    public Task create(TaskDto dto) {
        Project project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new EntityNotFoundException("Проект не найден: " + dto.getProjectId()));
        Developer dev = developerRepository.findById(dto.getAssignedToId())
                .orElseThrow(() -> new EntityNotFoundException("Разработчик не найден: " + dto.getAssignedToId()));

        Task task = new Task();
        task.setProject(project);
        task.setTaskName(dto.getTaskName());
        task.setAssignedTo(dev);
        task.setStatus(dto.getStatus());
        task.setDueDate(dto.getDueDate());
        task.setDescription(dto.getDescription());

        return taskRepository.save(task);
    }

    @Transactional
    public Optional<Task> update(Long id, TaskDto dto) {
        return taskRepository.findById(id).map(existing -> {
            Project project = projectRepository.findById(dto.getProjectId())
                    .orElseThrow(() -> new EntityNotFoundException("Проект не найден: " + dto.getProjectId()));
            Developer dev = developerRepository.findById(dto.getAssignedToId())
                    .orElseThrow(() -> new EntityNotFoundException("Разработчик не найден: " + dto.getAssignedToId()));

            existing.setProject(project);
            existing.setTaskName(dto.getTaskName());
            existing.setAssignedTo(dev);
            existing.setStatus(dto.getStatus());
            existing.setDueDate(dto.getDueDate());
            existing.setDescription(dto.getDescription());
            return taskRepository.save(existing);
        });
    }

    @Transactional
    public boolean delete(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}