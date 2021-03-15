package icu.shishc.service;

public interface BaseService<T> {

    /**
     * 新增数据
     * @param t
     * @return 受影响的行数
     */
    Integer insert(T t);


    /**
     * 根据id删除数据
     * @param id
     * @return 受影响的行数
     */
    Integer delete(int id);


    /**
     * 更新数据
     * @param t
     * @return
     */
    Integer update(T t);

}
