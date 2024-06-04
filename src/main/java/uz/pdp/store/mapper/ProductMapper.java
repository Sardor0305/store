package uz.pdp.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import uz.pdp.store.dto.product.ProductCreateDTO;
import uz.pdp.store.dto.product.ProductDTO;
import uz.pdp.store.dto.product.ProductUpdateDTO;
import uz.pdp.store.enitity.Product;
import uz.pdp.store.mapper.base.GenericMapper;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProductMapper extends GenericMapper<Product, ProductDTO, ProductCreateDTO, ProductUpdateDTO> {

    @Override
    @Mapping(target = "id", ignore = true)
    void toEntity(ProductUpdateDTO dto, @MappingTarget Product drug);

    @Mapping(target = "deleted", expression = "java(true)")
    Product toDeleteEntity(Product product);

    ProductDTO fromEntity(Product product);

}
