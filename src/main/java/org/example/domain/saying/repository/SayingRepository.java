package org.example.domain.saying.repository;

import org.example.domain.saying.model.Saying;

import java.util.List;
import java.util.Optional;

public class SayingRepository {
    public int save(Saying saying, List<Saying> sayingList) {
        sayingList.add(saying);
        return saying.getId();
    }

    public void deleteById(int findId, List<Saying> sayingList) {
        boolean isExist = sayingList.removeIf(
                (saying) -> saying.getId() == findId
        );

        if (isExist) {
            System.out.println("" + findId + "번 명언이 삭제되었습니다.");
        } else {
            System.out.println("" + findId + "번 명언은 존재하지 않습니다.");
        }
    }

    public Optional<Saying> findById(int findId, List<Saying> sayingList) {
        for (int i = 0; i < sayingList.size(); i++) {
            if (sayingList.get(i).getId() == findId) {
                return Optional.ofNullable(sayingList.get(i));
            }
        }
        return Optional.empty();
    }
}
