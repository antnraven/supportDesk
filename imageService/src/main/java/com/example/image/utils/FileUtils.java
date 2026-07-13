package com.example.image.utils;

import org.springframework.http.*;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.regex.Pattern;

public class FileUtils {
    private static final List<String> ALLOWED_DOMAINS = List.of(
            "https://disk.yandex.ru/"
    );

    private static final Pattern FORBIDDEN_EXTENSIONS = Pattern.compile("(?i)\\.(exe|bat|cmd|sh|js)$");

    public static MediaType determineMediaType(ResponseEntity<byte[]> response) {
        org.springframework.http.MediaType mediaType = response.getHeaders().getContentType();
        return (mediaType != null) ? mediaType : MediaType.APPLICATION_OCTET_STREAM;
    }

    public static void validateUrlSafety(String fileUrl) {
        URL url = null;
        try {
            url = new URL(fileUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        String host = url.getHost();

        boolean isAllowed = ALLOWED_DOMAINS.stream().anyMatch(host::endsWith);
        if (!isAllowed) {
            throw new SecurityException("Обращение к хосту '" + host + "' запрещено политикой безопасности.");
        }
    }

    public static boolean isForbiddenExtension(String filename) {
        return FORBIDDEN_EXTENSIONS.matcher(filename).find();
    }
}
