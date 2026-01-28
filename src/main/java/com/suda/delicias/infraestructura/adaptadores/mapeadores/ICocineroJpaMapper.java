package com.suda.delicias.infraestructura.adaptadores.mapeadores;

import com.suda.delicias.dominio.entidades.Cocinero;
import com.suda.delicias.infraestructura.adaptadores.jpa.entidades.CocineroJpaEntity;
import org.mapstruct.Mapper;

/**
 * Mapper MapStruct que convierte entre:
 * - Cocinero (dominio) ↔ CocineroJpaEntity (infraestructura)
 * 
 * MapStruct genera automáticamente la implementación de este mapper en tiempo de compilación.
 */
@Mapper(componentModel = "spring")
public interface ICocineroJpaMapper {
    
    /**
     * Convierte de entidad JPA a entidad de dominio.
     */
    Cocinero toDomain(CocineroJpaEntity entity);
    
    /**
     * Convierte de entidad de dominio a entidad JPA.
     */
    CocineroJpaEntity toEntity(Cocinero cocinero);
}