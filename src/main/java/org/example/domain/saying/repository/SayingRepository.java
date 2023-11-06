package org.example.domain.saying.repository;

import org.example.domain.saying.model.Saying;

import java.util.List;

public class SayingRepository {
    public int save(Saying saying, List<Saying> sayingList) {
        sayingList.add(saying);
        return saying.getId();
    }
}
