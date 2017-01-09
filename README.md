# spring-mvc-jwt-example

Sample Spring MVC application using JWT tokens and Spring Security 

Keep It Simple Stupid

### Register new user

```
curl -X POST 
--header 'Content-Type: application/json' 
--header 'Accept: */*' -d '
{"email":"jakub.pomykala%40gmail.com",
"firstName":"Jakub",
"token":"egFacebookToken",
"provider":"FACEBOOK"
}' 
'http://localhost:9200/auth/sign'
```
### Access to restricted endpoint by `@PreAuthorize("hasRole('ROLE_USER')")`

```
curl -X POST
--header 'Content-Type: application/json' 
--header 'Accept: */*' 
--header 'Authorization: <jwt_token>' 
'http://localhost:9200/tags?name=sometag'
```




### Utils
* H2 Database console `http://localhost:9400`
* Swagger `http://localhost:9200/swagger-ui.html`
