package springc1.clonecoding.controller.response;

import lombok.*;
import springc1.clonecoding.domain.Comment;
import springc1.clonecoding.domain.ImgPost;
import springc1.clonecoding.domain.Post;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {

    private Long id;
    private String nickname;
    private String title;
    private String location;
    private List<ImgPost> imgPostList;
    private List<Comment> comments;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.nickname = post.getNickname();
        this.title = post.getTitle();
        this.location = post.getLocation();
        this.imgPostList = post.getImgPostList();
        this.comments = post.getCommentList();

    }
}