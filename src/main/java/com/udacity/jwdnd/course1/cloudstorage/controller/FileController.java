package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/home")
public class FileController {
    private NoteService noteService;
    private CredentialsService credentialsService;
    private UserService userService;

    public FileController(NoteService noteService, CredentialsService credentialsService, UserService userService) {
        this.noteService = noteService;
        this.credentialsService = credentialsService;
        this.userService = userService;
    }

    private String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    private Integer getUserId() {
        return userService.getUserId(getUserName());
    }

    @GetMapping()
    public String fileView() {
        return "home";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam(required = false) MultipartFile file) {
        return fileView();
    }

    @PostMapping("/addNote")
    public String addNote(@RequestParam String noteTitle, @RequestParam String noteDescription) {
        Note note = new Note();
        note.setTitle(noteTitle);
        note.setDescription(noteDescription);
        note.setUserId(getUserId());
        noteService.addNote(note);
        return fileView();
    }

    @PostMapping("/addCredentials")
    public String addCredentials(@RequestParam String url, @RequestParam String username, @RequestParam String password) {
        Credentials credentials = new Credentials();
        credentials.setUserId(getUserId());
        credentials.setUrl(url);
        credentials.setPassword(username);
        credentials.setUsername(password);
        credentialsService.addCredentials(credentials);
        return fileView();
    }

    /// DELETE
    @DeleteMapping("/deleteFile")
    public String deleteFile(Integer fileId) {
        return fileView();
    }

    @DeleteMapping("/deleteCredentials")
    public String deleteCredentials(Integer fileId) {
        return fileView();
    }

    @DeleteMapping("/deleteNote")
    public String deleteNote(Integer fileId) {
        return fileView();
    }

    /// EDIT
    @PutMapping("/editCredentials")
    public String editCredentials(Credentials credentials) {
        return fileView();
    }

    @PutMapping("/editNote")
    public String deleteNote(Note note) {
        return fileView();
    }
}
