package com.suda.delicias.infraestructura.adaptadores.repositorios;

import com.suda.delicias.dominio.entidades.Cocinero;
import com.suda.delicias.dominio.repositorios.ICocineroRepositorio;
import com.suda.delicias.infraestructura.adaptadores.jpa.repositorios.ICocineroJpaRepository;
import com.suda.delicias.infraestructura.adaptadores.mapeadores.ICocineroJpaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador que implementa ICocineroRepositorio (del dominio)
 * usando tecnología JPA (infraestructura).
 * 
 * Este es el "pegamento" entre el dominio y la tecnología específica.
 */
@Repository
public final class CocineroRepositorioImpl implements ICocineroRepositorio {
    
    private final ICocineroJpaRepository jpaRepository;
    private final ICocineroJpaMapper mapper;
    
    /**
     * Inyección de dependencias por constructor.
     */
    public CocineroRepositorioImpl(
            ICocineroJpaRepository jpaRepository,
            ICocineroJpaMapper mapper
    ) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }
    
    @Override
    public List<Cocinero> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Cocinero> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
    
    @Override
    public Cocinero save(Cocinero cocinero) {
        var entity = mapper.toEntity(cocinero);
        var savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
    
    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}