package org.example.domain.saying.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.saying.dto.UrlParser;
import org.example.domain.saying.model.Saying;
import org.example.domain.saying.repository.SayingRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class SayingService {

    static final String JSON_FILE = "src/main/resources/data.json";
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

    public void actionModify(Scanner scanner, UrlParser url, List<Saying> sayingList) {
        int findId = url.getParamAsInt("id", 0);
        if (findId == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return;
        }
        Optional<Saying> findSaying = sayingRepository.findById(findId, sayingList);
        if (findSaying.isEmpty()) {
            System.out.println("" + findId + "번 명언은 존재하지 않아 수정을 하지 못했습니다.");
            return;
        }
        System.out.println("명언(기존) : " + findSaying.get().getContent());
        System.out.print("명언 : ");
        findSaying.get().setContent(scanner.nextLine());
        System.out.println("작가(기존) : " + findSaying.get().getAuthor());
        System.out.print("작가 : ");
        findSaying.get().setAuthor(scanner.nextLine());
        System.out.println("" + findId + "번 명언이 수정되었습니다.");
    }

    public void actionBuild(List<Saying> sayingList) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            FileOutputStream fileOutputStream = new FileOutputStream(JSON_FILE);
            mapper.writeValue(fileOutputStream, sayingList);
            fileOutputStream.close();
            System.out.println("data.json 파일의 내용이 갱신되었습니다.");
        } catch (IOException e) {
            System.out.println("JSON 파일에 데이터를 저장할 수 없습니다.");
        }
    }
}
