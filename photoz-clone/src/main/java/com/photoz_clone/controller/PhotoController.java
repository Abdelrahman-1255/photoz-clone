package com.photoz_clone.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

import com.photoz_clone.model.Photo;
import com.photoz_clone.service.PhotoService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api/photos")
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }
  

    @GetMapping("")
    public List<Photo> getAllPhotos() {
        return photoService.getAllPhotos();
    }

    @GetMapping("/{id}")
    public Photo getPhotoById(@PathVariable Long id){
        Photo photo = photoService.getPhotoById(id).orElse(null);
        if(photo == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found");
        }
        return photo;
    }

    @DeleteMapping("")
    public List<Photo> deleteAllPhotos() {
        photoService.deleteAllPhotos();
        return photoService.getAllPhotos();
    }

    @DeleteMapping("/{id}")
    public void deletePhotoById(@PathVariable Long id){
        Photo photo = photoService.getPhotoById(id).orElse(null);
        if(photo == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found");
        }
        photoService.deletePhotoById(id);
    }

    @PostMapping("")
    public ResponseEntity<Photo> uploadPhoto(@RequestParam("data") MultipartFile file){
        if (file == null || file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File is empty");
        }
        try{
            String filename = file.getOriginalFilename();
            String contentType = file.getContentType();
            byte[] data = file.getBytes();
            Photo saved = photoService.savePhoto(filename, contentType, data);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(saved.getId()).toUri();
            return ResponseEntity.created(location).body(saved);
        }catch(IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error uploading photo", e);
        }
    }
    

   
}
