package com.baeldung.lju.persistence.repository.impl;

import com.baeldung.lju.domain.model.Campaign;
import com.baeldung.lju.persistence.repository.CampaignRepository;

import static java.util.List.copyOf;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;


public class InMemoryCampaignRepository implements CampaignRepository {

    private Set<Campaign> campaigns;

    public InMemoryCampaignRepository() {
        super();
        this.campaigns = new HashSet<>();
    }

    public InMemoryCampaignRepository(Set<Campaign> campaigns) {
        super();
        this.campaigns = campaigns;
    }

    /**
     * This method finds an entity in Campaign repository given a specific ID:
     * @param id
     * @return Optional campaign
     */
    @Override
    public Optional<Campaign> findById(Long id) {
        return campaigns.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    /**
     * This method finds all entities in Campaign  repository
     * @return list of campaign
     */
    @Override
    public List<Campaign> findAll(){
        //using java 10
        return copyOf(campaigns);
    }

    /**
     * @param campaign
     * @return campaign
     */
    @Override
    public Campaign save(Campaign campaign) {
        Long campaignId = campaign.getId();
        if (campaignId == null) {
            campaign.setId(new Random().nextLong(Long.MAX_VALUE));
        }else{
            //Conditional Action With ifPresent()
            findById(campaignId).ifPresent(campaigns::remove);
        }
        campaigns.add(campaign);
        return campaign;
    }
}
