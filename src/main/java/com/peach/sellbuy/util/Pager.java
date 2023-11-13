package com.peach.sellbuy.util;

import java.util.LinkedList;
import java.util.List;

public class Pager<T> {
    private LinkedList<T> dataList;
    private int itemsPerPage;

    public Pager(LinkedList<T> dataList) {
        this.dataList = dataList;
        this.itemsPerPage = 10;
    }

    public Pager(LinkedList<T> dataList, int itemsPerPage) {
        this.dataList = dataList;
        this.itemsPerPage = itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public List<T> getPage(int page) {
        if (page >= 1 && this.itemsPerPage >= 1) {
            int startIdx = (page - 1) * this.itemsPerPage;
            int endIdx = Math.min(startIdx + this.itemsPerPage, this.dataList.size());
            return (List)(startIdx >= this.dataList.size() ? new LinkedList() : this.dataList.subList(startIdx, endIdx));
        } else {
            throw new IllegalArgumentException("Page and items per page must be positive integers.");
        }
    }

    public int getNumberOfPages() {
        return (int)Math.ceil((double)this.dataList.size() / (double)this.itemsPerPage);
    }
}
