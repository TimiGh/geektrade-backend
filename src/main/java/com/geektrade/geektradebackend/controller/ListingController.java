package com.geektrade.geektradebackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geektrade.geektradebackend.dto.Listing;
import com.geektrade.geektradebackend.dto.SearchDto;
import com.geektrade.geektradebackend.repository.ListingRepository;
import com.geektrade.geektradebackend.service.ListingImageService;
import com.geektrade.geektradebackend.service.ListingService;
import com.geektrade.geektradebackend.service.OpenAIService;
import lombok.Data;
import okhttp3.Response;
import org.apache.tika.Tika;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping(path = "/api/listings")
@Data
public class ListingController {

    private final ListingService listingService;

    private final OpenAIService openAIService;
    private final ListingRepository listingRepository;
    private final ListingImageService listingImageService;

    @PostMapping(path = "/search")
    public Page<Listing> searchListing(@RequestBody SearchDto searchDto, Pageable pageable
    ) {
        return listingService.search(pageable, searchDto);
    }

    @PostMapping(path = "/generate-description")
    public String generateDescription(@RequestBody MultipartFile file) throws IOException {
        String base64Image = Base64.getMimeEncoder().encodeToString(file.getBytes());
        var aiResponse = this.openAIService.generateDescriptionFrom(base64Image);
        var result = new HashMap<>();
        result.put("content", aiResponse.orElse(""));
        return new ObjectMapper().writeValueAsString(result);
    }

    @PostMapping(path = "/create")
    public Listing createListing(@RequestBody Listing listing) {
        return listingService.save(listing);
    }

    @PostMapping(path = "/{listingId}/{isPrimary}/image", consumes = {MULTIPART_FORM_DATA_VALUE})
    public Long setListingImage(@RequestBody MultipartFile file, @PathVariable Long listingId, @PathVariable Boolean isPrimary) throws IOException {
        return listingImageService.savePrimary(file, listingId, isPrimary);
    }

    @GetMapping(path = "/{listingId}")
    public Listing getListing(@PathVariable Long listingId) {
        return listingService.getListingById(listingId).toPojo();
    }

    @GetMapping(path = "/search/personal")
    public Page<Listing> getPersonalListings(Pageable pageable) {
        return listingService.getPersonalList(pageable);
    }

    @GetMapping(path = "/{imageId}/image")
    public ResponseEntity getListingImage(@PathVariable Long imageId) {
        String content = listingImageService.getListingImageById(imageId);
        byte[] decode = Base64.getDecoder().decode(content);
        return ResponseEntity
                .ok()
                .contentLength(decode.length)
                .contentType(MediaType.parseMediaType(new Tika().detect(decode)))
                .body(decode);
    }
}
