package com.wk.bean.views;

import com.wk.bean.Depentments;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Setter
@Getter
public class DepentmentVo extends Depentments implements Serializable{
    // 大部名字
    private String regionName;
}
