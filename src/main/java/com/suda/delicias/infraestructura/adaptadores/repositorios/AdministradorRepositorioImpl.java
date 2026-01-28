package com.suda.delicias.infraestructura.adaptadores.repositorios;

import com.suda.delicias.dominio.entidades.Administrador;
import com.suda.delicias.dominio.repositorios.IAdministradorRepositorio;
import com.suda.delicias.infraestructura.adaptadores.jpa.repositorios.IAdministradorJpaRepository;
import com.suda.delicias.infraestructura.adaptadores.mapeadores.IAdministradorJpaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador que implementa IAdministradorRepositorio (del dominio)
 * usando tecnología JPA (infraestructura).
 * 
 * Este es el "pegamento" entre el dominio y la tecnología específica.
 */
@Repository
public final class AdministradorRepositorioImpl implements IAdministradorRepositorio {
    
    private final IAdministradorJpaRepository jpaRepository;
    private final IAdministradorJpaMapper mapper;
    
    /**
     * Inyección de dependencias por constructor.
     */
    public AdministradorRepositorioImpl(
            IAdministradorJpaRepository jpaRepository,
            IAdministradorJpaMapper mapper
    ) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }
    
    @Override
    public List<Administrador> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Administrador> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
    
    @Override
    public Administrador save(Administrador administrador) {
        var entity = mapper.toEntity(administrador);
        var savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
    
    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}