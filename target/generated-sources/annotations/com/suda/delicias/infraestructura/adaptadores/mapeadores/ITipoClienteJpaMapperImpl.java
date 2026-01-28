package com.suda.delicias.infraestructura.adaptadores.mapeadores;

import com.suda.delicias.dominio.entidades.TipoCliente;
import com.suda.delicias.infraestructura.adaptadores.jpa.entidades.TipoClienteJpaEntity;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-28T09:51:32-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 25.0.1 (Eclipse Adoptium)"
)
@Component
public class ITipoClienteJpaMapperImpl implements ITipoClienteJpaMapper {

    @Override
    public TipoClienteJpaEntity toEntity(TipoCliente tipoCliente) {
        if ( tipoCliente == null ) {
            return null;
        }

        TipoClienteJpaEntity tipoClienteJpaEntity = new TipoClienteJpaEntity();

        return tipoClienteJpaEntity;
    }

    @Override
    public TipoCliente toDomain(TipoClienteJpaEntity entity) {
        if ( entity == null ) {
            return null;
        }

        int idTipoCliente = 0;
        String nombreTipo = null;
        String descripcion = null;
        boolean estado = false;

        TipoCliente tipoCliente = new TipoCliente( idTipoCliente, nombreTipo, descripcion, estado );

        return tipoCliente;
    }
}
