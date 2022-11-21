package com.example.pathfinder.service;

import com.example.pathfinder.exeptions.RouteNotFoundException;
import com.example.pathfinder.model.Comment;
import com.example.pathfinder.model.Route;
import com.example.pathfinder.model.User;
import com.example.pathfinder.model.dto.CommentCreationtDTO;
import com.example.pathfinder.model.views.CommentDisplayView;
import com.example.pathfinder.repository.CommentRepository;
import com.example.pathfinder.repository.RouteRepository;
import com.example.pathfinder.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final RouteRepository routeRepository;
    private final UserRepository userRepository;

    private CommentRepository commentRepository;

    public CommentService(RouteRepository routeRepository,
                          UserRepository userRepository,
                          CommentRepository commentRepository) {
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public CommentDisplayView createComment(CommentCreationtDTO commentDTO) {

        User author = userRepository.findByUsername(commentDTO.getUserName()).get();

        Comment comment = new Comment();
        comment.setCreated(LocalDateTime.now());
        comment.setRoute(routeRepository.getReferenceById(commentDTO.getRouteId()));
        comment.setAuthor(author);
        comment.setApproved(true);
        comment.setText(commentDTO.getMessage());
        commentRepository.save(comment);

        return new CommentDisplayView(comment.getId(),author.getFullName(), comment.getText());
    }

    public List<CommentDisplayView> getAllCommentsForRoute(Long routeId) {
        Route route = routeRepository.findById(routeId).orElseThrow(RouteNotFoundException::new);

        List<Comment> comments = commentRepository.findAllByRoute(route).get();
        return comments.stream().map(comment -> new CommentDisplayView(comment.getId(),
                comment.getAuthor().getFullName(),
                comment.getText())).collect(Collectors.toList());
    }
}
