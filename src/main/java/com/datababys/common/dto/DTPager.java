package com.datababys.common.dto;

/**
 * DataTable分页参数
 * 
 * @author shishun
 *
 */
public class DTPager {
    // Draw counter.
    private int draw;
    // Paging first record indicator.
    private int start;
    // Number of records that the table can display in the current draw.
    private int length;

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
     * @return the start
     */
    public int getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DTPager [draw=" + draw + ", start=" + start + ", length=" + length + "]";
    }

}
