package ua.training.web;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;
import ua.training.service.CommentService;
import ua.training.model.Comment;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class CommentControllerTest {

    private CommentController controller;

    private MockMvc mockMvc;

    @Test
    public void shouldReturnRecentComments() throws Exception {
        List<Comment> expectedComments = createCommentList(20);
        CommentService mockService = mock(CommentService.class);
        when(mockService.findComments(Long.MAX_VALUE, 20)).thenReturn(expectedComments);

        controller = new CommentController(mockService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/views/comments.jsp"))
                .build();

        mockMvc.perform(get("/comments"))
                .andExpect(view().name("comments"))
                .andExpect(model().attributeExists("commentList"))
                .andExpect(model().attribute("commentList", hasItems(expectedComments.toArray())));
    }

    @Test
    public void shouldReturnPagedComments() throws Exception {
        List<Comment> expectedComments = createCommentList(50);
        CommentService mockService = mock(CommentService.class);
        when(mockService.findComments(238900, 50))
                .thenReturn(expectedComments);

        controller = new CommentController(mockService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/views/comments.jsp"))
                .build();

        mockMvc.perform(get("/comments?max=238900&count=50"))
                .andExpect(view().name("comments"))
                .andExpect(model().attributeExists("commentList"))
                .andExpect(model().attribute("commentList", hasItems(expectedComments.toArray())));
    }

    @Test
    public void shouldReturnCommentById() throws Exception {
        Comment expectedComment = new Comment(null, "May the Force be with you!", new Date());
        CommentService mockService = mock(CommentService.class);
        when(mockService.findOne(42)).thenReturn(expectedComment);

        controller = new CommentController(mockService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/comments/42"))
                .andExpect(view().name("comment"))
                .andExpect(model().attributeExists("comment"))
                .andExpect(model().attribute("comment", expectedComment));
    }

    private List<Comment> createCommentList(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> new Comment(null, "Comment #" + i, new Date()))
                .collect(Collectors.toList());

    }

}