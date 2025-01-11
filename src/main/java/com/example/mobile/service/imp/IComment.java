package com.example.mobile.service.imp;

import com.example.mobile.dto.request.CommentCreationReq;
import com.example.mobile.dto.request.CommentUpdateReq;
import com.example.mobile.dto.response.CommentResponse;

import java.util.List;

public interface IComment {
    CommentResponse addComment(CommentCreationReq req);
    CommentResponse commentUpdate(int id, CommentUpdateReq req);
    void deleteComment(int id);
    List<CommentResponse> getListCommentByShopId(int shopId);
    List<CommentResponse> getListCommentByUserId(int userId);
    List<CommentResponse> getListCommentByProductId(int productId);
    CommentResponse findById(int id);
}
