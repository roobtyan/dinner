package dao;

import entity.DinnerTable;
import entity.TableStatus;

import java.util.List;

/**
 * 餐桌相关功能dao层接口
 */
public interface IDinnerTableDao {

    /**
     * 查询所有的餐桌信息
     */
    List<DinnerTable> findAll();

    /**
     * 更新餐桌信息
     */
    void setDinnerTableInfo(DinnerTable dinnerTableInfo);

    /**
     * 添加新的餐桌
     */
    void addDinnerTable(String tableName);

    /**
     * 删除餐桌:通过id
     */
    void deleteDinnerTable(int id);

    /**
     * 搜索餐桌
     */
    List<DinnerTable> findDinnerTable(String tableName);

    /**
     * 通过餐桌的预订状态查询餐桌
     *
     * @param tableStatus
     * @return
     */
    List<DinnerTable> findByStatus(TableStatus tableStatus);

    /**
     * 通过餐桌的id查询餐桌
     *
     * @return
     */
    DinnerTable findById(int id);
}
