package com.example.mobile.service;

import com.example.mobile.dto.request.CommentRequest;
import com.example.mobile.entity.Comment;
import com.example.mobile.entity.User;
import com.example.mobile.repository.CommentRepository;
import com.example.mobile.repository.UserRepository;
import com.example.mobile.service.imp.IComment;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CommentService implements IComment {

    CommentRepository commentRepository;
   UserService userService;

    @Override
    public Comment comment(CommentRequest commentRequest) {
        int id = commentRequest.getUserId();
        User user = userService.getUserById(id);
        Comment comment = new Comment();
        comment.setContent(commentRequest.getContent());
        comment.setUser(user);
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> showAllComment() {
        return commentRepository.findAll();
    }


}
