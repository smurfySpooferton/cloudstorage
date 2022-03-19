package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.util.SuccessBuilder;
import org.javatuples.Pair;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.udacity.jwdnd.course1.cloudstorage.util.Constants.*;
import static com.udacity.jwdnd.course1.cloudstorage.util.Constants.OperationStatus.*;

@Controller
@RequestMapping("/home")
public class FileController {
    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialsService credentialsService;
    private final UserService userService;

    public FileController(FileService fileService, NoteService noteService, CredentialsService credentialsService, UserService userService) {
        this.fileService = fileService;
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
                return fileService.getFileNames(userId);
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

    private Model updateModel(Model model, int activeTab, OperationStatus status) {
        model.addAttribute(TAB_KEY, activeTab);
        model.addAttribute(FILES_KEY, loadTab(FILES));
        model.addAttribute(NOTES_KEY, loadTab(NOTES));
        model.addAttribute(CREDENTIALS_KEY, loadTab(CREDENTIALS));
        if (status != null) {
            Pair<String, String> pair = SuccessBuilder.buildMessage(status, activeTab);
            model.addAttribute(pair.getValue0(), pair.getValue1());
        }
        return model;
    }

    @GetMapping()
    public String fileView(Model model) {
        updateModel(model, FILES, null);
        return fileViewName();
    }

    public String fileViewName() {
        return "home";
    }

    @GetMapping(value = "/download")
    public HttpEntity getFile(@RequestParam Integer fileId, Model model) {
        HttpHeaders header = new HttpHeaders();
        File file = fileService.getFile(getUserId(), fileId);
        if (!file.isValid()) {
            updateModel(model, FILES, ERROR_GET);
            return new HttpEntity(new byte[0], header);
        }
        header.setContentType(MediaType.valueOf(file.getContentType()));
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getFileName().replace(" ", "_"));
        header.setContentLength(file.getFileData().length);
        return new HttpEntity(file.getFileData(), header);
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam MultipartFile multipartFile, Model model) {
        OperationStatus status;
        try {
            File file = new File(multipartFile);
            file.setUserId(getUserId());
            if (file.isValid()) {
                fileService.addFile(file);
                status = SUCCESS_INSERT;
            } else {
                status = ERROR_INSERT;
            }
        } catch (IOException e) {
            status = ERROR_INSERT;
        }
        updateModel(model, FILES, status);
        return fileViewName();
    }

    @PostMapping("/note")
    public String addNote(@ModelAttribute Note note, Model model) {
        Integer userId = getUserId();
        OperationStatus status;
        if (note.getNoteId() != null) {
            if (noteService.isOwner(userId, note.getNoteId())) {
                noteService.editNote(note);
                status = SUCCESS_EDIT;
            } else {
                status = ERROR_EDIT;
            }
        } else {
            note.setUserId(userId);
            noteService.addNote(note);
            status = SUCCESS_INSERT;
        }
        updateModel(model, NOTES, status);
        return fileViewName();
    }

    @PostMapping("/credentials")
    public String addCredentials(@ModelAttribute Credentials credentials, Model model) {
        Integer userId = getUserId();
        OperationStatus status;
        if (credentials.getCredentialsId() != null) {
            if (credentialsService.isOwner(userId, credentials.getCredentialsId())) {
                credentialsService.editCredentials(credentials);
                status = SUCCESS_EDIT;
            } else {
                status = ERROR_EDIT;
            }
        } else {
            credentials.setUserId(getUserId());
            credentialsService.addCredentials(credentials);
            status = SUCCESS_INSERT;
        }
        updateModel(model, CREDENTIALS, status);
        return fileViewName();
    }

    /// DELETE
    @PostMapping("/deleteFile")
    public String deleteFile(Integer fileId, Model model) {
        fileService.deleteFile(fileId);
        updateModel(model, FILES, SUCCESS_DELETE);
        return fileViewName();
    }

    @PostMapping("/deleteNote")
    public String deleteNote(Integer noteId, Model model) {
        noteService.deleteNote(noteId);
        updateModel(model, NOTES, SUCCESS_DELETE);
        return fileViewName();
    }

    @PostMapping("/deleteCredentials")
    public String deleteCredentials(Integer credentialsId, Model model) {
        credentialsService.deleteCredentials(credentialsId);
        updateModel(model, CREDENTIALS, SUCCESS_DELETE);
        return fileViewName();
    }
}
