package com.github.loafer.examples.user;

import java.util.List;
import java.util.Map;

/**
 * Date Created  14-5-21
 *
 * @author zjh
 */
public interface ICrudService<T> {
    /**
     * 主键查询
     * @param id
     * @return
     */
    T selectOne(String id);

    /**
     * 多条件查询
     * @param params
     * @return
     */
    List<T> selectList(Map<String, Object> params);

    /**
     * 保存或更新
     * @param bean
     */
    void saveOrUpdate(T bean);

    /**
     * 按主键删除（逻辑删除）
     * @param id
     */
    void deleteOne(String id);

    /**
     * 按条件删除（逻辑删除）
     * @param params
     */
    void deleteAll(Map<String, Object> params);

    /**
     * 按主键删除（物理删除）
     * @param id
     */
    void removeOne(String id);

    /**
     * 按条件删除（物理删除）
     * @param params
     */
    void removeAll(Map<String, Object> params);
}
