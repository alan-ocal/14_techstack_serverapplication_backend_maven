package com.baeldung.lju.service;

import com.baeldung.lju.domain.model.Campaign;
import com.baeldung.lju.persistence.repository.CampaignRepository;
import com.baeldung.lju.service.impl.DefaultCampaignService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CampaignServiceMockLifecycleUnitTest {

    @Mock
    private CampaignRepository repository;

    @InjectMocks
    private DefaultCampaignService service;

    // Alternative 1
    // give Mockito full control over the mock lifecycle by enabling these annotations.
    // call MockitoAnnotations.openMocks() from a non-static context
    {
        MockitoAnnotations.openMocks(this);
    }

//    @BeforeEach
//    void setUp() {
//        this.repository = Mockito.mock(CampaignRepository.class);
//        this.service = new DefaultCampaignService(repository);
//    }

//    @Test
//    void whenClosingACampaignWhichIsNotFound_thenReturnEmpty(){
//        // -> given
//        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());
//
//        // -> then
//        assertTrue(service.closeCampaign(1L).isEmpty());
//
//    }

    @Test
    void whenClosingACampaign_thenReturnCorrectData(){
        // -> given
        Campaign testCampaign = new Campaign("test-code", "test-name", "test-description");
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(testCampaign));

        // -> when/then
        assertTrue(service.closeCampaign(1L).isPresent());
    }
}
