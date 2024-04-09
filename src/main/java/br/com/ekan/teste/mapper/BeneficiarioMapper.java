package br.com.ekan.teste.mapper;

import br.com.ekan.teste.domain.BeneficiarioDTO;
import br.com.ekan.teste.entity.Beneficiario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BeneficiarioMapper {

    BeneficiarioMapper INSTANCE = Mappers.getMapper(BeneficiarioMapper.class);


    BeneficiarioDTO convert(Beneficiario entity);

    @Mapping(target = "dataInclusao", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    Beneficiario covert(BeneficiarioDTO dto);

    List<BeneficiarioDTO> convert(List<Beneficiario> list);
}
