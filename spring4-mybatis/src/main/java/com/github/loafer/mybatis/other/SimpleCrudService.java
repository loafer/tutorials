package com.github.loafer.mybatis.other;

import java.util.List;

/**
 * @author zhaojh
 */
public class SimpleCrudService<T extends Model> implements ICrudService<T> {
    private IRepository<T> repository;

    protected IRepository<T> getRepository(){
        return repository;
    }

    @Override
    public T selectOne(T model) {
        return repository.selectOne(model.getNamespace() + SELECT_ONE_STATEMENT_ID, model.getId());
    }

    @Override
    public List<T> selectList(T model) {
        return repository.selectList(model.getNamespace() + SELECT_BY_PARAMETER_STATEMENT_ID, model);
    }

    @Override
    public Object selectPaging(T model, String offset, String limit) {
        int _offset = Integer.parseInt(offset),
            _limit = Integer.parseInt(limit);
        return selectPaging(model, _offset, _limit);
    }

    @Override
    public Object selectPaging(T model, int offset, int limit) {
        return null;
    }

    @Override
    public void create(T model) {
        create(model, false);
    }

    @Override
    public T create(T model, boolean isReturnModel) {
        model.setId("");
        repository.insert(model.getNamespace() + CREATE_STATEMENT_ID, model);
        if(isReturnModel){
            return selectOne(model);
        }
        return null;
    }

    @Override
    public void create(List<T> modelList) {
        for(T model : modelList){
            create(model);
        }
    }

    @Override
    public void updateOne(T model) {
        updateOne(model, false);
    }

    @Override
    public T updateOne(T model, boolean isReturnModel) {
        repository.update(model.getNamespace() + UPDATE_ONE_MODEL_STATEMENT_ID, model);
        if(isReturnModel){
            return selectOne(model);
        }
        return null;
    }

    @Override
    public void deleteOne(T model) {
        repository.delete(model.getId() + DELETE_ONE_MODEL_STATEMENT_ID, model.getId());
    }

    @Override
    public void removeOne(T model) {
        repository.update(model.getNamespace() + REMOVE_ONE_STATEMENT_ID, model.getId());
    }

//    @Override
//    public void updateMore(T model) {
//        repository.update(model.getNamespace() + UPDATE_BY_PARAMETER_STATEMENT_ID, model.getReplacedAtrributes());
//    }

//    @Override
//    public void deleteMore(T model) {
//        repository.delete(model.getId() + DELETE_BY_PARAMETER_STATEMENT_ID, model);
//    }

//    @Override
//    public void removeMore(T model) {
//        repository.update(model.getNamespace() + REMOVE_BY_PARAMETER_STATEMENT_ID, model);
//    }
}
