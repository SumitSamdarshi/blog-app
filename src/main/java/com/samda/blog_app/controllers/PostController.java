package com.samda.blog_app.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.samda.blog_app.payloads.ApiResponse;
import com.samda.blog_app.payloads.PostDto;
import com.samda.blog_app.payloads.PostResponse;
import com.samda.blog_app.services.FileService;
import com.samda.blog_app.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String uploadPath;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
                                              @PathVariable Integer categoryId) {
        PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value="pageSize",defaultValue = "5",required = false) Integer pageSize,
            @RequestParam(value="pageNumber",defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir)
    {
        return ResponseEntity.ok(postService.getAllpost(pageSize, pageNumber,sortBy, sortDir));

    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
        List<PostDto> post = postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(post, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
        List<PostDto> post = postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(post, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return new ApiResponse("Post is successfully deleted !!", true);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        return new ResponseEntity<PostDto>(postService.getPostById(postId), HttpStatus.OK);
    }

    // search
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords) {
        List<PostDto> result = this.postService.searchPosts(keywords);
        return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
    }

    //update
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto updatePost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

    }

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadImage(@RequestParam MultipartFile postImage, @PathVariable Integer postId) throws IOException {
        PostDto postDto = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(uploadPath, postImage);
        postDto.setImageName(fileName);
        PostDto updatedPost = this.postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
    }

    @GetMapping(value="/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void viewImage(@PathVariable String imageName, HttpServletResponse response) throws IOException{
        InputStream resource = this.fileService.getResource(uploadPath, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());

    }
}
