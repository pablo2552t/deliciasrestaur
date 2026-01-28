package com.suda.delicias.infraestructura.adaptadores.repositorios;

import com.suda.delicias.dominio.entidades.Cajero;
import com.suda.delicias.dominio.repositorios.ICajeroRepositorio;
import com.suda.delicias.infraestructura.adaptadores.jpa.repositorios.ICajeroJpaRepository;
import com.suda.delicias.infraestructura.adaptadores.mapeadores.ICajeroJpaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CajeroRepositorioImpl implements ICajeroRepositorio {

    // Inyectamos el repositorio de Spring Data y el Mapeador
    private final ICajeroJpaRepository jpaRepository;
    private final ICajeroJpaMapper mapper;

    public CajeroRepositorioImpl(ICajeroJpaRepository jpaRepository, ICajeroJpaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Cajero> obtenerTodos() {
        // Trae de la DB y convierte a lista de dominio
        return mapper.toDomainList(jpaRepository.findAll());
    }

    @Override
    public Optional<Cajero> obtenerPorId(Long id) {
        // Busca en DB y convierte el resultado si existe
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Cajero guardar(Cajero cajero) {
        // Convierte dominio a entidad JPA -> Guarda -> Convierte de vuelta a dominio
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(cajero)));
    }

    @Override
    public void eliminar(Long id) {
        jpaRepository.deleteById(id);
    }
}