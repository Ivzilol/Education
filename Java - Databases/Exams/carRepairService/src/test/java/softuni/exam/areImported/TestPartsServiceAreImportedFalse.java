package softuni.exam.areImported;
//TestPartsServiceAreImportedFalse

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.exam.repository.PartRepository;
import softuni.exam.service.impl.PartServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TestPartsServiceAreImportedFalse {

    @InjectMocks
    private PartServiceImpl partsService;
    @Mock
    private PartRepository mockPartsRepository;

    @Test
    void areImportedShouldReturnFalse() {
        Mockito.when(mockPartsRepository.count()).thenReturn(0L);
        Assertions.assertFalse(partsService.areImported());
    }
}