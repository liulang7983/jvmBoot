package com.controller;

import com.bean.CheckInvoice;
import com.bean.User;
import com.util.FileUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author ming.li
 * @date 2023/6/1 16:36
 */
@RestController
@RequestMapping("file")
public class FileController {

    @RequestMapping("/getCheckInvoice")
    public CheckInvoice getCheckInvoice(){
        //String str="{\"发票名称\":\"二手车销售统一发票\",\"发票类型\":\"二手车销售统一发票\",\"开票日期\":\"2019-04-28\",\"发票代码\":\"051001900117\",\"发票号码\":\"00273401\",\"买方单位/个人\":\"成都柏翠文化传播有限公司\",\"买方单位/个人住址\":\"成都高新区天仁路222号1幢2单元6层40号\",\"买方单位代码/身份证号码\":\"91510100MA6DE1HW95\",\"买方电话\":\"\",\"卖方单位/个人\":\"张宗玉\",\"卖方单位/个人住址\":\"成都市武侯区科华北路151号1栋2单元5楼2号\",\"卖方单位代码/身份证号码\":\"510102195409258447\",\"卖方电话\":\"\",\"车牌照号\":\"川AZ9W25\",\"车架号/车辆识别代码\":\"LBVPS5109BSD44566\",\"车价合计（大写）\":\"壹拾伍万圆整\",\"车价合计（小写）\":\"¥150000.00\",\"二手车市场\":\"四川农华二手汽车市场经营管理有限公司\",\"二手车市场纳税人识别号\":\"91510000680432551J\",\"二手车市场地址\":\"成都市高新区九兴大道9号\",\"二手车市场开户银行、账号\":\"平安银行金牛支行11009440962702\",\"二手车市场电话\":\"028-85146687\",\"车辆类型\":\"小型轿车(1.5升至2.0升(含))\",\"登记证号\":\"510011182339\",\"厂牌型号\":\"宝马牌\",\"省\":\"四川省\",\"市\":\"\",\"发票消费类型\":\"用车\",\"拍卖单位纳税人识别号\":\"拍卖单位纳税人识别号\",\"二维码\":\"1\",\"备注\":\"\",\"转入地车辆管理所名称\":\"成都市\"}";
        String str="{\"发票名称\":\"机动车销售统一发票\",\"发票代码\":\"132001822364\",\"发票号码\":\"00542371\",\"主管税务机关\":\"国家税务总局昆山市税务局\",\"主管税务机关代码\":\"132058300\",\"产地\":\"河北省\",\"不含税价(小写)\":\"¥45929.20\",\"价税合计\":\"伍万壹仟玖佰圆整\",\"价税合计(小写)\":\"¥51900.00\",\"增值税税率或征收率\":\"13%\",\"厂牌型号\":\"长安牌SC1035SGC5\",\"发动机号码\":\"191002105\",\"发票消费类型\":\"用车\",\"合格证号\":\"WAW09120K012092\",\"吨位\":\"1\",\"增值税税额\":\"¥5970.80\",\"开票人\":\"叶小花\",\"开票日期\":\"2019-04-01\",\"机器编号\":\"661616378501\",\"机打代码\":\"132001822364\",\"机打号码\":\"00542371\",\"购买方名称\":\"苏州昆微真空科技有限公司\",\"身份证号码/组织机构代码\":\"91320585MA1XNDF80P\",\"购买方纳税人识别号\":\"91320585MA1XNDF80P\",\"车辆类型\":\"轻型普通货车\",\"车辆识别代号/车架号码\":\"LSCBB22E0KG655055\",\"销售方地址\":\"昆山市巴城镇东部工业区\",\"销售方开户银行\":\"昆山农村商业银行巴城支行7066500331120100255872\",\"销售方电话\":\"0512-57655988\",\"销售方纳税人识别号\":\"913205837546008467\",\"销售方账号\":\"昆山农村商业银行巴城支行67066500331120100\",\"销货单位名称\":\"昆山市福海汽车销售服务有限公司\",\"省\":\"江苏省\",\"市\":\"\",\"限乘人数\":\"2+3\",\"进口证明书号\":\"\",\"商检单号\":\"\",\"备注\":\"一车一票\",\"发票类型\":\"机动车销售统一发票\",\"二维码\":\"1\"}";
        CheckInvoice catByItems = FileUtil.getCatByItems(str);
        return catByItems;
    }

    @RequestMapping("/getChange")
    public Map getChange(){
        Map change = FileUtil.getChange();
        return change;
    }
    @RequestMapping("/getUser")
    public String getUser(@RequestBody User user){
        Long id = user.getId();
        System.out.println(id);
        return "成功";
    }

    @RequestMapping("/getUser1")
    public String getUser1(Integer id,String name){
        String absolutePath = new ApplicationHome(getClass()).getSource().getParentFile().getAbsolutePath();
        System.out.println("包所在目录:"+absolutePath);
        System.out.println(id);
        return "成功";
    }

    @RequestMapping("/ofdToPdf")
    public String ofdToPdf(){
        URL resource = FileController.class.getClassLoader().getResource("file");
        if (resource!=null){
            String path = resource.getPath();
            File[] files = new File(path).listFiles();
            for (File file:files){
                System.out.println(file.getName());
            }

        }
        return "成功";
    }

    /*@RequestMapping("/getFile/{fileName}")
    public void getFile(@PathVariable("fileName")String fileName, HttpServletResponse response){
        try{
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8").replace("+", "%20");

            File zipFile = new File("D:\\李明"+File.separator+fileName);

            byte[] data = Files.readAllBytes(zipFile.toPath());
            OutputStream out = response.getOutputStream();
            response.reset();
            //response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName).getBytes(), "UTF-8"));
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            response.setContentType("application/octet-stream");
            IOUtils.write(data, out);
            out.flush();
            out.close();
        } catch (Exception e) {
        }
    }*/

    @RequestMapping("/getFile/{fileName}")
    public void getFile(@PathVariable("fileName")String fileName, HttpServletResponse response){
        try{
            String path="D:\\李明"+File.separator+fileName;
            InputStream in = new FileInputStream(path);
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            OutputStream out = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            in.close();
            out.close();
        } catch (Exception e) {
        }
    }
}
