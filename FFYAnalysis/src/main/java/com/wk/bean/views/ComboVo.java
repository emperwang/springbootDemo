package com.wk.bean.views;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class ComboVo implements Serializable{
    Integer id;
    String text;
}
