package com.example.demo.service;

import com.example.demo.dto.FactureDto;
import com.example.demo.dto.LigneFactureDto;

import java.util.List;

public interface LigneFactureService {
    List<LigneFactureDto> findAll();
}
