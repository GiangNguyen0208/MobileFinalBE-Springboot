package com.example.mobile.controller;

import com.example.mobile.dto.request.CommentRequest;
import com.example.mobile.entity.Comment;
import com.example.mobile.service.CommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentController {

    CommentService commentService;
    @PostMapping("/comment")
    public ResponseEntity<Comment> comment(@RequestBody CommentRequest commentRequest) {
        Comment comment = commentService.comment(commentRequest);
        return ResponseEntity.ok(comment);
    }
    @GetMapping("show-all-comment")
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.showAllComment();
        return ResponseEntity.ok(comments);  // Trả về danh sách tất cả comment
    }
}
