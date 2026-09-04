package com.baeldung.lju.persistence.repository.impl;

import com.baeldung.lju.domain.model.Task;
import com.baeldung.lju.domain.model.Worker;
import com.baeldung.lju.persistence.repository.TaskRepository;

import static java.util.List.copyOf;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class InMemoryTaskRepository  implements TaskRepository {

    private Set<Task> tasks;

    public InMemoryTaskRepository() {
        super();
        this.tasks = new HashSet<Task>();
    }

    public InMemoryTaskRepository(Set<Task> tasks) {
        super();
        this.tasks = tasks;
    }

    /**
     *
     * @param id
     * @return Optional task
     */
    @Override
    public Optional<Task> findById(Long id){
        return tasks.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    /**
     * This method finds all entities in Task repository
     * @return list of task
     */
    @Override
    public List<Task> findAll(){
        return copyOf(tasks);
    }

    /**
     * @param task
     * @return task
     */
    @Override
    public Task save(Task task) {
        Long taskId = task.getId();
        if(taskId == null){
            task.setId(new Random().nextLong(Long.MAX_VALUE));
        }else{
            findById(taskId).ifPresent(tasks::remove);
        }
        tasks.add(task);
        return task;
    }

    /**
     *  Optional.ofNullable() creates an Optional object from a value that might be null.
     *
     * @param name
     * @param assigneeId
     * @return
     */
    @Override
    public List<Task> findByNameContainingAndAssigneeId(String name, Long assigneeId) {
        return tasks.stream()
                .filter(p ->
                        (name == null || p.getName().contains(name)) //@findByNameContaining part from Task class
                        && (assigneeId == null || Optional.ofNullable(p.getAssignee()).map(Worker::getId)                             //@Worker's id from Worker class is mapping to
                                                                                      .map(assigneeId::equals).orElse(false)))  //@assigneeId variable
                .toList();  //then streaming by getAssignee() and converting to List
    }















}
