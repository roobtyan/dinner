package utils;

import java.util.List;

/**
 * 分页查询
 * 封装分页查询相关参数
 */

public class PageBean<T> {
    private int currentPage = 1;    //当前页
    private int totalPage;      //总页数
    private int pageCount = 6;      //每页显示的记录数
    private int totalCount;     //总记录数
    private Condition condition;//封装条件数据
    private List<T> pageData;   //封装每页数据

    //获取总页数
    public int getTotalPage() {
        //总页数 = 总记录数 / 每页显示的记录数 （ + 1）
        if (totalCount % pageCount == 0){
            totalPage = totalCount / pageCount;
        }else {
            totalPage = totalCount / pageCount + 1;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getPageData() {
        return pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
