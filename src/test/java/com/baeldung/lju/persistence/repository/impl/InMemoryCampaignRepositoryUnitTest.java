package com.baeldung.lju.persistence.repository.impl;

import com.baeldung.lju.domain.model.Campaign;
import com.baeldung.lju.persistence.repository.CampaignRepository;

import org.junit.jupiter.api.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.*;

//write a test for the InMemoryCampaignRepository class, focusing on the findAll() method
// with this pattern
//-> givenPreconditions_whenStateUnderTest(test subject)_thenExpectedBehavior <-

@DisplayName("In-memory Campaign repository unit test")
//@DisplayNameGeneration(DisplayNameGenerator.Standard.class)
//@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
@IndicativeSentencesGeneration(separator = " - ", generator = DisplayNameGenerator.ReplaceUnderscores.class)
public class InMemoryCampaignRepositoryUnitTest {

    final static Logger logger = LoggerFactory.getLogger(InMemoryCampaignRepositoryUnitTest.class);
    CampaignRepository campaignRepository;

    @BeforeEach
    void setupDataSource() {
        Campaign existingCampaign = new Campaign("C-1-CODE", "Campaign 1", "Campaign 1 Description");
        existingCampaign.setId(1L);
        //Arrays.asList used to convert an array into a fixed-size list.
        campaignRepository = new InMemoryCampaignRepository(new HashSet<>(Arrays.asList(existingCampaign)));
        logger.info("@BeforeEach - Initialized Data Source");
        logger.info("Repository reference id:{}", System.identityHashCode(campaignRepository));
        logger.info("Data Source has campaigns:", campaignRepository.findAll().size());
    }

    @AfterEach
    void cleanup() {
        logger.info("@AfterEach cleanup");
        logger.info("Repository reference id:{}", System.identityHashCode(campaignRepository));
        logger.info("Data Source has campaigns:", campaignRepository.findAll().size());
    }


    // -> testing happy path
    // for the method findById() of InMemoryCampaignRepository by invoking it with a valid ID of a Campaign:
    @Test
    void givenExistingCampaign_whenFindById_thenCampaignRetrieved() {
        // -> given
        // created the Campaign and set its ID to a hardcoded value
        Campaign campaign = new Campaign("C-1-CODE", "Campaign 1", "Campaign 1 Description");
        campaign.setId(1L);
        // defined the InMemoryCampaignRepository from a Set containing our test data.
        InMemoryCampaignRepository repository = new InMemoryCampaignRepository(Set.of(campaign));

        // -> when
        // called the findById() with the same ID and confirmed it returned the Campaign.
        Optional<Campaign> retrievedCampaign = repository.findById(1L);

        // -> then
        Assertions.assertTrue(retrievedCampaign.isPresent());
        Assertions.assertEquals(campaign, retrievedCampaign.get());
    }


    // -> “corner case” (“alternative path”)
    //  that checks the  where the repository cannot find any element with the given ID
    // use an ID that isn’t associated with any Campaign and expect the method to return an empty Optional
    @Test
    void givenExistingCampaign_whenFindByNonExistingId_thenNoCampaignRetrieved() {
        // -> given
        Campaign campaign = new Campaign("P-1-CODE", "Campaign 1", "Campaign 1 Description");
        campaign.setId(1L);
        InMemoryCampaignRepository repository = new InMemoryCampaignRepository(Set.of(campaign));

        // -> when
        Optional<Campaign> retrievedCampaign = repository.findById(99L);

        // -> then
        Assertions.assertTrue(retrievedCampaign.isEmpty());
    }


    // -> "side-effect" -  test the side effects produced by our software.
    // when we save a new Campaign, the InMemoryCampaignRepository generates an ID for the campaign and then it stores it in memory.
    // The function mutates the original Campaign object, causing a side-effect.
    // short test to check if a Campaign‘s ID has been set after calling the save() method.
    @Test
    void givenEmptyDataSource_whenSave_thenCampaignIsAssignedId(){
        // -> given
        InMemoryCampaignRepository repository = new InMemoryCampaignRepository(new HashSet<>());

        // -> when
        Campaign campaign = new Campaign("P-NEW-CODE", "New Campaign", "New Campaign Description");
        repository.save(campaign);

        // -> then
        Assertions.assertTrue(campaign.getId() != null);
    }


    // a simple test to create an empty in-memory repository and verify that,
    // when calling findAll(), it will return an empty list

    @DisplayName("No data scenario: Given an empty data source, " + "when finding all campaigns, then an empty list is retrieved")
    @Test
    void givenEmptyDataSource_whenFindAllCampaigns_thenEmptyListRetrieved() {

        // -> given -  prepare the test context
        // create an InMemoryCampaignRepository with an empty set of campaigns:
        InMemoryCampaignRepository repository = new InMemoryCampaignRepository(new HashSet<>());

        // -> when - to trigger the method or use case we want to test.
//        List<Campaign> campaigns = repository.findAll();
        List<Campaign> retrievedCampaigns = repository.findAll();

        // -> then - check if the test outcome matches our expectations. Use assertTrue() to verify if the list of campaigns is empty
//        Assertions.assertTrue(campaigns.isEmpty());
        Assertions.assertEquals(true, retrievedCampaigns.isEmpty());
    }

}



