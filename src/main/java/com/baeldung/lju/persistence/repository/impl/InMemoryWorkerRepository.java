package com.baeldung.lju.persistence.repository.impl;

import com.baeldung.lju.domain.model.Worker;
import com.baeldung.lju.persistence.repository.WorkerRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class InMemoryWorkerRepository implements WorkerRepository {

    private Set<Worker> workers;

    public InMemoryWorkerRepository() {
        super();
        this.workers = new HashSet<>();
    }

    public InMemoryWorkerRepository(Set<Worker> workers) {
        super();
        this.workers = workers;
    }

    /**
     * This method finds an entity in Worker repository given a specific ID:
     * @param id
     * @return Optional worker
     */
    @Override
    public Optional<Worker> findById(Long id) {
        return workers.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    /**
     * @param worker
     * @return worker
     */
    @Override
    public Worker save(Worker worker) {
        Long workerId = worker.getId();
        if (workerId == null) {
            worker.setId(new Random().nextLong(Long.MAX_VALUE));
        }else{
            findById(workerId).ifPresent(workers::remove);
        }
        workers.add(worker);
        return worker;
    }
}
