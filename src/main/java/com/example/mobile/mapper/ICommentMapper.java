package com.example.mobile.mapper;

import com.example.mobile.dto.request.CommentCreationReq;
import com.example.mobile.dto.request.CommentUpdateReq;
import com.example.mobile.dto.response.CommentResponse;
import com.example.mobile.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ICommentMapper {
    Comment toComment(CommentCreationReq req);
    @Mapping(target = "shopName", source = "shop.name")
    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "userName", source = "user.firstname")
    CommentResponse toCommentResponse(Comment comment);
    void updateComment(@MappingTarget Comment comment, CommentUpdateReq req);
}
