package com.suda.delicias.infraestructura.adaptadores.repositorios;

import com.suda.delicias.dominio.entidades.Cliente;
import com.suda.delicias.dominio.repositorios.IClienteRepositorio;
import com.suda.delicias.infraestructura.adaptadores.jpa.repositorios.IClienteJpaRepository;
import com.suda.delicias.infraestructura.adaptadores.mapeadores.IClienteJpaMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador que implementa IClienteRepositorio
 * Clase final - no se utilizan dependencias de Spring Boot en el dominio
 */
@Component
public final class ClienteRepositorioImpl implements IClienteRepositorio {
    
    private final IClienteJpaRepository jpaRepository;
    private final IClienteJpaMapper entityMapper;

    /**
     * Constructor con inyección de dependencias
     * Spring detecta automáticamente la inyección (sin @Autowired)
     */
    public ClienteRepositorioImpl(IClienteJpaRepository jpaRepository, 
                                  IClienteJpaMapper entityMapper) {
        this.jpaRepository = jpaRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        var entity = entityMapper.toEntity(cliente);
        var guardado = jpaRepository.save(entity);
        return entityMapper.toDomain(guardado);
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        return jpaRepository.findById(id)
                .map(entityMapper::toDomain);
    }

    @Override
    public List<Cliente> listarTodos() {
        return jpaRepository.findAll()
                .stream()
                .map(entityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        jpaRepository.deleteById(id);
    }
}
