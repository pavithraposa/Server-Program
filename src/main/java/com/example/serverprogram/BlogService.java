package com.example.serverprogram;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.io.*;
import java.util.*;

@Service
public class BlogService  {

    private final File blogListFile;
    ArrayList<Blog> blogs;//arraylist used for storing Blogs info
    int latestBlogID;//Initialising the Id from Blog info standard/predefined(not accepts from user, creates automatically)

    public BlogService() throws IOException  {
        blogs = new ArrayList<>();
        //creates a file if the file doesnot exist.if the file already exists, then doesnot creates a file.
        blogListFile = new File("BlogList.txt");
        if(!blogListFile.exists()) {
            blogListFile.createNewFile();
        }
        getBlogInfoFile();
        latestBlogID = blogs.size();
    }

    public ArrayList<Blog> getBlogs() {
        return blogs;
    }
    //creates a new Blog
    public Blog createBlog(Blog newBlog) {

        latestBlogID++;
        newBlog.setId(latestBlogID);
        blogs.add(newBlog);
        return newBlog;
    }

    public void updateBlogByID(int id, Blog updatedBlog) {
        for (int i = 0; i < blogs.size(); i++) {
            Blog currentBlog = blogs.get(i);
            if (currentBlog.getId() == id) {
                blogs.set(i, updatedBlog);
                return;
            }
        }
        new Blog();
    }

    public Blog getBlogById(int id) {
        for (Blog currentBlog : blogs) {
            if (currentBlog.getId() == id) {
                return currentBlog;
            }
        }
        return null;
    }

    //when ExitProgram is clicked then it saves(call from other method "saveToFile") the info in array list in a file
    public void exitProgram() throws IOException {
        //Creates a FileWriter
        new FileWriter(blogListFile);
        for (Blog blogArray : blogs) {
            if (blogs.size() != 0) {
                String outPutText = blogArray.getId() + "|" + blogArray.getTitle() + "|" + blogArray.getDescription() + "|" + blogArray.getAuthor() + "\n";
                saveToFile(blogListFile.getName(), outPutText, true);
            }
        }
        System.out.println("Shutting Down...");
    }

    //save the content in file with the help of FileWriter
    public static void saveToFile(String fileName, String text,boolean append) throws IOException {
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file,append);
        fw.write(text);
        fw.close();
    }

    //fetches the information from file in a new Arraylist called "loadToList"
    public ArrayList<Blog> loadToList(String fileName) throws FileNotFoundException {

        File file = new File(fileName);
        Scanner myScanner = new Scanner(file);
        ArrayList<Blog> blogFile = new ArrayList<>();

        if (file.length() > 1) {
            while (myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                String[] items = line.split("\\|");
                int blogId = Integer.parseInt(items[0]);
                String blogTitle = items[1];
                String blogDescription = items[2];
                String blogAuthor = items[3];

                Blog newObject = new Blog();

                newObject.setId(blogId);
                newObject.setTitle(blogTitle);
                newObject.setDescription(blogDescription);
                newObject.setAuthor(blogAuthor);
                blogFile.add(newObject);

            }
        }
        return blogFile;
    }

    //fetching the arraylist details from loadToList method and updating it to existsing arraylist from Blog(name:blogs)
    public void getBlogInfoFile() throws FileNotFoundException {
        ArrayList<Blog> blogsArrayList = loadToList(blogListFile.getName());
        blogs.addAll(blogsArrayList);
    }
}

