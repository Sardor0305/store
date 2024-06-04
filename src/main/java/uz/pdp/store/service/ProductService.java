package uz.pdp.store.service;

import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.store.criteria.ProductCriteria;
import uz.pdp.store.dto.product.ProductCreateDTO;
import uz.pdp.store.dto.product.ProductDTO;
import uz.pdp.store.dto.product.ProductUpdateDTO;
import uz.pdp.store.enitity.Product;
import uz.pdp.store.exeptions.NotFoundException;
import uz.pdp.store.mapper.ProductMapper;
import uz.pdp.store.repository.ProductRepository;
import uz.pdp.store.service.base.AbstractService;
import uz.pdp.store.service.base.BaseService;
import uz.pdp.store.service.base.GenericService;
import uz.pdp.store.specification.ProductSpecification;
import uz.pdp.store.unitls.MessageKey;


import java.time.LocalDate;
import java.util.List;

@Service
public class ProductService
        extends AbstractService<ProductMapper, ProductRepository>
        implements GenericService<Product,ProductDTO,ProductCreateDTO,ProductUpdateDTO,ProductCriteria>,
        BaseService {

    private final ProductSpecification specification;





    public ProductService(
            ProductMapper mapper,
            ProductRepository repository,
            ProductSpecification specification

    ) {
        super(mapper, repository);
        this.specification = specification;
    }

    @Override
    public ProductDTO get(Long id) {
        Product product = findById(id);

        return mapper.fromEntity(repository.save(product));
    }

    @Override
    public List<ProductDTO> getAll(ProductCriteria criteria) {
        return repository.findAll(specification.getSpecification(criteria))
                .stream()
                .map(mapper::fromEntity)
                .toList();
    }

    @Override
    public Page<ProductDTO> getAll(ProductCriteria criteria, Pageable pageable) {
        return repository.findAll(specification.getSpecification(criteria), pageable)
                .map(mapper::fromEntity);
    }

    @Override
    public ProductDTO create(ProductCreateDTO dto) {

        Product product = mapper.toEntity(dto);
        repository.save(product);
        return mapper.fromEntity(product);
    }

    @Override
    public ProductDTO update(Long id, ProductUpdateDTO dto) {

        Product product = findById(id);
        mapper.toEntity(dto, product);
        repository.save(product);
        return mapper.fromEntity(product);
    }

    @Override
    public void delete(Long id) {
        Product product = mapper.toDeleteEntity(findById(id));
        repository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(MessageKey.DRUG_NOT_FOUND));
    }


}
