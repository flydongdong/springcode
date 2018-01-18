package com.fly.dd.mycode.common.utils;

import java.io.Serializable;

/**
 * 通用分页类
 *
 * @author zyd
 */

public class Pagenation implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 当前页号
     */
    private int pageNum;
    /**
     * 记录总数
     */
    private int rowTotalNum;

    /**
     * 页面显示记录的个数
     */
    private int pageSize;
    /**
     * 总页数
     *
     */
    private int pageCount;
    /**
     * 本页起始记录
     */
    private int startRow;
    /*
     * 页号导航
     */

    /**
     * 第一页
     */
    private int first;
    /**
     * 最后一页
     */
    private int last;
    /**
     * 上一页
     */
    private int previous;
    /**
     * 下一页
     */
    private int next;
    /**
     * 导航起始页号
     */
    private int startNav;
    /**
     * 导航结束页号
     */
    private int endNav;
    /**
     * 网页式导航,最多显示页号数量为numCount+1;这是显示11页.
     */
    private int navCount = 10;

    /**
     * 每页默认显示10条
     */
    private static final int DEFAULT_PAGE_SIZE = 10;
    /**
     * 默认第几页
     */
    private static final int DEFAULT_PAGE_NUM = 1;

    public Pagenation(String str_pageNum, int rowTotalNum, int pageSize) {

        this.pageNum = (str_pageNum == "") ? 1 : (Integer.parseInt(str_pageNum));
        this.rowTotalNum = rowTotalNum;
        this.pageSize = pageSize;

        this.startRow = (this.pageNum - 1) * pageSize;// 起始记录
        this.pageCount = (int) Math.ceil(this.rowTotalNum / 10.0);// 总页数
        this.pageNum = Math.min(this.pageNum, pageCount);
        this.pageNum = Math.max(1, this.pageNum);

        this.first = 1;
        this.last = this.pageCount;

        this.previous = this.pageNum - 1;
        this.previous = Math.max(1, this.pageNum - 1);
        this.next = Math.min(this.pageCount, this.pageNum + 1);

        // 导航处理
        this.startNav = (this.pageNum - navCount / 2 < 1) ? 1 : this.pageNum - navCount / 2;
        this.endNav = this.startNav + this.navCount;
        this.endNav = Math.min(this.endNav, this.pageCount);

        /*
         * if (this.endNav-this.startNav<navCount) { this.startNav = Math.max(this.endNav-this.navCount, 1); }
         */
    }

    /**
     * 初始化属性
     *
     * @param rowTotalNum
     *            总记录数
     */
    public void initalize(int rowTotalNum) {
        this.setRowTotalNum(rowTotalNum);

        this.setPageSize(this.getPageSize() == 0 ? DEFAULT_PAGE_SIZE : this.getPageSize());
        this.setPageCount((int) Math.ceil(this.getRowTotalNum() / (this.getPageSize()* 1.0)) );// 总页数;
        this.setPageNum(Math.min(this.getPageNum(), this.getPageCount()));
        this.setPageNum(Math.max(DEFAULT_PAGE_NUM, this.getPageNum()));
        this.setStartRow((this.getPageNum() - 1) * this.getPageSize());// 起始记录

        this.setFirst(1);
        this.setLast(this.getPageCount());

        this.setPrevious(this.getPageNum() - 1);
        this.setPrevious(Math.max(1, this.getPageNum() - 1));
        this.setNext(Math.min(this.getPageCount(), this.getPageNum() + 1));

        // 导航处理
        this.setStartNav(
                (this.getPageNum() - this.getNavCount() / 2 < 1) ? 1 : this.getPageNum() - this.getNavCount() / 2);
        this.setEndNav(this.getStartNav() + this.getNavCount());
        this.setEndNav(Math.min(this.getEndNav(), this.getPageCount()));
    }

    /**
     * 初始化
     */
    public Pagenation() {
        this.setPageSize(DEFAULT_PAGE_SIZE);
        this.setPageNum(DEFAULT_PAGE_NUM);
    }


    /**
     * 当前页号
     *
     * @return
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * 设置当前页号
     *
     * @param pageNum
     */
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * 记录总数
     *
     * @return
     */
    public int getRowTotalNum() {
        return rowTotalNum;
    }

    /**
     * 设置记录总数
     *
     * @param rowTotalNum
     */
    public void setRowTotalNum(int rowTotalNum) {
        this.rowTotalNum = rowTotalNum;
    }

    /**
     * 本页起始记录
     *
     * @return
     */
    public int getStartRow() {
        return startRow;
    }

    /**
     * 设置本页开始记录数
     *
     * @param startRow
     */
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    /**
     *
     * 获取总页数
     *
     * @return
     */
    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int getPrevious() {
        return previous;
    }

    public void setPrevious(int previous) {
        this.previous = previous;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getStartNav() {
        return startNav;
    }

    public void setStartNav(int startNav) {
        this.startNav = startNav;
    }

    public int getEndNav() {
        return endNav;
    }

    public void setEndNav(int endNav) {
        this.endNav = endNav;
    }

    public int getNavCount() {
        return navCount;
    }

    public void setNavCount(int navCount) {
        this.navCount = navCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "Pagenation [pageNum=" + pageNum + ", rowTotalNum=" + rowTotalNum + ", pageSize=" + pageSize
                + ", pageCount=" + pageCount + ", startRow=" + startRow + ", first=" + first + ", last=" + last
                + ", previous=" + previous + ", next=" + next + ", startNav=" + startNav + ", endNav=" + endNav
                + ", navCount=" + navCount + "]";
    }

}
