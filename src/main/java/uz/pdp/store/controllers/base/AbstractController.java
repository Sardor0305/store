package uz.pdp.store.controllers.base;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import uz.pdp.store.response.ResponseBuilder;
import uz.pdp.store.service.base.BaseService;


@RequiredArgsConstructor
public abstract class AbstractController<S extends BaseService> {

    protected final S service;
    protected ResponseBuilder responseBuilder;

    @Autowired
    public void setResponseBuilder(ResponseBuilder responseBuilder) {
        this.responseBuilder = responseBuilder;
    }

}
