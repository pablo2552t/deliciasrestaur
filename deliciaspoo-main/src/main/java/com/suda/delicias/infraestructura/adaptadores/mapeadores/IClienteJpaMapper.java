package com.suda.delicias.infraestructura.adaptadores.mapeadores;

import com.suda.delicias.dominio.entidades.Cliente;
import com.suda.delicias.infraestructura.adaptadores.jpa.entidades.ClienteJpaEntity;
import org.mapstruct.Mapper;

/**
 * Mapper de MapStruct para convertir entre Cliente (dominio) y ClienteJpaEntity (infraestructura)
 * MapStruct generará automáticamente la implementación
 */
@Mapper(componentModel = "spring")
public interface IClienteJpaMapper {
    
    /**
     * Convierte de dominio a entidad JPA
     */
    ClienteJpaEntity toEntity(Cliente cliente);
    
    /**
     * Convierte de entidad JPA a dominio
     */
    Cliente toDomain(ClienteJpaEntity entity);
}
