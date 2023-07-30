package com.wmpn.service;

import com.wmpn.entity.Brand;
import java.util.List;
public interface BrandService {

    /**
     * 查詢所有的品牌
     * @return
     */

    List<Brand> queryAllbrand();
}
