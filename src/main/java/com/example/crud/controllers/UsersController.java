package com.example.crud.controllers;

import com.example.crud.domain.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersRepository repository;

    @GetMapping("/todos")
    public ResponseEntity getAllUsers() {
        var allUsers = repository.findAllByActiveTrue();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/especial")
    public ResponseEntity getSpecialUsers() {
        var allUsers = repository.findAllByType("special");
        return ResponseEntity.ok(allUsers);
    }
    @GetMapping("/normal")
    public ResponseEntity getNormalUsers() {
        var allUsers = repository.findAllByType("normal");
        return ResponseEntity.ok(allUsers);
    }
    @GetMapping("/trabalhoso")
    public ResponseEntity getLaboriousUsers() {
        var allUsers = repository.findAllByType("laborious");
        return ResponseEntity.ok(allUsers);
    }
    @GetMapping("/norte")
    public ResponseEntity getNorteUsers() {
        var allUsers = repository.findAllByLocationRegion("norte");
        return ResponseEntity.ok(allUsers);
    }
    @GetMapping("/nordeste")
    public ResponseEntity getNordesteUsers() {
        var allUsers = repository.findAllByLocationRegion("nordeste");
        return ResponseEntity.ok(allUsers);
    }
    @GetMapping("/centro-oeste")
    public ResponseEntity getCentroUsers() {
        var allUsers = repository.findAllByLocationRegion("centro-oeste");
        return ResponseEntity.ok(allUsers);
    }
    @GetMapping("/sudeste")
    public ResponseEntity getSudesteUsers() {
        var allUsers = repository.findAllByLocationRegion("sudeste");
        return ResponseEntity.ok(allUsers);
    }
    @GetMapping("/sul")
    public ResponseEntity getSulUsers() {
        var allUsers = repository.findAllByLocationRegion("sul");
        return ResponseEntity.ok(allUsers);
    }
}
