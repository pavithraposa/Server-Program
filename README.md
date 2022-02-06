# ATTRIBUTES
- [x] A heading (title)
- [x] description
- [x] description
- [x] id



# Checklist: Functionality for Pass(G)
###Planning:
- [x] You should have a file named README.md in the server component where you
  lists at least 10 points of things you need to do. The descriptions
  may be short but should be clear. It should be clear to read
  whether that point is settled or not.

- [ ]  It is encouraged that you use the BDD formulations
  (“Given / when / then”)
###Server component:
- [x] The server should use the Spring framework and it is in the server like everyone else
  blog posts are saved
- [x] The server should respond to API requests to list posts, edit posts, delete
  posts and view specific posts.
- [x] The addresses of these API requests should be as follows:
    - [x] / api / v1 / blog / list - List all posts
    - [x]  / api / v1 / blog / view / <id> - View a specific post
    - [x]  / api / v1 / blog / update / <id> - View a specific post
    - [x]  / api / v1 / blog / delete / <id> - Delete a specific post
    - [x]  / api / v1 / blog / create - Add a new post
- [x]  More may be added if you feel the need for it
- [x]  Each request must use an appropriate HTTP method (GET, POST, DELETE)
- [ ]  Your code must be saved in version control with Git
###Client components:
- [ ] The client component must be a text-based interface (CLI)
- [ ] The user should be able to list / add / edit / delete blog posts via API requests to the server component
- [x]  The user must be able to list all posts (ID and title are displayed)
- [x]  The user should be able to request a specific post and read the content
- [x]  No information should be stored in the client
- [ ]  If the user tries to view, delete, edit or change a post that does not
  there should be a clear error message displayed
- [ ]  Your code must be saved in version control with Git
###Level up (optional challenges that do not affect the grade, do one or more of these):
- [ ]  You can save all the information in the server in files and recover from these files when
  the server starts, so that no information is lost when the server restarts.
- [ ] Use NASA's API (https://api.nasa.gov/) from the server ,add a menu option to the client called "Get today's image from NASA", whereupon our client connects to
  our server which in turn connects to NASA's API. Our server then forwards
  the response from NASA to our client. (Client -> Server -> NASA).
- -----
#Checklist: Functionality for Well Pass

- [ ] You should use Dependency Injection
- [ ] You should create a Docker file and write instructions on how to use the server component
  can be started as a Docker container, and the client component must be able to connect
  to the server component.
- [ ] You should use logging with SLF4J and log all API calls