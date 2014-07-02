package com.github.loafer.mybatis.other;

import java.util.List;

/**
 * @author zhaojh
 */
public interface ICrudService<T> {
    String SELECT_ONE_STATEMENT_ID = ".selectOne";
    String SELECT_BY_PARAMETER_STATEMENT_ID = ".selectList";
    String SELECT_PAGING_STATEMENT_ID = ".selectPaging";
    String CREATE_STATEMENT_ID = ".insert";
    String UPDATE_ONE_MODEL_STATEMENT_ID = ".updateById";
    String UPDATE_BY_PARAMETER_STATEMENT_ID = ".updateByParameter";
    String DELETE_ONE_MODEL_STATEMENT_ID = ".deleteById";
    String DELETE_BY_PARAMETER_STATEMENT_ID = ".deleteByParameter";
    String REMOVE_ONE_STATEMENT_ID = ".removeById";
    String REMOVE_BY_PARAMETER_STATEMENT_ID = ".removeByParameter";

    T selectOne(T model);

    List<T> selectList(T model);

    Object selectPaging(T model, String offset, String limit);

    Object selectPaging(T model, int offset, int limit);

    void create(T model);

    T create(T model, boolean isReturnModel);

    void create(List<T> modelList);

    void updateOne(T model);

    T updateOne(T model, boolean isReturnModel);

    void deleteOne(T model);

    void removeOne(T model);

//    void updateMore(T model);

//    void deleteMore(T model);

//    void removeMore(T model);
}
