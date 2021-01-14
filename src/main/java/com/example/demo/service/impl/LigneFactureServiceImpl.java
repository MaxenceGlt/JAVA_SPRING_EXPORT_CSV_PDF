package com.example.demo.service.impl;

import com.example.demo.dto.FactureDto;
import com.example.demo.dto.LigneFactureDto;
import com.example.demo.entity.LigneFacture;
import com.example.demo.repository.FactureRepository;
import com.example.demo.repository.LigneFactureRepository;
import com.example.demo.service.LigneFactureService;
import com.example.demo.service.mapper.FactureMapper;
import com.example.demo.service.mapper.LigneFactureMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;
@Service
@Transactional
public class LigneFactureServiceImpl implements LigneFactureService {
    private LigneFactureRepository ligneFactureRepository;
    private LigneFactureMapper ligneFactureMapper;

    public LigneFactureServiceImpl(LigneFactureRepository ligneFactureRepository, LigneFactureMapper ligneFactureMapper) {
        this.ligneFactureRepository = ligneFactureRepository;
        this.ligneFactureMapper = ligneFactureMapper;
    }

    @Override
    public List<LigneFactureDto> findAll() {
        return ligneFactureRepository.findAll().stream().map(ligneFacture -> ligneFactureMapper.ligneFactureDto(ligneFacture)).collect(toList());
    }
}
