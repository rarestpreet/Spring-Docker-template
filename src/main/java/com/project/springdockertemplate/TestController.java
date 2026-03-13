package com.project.springdockertemplate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final UserRepo userRepo;

    @GetMapping("/")
    public String test(){
        return "Hello World";
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok().body(userRepo.findAll());
    }
}

@Repository
interface UserRepo extends JpaRepository<UserInfo,Integer> {}

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
class UserInfo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
}