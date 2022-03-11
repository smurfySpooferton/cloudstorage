package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
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

    public FileController(NoteService noteService, CredentialsService credentialsService) {
        this.noteService = noteService;
        this.credentialsService = credentialsService;
    }

    private String userName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping()
    public String fileView() {
        return "home";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam(required = false) MultipartFile file) {
        return "home";
    }

    @PostMapping("/addNote")
    public String addNote(@RequestParam(required = false) String noteTitle,
                          @RequestParam(required = false) String noteDescription) {
        Note note = new Note();
        note.setNoteTitle(noteTitle);
        note.setNoteDescription(noteDescription);
        note.setNoteOwner(userName());
        noteService.addNote(note);
        return "home";
    }

    @PostMapping("/addCredentials")
    public String addCredentials(@RequestParam(required = false) Credentials credentials) {
        if (credentials != null) {
            credentials.setCredentialsOwner(userName());
            credentialsService.addCredentials(credentials);
        }
        return "home";
    }

    /// DELETE
    @DeleteMapping("/deleteFile")
    public String deleteFile(Integer fileId) {
        return "home";
    }

    @DeleteMapping("/deleteCredentials")
    public String deleteCredentials(Integer fileId) {
        return "home";
    }

    @DeleteMapping("/deleteNote")
    public String deleteNote(Integer fileId) {
        return "home";
    }

    /// EDIT
    @PutMapping("/editCredentials")
    public String editCredentials(Credentials credentials) {
        return "home";
    }

    @PutMapping("/editNote")
    public String deleteNote(Note note) {
        return "home";
    }
}
