package uz.pdp.store.controllers;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.store.controllers.base.AbstractController;
import uz.pdp.store.controllers.base.BaseController;
import uz.pdp.store.criteria.ProductCriteria;
import uz.pdp.store.dto.product.ProductCreateDTO;
import uz.pdp.store.dto.product.ProductDTO;
import uz.pdp.store.dto.product.ProductUpdateDTO;
import uz.pdp.store.enitity.Product;
import uz.pdp.store.response.ResponseData;
import uz.pdp.store.response.ResponseMessage;
import uz.pdp.store.service.ProductService;
import uz.pdp.store.unitls.APIUtils;
import uz.pdp.store.unitls.MessageKey;

import java.util.List;

@RestController

@RequestMapping(value = APIUtils.API + APIUtils.V1 + APIUtils.DRUG)
public class ProductController extends AbstractController<ProductService> implements BaseController {

    public ProductController(ProductService service) {
        super(service);
    }

    @GetMapping(value = APIUtils.ID)
    public ResponseEntity<ResponseData<ProductDTO>> get(@PathVariable(name = "id") Long id) {
        return ResponseData.ok(service.get(id));
    }

    @GetMapping(value = APIUtils.EXCEL)
    public ResponseEntity<ResponseData<List<ProductDTO>>> getAll(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "countryName", required = false) String countryName

    ) {
        List<ProductDTO> drugDTOList = service.getAll(
                ProductCriteria.builder()
                        .name(name)
                        .countryName(countryName)
                        .build()
        );
        return ResponseData.ok(drugDTOList);
    }
    @GetMapping(value = APIUtils.ALL)
    public ResponseEntity<ResponseData<Page<ProductDTO>>> getAll(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "countryName", required = false) String countryName,
            Pageable pageable
    ) {
        Page<ProductDTO> drugDTOPage = service.getAll(
                ProductCriteria.builder()
                        .name(name)
                        .countryName(countryName)
                        .build(),
                pageable
        );
        return ResponseData.ok(drugDTOPage);
    }


    @PostMapping
    public ResponseEntity<ResponseData<ProductDTO>> create(@Valid @RequestBody ProductCreateDTO dto) {
        return ResponseData.ok(service.create(dto));
    }

    @PutMapping(value = APIUtils.ID)
    public ResponseEntity<ResponseData<ProductDTO>> update(@PathVariable("id") Long id, @Valid @RequestBody ProductUpdateDTO dto) {
        return ResponseData.ok(service.update(id, dto));
    }

    @DeleteMapping(value = APIUtils.ID)
    public ResponseEntity<ResponseData<ResponseMessage>> delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return responseBuilder.success(MessageKey.SUCCESS);
    }

}
