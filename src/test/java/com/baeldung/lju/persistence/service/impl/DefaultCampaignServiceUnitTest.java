package com.baeldung.lju.persistence.service.impl;

import java.util.List;
import java.util.Optional;

import com.baeldung.lju.domain.model.Campaign;
import com.baeldung.lju.persistence.repository.CampaignRepository;
import com.baeldung.lju.service.CampaignService;
import com.baeldung.lju.service.impl.DefaultCampaignService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DefaultCampaignServiceUnitTest {

    @Mock
    CampaignRepository campaignRepository;

    CampaignService campaignService;

    @BeforeEach
    void setUpDataSource() {
        campaignService = new DefaultCampaignService(campaignRepository);;
    }

    //using assertEquals & assertNotEquals
    @Test
    void givenMockedPersistedCampaign_whenFindById_thenCodeEqualsAndNameNotEquals() {
        // -> given
        // create a mockedCampaign object
        Campaign mockedCampaign = new Campaign("C-1-CODE", "Campaign 1", "Campaign 1 Description");

        // retrieve it in a new campaign variable based on the id
        when (campaignRepository.findById(3L)).thenReturn(Optional.of(mockedCampaign));

        // -> when
        Campaign campaign = campaignService.findById(3L).get(); //!!VIP campaign is used in assertEquals()

        // -> then
        // define another Campaign object with the same code, but a different name and description
        //Campaign expectedCampaign = new Campaign("C-1-CODE", "Different Name", "Different Description");
        //change the code of expectedCampaign to C-2-CODE:
        Campaign expectedCampaign = new Campaign("C-2-CODE", "Different Name", "Different Description");
        expectedCampaign.setId(99L);                            //!!VIP expectedCampaign is used in assertEquals()

        //use the assertEquals() method to check whether the code field contains the same value in both campaign and expectedCampaign:
        assertEquals(expectedCampaign.getCode(), campaign.getCode(), "The code should be equal");
        assertEquals(expectedCampaign.getCode(), campaign.getCode(), "The code should be equal");

        //use the assertNotEquals() method to verify two values aren’t equal
        assertNotEquals(expectedCampaign.getName(), campaign.getName(), "The name mismatched");
    }

    //using  assertSame &  assertNotSame
    @Test
    void givenMockedPersistedCampaign_whenFindById_thenCampaignIsTheSameAsMockedAndDifferentFromExpected(){
        // -> given
        Campaign mockedCampaign = new Campaign("C-1-CODE", "Campaign 1", "Campaign 1 Description");         //!!VIP mockedCampaign is used in assertSame()
        when (campaignRepository.findById(3L)).thenReturn(Optional.of(mockedCampaign));

        // -> when
        Campaign campaign = campaignService.findById(3L).get();                                                                 //!!VIP campaign is used in assertSame()

        // -> then
        Campaign expectedCampaign = new Campaign("C-1-CODE", "Different Name", "Different Description");  //!!VIP expectedCampaign is used in assertSame()
        expectedCampaign.setId(99L);

        //          (expected, actual)
        assertSame(mockedCampaign,campaign);        //mockedCampaign and campaign refer to the same object
        assertNotSame(expectedCampaign,campaign);   //expectedCampaign refers to a different object than the campaign.
    }

    //using assertIterableEquals()
    @Test
    void givenMockedPersistedCampaigns_whenFindAll_thenCampaignsEquals(){
        // -> given
        Campaign mockedCampaign = new Campaign("C-1-CODE", "Campaign 1", "Campaign 1 Description");     //!!VIP mockedCampaign is used in assertIterableEquals()
        when (campaignRepository.findAll()).thenReturn(List.of(mockedCampaign));

        // -> when
        List<Campaign> campaigns = campaignService.findCampaigns();                                                            //!!VIP campaigns is used in assertIterableEquals()

        // -> then
        assertIterableEquals(List.of(mockedCampaign), campaigns, "The campaigns should be equal");
    }

    //using assertTrue
    @Test
    void givenMockedPersistedCampaign_whenFindById_thenDescriptionIsBlank() {

        //-> given
        Campaign mockedCampaign = new Campaign("C-1-CODE", "Campaign 1", "");
        when (campaignRepository.findById(3L)).thenReturn(Optional.of(mockedCampaign));

        // -> when
        Campaign campaign = campaignService.findById(3L).get();

        // -> then
        assertTrue(campaign.getDescription().isBlank()); //The test passes since the getDescription() method returns an empty String value.

    }

    //using assertFalse
    @Test
    void givenMockedPersistedCampaign_whenFindById_thenIsClosedFalse(){
        //-> given
        Campaign mockedCampaign = new Campaign("C-1-CODE", "Campaign 1", "Campaign 1 Description");
        when (campaignRepository.findById(3L)).thenReturn(Optional.of(mockedCampaign));

        // -> when
        Campaign campaign = campaignService.findById(3L).get();

        // -> then
        assertFalse(campaign.isClosed());
    }

    // using assertNull
    // mocked the Campaign object by the findById() method returns
    @Test
    void givenMockedPersistedCampaign_whenFindById_thenIdNull() {
        //-> given
        Campaign mockedCampaign = new Campaign("C-1-CODE", "Campaign 1", "");
        when (campaignRepository.findById(3L)).thenReturn(Optional.of(mockedCampaign));

        // -> when
        Campaign campaign = campaignService.findById(3L).get();

        // -> then
        // accepts a single argument, representing the object I want to check.
        // assertNull(campaign.getId());       // Since I created an object without an `id`, by using the assertNull() method, I verified that the getId() method returns a null value.
        assertNull(campaign.getCode());        // Since I created an object with a `code`, by using the assertNull() method, I received an error
    }

    // using assertNotNull()
    // The test confirms the getCode() method returns a non-null value.
    @Test
    void givenMockedPersistedCampaign_whenFindById_thenCodeNotNull() {
        //-> given
        Campaign mockedCampaign = new Campaign("C-1-CODE", "Campaign 1", "");
        when (campaignRepository.findById(3L)).thenReturn(Optional.of(mockedCampaign));

        // -> when
        Campaign campaign = campaignService.findById(3L).get();

        // -> then
        assertNotNull(campaign.getCode());

    }
}





















