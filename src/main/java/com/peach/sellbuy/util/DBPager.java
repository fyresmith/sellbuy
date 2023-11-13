//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.peach.sellbuy.util;

import com.peach.sellbuy.business.Access;

import java.util.LinkedList;
import java.util.List;

public class DBPager<T> {
    private Access<T> database;
    private int itemsPerPage;

    public DBPager(Access<T> database) {
        this.database = database;
        this.itemsPerPage = 10;
    }

    public DBPager(Access<T> database, int itemsPerPage) {
        this.database = database;
        this.itemsPerPage = itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public List<T> getPage(int page) {
        if (page >= 1 && this.itemsPerPage >= 1) {
            int startIdx = (page - 1) * this.itemsPerPage;
            int endIdx = Math.min(startIdx + this.itemsPerPage, this.database.size());
            return startIdx >= this.database.size() ? new LinkedList() : this.database.getRows(startIdx, endIdx);
        } else {
            throw new IllegalArgumentException("Page and items per page must be positive integers.");
        }
    }

    public int getNumberOfPages() {
        return (int)Math.ceil((double)this.database.size() / (double)this.itemsPerPage);
    }
}
