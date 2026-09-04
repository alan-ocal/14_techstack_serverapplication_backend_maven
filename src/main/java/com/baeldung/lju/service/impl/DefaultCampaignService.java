package com.baeldung.lju.service.impl;

import com.baeldung.lju.domain.model.Campaign;
import com.baeldung.lju.domain.model.TaskStatus;
import com.baeldung.lju.persistence.repository.CampaignRepository;
import com.baeldung.lju.persistence.repository.impl.InMemoryCampaignRepository;
import com.baeldung.lju.service.CampaignService;

import java.util.List;
import java.util.Optional;

public class DefaultCampaignService implements CampaignService {
    private CampaignRepository campaignRepository;

    public DefaultCampaignService(CampaignRepository campaignRepository) {
        super();
        // IoC (Inversion of Control) pattern
        // since the instance of the CampaignRepository interface is being used
        this.campaignRepository = campaignRepository;
    }
    public DefaultCampaignService() {
        super();
        this.campaignRepository = new InMemoryCampaignRepository();
    }

    @Override
    public Optional<Campaign> findById(Long id){
        return campaignRepository.findById(id);
    }

    @Override
    public Campaign create(Campaign campaign){
       if(campaign.getId() != null){
           throw new IllegalArgumentException("Can't create Campaign with assigned 'id'");
       }
       return campaignRepository.save(campaign);
    }
    @Override
    public List<Campaign> findCampaigns(){
        return campaignRepository.findAll();
    }

    @Override
    public Optional<Campaign> closeCampaign (Long id){
        return campaignRepository.findById(id)
                .map(campaign -> {
                    campaign.setClosed(true);
                    campaign.getTasks()
                            .forEach(task -> {
                                task.setStatus(TaskStatus.DONE);
                            });
                   return campaign;
                });

    }
}
