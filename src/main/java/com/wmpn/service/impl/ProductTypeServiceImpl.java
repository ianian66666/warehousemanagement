package com.wmpn.service.impl;

import com.wmpn.entity.ProductType;
import com.wmpn.entity.Result;
import com.wmpn.mapper.ProductTypeMapper;
import com.wmpn.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
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

    @Override
    public Result checkTypeCode(String typeCode) {
        ProductType p  = new ProductType();
        p.setTypeCode(typeCode);
        ProductType prodType = productTypeMapper.findProductTypeBycodeorName(p);
        return  Result.ok(prodType == null);


    }

    @CacheEvict(key ="'all:typeTree'")
    @Override
    public Result saveProductType(ProductType productType) {
        ProductType p = new ProductType();
        p.setTypeName(productType.getTypeName());
        //校驗分類名稱是否存在
        ProductType productTypeName = productTypeMapper.findProductTypeBycodeorName(p);
        if(productTypeName != null){
                return  Result.err(Result.CODE_ERR_BUSINESS,"分類名稱已存在");
        }
        int i = productTypeMapper.insertProductType(productType);
        if(i>0){
            return Result.ok("商品分類添加成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"商品分類添加失敗");
    }
    @CacheEvict(key ="'all:typeTree'")
    @Override
    public Result removeProductType(Integer typeId) {

        int i = productTypeMapper.removeProductType(typeId);
        if(i>0){
            return  Result.ok("商品分類刪除成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"商品分類刪除失敗");
    }
    @CacheEvict(key ="'all:typeTree'")
    @Override
    public Result updateproduct(ProductType productType) {
        ProductType p = new ProductType();
        p.setTypeName(productType.getTypeName());
        ProductType productTypeByName = productTypeMapper.findProductTypeBycodeorName(p);
        if(productTypeByName != null && !productTypeByName.getTypeId().equals(productType.getTypeId())) return Result.err(Result.CODE_ERR_BUSINESS,"商品分類名稱已重複");
        int i = productTypeMapper.updateProductType(productType);
        if (i > 0) {

            return Result.ok("商品分類修改成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"商品分類修改失敗");
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
