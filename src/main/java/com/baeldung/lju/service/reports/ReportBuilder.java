package com.baeldung.lju.service.reports;


import com.baeldung.lju.domain.model.Task;
import com.baeldung.lju.domain.model.Worker;

import java.util.List;

/**
 * this functional interface receives an input of type T and
 * returns nothing
 *
 */

public interface ReportBuilder <T>{
    void addWorkersData (List<Task> tasks);
    void addCampaignsData(List<Task> tasks);
    void addTasksData (List<Task> tasks);
    void addSpecificWorkerData(Worker worker);
    T obtainReport();
}
