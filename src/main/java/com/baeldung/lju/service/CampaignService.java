package com.baeldung.lju.service;

import com.baeldung.lju.domain.model.Campaign;

import java.util.List;
import java.util.Optional;

public interface CampaignService {
    List<Campaign> findCampaigns();
    Optional<Campaign> findById(Long id); //same with interface CampaignRepository
    Campaign create(Campaign campaign);
    Optional<Campaign> closeCampaign (Long id);

}
