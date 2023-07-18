package softuni.exam.areImported;
//TestTasksServiceAreImportedTrue

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.exam.repository.TaskRepository;
import softuni.exam.service.impl.TaskServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TestTasksServiceAreImportedTrue {

    @InjectMocks
    private TaskServiceImpl tasksService;
    @Mock
    private TaskRepository mockTasksRepository;

    @Test
    void areImportedShouldReturnTrue() {
        Mockito.when(mockTasksRepository.count()).thenReturn(1L);
        Assertions.assertTrue(tasksService.areImported());
    }
}