package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ExportPricedTasksDto;
import softuni.exam.models.dto.TaskSeedRootDto;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.models.entity.Part;
import softuni.exam.models.entity.Task;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.MechanicRepository;
import softuni.exam.repository.PartRepository;
import softuni.exam.repository.TaskRepository;
import softuni.exam.service.TaskService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private static String TASKS_FILE_PATH = "src/main/resources/files/xml/tasks.xml";
    private final TaskRepository taskRepository;
    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final MechanicRepository mechanicRepository;

    private final ValidationUtil validationUtil;

    private final ModelMapper mapper;

    private final XmlParser xmlParser;

    public TaskServiceImpl(TaskRepository taskRepository, CarRepository carRepository, PartRepository partRepository, MechanicRepository mechanicRepository, ValidationUtil validationUtil, ModelMapper mapper, XmlParser xmlParser) {
        this.taskRepository = taskRepository;
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.mechanicRepository = mechanicRepository;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.taskRepository.count() > 0;
    }

    @Override
    public String readTasksFileContent() throws IOException {
        return Files
                .readString(Path.of(TASKS_FILE_PATH));
    }

    @Override
    public String importTasks() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        xmlParser
                .fromFile(TASKS_FILE_PATH, TaskSeedRootDto.class)
                .getTaskSeedDtoList()
                .stream()
                .filter(taskSeedDto -> {
                    boolean isValid = validationUtil.isValid(taskSeedDto);

                    Optional<Mechanic> mechanic = this.mechanicRepository
                            .findByFirstName(taskSeedDto.getMechanicFirstName().getFirstName());

                    if (mechanic.isEmpty()) {
                        isValid = false;
                    }
                    sb.append(isValid
                            ?
                            String.format("Successfully imported task %.2f",
                                    taskSeedDto.getPrice())
                            :
                            "Invalid task"
                    ).append(System.lineSeparator());
                    return isValid;
                })
                .map(taskSeedDto -> {
                    Task task = mapper.map(taskSeedDto, Task.class);
                    Optional<Mechanic> mechanic = this.mechanicRepository
                            .findByFirstName(taskSeedDto.getMechanicFirstName().getFirstName());
                    Optional<Car> car = this.carRepository
                            .findById(taskSeedDto.getCar().getId());
                    Optional<Part> part = this.partRepository
                            .findById(taskSeedDto.getPart().getId());
                    task.setMechanic(mechanic.get());
                    task.setCars(car.get());
                    task.setParts(part.get());
                    return task;
                })
                .forEach(this.taskRepository::save);
        return sb.toString();
    }

    @Override
    public String getCoupeCarTasksOrderByPrice() {
        StringBuilder sb = new StringBuilder();
        List<ExportPricedTasksDto> tasks = this.taskRepository
                .findPricedTasks();

        tasks.forEach(t -> {
            sb.append(String.format("Car %s %s with %dkm%n" +
                            "-Mechanic: %s - task №%d:%n" +
                            " --Engine: %.1f%n" +
                            "---Price: %.2f$%n",
                    t.getCarMake(), t.getCarModel(), t.getKilometers(),
                    t.getFullName(), t.getId(),
                    t.getEngine(),
                    t.getPrice()
            ));
        });

        return sb.toString();
    }
}
