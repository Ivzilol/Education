package softuni.exam.instagraphlite.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.PostSeedRootDto;
import softuni.exam.instagraphlite.models.entity.Pictures;
import softuni.exam.instagraphlite.models.entity.Posts;
import softuni.exam.instagraphlite.models.entity.Users;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PostService;
import softuni.exam.instagraphlite.util.ValidationUtil;
import softuni.exam.instagraphlite.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final PictureRepository pictureRepository;

    private static final String POST_PATH = "src/main/resources/files/posts.xml";

    private final ValidationUtil validationUtil;

    private final ModelMapper mapper;

    private final XmlParser xmlParser;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, PictureRepository pictureRepository, ValidationUtil validationUtil, ModelMapper mapper, XmlParser xmlParser) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
        this.xmlParser = xmlParser;
    }


    @Override
    public boolean areImported() {
        return this.postRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files
                .readString(Path.of(POST_PATH));
    }

    @Override
    public String importPosts() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        xmlParser
                .fromFile(POST_PATH, PostSeedRootDto.class)
                .getPostSeedDtoList()
                .stream()
                .filter(postSeedDto -> {
                    boolean isValid = validationUtil.isValid(postSeedDto);

                    Optional<Users> userByUsername =
                            this.userRepository.findByUsername(postSeedDto.getUserUsername()
                                    .getUsername());
                    if (userByUsername.isEmpty()) {
                        isValid = false;
                    }
                    Optional<Pictures> pictureByPath =
                            this.pictureRepository.findByPath(postSeedDto.getPicturePate()
                                    .getPath());
                    if (pictureByPath.isEmpty()) {
                        isValid = false;
                    }

                    sb.append(isValid
                            ?
                            String.format("Successfully imported Post, made by %s",
                                    postSeedDto.getUserUsername().getUsername())
                            :
                            "InvalidInvalid Post"
                    ).append(System.lineSeparator());
                    return isValid;
                })
                .map(postSeedDto -> {
                    Posts post = mapper.map(postSeedDto, Posts.class);
                    Optional<Users> userByUsername =
                            this.userRepository.findByUsername(postSeedDto.getUserUsername()
                                    .getUsername());
                    Optional<Pictures> pictureByPath =
                            this.pictureRepository.findByPath(postSeedDto.getPicturePate()
                                    .getPath());
                    post.setUser(userByUsername.get());
                    post.setPicture(pictureByPath.get());
                    return post;
                })
                .forEach(postRepository::save);
        return sb.toString();
    }
}
