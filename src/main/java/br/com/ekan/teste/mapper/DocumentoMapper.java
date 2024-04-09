package br.com.ekan.teste.mapper;

import br.com.ekan.teste.domain.DocumentoDTO;
import br.com.ekan.teste.entity.Documento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentoMapper {

    DocumentoMapper INSTANCE = Mappers.getMapper(DocumentoMapper.class);

    DocumentoDTO convert(Documento entity);

    @Mapping(target = "tipoDocumento", source = "tipoDocumento")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "dataInclusao", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    @Mapping(target = "beneficiario", ignore = true)
    Documento convert(DocumentoDTO dto);

    List<DocumentoDTO> convert(List<Documento> list);
}
