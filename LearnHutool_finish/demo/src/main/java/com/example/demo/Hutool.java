package com.example.demo;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

public class Hutool {
    public static void main(String[] args) throws IOException {

        //-------------------------------------转换类-----------------------------
        // Convert 转换各种数据类型
        int a=1;
        String aStr = Convert.toStr(a);

        String[] b = {"1", "2"};
        Integer[] bInt = Convert.toIntArray(b);

        String dateStr = "2022-01-01";
        Date date = Convert.toDate(dateStr);

        String[] strArr = {"a", "b"};
        List<String> strList = Convert.toList(String.class, strArr);

        // JSONUtil
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("Apple");
        brand.setStatus(1);
        // 对象转化为json字符串
        String jsonStr = JSONUtil.parse(brand).toString();
        System.out.println(jsonStr);    //{"name":"Apple","id":1,"status":1}
        // json字符串转化为对象
        Brand brandBean = JSONUtil.toBean(jsonStr, Brand.class);
        System.out.println(brandBean);   // Brand(id=1, name=Apple, status=1)
        // json字符串和列表互相转换
        List<Brand> brandList = new ArrayList<>();
        brandList.add(brand);
        String jsonListStr = JSONUtil.parse(brandList).toString();
        brandList = JSONUtil.toList(new JSONArray(jsonListStr), Brand.class);
        System.out.println(brandList);  // [Brand(id=1, name=Apple, status=1)]

        // BeanUtil Map和JavaBean对象的互相转换
        Map<String, Object> map = BeanUtil.beanToMap(brand);  // Bean转Map
        System.out.println(map);  // {id=1, name=Apple, status=1}
        Brand brand2 = BeanUtil.toBean(map, Brand.class);     // Map转Bean
        System.out.println(brand2);  // Brand(id=1, name=Apple, status=1)

        //-------------------------------------基本数据类型类-----------------------------
        // StrUtil
        StrUtil.isEmpty("test");
        StrUtil.removeSuffix("apple.jpg", ".jpg"); // 去除后缀
        String sample = StrUtil.format("This is {}", "placeholder");
        System.out.println(sample);  // This is placeholder

        // NumberUtil
        double n1 = 1.12;
        double n2 = 2.23;
        double result = NumberUtil.add(n1, n2);
        BigDecimal roundNum = NumberUtil.round(n1, 1);  // 保留一位小数
        System.out.println(roundNum);  // 1.1
        String n3 = "1.243";
        NumberUtil.isNumber(n3);  // 判断字符串
        NumberUtil.isDouble(n3);

        // CollUtil
        // 数组和列表的转换
        String[] array = new String[]{"a", "b", "c"};
        List<String> list = CollUtil.newArrayList(array);
        // join 数组转字符串时添加连接符号
        String joinStr = CollUtil.join(list, ",");
        System.out.println(joinStr);   // a, b, c
        // split 字符串再转为列表
        List<String> listSplit = StrUtil.split(joinStr, ',');  // 此处是char
        System.out.println(listSplit);

        // MapUtil
        Map<Object, Object> map1 = MapUtil.of(new String[][]{{"k1", "v1"}, {"k2", "v2"}, {"k3", "v3"}});
        map1.isEmpty();

        //-------------------------------------工具类-----------------------------

        // ClassPathResource 获取在src/main/resources下的文件
        ClassPathResource resource = new ClassPathResource("application.properties");
        Properties properties = new Properties();
        properties.load(resource.getStream());
        System.out.println(properties);  // {my_favourite="pupu"}

        // RefectUtil 反射获取类的方法及创建对象
        Method[] methods = ReflectUtil.getMethods(Brand.class);
        Method method = ReflectUtil.getMethod(Brand.class, "getId");
        Brand brand1 = ReflectUtil.newInstance(Brand.class);  // 使用反射创建对象
        ReflectUtil.invoke(brand1, "setId", 1L);
        System.out.println(brand1);  // Brand(id=1, name=null, status=0)

        // HttpUtil
        String response = HttpUtil.get("http://localhost:8080/hello");

        // Validator 字段校验器
        boolean ret2;
        ret2 = Validator.isEmail("11@qq.com");
        ret2 = Validator.isIpv4("1.1.1.1");
        ret2 = Validator.isBirthday("2022-02-31");  // false

        // AnnotationUtil 用于获取注解和注解中指定的值

        //-------------------------------------时间类-----------------------------
        // DateUtil Date, long, Canlendar互相转换
        // 获得现在的时间 Date
        Date date1 = DateUtil.date();
        // 转换成Calender（Calender可以运算，如date+1，Date不行)
        date = DateUtil.date(Calendar.getInstance());
        // 时间戳转date
        date = DateUtil.date(System.currentTimeMillis());

        //-------------------------------------加密类-----------------------------

        // SecureUtil 可用于md5加密
        String str = "12345";
        String md5Str = SecureUtil.md5(str);
        System.out.println(md5Str);  // 827ccb0eea8a706c4c34a16891f84e7b

        // BCrypt 可用于BCrypt加密和校验，SpringSecurity默认采用这种
        String str1 = "12345";
        String bCryptStr = BCrypt.hashpw(str1);
        System.out.println(bCryptStr);  // $2a$10$QolCvFuq3aZwTYHIdbQtzeV7lPD2ScO5OXk6oEHnzhgJS.m28e/c6
        // BCrypt校验
        boolean ret = BCrypt.checkpw(str1, bCryptStr);
        System.out.println(ret);  // true

        // CaptchaUtil 用于生成图形验证码

        // DigestUtil 摘要算法工具，可用于MD5, SHA-256, BCrypt等
        String password = "123456";
        String ret1;
        ret1 = DigestUtil.md5Hex(password);  // 计算MD5摘要值，并转为16进制字符串
        ret1 = DigestUtil.sha256Hex(password);
        ret1 = DigestUtil.bcrypt(password);
        boolean check = DigestUtil.bcryptCheck(password, ret1);
    }
}
