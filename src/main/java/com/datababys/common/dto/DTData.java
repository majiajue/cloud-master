package com.datababys.common.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

/**
 * DataTable返回数据
 * 
 * @author ycitss
 *
 * @param <T>
 */
public class DTData<T> {
    // Draw counter.
    private int draw;
    // Total records, before filtering.
    private int recordsTotal;
    // Total records, after filtering.
    private int recordsFiltered;
    // The data to be displayed in the table.
    private List<T> data = new ArrayList<T>();
    // error message
    private String error;

    /**
     * 无参构造方法
     */
    public DTData() {
    }

    /**
     * 构造方法初始化数据
     *
     * @param dataPage
     * @param pager
     */
    public DTData(Page<T> dataPage, DTPager pager) {
        this.data = dataPage.getContent();
        if (this.data != null) {
            this.recordsTotal = (int) dataPage.getTotalElements();
            this.recordsFiltered = (int) dataPage.getTotalElements();
        }
        this.draw = pager.getDraw();
    }

    /**
     * @return the draw
     */
    public int getDraw() {
        return draw;
    }

    /**
     * @param draw the draw to set
     */
    public void setDraw(int draw) {
        this.draw = draw;
    }

    /**
     * @return the recordsTotal
     */
    public int getRecordsTotal() {
        return recordsTotal;
    }

    /**
     * @param recordsTotal the recordsTotal to set
     */
    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    /**
     * @return the recordsFiltered
     */
    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    /**
     * @param recordsFiltered the recordsFiltered to set
     */
    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    /**
     * @return the data
     */
    public List<T> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(List<T> data) {
        this.data = data;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

}
