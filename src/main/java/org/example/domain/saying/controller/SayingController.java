package org.example.domain.saying.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.saying.dto.UrlParser;
import org.example.domain.saying.model.Saying;
import org.example.domain.saying.service.SayingService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SayingController {

    private Scanner scanner;
    static final String JSON_FILE = "src/main/resources/data.json";
    private static List<Saying> sayingList;
    private final SayingService sayingService;

    public SayingController(Scanner scanner) {
        sayingService = new SayingService();
        this.scanner = scanner;
        sayingList = new ArrayList<>();

        loadFromJsonFile();
    }

    public void actionWrite() {
        sayingService.actionWrite(scanner, sayingList);
    }

    public void actionList() {
        sayingService.actionList(sayingList);
    }

    public void actionRemove(UrlParser url) {
        sayingService.actionRemove(url, sayingList);
    }

    public void actionModify(UrlParser url) {
        sayingService.actionModify(scanner, url, sayingList);
    }

    public void actionBuild() {
        sayingService.actionBuild(sayingList);
    }

    private static void loadFromJsonFile() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            FileInputStream fileInputStream = new FileInputStream(JSON_FILE);
            TypeReference<List<Saying>> typeReference = new TypeReference<>() {};
            sayingList = mapper.readValue(fileInputStream, typeReference);
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println("JSON 파일을 읽을 수 없습니다.");
        }
    }
}
