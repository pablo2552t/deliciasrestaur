package com.suda.delicias.infraestructura.adaptadores.mapeadores;

import com.suda.delicias.dominio.entidades.Administrador;
import com.suda.delicias.infraestructura.adaptadores.jpa.entidades.AdministradorJpaEntity;
import org.mapstruct.Mapper;

/**
 * Mapper MapStruct que convierte entre:
 * - Administrador (dominio) ↔ AdministradorJpaEntity (infraestructura)
 * 
 * MapStruct genera automáticamente la implementación de este mapper en tiempo de compilación.
 */
@Mapper(componentModel = "spring")
public interface IAdministradorJpaMapper {
    
    /**
     * Convierte de entidad JPA a entidad de dominio.
     */
    Administrador toDomain(AdministradorJpaEntity entity);
    
    /**
     * Convierte de entidad de dominio a entidad JPA.
     */
    AdministradorJpaEntity toEntity(Administrador administrador);
}