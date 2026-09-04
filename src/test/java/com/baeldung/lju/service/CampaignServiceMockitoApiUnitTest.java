package com.baeldung.lju.service;

import com.baeldung.lju.domain.model.Campaign;
import com.baeldung.lju.domain.model.TaskStatus;
import com.baeldung.lju.persistence.repository.CampaignRepository;
import com.baeldung.lju.service.impl.DefaultCampaignService;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.mockito.Mockito;

import java.util.Optional;

class CampaignServiceMockitoApiUnitTest {
    @Test
    void whenClosingACampaign_thenReturnCorrectData(){
        // ->given
        // Mock the CampaignRepository (create a mock for the repository)
        CampaignRepository repository = Mockito.mock(CampaignRepository.class);

        // create the CampaignService (inject repository into the CampaignService through its constructor.)
        CampaignService service = new DefaultCampaignService(repository);

        Campaign testCampaign = new Campaign("test-code", "test-name", "test-description");
        // return a valid test Campaign when it is called with an id value of “1L”:
        // whenever I call findById() passing the value of “1L“, the mock will return the testCampaign instance
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(testCampaign));

        // -> when
        // call the method being tested, `closeCampaign()`,
        Optional<Campaign> result = service.closeCampaign(1L);

        // -> then
        //and perform the relevant assertions
        assertTrue(result.isPresent());
        Campaign campaign = result.get();
        assertEquals("test-code", campaign.getCode());
        assertEquals("test-name", campaign.getName());
        assertEquals("test-description", campaign.getDescription());

        // -> and
        assertTrue(campaign.isClosed());
        campaign.getTasks().forEach(task -> assertEquals(TaskStatus.DONE, task.getStatus()));
    }

    // validate the use case where the campaign cannot be found based
    @Test
    void whenClosingACampaignWhichIsNotFound_thenReturnEmpty(){
        // -> given
        // Mock the CampaignRepository (create a mock for the repository)
        CampaignRepository repository = Mockito.mock(CampaignRepository.class);

        // create the CampaignService (inject repository into the CampaignService through its constructor.)
        CampaignService service = new DefaultCampaignService(repository);

        // CampaignRepository is invoked to find the campaign with an id value of “1L“,
        // it will return an empty Optional
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());

        // -> when
        // call the method being tested, `closeCampaign()`,
        Optional<Campaign> result = service.closeCampaign(1L);

        // -> then
        assertTrue(result.isEmpty());
    }

    @Test
    void whenClosingCampaigns_thenClosesCampaignIfFound(){
        // given
        // Mock the CampaignRepository (create a mock for the repository)
        CampaignRepository repository = Mockito.mock(CampaignRepository.class);

        // create the CampaignService (inject repository into the CampaignService through its constructor.)
        CampaignService service = new DefaultCampaignService(repository);

        Campaign testCampaign = new Campaign("test-code", "test-name", "test-description");

        /*
        use Mockito.anyLong() to make sure that the testCampaign will be returned, regardless of the value of the id argument:
        if I don’t know or aren’t interested in checking the exact value of the argument passed to the mocked method,
        I can use an ArgumentMatcher instead.
         */
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(testCampaign));

        // -> when
        Optional<Campaign> result = service.closeCampaign(99L);

        // -> then
        assertTrue(result.isPresent());
        Campaign campaign = result.get();

        assertEquals("test-code", campaign.getCode());

    }
}
