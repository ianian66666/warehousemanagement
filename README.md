# warehousemanagement  
倉庫管理系統 - 前後端分離項目(借鑑別人項目)    
前端:html、css、vue、javascript    
後端：java、springboot、mybatis  
登入驗證：jwt         
資料庫：mysql、redis  

項目功能：  
1.進行登入校驗header若無攜帶token(jwt)，會被filter攔截並返回登入頁面，並將token放入redis中，每次跟redis驗證token是否存在，減少大量解析token的效能，redis儲存token2小時過期。  
2.權限控管，使用菜單樹的方式，菜單樹會依照帳號當前權限展開菜單樹。  
3.角色控管(分配角色ex:管理者、商品管理者、倉庫管理者)：可自行創建角色，並賦予個別角色權限，再將角色賦予給使用者。  
4.實作驗證碼機制，阻止非人類的攻擊行為，並將驗證碼放入redis中，五分鐘過期一次。  
5.redis儲存不常更新的資料減少與sql之間的查詢： ex: 商品分類頁、角色列表等都會緩存進redis中，若有更新會同時更新redis內容。  
6.透過aop實作實現增刪改的log紀錄，並寫入資料庫MySQL中。(自己增加功能)    
7.透過aop將當前使用者的id從token解析出來，並使用ThreadLocal，將使用者的id放入當前線程中實現常用字段的自動填充(因使用者id在幾乎需要增刪改的表都會有修改人或創建人字段，可透過此aop統一將entity類set填充所需字段)(自己增加功能)。   
8.業務流程分析：  
   (1)使用者可自行新增商品，可自行上架下架，並選擇是要採購還是出庫。  
   (2)採購需填寫採購單，填寫完後會到採購列表，在採購列表需填寫實際採購數量，填寫完畢後，需在當前頁生成入庫單。  
   (3)當商品入庫完成後，需到入庫列表操作入庫。  
   (4)入庫完畢後，商品的數量會變動。  
   (5)出庫的流程與上述相同。
   

畫面展示：    

![image](https://github.com/ianian66666/warehousemanagement/blob/master/%E6%88%AA%E5%9C%96%202023-08-02%20%E4%B8%8B%E5%8D%886.47.04.png)
![image](https://github.com/ianian66666/warehousemanagement/blob/master/%E6%88%AA%E5%9C%96%202023-08-02%20%E4%B8%8B%E5%8D%886.52.18.png)
![image](https://github.com/ianian66666/warehousemanagement/blob/master/%E6%88%AA%E5%9C%96%202023-08-02%20%E4%B8%8B%E5%8D%886.52.46.png)
![image](https://github.com/ianian66666/warehousemanagement/blob/master/%E6%88%AA%E5%9C%96%202023-08-02%20%E4%B8%8B%E5%8D%887.14.25.png)
![image](https://github.com/ianian66666/warehousemanagement/blob/master/%E6%88%AA%E5%9C%96%202023-08-02%20%E4%B8%8B%E5%8D%886.59.25.png)
![image](https://github.com/ianian66666/warehousemanagement/blob/master/%E6%88%AA%E5%9C%96%202023-08-02%20%E4%B8%8B%E5%8D%888.23.52.png)
![image](https://github.com/ianian66666/warehousemanagement/blob/master/%E6%88%AA%E5%9C%96%202023-08-02%20%E4%B8%8B%E5%8D%888.27.32.png)
![image](https://github.com/ianian66666/warehousemanagement/blob/master/%E6%88%AA%E5%9C%96%202023-08-02%20%E4%B8%8B%E5%8D%887.01.22.png)
![image](https://github.com/ianian66666/warehousemanagement/blob/master/%E6%88%AA%E5%9C%96%202023-08-02%20%E4%B8%8B%E5%8D%886.52.56.png)
![image](https://github.com/ianian66666/warehousemanagement/blob/master/%E6%88%AA%E5%9C%96%202023-08-02%20%E4%B8%8B%E5%8D%888.01.03.png)


