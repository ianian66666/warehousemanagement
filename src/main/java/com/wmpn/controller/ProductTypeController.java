package com.wmpn.controller;

import com.wmpn.entity.ProductType;
import com.wmpn.entity.Result;
import com.wmpn.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productCategory")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping("/product-category-tree")
    public Result productCategoryTree(){
        List<ProductType> productTypes = productTypeService.queryProductTree();
        return Result.ok(productTypes);
    }

    /**
     * 校驗分類code是否重複
     * @param typeCode
     * @return
     */

    @GetMapping("/verify-type-code")
    public Result checkTypeCode(String typeCode){
        Result result = productTypeService.checkTypeCode(typeCode);
        return result;
    }

    /**
     * 添加商品分類
     * @param productType
     * @return
     */
    @PostMapping("/type-add")
    public Result addProductType(@RequestBody ProductType productType){

        Result result = productTypeService.saveProductType(productType);
        return result;
    }
    @DeleteMapping("/type-delete/{typeId}")
    public Result typeDeleteById(@PathVariable Integer typeId){
        Result result = productTypeService.removeProductType(typeId);
        return  result;
    }
    @PutMapping("/type-update")
    public Result setProductType(@RequestBody ProductType productType){
        Result result = productTypeService.updateproduct(productType);
        return result;
    }


}
