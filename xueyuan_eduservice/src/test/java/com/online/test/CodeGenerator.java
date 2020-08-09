package com.online.test;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;

public class CodeGenerator {

    @Test
    public void run() {
// 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("dxq");
        gc.setOpen(false);
        gc.setFileOverride(false);
        gc.setIdType(IdType.ID_WORKER);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/xueyuan_edu?serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("admin");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("eduservice");
        pc.setParent("com.online.edu");
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");

        mpg.setPackageInfo(pc);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("edu_course","edu_course_description");

        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);


        mpg.setStrategy(strategy);
        mpg.execute();
    }
}


