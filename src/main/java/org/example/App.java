package org.example;

import org.example.domain.saying.controller.SayingController;
import org.example.domain.saying.dto.UrlParser;

import java.util.Scanner;

public class App {
    private Scanner scanner;

    public App() {
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

        SayingController sayingController = new SayingController(scanner);

        while (true) {
            System.out.print("명령) ");

            String cmd = scanner.nextLine();

            UrlParser url = new UrlParser(cmd);

            switch (url.getAction()) {
                case "종료":
                    return;
                case "등록":
                    sayingController.actionWrite();
                    break;
                case "목록":
                    sayingController.actionList();
                    break;
                case "삭제":
                    sayingController.actionRemove(url);
                    break;
                case "수정":
                    sayingController.actionModify(url);
                    break;
                case "빌드":
                    sayingController.actionBuild();
                    break;
            }
        }
    }

}
