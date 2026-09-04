package com.baeldung.lju.service;

import com.baeldung.lju.domain.model.Worker;

import java.util.Optional;

public interface WorkerService {
    Optional<Worker> findById(Long id ); //same with interface WorkerRepository
    Worker create (Worker worker);
    Optional <Worker> updateWorker (Long id, Worker worker);
}
