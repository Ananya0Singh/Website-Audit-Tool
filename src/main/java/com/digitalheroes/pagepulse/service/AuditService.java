package com.digitalheroes.pagepulse.service;

import com.digitalheroes.pagepulse.dto.AuditResponse;
import com.digitalheroes.pagepulse.exception.AuditException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;

@Service
public class AuditService {

    @Value("${pagepulse.request.timeout:8000}")
    private int timeoutMillis;

    public AuditResponse analyze(String rawUrl) {
        String url = validateAndNormalizeUrl(rawUrl);

        long startTime = System.currentTimeMillis();
        Connection.Response httpResponse;

        try {
            httpResponse = Jsoup.connect(url)
                    .timeout(timeoutMillis)
                    .userAgent("Mozilla/5.0 (compatible; PagePulseBot/1.0)")
                    .ignoreHttpErrors(true)
                    .execute();
        } catch (SocketTimeoutException e) {
            throw new AuditException("The request timed out. Please try again");
        } catch (UnknownHostException e) {
            throw new AuditException("Website unreachable");
        } catch (java.io.IOException e) {
            throw new AuditException("Website unreachable");
        }

        long responseTime = System.currentTimeMillis() - startTime;

        String contentType = httpResponse.contentType();
        if (contentType == null || !contentType.toLowerCase().contains("html")) {
            throw new AuditException("The provided URL did not return an HTML page");
        }

        Document document;
        try {
            document = httpResponse.parse();
        } catch (java.io.IOException e) {
            throw new AuditException("Unable to parse the page content");
        }

        String title = document.title();

        String metaDescription = document.select("meta[name=description]")
                .stream()
                .findFirst()
                .map(meta -> meta.attr("content"))
                .orElse("");

        int h1Count = document.select("h1").size();

        int imagesWithoutAlt = (int) document.select("img").stream()
                .filter(img -> img.attr("alt").trim().isEmpty())
                .count();

        String bodyText = document.body() != null ? document.body().text() : "";
        int wordCount = bodyText.isBlank()
                ? 0
                : bodyText.trim().split("\\s+").length;

        return new AuditResponse(
                httpResponse.statusCode(),
                responseTime,
                title,
                metaDescription,
                h1Count,
                imagesWithoutAlt,
                wordCount
        );
    }

    private String validateAndNormalizeUrl(String rawUrl) {
        if (rawUrl == null || rawUrl.trim().isEmpty()) {
            throw new AuditException("URL cannot be empty");
        }

        String url = rawUrl.trim();

        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }

        try {
            new URL(url).toURI();
        } catch (MalformedURLException | java.net.URISyntaxException e) {
            throw new AuditException("Invalid URL");
        }

        return url;
    }

}
