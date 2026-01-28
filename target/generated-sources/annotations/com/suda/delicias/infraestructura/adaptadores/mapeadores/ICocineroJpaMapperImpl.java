package com.suda.delicias.infraestructura.adaptadores.mapeadores;

import com.suda.delicias.dominio.entidades.Cocinero;
import com.suda.delicias.infraestructura.adaptadores.jpa.entidades.CocineroJpaEntity;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-28T09:52:24-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 25.0.1 (Eclipse Adoptium)"
)
@Component
public class ICocineroJpaMapperImpl implements ICocineroJpaMapper {

    @Override
    public Cocinero toDomain(CocineroJpaEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String nombre = null;
        String apellido = null;
        String email = null;
        String telefono = null;
        String especialidad = null;
        String turno = null;

        Cocinero cocinero = new Cocinero( id, nombre, apellido, email, telefono, especialidad, turno );

        return cocinero;
    }

    @Override
    public CocineroJpaEntity toEntity(Cocinero cocinero) {
        if ( cocinero == null ) {
            return null;
        }

        CocineroJpaEntity cocineroJpaEntity = new CocineroJpaEntity();

        return cocineroJpaEntity;
    }
}
