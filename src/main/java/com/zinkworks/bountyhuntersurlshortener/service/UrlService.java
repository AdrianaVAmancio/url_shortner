package com.zinkworks.bountyhuntersurlshortener.service;

import com.zinkworks.bountyhuntersurlshortener.exceptions.UrlNotFoundException;
import com.zinkworks.bountyhuntersurlshortener.model.BountyUrlTable;
import com.zinkworks.bountyhuntersurlshortener.repository.RepositoryUrl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class UrlService {


    private final RepositoryUrl repositoryUrl;    // creating object of RepositoryUrl class
    private BountyUrlTable bountyUrlTable;


    @Autowired      // used on properties, setters and constructors.
    // Autowires relationships between collaborating beans. Injects dependencies into a class.
    public UrlService(RepositoryUrl repositoryUrl) {

        this.repositoryUrl = repositoryUrl;
    }

    // Read or search all fields.

    // Creating a list of type 'bounty_url_table' to hold id, created date, original url and short url.
    public List<BountyUrlTable> getAllUrlInfo() {
        return repositoryUrl.findAll();    // returns all database info from the repository class.
    }

    // Create
    // Add a new short URL. MD5 conversion will go here (I think)
    public String addNewUrl(String originalUrl) {

        String createdShortUrl = MD5Hash.MD5HashingMethod(originalUrl);
        LocalDateTime createdDate = LocalDateTime.now();

        BountyUrlTable newRecord = new BountyUrlTable();

        newRecord.setShortUrl(createdShortUrl);
        newRecord.setOriginalUrl(originalUrl);
        newRecord.setCreatedDate(createdDate);
        // Validate url
        // if url is valid, check black list.
        // if its not blacklisted, run Md5Hash


//        Optional<BountyUrlTable> shortenedUrl = repositoryUrl.findByShortUrl(bountyUrlTable.getShortUrl());
//        if (shortenedUrl.isPresent()) {
//            throw new IllegalStateException("short url already in use");
//        }
        repositoryUrl.save(newRecord);
        return createdShortUrl;
    }


    // READ
    public String findOriginalUrl(String shortUrl) throws UrlNotFoundException {

        var entity = repositoryUrl.findByShortUrl(shortUrl)
                .orElseThrow(() -> new UrlNotFoundException("No entity with " + shortUrl + " found."));

        return entity.getOriginalUrl();

    }


    // Delete
    public boolean deleteUrl(String shortUrl) {

        var entity = repositoryUrl.findByShortUrl(shortUrl)
                .orElseThrow(() -> new EntityNotFoundException("No entity with " + shortUrl + " found."));
        Long id = entity.getId();
        if (id != null) {
            repositoryUrl.deleteById(id);

            return true;
        } else {
            return false;
        }

    }


}
