package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/home")
public class FileController {
    private static final int FILES = 0;
    private static final int NOTES = 1;
    private static final int CREDENTIALS = 2;

    private final NoteService noteService;
    private final CredentialsService credentialsService;
    private final UserService userService;

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

    private List<?> loadTab(int activeTab) {
        Integer userId = getUserId();
        switch (activeTab) {
            case FILES: {
                break;
            }
            case NOTES: {
                return noteService.getNotes(userId);
            }
            case CREDENTIALS: {
                return credentialsService.getCredentials(userId);
            }
            default:
                break;
        }
        return List.of();
    }

    @GetMapping()
    public String fileView() {
        return fileViewName();
    }

    public String fileViewName() {
        return "home";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam(required = false) MultipartFile file) {
        return fileViewName();
    }

    @PostMapping("/addNote")
    public String addNote(@ModelAttribute Note note, Model model) {
        note.setUserId(getUserId());
        noteService.addNote(note);
        model.addAttribute("data", loadTab(NOTES));
        model.addAttribute("tab", NOTES);
        return fileViewName();
    }

    @PostMapping("/addCredentials")
    public String addCredentials(@ModelAttribute Credentials credentials, Model model) {
        credentials.setUserId(getUserId());
        credentialsService.addCredentials(credentials);
        model.addAttribute("data", loadTab(CREDENTIALS));
        model.addAttribute("tab", CREDENTIALS);
        return fileViewName();
    }

    /// DELETE
    @DeleteMapping("/deleteFile")
    public String deleteFile(Integer fileId) {
        return fileViewName();
    }

    @DeleteMapping("/deleteNote")
    public String deleteNote(Integer noteId) {
        return fileViewName();
    }

    @DeleteMapping("/deleteCredentials")
    public String deleteCredentials(Integer credentialsId) {
        return fileViewName();
    }

    /// EDIT
    @PutMapping("/editNote")
    public String editNote(@ModelAttribute Note note) {
        if (getUserId().equals(note.getUserId())) {
            noteService.editNote(note);
        }
        return fileViewName();
    }

    @PutMapping("/editCredentials")
    public String editCredentials(@ModelAttribute Credentials credentials) {
        if (getUserId().equals(credentials.getUserId())) {
            credentialsService.editCredentials(credentials);
        }
        return fileViewName();
    }
}
