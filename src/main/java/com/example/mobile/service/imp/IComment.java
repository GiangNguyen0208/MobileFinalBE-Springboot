package com.example.mobile.service.imp;

import com.example.mobile.dto.request.CommentRequest;
import com.example.mobile.entity.Comment;

import java.util.List;

public interface IComment {
    public Comment comment (CommentRequest commentRequest);
    public List<Comment>showAllComment();
}
