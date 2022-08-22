# FakeIsta API - SpringBoot
This application is a version of a popular Instagram app by Meta. Users can add photos with Description and Tags, follow other users to see their posts and be followed by others.
Every post have likes and comments section.

Users can make requests only after authentication.

***

### API LINK

***
https://fake-insta-mind-api.herokuapp.com/
<br/><br/>

### IMPLEMENTATIONS

***

- Model Relationships
- Spring Security and JWT
- Postman Collection
- Docker Compose
- Heroku PostGres DB deployment
- Local cache (Caffeine)
- Frontend
  <br/><br/>

### METHODS

***

| Request  | Description                                 |
|----------|---------------------------------------------|
| `GET`    | Returns information of one or more records. |
| `POST`   | Used to create new record in DB.            |
| `PUT`    | Updates date from a record.                 |
| `DELETE` | Deletes a record from the DB.               |

<br/><br/>

### RESPONSES

***

| Responses | Description                          |
|-----------|--------------------------------------|
| `200`     | Request executed successfully.       |
| `400`     | Validation errors.                   |
| `403`     | Forbidden Access.                    |
| `404`     | Searched record not found.           |
| `405`     | Method not implemented.              |
| `409`     | Conflict trying to save same record. |
| `500`     | Server error.                        |

<br/><br/>

### AUTHENTICATION - AUTH0

***
This API uses [AuthO](https://auth0.com/) as a way of authentication/authorization.
<br/><br/>
**Sign Up and Login:**

| Request | Description | Link          |
|---------|-------------|---------------|
| `POST`  | `SignUp`    | /api/v1/users |

      {
    "name" : "Example User",
    "username": "username",
    "password": "password",
    "email": "mail@mail.com",
    "description": "Hey all!"
      }

| Request | Description | Link          |
|---------|-------------|---------------|
| `POST`  | `Login`     | /login        |


      {
        "email": "mail@mail.com",
        "password": "palavrapass"
      }

<br/><br/>

## RESOURCE GRUPS

***

### USER ( api/v1/user )

| Request  | Description                                | Link               | Query parameters                           |
|----------|--------------------------------------------|--------------------|--------------------------------------------|
| `GET`    | Get logged user                            | api/v1/user        |                                            |
| `GET`    | Get user by ID                             | api/v1/user/{id}   |                                            |
| `GET`    | Search user by Username/ name/ email       | api/v1/user/search | String username, String name, String email |
| `GET`    | Get all users (admin role authorized only) | api/v1/user/admin  |                                            |
| `DELETE` | Delete user                                | api/v1/user        |                                            |
| `PATCH`  | Follow user                                | api/v1/user/follow |                                            |

      {
          "toFollowUserId": 1
      }


| Request | Description   | Link                  | 
|---------|---------------|-----------------------|
| `PATCH` | Unfollow user | /api/v1/user/unfollow |                  


        {
          "toUnfollowUserId": 1
        }


| Request | Description | Link        |
|---------|-------------|-------------|
| `PATCH` | Update user | api/v1/user |                  


     {
        "name" : "User", 
        "username": "user1", 
        "password": "password", 
        "email": "mail@mail.com", 
        "description": "Hey all!"
      } 


### POST ( api/v1/post )

| Request | Description | Link        | 
|---------|-------------|-------------|
| `POST`  | Add post    | api/v1/post |                  

      {
        "photo":"https://i.imgur.com/imh9kdu.jpg",
        "description": "The Universe",
        "tagList": [{"tag":"#sky"}]
      }

| Request  | Description                                    | Link                | Query parameters |
|----------|------------------------------------------------|---------------------|------------------|
| `GET`    | Get all posts                                  | api/v1/post         |                  |
| `GET`    | Search posts by tag                            | api/v1/post/search/ | String tag       |
| `GET`    | Get post by Id                                 | api/v1/post/{id}    |                  |
| `GET`    | Search post by username                        | api/v1/post         | String name      |
| `GET`    | Get post by user Id                            | api/v1/post/user    |                  |
| `DELETE` | Delete post                                    | api/v1/post         |                  |
| `DELETE` | Delete post by Id (admin role authorized only) | api/v1/post/ad{id}  |

### FOLLOWER ( api/v1/follower )

| Request | Description                  | Link                           | 
|---------|------------------------------|--------------------------------|
| `GET`   | Get followers by user Id     | api/v1/follower/followers/{id} | 
| `GET`   | Get follows by user Id       | api/v1/follower/followers/{id} | 
| `GET`   | Get followers by logged user | api/v1/follower/followers      | 
| `GET`   | Get follows by logged user   | api/v1/follower/follows        | 

### TAG ( api/v1/tag )

| Request  | Description  | Link              |
|----------|--------------|-------------------|
| `POST`   | Add tag      | api/v1/tag        |

      [
        {
          "tag": "#hey"
        }
      ]

### COMMENT ( api/v1/comment )

| Request  | Description  | Link              |
|----------|--------------|-------------------|
| `POST`   | Add comment  | api/v1/comment    |

    {
      "description": "Very nice!",
      "postId": 7,
      "userId": 1
    }

| Request  | Description                                       | Link                      |
|----------|---------------------------------------------------|---------------------------|
| `DELETE` | Delete comment                                    | api/v1/comment            |
| `PUT`    | Delete comment by Id (admin role authorized only) | api/v1/comment/admin/{id} |




### LIKE ( api/v1/like )

| Request  | Description        | Link             |
|----------|--------------------|------------------|
| `POST`   | Add like to a post | api/v1/like/post |

    {
      "postId": 1,
      "userId": 1
    }


| Request  | Description           | Link                |
|----------|-----------------------|---------------------|
| `POST`   | Add like to a comment | api/v1/like/comment |

    {
      "commentId": 1,
      "userId": 1
    }


| Request  | Description                | Link                |
|----------|----------------------------|---------------------|
| `DELETE` | Delete like from a post    | api/v1/like/post    |
| `DELETE` | Delete like from a comment | api/v1/like/comment |
