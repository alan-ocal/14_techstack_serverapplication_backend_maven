package com.baeldung.lju.persistence.repository.impl;

import com.baeldung.lju.domain.model.Campaign;
import com.baeldung.lju.persistence.repository.CampaignRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FakeCampaignRepository implements CampaignRepository {

    private Campaign mockedCampaign;

    public FakeCampaignRepository(Campaign mockedCampaign) {
        this.mockedCampaign = mockedCampaign;
    }

    @Override
    public Optional <Campaign>findById(Long id) {
        return Optional.ofNullable(mockedCampaign);
    }

    @Override
    public List<Campaign> findAll() {
        if (mockedCampaign != null) {
            //The singletonList() method of java.util.Collections class is used to return an immutable list containing only the specified object
            return Collections.singletonList(mockedCampaign);
        }
        return Collections.emptyList();

    }

    @Override
    public Campaign save(Campaign campaign) {
        return mockedCampaign;
    }

}
