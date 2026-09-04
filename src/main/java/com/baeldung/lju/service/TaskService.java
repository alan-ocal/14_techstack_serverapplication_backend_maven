package com.baeldung.lju.service;

import com.baeldung.lju.domain.model.Task;
import com.baeldung.lju.domain.model.TaskStatus;
import com.baeldung.lju.domain.model.Worker;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> searchTasks(String nameSubstring, Long assigneeId);
    Optional<Task> findById(Long id); //same with interface TaskRepository
    Task create(Task task);
    Optional<Task> updateTask(Long id, Task task);
    Optional<Task> updateStatus(Long id, TaskStatus status);
    Optional<Task> updateAssignee(Long id, Worker assignee);
}
