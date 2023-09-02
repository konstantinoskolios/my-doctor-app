package com.example.mydoctorapp.services;

import com.example.mydoctorapp.entities.DummyEntity;
import com.example.mydoctorapp.repositories.DummyEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DummyEntityService {

    private final DummyEntityRepository dummyEntityRepository;

    public DummyEntity dummySave() {
        var dummyEntity = dummyEntityRepository.save(new DummyEntity("test"));
        log.info("The dummy obj saved successfully: {}", dummyEntity);
        return dummyEntity;
    }
}
