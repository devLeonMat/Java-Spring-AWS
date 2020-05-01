package com.example.awss3bucket.controller;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.example.awss3bucket.model.FileProperties;
import com.example.awss3bucket.service.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/storage/")
public class BucketController {

    private AmazonClient amazonClient;

    @Autowired
    BucketController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestPart(value = "file") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();
        FileProperties fileProp;
        try {
            fileProp = this.amazonClient.uploadFile(file);
        } catch (AmazonServiceException e) {
            response.put("message", "error en los servicios de aws");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);

        } catch (SdkClientException ex) {
            response.put("message", "error con el sdk");
            response.put("error", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "subida completa");
        response.put("File", fileProp);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteFile")
    public ResponseEntity<?> deleteFile(@RequestPart(value = "url") String fileUrl) {
        Map<String, Object> response = new HashMap<>();
        String msj;
        try {
            msj = this.amazonClient.deleteFileFromS3Bucket(fileUrl);
        } catch (AmazonServiceException e) {
            response.put("message", "error al borrar archivo");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", msj);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/buckets")
    public ResponseEntity<?> getListBuckets() {
        Map<String, Object> response = new HashMap<>();
        List<Bucket> listBuckets;
        try {
            listBuckets = amazonClient.obtainListBuckets();
        } catch (AmazonServiceException e) {
            response.put("message", "error al listar los buckets");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "consulta exitosa");
        response.put("Buckets", listBuckets);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/listObject")
    public ResponseEntity<?> getListObjects() {
        Map<String, Object> response = new HashMap<>();
        ObjectListing listObject;
        try {
            listObject = amazonClient.getListObject();
        } catch (AmazonServiceException e) {
            response.put("message", "error al listar los Objetos");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "consulta exitosa");
        response.put("Buckets", listObject);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}