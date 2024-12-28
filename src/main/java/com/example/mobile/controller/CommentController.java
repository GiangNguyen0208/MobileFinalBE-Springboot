package com.example.mobile.controller;

import com.example.mobile.dto.request.CommentCreationReq;
import com.example.mobile.dto.request.CommentUpdateReq;
import com.example.mobile.dto.request.ProductCreationReq;
import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.dto.response.CommentResponse;
import com.example.mobile.dto.response.ProductResponse;
import com.example.mobile.entity.Comment;
import com.example.mobile.repository.CommentRepository;
import com.example.mobile.service.imp.IComment;
import com.example.mobile.service.imp.IProduct;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentController {
    IComment commentService;

    @PostMapping("/add")
    public CommentResponse addComment(@RequestBody CommentCreationReq comment) {
        return commentService.addComment(comment);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("commentId") int id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findId/{commentId}")
    public CommentResponse findCommentById(@PathVariable("commentId") int id) {
        return commentService.findById(id);
    }

    @GetMapping("/list/shop/{shopId}")
    public List<CommentResponse> getListByShopId(@PathVariable("shopId") int id) {
        return commentService.getListCommentByShopId(id);
    }

    @GetMapping("/list/user/{userId}")
    public List<CommentResponse> getListByUserId(@PathVariable("userId") int id) {
        return commentService.getListCommentByUserId(id);
    }

    @GetMapping("/list/product/{productId}")
    public List<CommentResponse> getListByProductId(@PathVariable("productId") int id) {
        return commentService.getListCommentByProductId(id);
    }

    @PutMapping("/update/{commentId}")
    public CommentResponse updateComment(@PathVariable("commentId") int id, @RequestBody CommentUpdateReq req) {
        return commentService.commentUpdate(id,req);
    }
}

