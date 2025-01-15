package com.example.mobile.service;

import com.example.mobile.dto.request.CommentCreationReq;
import com.example.mobile.dto.request.CommentUpdateReq;
import com.example.mobile.dto.response.CommentResponse;
import com.example.mobile.entity.Comment;
import com.example.mobile.entity.Product;
import com.example.mobile.entity.Shop;
import com.example.mobile.entity.User;
import com.example.mobile.mapper.ICommentMapper;
import com.example.mobile.repository.CommentRepository;
import com.example.mobile.repository.ProductRepository;
import com.example.mobile.repository.ShopRepository;
import com.example.mobile.repository.UserRepository;
import com.example.mobile.service.imp.IComment;
import com.example.mobile.service.imp.IProduct;
import com.example.mobile.service.imp.IShop;
import com.example.mobile.service.imp.IUser;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CommentService implements IComment {
    ICommentMapper commentMapper;
    CommentRepository commentRepository;
    UserRepository userRepository;
    ProductRepository productRepository;
    ShopRepository shopRepository;


    @Override
    public CommentResponse addComment(CommentCreationReq req) {
        Comment comment = commentMapper.toComment(req);
        User user = userRepository.findUserById(req.getUserId());
        Product product = productRepository.findById(req.getProductId()).orElseThrow(()-> new RuntimeException("product not found"));
        Shop shop = shopRepository.findById(req.getShopId()).orElseThrow();
        comment.setRating(req.getRating());
        comment.setMessage(req.getMessage());
        comment.setContent(req.getMessage());
        comment.setUser(user);
        comment.setProduct(product);
        comment.setShop(shop);
        return commentMapper.toCommentResponse(commentRepository.save(comment));
    }


    @Override
    public CommentResponse commentUpdate(int id, CommentUpdateReq req) {
        Comment comment = commentRepository.findById(id).orElseThrow(()->new RuntimeException("Comment not found"));
        commentMapper.updateComment(comment,req);
        return commentMapper.toCommentResponse(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(int id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<CommentResponse> getListCommentByShopId(int shopId) {
        return commentRepository.getListByShopId(shopId).stream()
                .map(commentMapper::toCommentResponse).toList();
    }

    @Override
    public List<CommentResponse> getListCommentByUserId(int userId) {
        return commentRepository.getListByUserId(userId).stream()
                .map(commentMapper::toCommentResponse).toList();
    }

    @Override
    public List<CommentResponse> getListCommentByProductId(int productId) {
        return commentRepository.getListByProductId(productId).stream()
                .map(commentMapper::toCommentResponse).toList();
    }

    @Override
    public CommentResponse findById(int id){
        return  commentMapper.toCommentResponse(commentRepository.findById(id).orElseThrow());
    }
}
