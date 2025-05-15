package com.example.demo.DTO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.example.demo.entidades.Cliente;

@Mapper
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    ClienteDTO convert(Cliente cliente);
    Cliente convert(ClienteDTO clienteDTO);
}
