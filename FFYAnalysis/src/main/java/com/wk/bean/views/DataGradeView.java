package com.wk.bean.views;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class DataGradeView<T> implements Serializable {
    private int total;
    private List<T> rows;
}
