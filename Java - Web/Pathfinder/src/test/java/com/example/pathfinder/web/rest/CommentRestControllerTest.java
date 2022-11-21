package com.example.pathfinder.web.rest;

import com.example.pathfinder.model.dto.CommentCreationtDTO;
import com.example.pathfinder.model.dto.CommentMessageDTO;
import com.example.pathfinder.model.views.CommentDisplayView;
import com.example.pathfinder.service.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentRestControllerTest {

    private static final Long ROUTE_ID = 1L;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

//    @MockBean(name = "mockUserDetails")
//    private UserDetails userDetails;

    @Test
//    @WithUserDetails("mockUserDetails")
    public void getComments_twoCommentsExist_commentsReturnedAsJsonAndStatusIsOk() throws Exception {
        when(commentService.getAllCommentsForRoute(ROUTE_ID)).thenReturn(List.of(
                new CommentDisplayView(1L, "John Doe", "This is comment #1"),
                new CommentDisplayView(2L, "Foo Bar", "This is comment #2")
        ));

        mockMvc.perform(get("/api/" + ROUTE_ID + "/comments"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.[0].authorName", is("John Doe")))
                .andExpect((ResultMatcher) jsonPath("$.[0].message", is("This is comment #1")))
                .andExpect((ResultMatcher) jsonPath("$.[1].authorName", is("Foo Bar")))
                .andExpect((ResultMatcher) jsonPath("$.[1].message", is("This is comment #2")));
    }

//    @Test
//    @WithMockUser(username = "testUsername")
//    public void createComment_sampleData_commentIsReturnedAsExpected() throws Exception {
//        when(commentService.createComment(any())).thenAnswer(interaction -> {
//            CommentCreationtDTO commentCreationDto = interaction.getArgument(0);
//            return new CommentDisplayView(1L, commentCreationDto.getUserName(),
//                    commentCreationDto.getMessage());
//        });
//        CommentMessageDTO commentMessageDto = new CommentMessageDTO("This is comment #1");
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        mockMvc.perform(post("/api/" + ROUTE_ID + "/comments")
//                        .content(objectMapper.writeValueAsString(commentMessageDto))
//                        .with(csrf())
//                        .contentType("application/json")
//                        .accept("application/json"))
//                .andExpect(status().is(201))
//                .andExpect((ResultMatcher) jsonPath("$.message", is("This is comment #1")))
//                .andExpect((ResultMatcher) jsonPath("$.authorName", is("testUsername")));
//    }
}
