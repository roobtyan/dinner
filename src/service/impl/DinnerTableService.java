package service.impl;

import dao.IDinnerTableDao;
import entity.DinnerTable;
import entity.TableStatus;
import factory.BeanFactory;
import service.IDinnerTableService;

import java.util.List;

public class DinnerTableService implements IDinnerTableService {
    //通过工厂类创建对象
    private IDinnerTableDao dinnerTableDao = BeanFactory.getInstance("dinnerTableDao", IDinnerTableDao.class);

    /**
     * 根据输入条件查询餐桌
     *
     * @param tableName
     */
    @Override
    public List<DinnerTable> findByTableName(String tableName) {
        try {
            return dinnerTableDao.findDinnerTable(tableName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询所有的餐桌
     */
    @Override
    public List<DinnerTable> findAll() {
        try {
            List<DinnerTable> dinnerTables = dinnerTableDao.findAll();
            System.out.println(dinnerTables.toString());
            return dinnerTables;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /**
     * 添加餐桌
     *
     * @param tableName
     */
    @Override
    public void addDinnerTable(String tableName) {
        //调用service
        try {
            dinnerTableDao.addDinnerTable(tableName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除餐桌
     *
     * @param id
     */
    @Override
    public void deleteDinnerTable(int id) {
        try {
            dinnerTableDao.deleteDinnerTable(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 更新餐桌信息
     *
     * @param dinnerTable
     */
    @Override
    public void updateDinnerTable(DinnerTable dinnerTable) {
        try {
            dinnerTableDao.setDinnerTableInfo(dinnerTable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询所有未预订状态的餐桌
     *
     * @return
     */
    @Override
    public List<DinnerTable> findNoUse() {
        try {
            //通过查找状态为free的
            return dinnerTableDao.findByStatus(TableStatus.Free);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过餐桌id查询餐桌
     *
     * @param id
     * @return
     */
    @Override
    public DinnerTable findById(int id) {
        try {
            return dinnerTableDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
