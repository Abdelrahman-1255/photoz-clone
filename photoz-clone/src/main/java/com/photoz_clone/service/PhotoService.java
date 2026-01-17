package com.photoz_clone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import com.photoz_clone.model.Photo;
import com.photoz_clone.repository.PhotosRepository;

@Service
public class PhotoService {
     
    private final PhotosRepository photosRepository;

    public PhotoService(PhotosRepository photosRepository) {
        this.photosRepository = photosRepository;
    }

    public List<Photo> getAllPhotos() {
        return (List<Photo>) photosRepository.findAll();
    }

    public Optional<Photo> getPhotoById(Long id){
        return photosRepository.findById(id);
    }
    
    public Photo savePhoto(String filename,String contentType, byte[] data){
            Photo newPhoto = new Photo(filename, contentType, data);
           return photosRepository.save(newPhoto);
    }
    public void deletePhotoById(Long id){
        photosRepository.deleteById(id);
    }

    public void deleteAllPhotos(){
        photosRepository.deleteAll();
    }
}
