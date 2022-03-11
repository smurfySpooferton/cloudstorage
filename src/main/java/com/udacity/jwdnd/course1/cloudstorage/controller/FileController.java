package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Notice;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoticeService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/home")
public class FileController {
    private NoticeService noticeService;
    private CredentialsService credentialsService;

    public FileController(NoticeService noticeService, CredentialsService credentialsService) {
        this.noticeService = noticeService;
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
        return fileView();
    }

    @PostMapping("/addNote")
    public String addNote(@RequestParam String noteTitle, @RequestParam String noteDescription) {
        Notice notice = new Notice();
        notice.setTitle(noteTitle);
        notice.setDescription(noteDescription);
        notice.setOwner(userName());
        noticeService.addNotice(notice);
        return fileView();
    }

    @PostMapping("/addCredentials")
    public String addCredentials(@RequestParam String url, @RequestParam String username, @RequestParam String password) {
        Credentials credentials = new Credentials();
        credentials.setOwner(userName());
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
    public String deleteNote(Notice notice) {
        return fileView();
    }
}
