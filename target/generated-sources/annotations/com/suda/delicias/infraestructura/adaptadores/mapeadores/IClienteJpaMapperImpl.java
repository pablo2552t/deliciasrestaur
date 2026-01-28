package com.suda.delicias.infraestructura.adaptadores.mapeadores;

import com.suda.delicias.dominio.entidades.Cliente;
import com.suda.delicias.infraestructura.adaptadores.jpa.entidades.ClienteJpaEntity;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-28T09:51:32-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 25.0.1 (Eclipse Adoptium)"
)
@Component
public class IClienteJpaMapperImpl implements IClienteJpaMapper {

    @Override
    public ClienteJpaEntity toEntity(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteJpaEntity clienteJpaEntity = new ClienteJpaEntity();

        return clienteJpaEntity;
    }

    @Override
    public Cliente toDomain(ClienteJpaEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String nombre = null;
        String apellido = null;
        String direccion = null;
        String email = null;
        String telefono = null;
        String ci = null;

        Cliente cliente = new Cliente( id, nombre, apellido, direccion, email, telefono, ci );

        return cliente;
    }
}
