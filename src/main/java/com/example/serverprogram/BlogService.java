package com.example.serverprogram;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.io.*;
import java.util.*;

@Service
public class BlogService  {


    private File blogListFile;

    ArrayList<Blog> blogs;
    //ArrayList<Blog> blogFile;

    int latestBlogID;


    public BlogService() throws IOException  {
        blogs = new ArrayList<>();



        blogListFile = new File("BlogList.txt");
        if(!blogListFile.exists()){
            // creates the file
            blogListFile.createNewFile();

        }


        getBlogInfoFile();
        //blogFile = new ArrayList<>();

        latestBlogID = 0;
    }

    /*public BlogService() throws IOException {


        blogList = new File("RoomList.txt");
            // creates the file
            blogList.createNewFile();
            // creates a FileWriter Object
    }*/

    public ArrayList<Blog> getBlogs() throws FileNotFoundException {

        return blogs;
    }

    public Blog createBlog(Blog newBlog) throws FileNotFoundException {



        latestBlogID++;
        newBlog.setId(latestBlogID);
        blogs.add(newBlog);
        return newBlog;
    }

    public Blog updateBlogByID(int id, Blog updatedBlog) {
        for (int i = 0; i < blogs.size(); i++) {
            Blog currentBlog = blogs.get(i);
            if (currentBlog.getId() == id) {
                blogs.set(i, updatedBlog);
                return blogs.get(i);
            }
        }
        return new Blog();
    }

    public Blog getBlogById(int id) {

        for (Blog currentBlog : blogs) {
            if (currentBlog.getId() == id) {
                return currentBlog;
            }
        }

        return null;
    }

    public boolean exitProgram() throws IOException {

        //Creates a FileWriter, that write to file roomList
        new FileWriter(blogListFile);

        //Checks if any rooms are booked, if true it will save all of those rooms and guests into a txt document by splitting all parameters with "|". And then returning false to programRunning, which exits the program.
        for (Blog blogArray : blogs) {

            if (blogs.size() != 0) {

                String outPutText = blogArray.getId() + "|" + blogArray.getTitle() + "|" + blogArray.getDescription() + "|" + blogArray.getAuthor() + "\n";
                saveToFile(blogListFile.getName(), outPutText, true);
            }
        }

        System.out.println("Shutting Down...");
        return false;
    }

    public static void saveToFile(String fileName, String text,boolean append) throws IOException {

        //Creating an object of a file
        File file = new File(fileName);

        //FileWriter that writes to file(file), in this case RoomList.txt
        FileWriter fw = new FileWriter(file,append);

        //Writes down "text" which is the saved rooms.
        fw.write(text);

        //Closes the FileWriter
        fw.close();
    }

    public ArrayList<Blog> loadToList(String fileName) throws FileNotFoundException {

        //Creates a new file and sets it as RoomList.txt
        File file = new File(fileName);

        //Creates a scanner that scans our selected file (RoomList.txt)
        Scanner myScanner = new Scanner(file);

        //An array list of the class called Room, Where all of our saved rooms will be to from RoomList.txt
        ArrayList<Blog> blogfile = new ArrayList<>();

        //Checks if the file length is bigger than 1, if true it will start to upload
        if (file.length() > 1) {

            //As long as scanner has a next line, it will keep run the while loop
            while (myScanner.hasNextLine()) {

                //Creates a String variable that contains one of the lines in text document, and then splits the string by each "|" adn saves all the elements into an array
                String line = myScanner.nextLine();

                String[] items = line.split("\\|");

                //String roomName = items[0];
                int blogId = Integer.parseInt(items[0]);
                //boolean isBooked = Boolean.parseBoolean(items[2]);
                String blogTitle = items[1];
                String blogdescription = items[2];
                String blogAuthor = items[3];

                //Creates a new animal object with all the parameters that goes into an animal
                Blog newObject = new Blog();

                newObject.setId(blogId);
                newObject.setTitle(blogTitle);
                newObject.setDescription(blogdescription);
                newObject.setAuthor(blogAuthor);
                //If statement that depending on the name, it creates a new room for that room type and sets its guest that was created above "newObject"
                blogfile.add(newObject);

            }


        }
        return blogfile;
    }

    public void getBlogInfoFile() throws FileNotFoundException {

        ArrayList<Blog> blogsArrayList = loadToList(blogListFile.getName());
        for (int i = 0; i < blogsArrayList.size(); i++) {
            blogs.add(blogsArrayList.get(i));
        }
    }
}

