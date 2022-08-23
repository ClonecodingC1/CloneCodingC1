package springc1.clonecoding.controller;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springc1.clonecoding.controller.request.PostRequestDto;
import springc1.clonecoding.controller.response.ResponseDto;
import springc1.clonecoding.domain.UserDetailsImpl;
import springc1.clonecoding.service.PostService;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 작성
    @PostMapping("/api/post")
    public ResponseDto<?> createPost(@RequestBody PostRequestDto postRequestDto,
                                     @AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        return postService.createPost(postRequestDto, userDetailsImpl);
    }

    // 게시글 수정
    @PutMapping("/api/post/{postId}")
    public ResponseDto<?> editPost(@PathVariable Long postId,
                                   @RequestBody PostRequestDto postRequestDto,
                                   @AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        return postService.editPost(postId, postRequestDto, userDetailsImpl);
    }

    // 게시글 삭제
    @DeleteMapping("/api/post/{postId}")
    public ResponseDto<?> deletePost(@PathVariable Long postId,
                                     @AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        return postService.deletePost(postId, userDetailsImpl);
    }

    // 특정 지역 게시글 전체 조회
    @GetMapping("/api/post/{location}")
    public ResponseDto<?> getAllLocalPosts(@PathVariable String location){
        return postService.getAllLocalPosts(location);
    }

    // 전체 지역 게시글 전체 조회
    @GetMapping("/api/post")
    public ResponseDto<?> getAllPosts(){
        return postService.getAllPosts();
    }

    // 게시글 상세 조회
    @GetMapping("/api/post/id/{postId}")
    public ResponseDto<?> getPostDetails(@PathVariable Long postId){
        return postService.getPostDetails(postId);
    }






}
