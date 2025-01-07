package com.example.mobile.service;

import com.example.mobile.dto.request.CommentRequest;
import com.example.mobile.dto.response.CommentResponse;
import com.example.mobile.dto.response.UserResponse;
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

//    @Override
//    public CommentResponse comment(CommentRequest commentRequest) {
//        Comment comment = Comment.builder()
//                .content(commentRequest.getContent())
//                .
//                .build();
//
//
//        return commentRepository.save(comment);
//    }

    @Override
    public Comment comment(CommentRequest commentRequest) {
        return null;
    }

    @Override
    public List<Comment> showAllComment() {
        return commentRepository.findAll();
    }


}
