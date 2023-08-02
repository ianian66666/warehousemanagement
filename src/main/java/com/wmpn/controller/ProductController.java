package com.wmpn.controller;

import com.wmpn.entity.*;
import com.wmpn.mapper.ProductMapper;
import com.wmpn.page.Page;
import com.wmpn.service.*;
import com.wmpn.utils.TokenUtils;
import com.wmpn.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private SupplyService supplyService;
    @Autowired
    private PlaceService placeService;
    @Autowired
    private UnitService unitService;
    @Value("${file.upload-path}")
    private String uploadPath;
    @Autowired
    private TokenUtils tokenUtils;

    /**
     * 查詢所有倉庫
     */
    @GetMapping("/store-list")
    public Result storeList() {
        List<Store> allStore = storeService.findAllStore();
        return Result.ok(allStore);
    }

    @GetMapping("/brand-list")
    public Result BrandList() {
        List<Brand> brands = brandService.queryAllbrand();
        return Result.ok(brands);
    }

    /**
     * 商品的分頁查詢
     *
     * @param page
     * @param product
     * @return
     */
    @GetMapping("/product-page-list")
    public Result productListPage(Page page, Product product) {
        page = productService.queryProguctListPage(page, product);
        return Result.ok(page);


    }

    /**
     * 查詢商品分類樹
     *
     * @return
     */
    @GetMapping("/category-tree")
    public Result loadTypeTree() {
        List<ProductType> productTypes = productTypeService.queryProductTree();
        return Result.ok(productTypes);
    }

    /**
     * 查詢所有的供應商
     *
     * @return
     */
    @GetMapping("/supply-list")
    public Result supplyList() {
        List<Supply> supplies = supplyService.queryAllSupply();
        return Result.ok(supplies);
    }

    /**
     * 查詢所有的產地
     *
     * @return
     */
    @GetMapping("/place-list")
    public Result placeList() {
        List<Place> places = placeService.queryAllPlace();
        return Result.ok(places);
    }

    /**
     * 查詢所有單位
     *
     * @return
     */
    @GetMapping("/unit-list")
    public Result UnitList() {
        List<Unit> units = unitService.queryAllUnit();
        return Result.ok(units);
    }

    /**
     * 上傳圖片功能
     *
     * @param file
     * @return
     */
    @CrossOrigin//表示此api可以被跨域請求
    @RequestMapping("/img-upload")
    public Result uploadImg(MultipartFile file) {
        try {
            //拿到圖片上傳到的目錄路徑的file對像
            //因為圖片上傳到的目錄路徑是類路徑(resource下的路徑/編譯後：class下得路徑，或是帶有前綴的classpath)，
            // 所以不能直接封裝到file對象，使用工具類ResourceUtils的getFile來解析類路徑，並拿到封裝了類路徑的file對象
            File uploadDirFile = ResourceUtils.getFile(uploadPath);
            //拿到圖片上傳到的目錄的硬碟路徑
            String uploadDirPath = uploadDirFile.getAbsolutePath();
            //拿到上傳圖片名稱
            String filename = file.getOriginalFilename();
            //TODO
//            String suffix = filename.substring(filename.lastIndexOf("."));
//            String newFileName = UUID.randomUUID().toString() + suffix;
            //拿到上傳文件的整體路徑
            String uploadFilePath = uploadDirPath + "/" + filename;
            file.transferTo(new File(uploadFilePath));

            return Result.ok("圖片上傳成功");
        } catch (Exception e) {
            return Result.err(Result.CODE_ERR_BUSINESS, "圖片上傳失敗");
        }
    }

    /**
     * 添加商品
     *
     * @param product
     * @return
     */
    @PostMapping("/product-add")
    public Result addProduct(@RequestBody Product product) {
        Result result = productService.insertProduct(product);
        return result;

    }

    /**
     * 根據商品id更改商品狀態
     * @param product
     * @return
     */

    @PutMapping("/state-change")
    public Result setProductState(@RequestBody Product product) {
        Result result = productService.setProductState(product);
        return  result;
    }

    @DeleteMapping("/product-delete/{productId}")
    public Result deleteProductById(@PathVariable Integer productId){
        Result result = productService.removeProductByIds(Arrays.asList(productId));
        return result;

    }

    @DeleteMapping("/product-list-delete")
    public Result deleteproductByIdList(@RequestBody List<Integer> productIdList){
        Result result = productService.removeProductByIds(productIdList);
        return result;
    }
    @PutMapping("/product-update")
    public Result setProduct(@RequestBody Product product){
        Result result = productService.setProductById(product);
        return result;
    }

}
