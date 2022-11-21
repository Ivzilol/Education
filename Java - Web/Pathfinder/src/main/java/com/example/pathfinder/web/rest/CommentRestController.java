package com.example.pathfinder.web.rest;

import com.example.pathfinder.model.dto.CommentCreationtDTO;
import com.example.pathfinder.model.dto.CommentMessageDTO;
import com.example.pathfinder.model.views.CommentDisplayView;
import com.example.pathfinder.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentRestController {

    private final CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{routeId}/comments")
    public ResponseEntity<List<CommentDisplayView>> getComments(@PathVariable("routeId") Long routeId) {
        return ResponseEntity.ok(commentService.getAllCommentsForRoute(routeId));
    }

    @PostMapping(value = "/{routeId}/comments",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<CommentDisplayView> createComments(@PathVariable("routeId") Long routeId,
                                                            @AuthenticationPrincipal UserDetails userDetails,
                                                            @RequestBody CommentMessageDTO commentDto){
        CommentCreationtDTO commentCreationtDTO = new CommentCreationtDTO(
                userDetails.getUsername(),
                routeId,
                commentDto.getMessage()
        );

        CommentDisplayView comment = commentService.createComment(commentCreationtDTO);
        return ResponseEntity
                .created(URI.create(String.format("/api/%d/comments/%d", routeId, comment.getId())))
                .body(comment);
    }
}

// GET /api/{routeId}/comments -> return list of comments dor route
// POST /api/{routeId}/comments -> create comments to route to return the comment just created
// GET /api/{routeId}/comments/{commentId} -> return specific comment by id
