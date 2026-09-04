package com.baeldung.lju;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

import com.baeldung.lju.domain.model.Campaign;
import com.baeldung.lju.domain.model.Task;
import com.baeldung.lju.domain.model.TaskStatus;
import com.baeldung.lju.domain.model.Worker;

import com.baeldung.lju.service.CampaignService;
import com.baeldung.lju.service.ReportsService;
import com.baeldung.lju.service.TaskService;
import com.baeldung.lju.service.WorkerService;

import com.baeldung.lju.service.impl.DefaultCampaignService;
import com.baeldung.lju.service.impl.DefaultReportsService;
import com.baeldung.lju.service.impl.DefaultTaskService;
import com.baeldung.lju.service.impl.DefaultWorkerService;

public class LjuApp {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(LjuApp.class);
        logger.info("LJU App start");

        // constructing Services
        CampaignService campaignService = new DefaultCampaignService();
        TaskService taskService = new DefaultTaskService();
        WorkerService workerService = new DefaultWorkerService();
        ReportsService reportsHandler= new DefaultReportsService(taskService, workerService);

        //CREATE
        //creating new Campaigns
        com.baeldung.lju.domain.model.Campaign newCampaign = new Campaign ("C1", "Campaign 1", "Campaign 1 Description");
        campaignService.create(newCampaign);
        logger.info("Saved new Campaign 1: {}", newCampaign);

        //creating new Tasks
        Task newTask = new Task("Task 1", "Task 1 Description", LocalDate.now(), newCampaign, TaskStatus.TO_DO, null);
        taskService.create(newTask);
        logger.info("Saved new Task 1: {}", newTask);

        // create a Task and assign a Worker to it
        Task newTask2 = new Task("Task 2", "Task 2 Description", LocalDate.now().minusDays(5), newCampaign, TaskStatus.TO_DO, null);
        taskService.create(newTask2);

        Worker worker = new Worker("alan.ocallaghan@gmail.com","Alan","O'callaghan");
        workerService.create(worker);
        taskService.updateAssignee(newTask2.getId(), worker);

        // FIND
        // find Campaign by Id
        Campaign existingCampaign = campaignService.findById(newCampaign.getId()).get();
        logger.info("Retrieved Campaign: {}", existingCampaign);
        logger.info("Retrieved Campaign's Tasks: {}", existingCampaign.getTasks());

        // find all Campaigns
        List<Campaign>allExistingCampaigns = campaignService.findCampaigns();
        logger.info("Retrieved all ({}) existing Campaigns: {}", allExistingCampaigns.size(), allExistingCampaigns);

        // CREATE
        // create Task and add Assignee
        Worker worker1 = new Worker("john@test.com", "John", "Doe");
        workerService.create(worker1);
        Task newTask3 = new Task("Task 3", "Task 2 Description", LocalDate.now().minusDays(5), newCampaign, TaskStatus.TO_DO, null);
        taskService.updateAssignee(newTask3.getId(), worker1);
    }
}