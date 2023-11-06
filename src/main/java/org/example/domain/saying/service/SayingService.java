package org.example.domain.saying.service;

import org.example.domain.saying.model.Saying;
import org.example.domain.saying.repository.SayingRepository;

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
}
