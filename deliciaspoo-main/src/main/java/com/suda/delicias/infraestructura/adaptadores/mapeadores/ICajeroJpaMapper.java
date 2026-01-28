package com.suda.delicias.infraestructura.adaptadores.mapeadores;

import com.suda.delicias.dominio.entidades.Cajero;
import com.suda.delicias.infraestructura.adaptadores.jpa.entidades.CajeroJpaEntity;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ICajeroJpaMapper {

    // Convierte la entidad de Base de Datos al objeto de Dominio (Negocio)
    Cajero toDomain(CajeroJpaEntity jpaEntity);

    // Convierte el objeto de Dominio a la entidad de Base de Datos para guardar
    CajeroJpaEntity toEntity(Cajero domain);

    // Convierte listas completas (útil para el método obtenerTodos)
    List<Cajero> toDomainList(List<CajeroJpaEntity> jpaEntityList);
}