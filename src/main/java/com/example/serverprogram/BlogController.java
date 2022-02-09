package com.example.serverprogram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.io.*;

@RestController
@RequestMapping(value = "/api/v1/blog/")
public class BlogController {
    private final BlogService blogService;
    private final Logger logger;

    //Dependency Injection
    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
        logger = LoggerFactory.getLogger(BlogController.class);
    }

    //CRUD - Create
    //Creates new Blog
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity createBlog(@RequestBody Blog blog) {
        //if content inside the body for title/description/author is empty then return error message
        if (blog.getTitle().equals("")) {
            logger.error("Title is empty. write something under Title");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (blog.getDescription().equals("")) {
            logger.error("Description is empty. write something under Description");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (blog.getAuthor().equals("")) {
            logger.error("Author is empty. write something under Author");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        System.out.println("The new Blog has been added with the Title: ' " + blog.getTitle() + " ' ");
        logger.info("The new Blog has been added with the Title: '  " + blog.getTitle() + " ' ");
        return new ResponseEntity<>(blogService.createBlog(blog), HttpStatus.CREATED);
    }

    //CRUD - Read
    //Lists all the blogs present in array
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Blog>> listBlog() {

        System.out.println("The list of Blogs are as follows.");
        logger.info("The list of blogs are displayed successfully");
        return new ResponseEntity<>(blogService.getBlogs(), HttpStatus.OK);
    }

    //CRUD - Read
    //Can view the details of a specific blog by giving id
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public ResponseEntity viewBlog(@PathVariable("id") int id) {

        Blog getBlog = blogService.getBlogById(id);
        if (getBlog == null) {
            logger.warn("Could not find the id: " + id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        logger.info("view Blog: " + getBlog.getTitle());
        return new ResponseEntity<>(getBlog, HttpStatus.OK);
    }


    //CRUD - Update
    //Updates a specific blog content if Id is provided
    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public ResponseEntity updateBlog(@PathVariable("id") int id, @RequestBody Blog blogChanges) {
        System.out.println("Updating Blog with Id :" + id);
        Blog blogToUpdate = blogService.getBlogById(id);

        //if the Id is not present in Arraylist of blogs then returns error message with 'id not found'
        if (blogToUpdate == null) {
            logger.error("Could not find Blog with ID : " + id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        //if the content inside the Id blog is not null then it updates the changes provided by user
        if (blogChanges.getTitle() != null) {
            blogToUpdate.setTitle(blogChanges.getTitle());
        }
        if (blogChanges.getDescription() != null) {
            blogToUpdate.setDescription(blogChanges.getDescription());
        }
        if (blogChanges.getAuthor() != null) {
            blogToUpdate.setAuthor(blogChanges.getAuthor());
        }

        blogService.updateBlogByID(id, blogToUpdate);
        System.out.println("The Blog with id :" + id + "has been updated successfully.");
        logger.info("Blog ' " + blogChanges.getTitle() + " ' is Updated Successfully :) ");
        return new ResponseEntity<>(blogToUpdate, HttpStatus.OK);

    }

    //CRUD - Delete
    //if the provided ID is present in arraylist blogs then it deletes the info of blog
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteBlogById(@PathVariable("id") int id) {

        Blog blogToDelete = blogService.getBlogById(id);
        if (blogToDelete == null) {
            logger.warn("Issue with deleting blog");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        blogService.blogs.remove(blogToDelete);
        System.out.println("The Blog with id : ' " + id + " ' is successfully Deleted.");
        logger.info("The Blog is deleted successfully. ");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //CRUD - Delete
    //Deletes all the Blogs from arraylist
    @RequestMapping(value = "/clear", method = RequestMethod.DELETE)
    public void deleteAllBlogs() {
        blogService.blogs.clear();
        System.out.println("The Blog list is cleared!!!");
        logger.info("The list is Cleared!!");
    }

    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public void exitBlog() throws IOException {
        blogService.exitProgram();
        System.out.println("Exiting the program");
    }
}

