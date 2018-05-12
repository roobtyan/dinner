package service;

import entity.DinnerTable;

import java.util.List;

/**
 * 餐桌业务逻辑接口设计
 */
public interface IDinnerTableService {

    /**
     * 根据输入条件查询餐桌
     */
    List<DinnerTable> findByTableName(String tableName);

    /**
     * 查询所有的餐桌
     */
    List<DinnerTable> findAll();

    /**
     * 添加餐桌
     */
    void addDinnerTable(String tableName);

    /**
     * 删除餐桌
     */
    void deleteDinnerTable(int id);

    /**
     * 更新餐桌信息
     */
    void updateDinnerTable(DinnerTable dinnerTable);

    /**
     * 查询所有未预订状态的餐桌
     * @return
     */
    List<DinnerTable> findNoUse();

    /**
     * 通过餐桌id查询餐桌
     * @param id
     * @return
     */
    DinnerTable findById(int id);
}
