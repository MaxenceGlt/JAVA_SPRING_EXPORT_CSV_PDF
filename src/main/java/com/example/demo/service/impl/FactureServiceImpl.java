package com.example.demo.service.impl;

import com.example.demo.dto.ClientDto;
import com.example.demo.dto.FactureDto;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.FactureRepository;
import com.example.demo.service.FactureService;
import com.example.demo.service.mapper.ClientMapper;
import com.example.demo.service.mapper.FactureMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class FactureServiceImpl implements FactureService {
    private FactureRepository factureRepository;
    private FactureMapper factureMapper;

    public FactureServiceImpl(FactureRepository factureRepository, FactureMapper factureMapper) {
        this.factureRepository = factureRepository;
        this.factureMapper = factureMapper;
    }

    @Override
    public List<FactureDto> findAll() {
        return factureRepository.findAll().stream().map(facture -> factureMapper.factureDto(facture)).collect(toList());
    }
}
