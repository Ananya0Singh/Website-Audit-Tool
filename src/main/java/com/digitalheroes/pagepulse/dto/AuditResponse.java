package com.digitalheroes.pagepulse.dto;

public class AuditResponse {

    private int status;
    private long responseTime;
    private String title;
    private String metaDescription;
    private int h1Count;
    private int imagesWithoutAlt;
    private int wordCount;

    public AuditResponse(int status, long responseTime, String title,
                         String metaDescription, int h1Count,
                         int imagesWithoutAlt, int wordCount) {
        this.status = status;
        this.responseTime = responseTime;
        this.title = title;
        this.metaDescription = metaDescription;
        this.h1Count = h1Count;
        this.imagesWithoutAlt = imagesWithoutAlt;
        this.wordCount = wordCount;
    }

    public int getStatus() { return status; }
    public long getResponseTime() { return responseTime; }
    public String getTitle() { return title; }
    public String getMetaDescription() { return metaDescription; }
    public int getH1Count() { return h1Count; }
    public int getImagesWithoutAlt() { return imagesWithoutAlt; }
    public int getWordCount() { return wordCount; }
}