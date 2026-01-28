package com.suda.delicias.infraestructura.adaptadores.repositorios;

import com.suda.delicias.dominio.entidades.TipoCliente;
import com.suda.delicias.dominio.repositorios.ITipoClienteRepositorio;
import com.suda.delicias.infraestructura.adaptadores.jpa.repositorios.ITipoClienteJpaRepository;
import com.suda.delicias.infraestructura.adaptadores.mapeadores.ITipoClienteJpaMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador que implementa ITipoClienteRepositorio
 * Clase final - no se utilizan dependencias de Spring Boot en el dominio
 */
@Component
public final class TipoClienteRepositorioImpl implements ITipoClienteRepositorio {
    
    private final ITipoClienteJpaRepository jpaRepository;
    private final ITipoClienteJpaMapper entityMapper;

    /**
     * Constructor con inyección de dependencias
     * Spring detecta automáticamente la inyección (sin @Autowired)
     */
    public TipoClienteRepositorioImpl(ITipoClienteJpaRepository jpaRepository, 
                                      ITipoClienteJpaMapper entityMapper) {
        this.jpaRepository = jpaRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public TipoCliente guardar(TipoCliente cliente) {
        var entity = entityMapper.toEntity(cliente);
        var guardado = jpaRepository.save(entity);
        return entityMapper.toDomain(guardado);
    }

    @Override
    public Optional<TipoCliente> buscarPorId(int id) {
        return jpaRepository.findById(id)
                .map(entityMapper::toDomain);
    }

    @Override
    public List<TipoCliente> listarTodos() {
        return jpaRepository.findAll()
                .stream()
                .map(entityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(int id) {
        jpaRepository.deleteById(id);
    }
}
