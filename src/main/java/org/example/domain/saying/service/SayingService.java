package org.example.domain.saying.service;

import org.example.domain.saying.repository.SayingRepository;

public class SayingService {

    private final SayingRepository sayingRepository;

    public SayingService() {
        this.sayingRepository = new SayingRepository();
    }
}
