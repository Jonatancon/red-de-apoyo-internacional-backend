package com.pragma.reddeapoyointernacionalbackend.domain.services;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CasaEntity;
import com.pragma.reddeapoyointernacionalbackend.data.repository.CasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CasaService {

    @Autowired
    private CasaRepository casaRepository;

    public CasaEntity crearUnaCasa(CasaEntity casaEntity) {
        return casaRepository.save(casaEntity);
    }

    public List<CasaEntity> todasLasCasas() {
        return casaRepository.findAll();
    }
}
