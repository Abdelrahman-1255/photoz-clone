package com.photoz_clone.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotEmpty;

@Table("photos")
public class Photo {
    
    @Id
    private Long id;
    @NotEmpty
    private String filename;
    @NotEmpty
    private String contentType;
    @JsonIgnore
    private byte[] data;

    public Photo() {
    }
    public Photo(Long id, String filename) {
        this.id = id;
        this.filename = filename;
    }
    public Photo(Long id, String filename, String contentType, byte[] data) {
        this.id = id;
        this.filename = filename;
        this.contentType = contentType;
        this.data = data;
    }
     public Photo(String filename, String contentType, byte[] data) {
        this.filename = filename;
        this.contentType = contentType;
        this.data = data;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public byte[] getData() {
        return data;
    }
    public void setData(byte[] data) {
        this.data = data;
    }
    public String getContentType() {
        return contentType;
    }
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}