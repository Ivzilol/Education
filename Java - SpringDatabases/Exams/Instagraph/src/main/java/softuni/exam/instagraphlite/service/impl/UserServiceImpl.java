package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.ExportUsersWithPostDto;
import softuni.exam.instagraphlite.models.dto.UserSeedDto;
import softuni.exam.instagraphlite.models.entity.Pictures;
import softuni.exam.instagraphlite.models.entity.Users;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static org.springframework.data.util.Pair.toMap;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PictureRepository pictureRepository;

    private final PostRepository postRepository;

    private static final String USERS_PATH = "src/main/resources/files/users.json";

    private final Gson gson;

    private final ValidationUtil validationUtil;

    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, PictureRepository pictureRepository, PostRepository postRepository, Gson gson, ValidationUtil validationUtil, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.postRepository = postRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return userRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files
                .readString(Path.of(USERS_PATH));
    }

    @Override
    public String importUsers() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson
                        .fromJson(readFromFileContent(), UserSeedDto[].class))
                .filter(userSeedDto -> {
                    boolean isValid = validationUtil.isValid(userSeedDto);

                    Optional<Users> byUsername = this.userRepository
                            .findByUsername(userSeedDto.getUsername());
                    if (byUsername.isPresent()) {
                        isValid = false;
                    }
                    Optional<Pictures> picture = this.pictureRepository.findByPath(userSeedDto.getProfilePicture());
                    if (picture.isEmpty()) {
                        isValid = false;
                    }


                    sb.append(isValid
                            ?
                            String.format("Successfully imported User: %s",
                                    userSeedDto.getUsername())
                            :
                            "Invalid User"
                    ).append(System.lineSeparator());
                    return isValid;
                }).map(userSeedDto -> {
                    Users user = mapper.map(userSeedDto, Users.class);
                    Optional<Pictures> picture = this.pictureRepository.findByPath(userSeedDto.getProfilePicture());
                    picture.ifPresent(user::setProfilePicture);
                    return user;
                })
                .forEach(this.userRepository::save);
        return sb.toString();
    }

    @Override
    public String exportUsersWithTheirPosts() {
        StringBuilder sb = new StringBuilder();

        List<ExportUsersWithPostDto> usersPost = this.postRepository.findAllPost();
        Map<String, List<String>> print = new LinkedHashMap<>();

        usersPost.forEach(u -> {
            if (!print.containsKey(u.getUsername())) {
                print.put(u.getUsername(), null);
            }
        });

        for (Map.Entry<String, List<String>> user : print.entrySet()) {
            List<String> captionAndSize = new ArrayList<>();
            usersPost.forEach(u -> {
                if (Objects.equals(user.getKey(), u.getUsername())) {
                    captionAndSize.add(u.getCaption());
                    captionAndSize.add(String.valueOf(u.getSize()));
                }
            });
            print.put(user.getKey(), captionAndSize);
        }

        LinkedHashMap<String, List<String>> sorted = new LinkedHashMap<>();

        print.entrySet()
                .stream()
                .sorted((u1, u2) -> u2.getValue().size() - u1.getValue().size())
                .forEachOrdered(s -> sorted.put(s.getKey(), s.getValue()));

        for (Map.Entry<String, List<String>> user : sorted.entrySet()) {
            sb.append(String.format("User: %s%n" +
                            "Post count: %d%n",
                    user.getKey(),
                    user.getValue().size() / 2
            ));
            IntStream.range(0, user.getValue().size()).forEach(i -> {
                if (i % 2 == 0) {
                    sb.append(String.format("==Post Details:%n" +
                                    "----Caption: %s%n",
                            user.getValue().get(i)
                    ));
                } else {
                    sb.append(String.format("----Picture Size: %.2f%n",
                            Double.parseDouble(user.getValue().get(i))));
                }
            });
        }
        return sb.toString();
    }
}
