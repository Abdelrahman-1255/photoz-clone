package com.photoz_clone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.photoz_clone.model.Photo;
import com.photoz_clone.service.PhotoService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/downloads")
public class DownloadController {
    private final PhotoService photoService;
    public DownloadController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> downloadPhoto(@PathVariable long id){
        Photo photo = photoService.getPhotoById(id).orElse(null);
        if(photo == null || photo.getData() == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        byte [] data = photo.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(photo.getContentType()));
        ContentDisposition build = ContentDisposition.builder("attachment").filename(photo.getFilename()).build();
        headers.setContentDisposition(build);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
        
    }
    


}
