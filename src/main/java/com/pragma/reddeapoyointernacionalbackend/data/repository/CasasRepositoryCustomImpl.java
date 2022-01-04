package com.pragma.reddeapoyointernacionalbackend.data.repository;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.CriterioDto;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CasaEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CasasRepositoryCustomImpl implements CasasRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<CasaEntity> findAllByCriterial(CriterioDto criterioDto) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CasaEntity> query = cb.createQuery(CasaEntity.class);
        Root<CasaEntity> casa = query.from(CasaEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        if (criterioDto == null) {
            query.select(casa);

            return entityManager.createQuery(query).getResultList();
        }

        if (!criterioDto.getPais().isEmpty() || !criterioDto.getPais().isBlank()) {
            predicates.add(cb.like(casa.get("pais"), criterioDto.getPais()));

            if (!criterioDto.getEstado().isEmpty() || !criterioDto.getEstado().isBlank()) {
                predicates.add(cb.like(casa.get("estado"), criterioDto.getEstado()));

                if (!criterioDto.getCiudad().isEmpty() || !criterioDto.getCiudad().isBlank()) {
                    predicates.add(cb.like(casa.get("ciudad"), criterioDto.getCiudad()));
                }
            }
        }
        query.select(casa)
                .where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query).getResultList();
    }
}
