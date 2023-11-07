package org.example.domain.saying.service;

import org.example.domain.saying.dto.UrlParser;
import org.example.domain.saying.model.Saying;
import org.example.domain.saying.repository.SayingRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SayingService {

    private final SayingRepository sayingRepository;

    public SayingService() {
        this.sayingRepository = new SayingRepository();
    }

    public void actionWrite(Scanner scanner, List<Saying> sayingList) {
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가 : ");
        String author = scanner.nextLine();

        //id List 의 마지막번호 + 1
        int id = sayingList.size() == 0 ? 1 : sayingList.get(sayingList.size() - 1).getId() + 1;

        int saveId = sayingRepository.save(new Saying(id, content, author), sayingList);
        System.out.println(saveId + "번 명언이 등록되었습니다.");
    }

    public void actionList(List<Saying> sayingList) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        sayingList.stream()
                .sorted(Comparator.comparing(Saying::getId).reversed())
                .forEach(saying -> {
                    int id = saying.getId();
                    String content = saying.getContent();
                    String author = saying.getAuthor();
                    System.out.println("" + id + " / " + author + " / " + content);
                });
    }

    public void actionRemove(UrlParser url, List<Saying> sayingList) {
        int findId = url.getParamAsInt("id", 0);
        if (findId <= 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return;
        }
        sayingRepository.deleteById(findId, sayingList);
    }
}
