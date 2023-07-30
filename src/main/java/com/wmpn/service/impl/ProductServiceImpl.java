package com.wmpn.service.impl;

import com.wmpn.entity.Product;
import com.wmpn.entity.Result;
import com.wmpn.mapper.ProductMapper;
import com.wmpn.page.Page;
import com.wmpn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    //上傳圖片的訪問路徑
    @Value("${file.access-path}")
    private String fileAccessPath;

    @Override
    public Page queryProguctListPage(Page page, Product product) {
        Integer productRowCount = productMapper.findProductRowCount(product);
        List<Product> productAllPage = productMapper.findProductAllPage(page, product);
        page.setTotalNum(productRowCount);
        page.setResultList(productAllPage);
        return page;
    }

    @Override
    public Result insertProduct(Product product) {
        Product productByNum = productMapper.findProductByNum(product.getProductNum());
        if (productByNum != null) {
            return Result.err(Result.CODE_ERR_BUSINESS, "商品型號已存在");
        }
        //處理上傳圖片的訪問路徑
        product.setImgs(fileAccessPath + product.getImgs());
        int i = productMapper.insertProduct(product);
        if(i>0){
            return Result.ok("商品添加成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"商品添加失敗");
    }

    @Override
    public Result setProductState(Product product) {
        int i = productMapper.setProductStateByPid(product);
        if(i>0){
            return Result.ok("商品狀態修改成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"商品狀態修改失敗");
    }

    @Override
    public Result removeProductByIds(List<Integer> productIds) {
        int i = productMapper.removeProductByPids(productIds);
        if(i>0){
            return Result.ok("商品刪除成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"商品刪除失敗");
    }

    @Override
    public Result setProductById(Product product) {
        Product productByNum = productMapper.findProductByNum(product.getProductNum());
        if(productByNum != null && !product.getProductId().equals(productByNum.getProductId())){
            return Result.err(Result.CODE_ERR_BUSINESS,"修改的商品型號已存在");
        }
        if(!product.getImgs().contains(fileAccessPath)){
            product.setImgs(fileAccessPath+product.getImgs());
        }
        int i = productMapper.setProductById(product);
        if(i>0){
            return Result.ok("商品修改成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"商品修改失敗");
    }
}
