package com.photoz_clone.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.photoz_clone.model.Photo;

@Repository
public interface PhotosRepository extends CrudRepository<Photo, Long> {
    
} 