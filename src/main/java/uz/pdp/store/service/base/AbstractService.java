package uz.pdp.store.service.base;

import lombok.RequiredArgsConstructor;
import uz.pdp.store.mapper.base.BaseMapper;
import uz.pdp.store.repository.BaseRepository;


@RequiredArgsConstructor
public abstract class AbstractService<M extends BaseMapper, R extends BaseRepository> {
    protected final M mapper;
    protected final R repository;
}
