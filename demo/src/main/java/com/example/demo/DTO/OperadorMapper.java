package com.example.demo.DTO;
import com.example.demo.entidades.Operador;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OperadorMapper {

    OperadorMapper INSTANCE = Mappers.getMapper(OperadorMapper.class);

    OperadorDTO convert(Operador operador); // Entidad → DTO (oculta contraseña)
    Operador convert(OperadorDTO dto);  
    
    
}
