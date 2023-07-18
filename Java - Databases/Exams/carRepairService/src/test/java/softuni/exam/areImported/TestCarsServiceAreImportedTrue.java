package softuni.exam.areImported;
//TestCarsServiceAreImportedTrue

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.impl.CarServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TestCarsServiceAreImportedTrue {

    @InjectMocks
    private CarServiceImpl carsService;
    @Mock
    private CarRepository mockCarsRepository;

    @Test
    void areImportedShouldReturnTrue() {
        Mockito.when(mockCarsRepository.count()).thenReturn(1L);
        Assertions.assertTrue(carsService.areImported());
    }
}