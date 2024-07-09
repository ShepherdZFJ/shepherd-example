package com.shepherd.basedemo.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.shepherd.basedemo.entity.Account;
import com.shepherd.basedemo.entity.User;
import com.shepherd.basedemo.excel.listener.AccountExcelListener;
import com.shepherd.basedemo.excel.listener.BaseExcelListener;
import com.shepherd.basedemo.excel.listener.HeadExcelListener;
import com.shepherd.basedemo.excel.listener.UserExcelListener;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.jvm.hotspot.debugger.Page;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/11 18:04
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ExcelTest {

    @Test
    public void testExcelRead() {
        String fileName = "/Users/shepherdmy/Desktop/111.xlsx";
        EasyExcel.read(fileName, new BaseExcelListener(dataList -> System.out.println(dataList))).headRowNumber(1).doReadAll();
        // 读取用户信息两个sheet
//        EasyExcel.read(fileName, User.class, new UserExcelListener()).sheet(0).doRead();
//        EasyExcel.read(fileName, User.class, new UserExcelListener()).sheet(1).doRead();
        // 读取银行账户信息
//        EasyExcel.read(fileName, Account.class, new HeadExcelListener()).sheet(2).doRead();
//        EasyExcel.read(fileName, Account.class, new AccountExcelListener()).sheet(2).doRead();
        // 如果excel是多行表头比如说2行，需要设置行头数headRowNumber，默认不设置为1行表头，sheet不传默认读取第一个sheet
//        EasyExcel.read(fileName, User.class, new UserExcelListener()).sheet().headRowNumber(2).doRead();

        // 读取所有sheet
//        EasyExcel.read(fileName, User.class, new UserExcelListener()).doReadAll();
    }

    @Test
    public void testExcelWrite() {
        List<User> userList = new ArrayList<>();
        int i = 0;
        while (i < 10) {
            User user = User.builder().id((long)i).userNo("no-" + i).birthday(LocalDate.now()).gender(i%3)
                    .phone("123456789"+i).email("she12dfe@163.com").name("芽儿哟"+i).address("杭州"+i).build();
            userList.add(user);
            i++;
        }
        String fileName = "export.xlsx";
//        EasyExcel.write(fileName, User.class).sheet("员工信息").doWrite(userList);

        // 导出大批量数据，我们不能一次查询所有数据到内存中，只能分页查询导出，这里UserList假设需要分页导出，每页查询3条
        int size = userList.size();
        int page = size/ 3 + (size % 3 == 0 ? 0 : 1);
//        // 创建excelWriter
//        ExcelWriter excelWriter = EasyExcel.write(fileName, User.class).build();
//        // 创建writeSheet
//        WriteSheet writeSheet = EasyExcel.writerSheet(0,"分页员工信息").build();
//        for (int k = 0; k < page; k++) {
//            int start = k * 3;
//            int end = (k + 1) * 3 >= size ? size : (k + 1) * 3;
//            // 这里其实应该是分页查询数据，下面只是简单模拟下
//            List<User> users = userList.subList(start, end);
//            excelWriter.write(users, writeSheet);
//        }
//        // 关闭io才会写入数据，必须执行，所以最好用try-with-source安全，如下
//        excelWriter.finish();

        // 官方安全写法
        try (ExcelWriter excelWriter = EasyExcel.write(fileName, User.class).build()) {
            // 这里注意 如果同一个sheet只要创建一次
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            // 去调用写入,实际使用时根据数据库分页的总的页数来
            for (int k = 0; k < page; k++) {
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                int start = k * 3;
                int end = (k + 1) * 3 >= size ? size : (k + 1) * 3;
                List<User> users = userList.subList(start, end);
                excelWriter.write(users, writeSheet);
            }
        }
    }

    @Test
    public void testExport() {
        List<String> heads = ListUtils.newArrayList("员工编号", "姓名", "性别", "金额", "基本信息,手机号", "基本信息,邮箱");
        List<List<String>> dataList = new ArrayList<>();
        List<List<String>> headList = new ArrayList<>();
        headList.add(ListUtils.newArrayList("员工编号"));
        headList.add(ListUtils.newArrayList("姓名"));
        headList.add(ListUtils.newArrayList("性别"));
        headList.add(ListUtils.newArrayList("金额"));
        headList.add(ListUtils.newArrayList("基本信息，手机号"));
        headList.add(ListUtils.newArrayList("基本信息，邮箱"));
        int i = 0;
        while (i < 10) {
            i++;
            dataList.add(ListUtils.newArrayList(String.valueOf(i), "王"+i, i%2 == 0 ? "男" : "女", "666.88", "1234456", "she345456@126.com"));
        }

        String fileName = "export.xlsx";
        EasyExcel.write(fileName).sheet("员工").head(headList).doWrite(dataList);

    }

    @Test
    public void testExcelBaselRead() {
        String fileName = "/Users/shepherdmy/Desktop/testExcel.xlsx";
        EasyExcel.read(fileName, User.class, new BaseExcelListener((dataList)->handleUser((List<User>) dataList))).sheet(0).headRowNumber(1).doRead();

    }

    void handleUser(List<User> users) {
        System.out.println(users.size());
        System.out.println("users:" + users);

    }

    @Test
    public void testExportCSV() {
        List<User> userList = new ArrayList<>();
        int i = 0;
        while (i < 10) {
            User user = User.builder().id((long)i).userNo("no-" + i).birthday(LocalDate.now()).gender(i%3)
                    .phone("123456789"+i).email("she12dfe@163.com").name("芽儿哟"+i).address("杭州"+i).build();
            userList.add(user);
            i++;
        }
        String fileName = "example.csv";
        EasyExcel.write(fileName).excelType(ExcelTypeEnum.CSV).sheet("员工").doWrite(userList);
    }

    @Test
    public void testReadCSV() {
        String fileName = "example.csv";
        EasyExcel.read(fileName, User.class, new UserExcelListener()).headRowNumber(0).doReadAll();

    }
}


