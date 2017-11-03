package com.xinho.bean;

public class Page {
    // 总条数
    private int totalRecord;
    // 当前页数
    private int currentPage;
    // 总页数
    private int sumPageNum;
    // 每页显示条数
    private int pageSize;

    public Page() {
        this.currentPage = 1;
        this.pageSize = 10;
    }

    public int getTotalNumber() {
        return totalRecord;
    }

    private void count() {
        this.sumPageNum = this.totalRecord / this.pageSize;
        if(this.totalRecord % this.pageSize > 0) {
            this.sumPageNum++;
        }
        if(this.sumPageNum <= 0) {
            this.sumPageNum = 1;
        }
        if(this.currentPage > this.sumPageNum) {
            this.currentPage = this.sumPageNum;
        }
        if(this.currentPage <= 0) {
            this.currentPage = 1;
        }
    }

    public void setTotalNumber(int totalNumber) {
        this.totalRecord = totalNumber;
        this.count();
    }

    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getSumPageNum() {

        return sumPageNum;
    }
    public void setSumPageNum(int sumPageNum) {
        this.sumPageNum = sumPageNum;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
