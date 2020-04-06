# Book Store -  Monolithic
Monolithic

# Technology Stack
  Berikut teknologi stack yang digunakan :
  
* Java 1.8 or above
* Apache Maven 3.5 or above
* Postgresql 9.6 or above
* Spring Framework 5.x
* Spring Boot 2.x
* Spring Security 2.x
* Hibernate 5.x
* Lombok 1.18.x
* JUnit 4
* Gson 2.8.x
* Swagger 2.x
* Apache Commons Lang 3.x

# Enhanced Entity Relationship Model

![Arsitektur Aplikasi](src/main/resources/static/ERM.png)

## #19 - WEEK 14
```
INSERT INTO public.role (role_name) 
VALUES ('ROLE_CLIENT');

INSERT INTO public."user" (id, created_by, created_time, status, updated_by, updated_time, address, email, full_name, password, phone_number, username) 
VALUES (3, 'init', '2020-03-29 00:00:00', 'ACTIVE', NULL, '2020-03-29 00:00:00', 'Semarang, Jawa Tengah', 'clientapps@email.com', 'Client Apps', '$2a$04$mX4QWTHsMhsvclOWafopAOVlmmx0r23sjuk2a0vGhr1WpK8h1fPHm', '081234567890', 'clientapps');

INSERT INTO public.user_role (username, role_name) 
VALUES ('clientapps', 'ROLE_CLIENT');
```

## Single
```
@PreAuthorize("hasRole('ROLE_ADMIN')")
```

## Multi
```
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_USER')")
```

