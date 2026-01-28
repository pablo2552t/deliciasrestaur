package com.suda.delicias.infraestructura.adaptadores.mapeadores;

import com.suda.delicias.dominio.entidades.TipoCliente;
import com.suda.delicias.infraestructura.adaptadores.jpa.entidades.TipoClienteJpaEntity;
import org.mapstruct.Mapper;

/**
 * Mapper de MapStruct para convertir entre TipoCliente (dominio) y TipoClienteJpaEntity (infraestructura)
 * MapStruct generará automáticamente la implementación
 */
@Mapper(componentModel = "spring")
public interface ITipoClienteJpaMapper {
    
    /**
     * Convierte de dominio a entidad JPA
     */
    TipoClienteJpaEntity toEntity(TipoCliente tipoCliente);
    
    /**
     * Convierte de entidad JPA a dominio
     */
    TipoCliente toDomain(TipoClienteJpaEntity entity);
}
