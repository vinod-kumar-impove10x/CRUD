package com.improve10X.crud.series;

public interface OnItemActionListener {

    void onItemClicked(SeriesItem seriesItem);
    void onItemDelete(SeriesItem seriesItem);
    void onItemEdit(SeriesItem seriesItem);

}
