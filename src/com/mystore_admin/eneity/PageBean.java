package com.mystore_admin.eneity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PageBean {
    private Integer current;
    private Integer pageCount;
    private Integer allCounts;
    private List<Goods> pageGoodsList = new ArrayList<>();
}
