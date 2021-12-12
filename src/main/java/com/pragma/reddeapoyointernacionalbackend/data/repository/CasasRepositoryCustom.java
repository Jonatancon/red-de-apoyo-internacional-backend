package com.pragma.reddeapoyointernacionalbackend.data.repository;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.CriterioDto;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CasaEntity;
import java.util.List;

public interface CasasRepositoryCustom {

    List<CasaEntity> findAllByCriterial(CriterioDto criterioDto);
}
