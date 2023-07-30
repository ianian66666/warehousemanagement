package com.wmpn.service.impl;

import com.wmpn.entity.ProductType;
import com.wmpn.mapper.ProductTypeMapper;
import com.wmpn.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@CacheConfig(cacheNames = "com.wmpn.service.impl.ProductTypeServiceImpl")
@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Cacheable(key = "'all:typeTree'")
    @Override
    public List<ProductType> queryProductTree() {
        List<ProductType> allProductTypeTree = productTypeMapper.findAllProductTypeTree();
        List<ProductType> productTypeListTree = allTypeToTypeTree(allProductTypeTree, 0);
        return productTypeListTree;
    }

    private List<ProductType> allTypeToTypeTree(List<ProductType> typeList, Integer pid) {
        List<ProductType> firstTypeTree = new ArrayList<>();
        for (ProductType productType : typeList) {
            if(productType.getParentId().equals(pid)){
                firstTypeTree.add(productType);
            }
        }
        for (ProductType productType : firstTypeTree) {
            List<ProductType> secondTypeTree = allTypeToTypeTree(typeList, productType.getTypeId());
            productType.setChildProductCategory(secondTypeTree);
        }
            return firstTypeTree;
    }
}
