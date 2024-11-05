package org.example.qwiz.GooglebooksFeatures.DTO;

import lombok.Data;

@Data
public class LibraryDTO {
    private String title;
    private String author;
    private String publishedDate;
    private String language;
    private String description;
    private String pageCount;
    private String averageRating;
    private String thumbnailLink;
    private String pdfStatus;
    private String pdfLink;
    private String webReadLink;

    public LibraryDTO(String title,
            String author,
            String publishedDate,
            String language,
            String description,
            String pageCount,
            String averageRating,
            String thumbnailLink,
            String pdfStatus,
            String pdfLink,
            String webReadLink) {
        this.title = title;
        this.author = author;
        this.publishedDate = publishedDate;
        this.language = language;
        this.description = description;
        this.pageCount = pageCount;
        this.averageRating = averageRating;
        this.thumbnailLink = thumbnailLink;
        this.pdfStatus = pdfStatus;
        this.pdfLink = pdfLink;
        this.webReadLink = webReadLink;
    }
}
