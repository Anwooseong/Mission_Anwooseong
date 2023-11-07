package org.example.domain.saying.controller;

import org.example.domain.saying.dto.UrlParser;
import org.example.domain.saying.model.Saying;
import org.example.domain.saying.service.SayingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SayingController {

    private Scanner scanner;
    private static List<Saying> sayingList;
    private final SayingService sayingService;

    public SayingController(Scanner scanner) {
        sayingService = new SayingService();
        this.scanner = scanner;
        sayingList = new ArrayList<>();
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
    }

    public void actionBuild() {
    }
}
