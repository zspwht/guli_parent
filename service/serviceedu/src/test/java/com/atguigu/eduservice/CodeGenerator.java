package com.atguigu.eduservice;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;

public class CodeGenerator {
    @Test
    public void main1(){
        //创建代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        //全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        gc.setOutputDir(projectPath+"/src/main/java");
        gc.setAuthor("atguigu");
        gc.setOpen(false);//生成后是否打开资源管理器
        gc.setFileOverride(false); //重新生成时文件是否覆盖
        gc.setServiceName("%sService");  //去掉service的首字母I
        gc.setIdType(IdType.ID_WORKER);  //主键策略
        gc.setDateType(DateType.ONLY_DATE); // 定义生成的实体类中日期类型
        gc.setSwagger2(true);  //开启swagger2模式
        autoGenerator.setGlobalConfig(gc);
        //配置数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/guli_edu?serverTimezone=GMT%2B8");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456");
        dataSourceConfig.setDbType(DbType.MYSQL);
        autoGenerator.setDataSource(dataSourceConfig);
        //包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName("edu"); //模块名
        packageConfig.setParent("com.atguigu");
        packageConfig.setController("controller");
        packageConfig.setEntity("entity");
        packageConfig.setService("service");
        packageConfig.setMapper("mapper");
        autoGenerator.setPackageInfo(packageConfig);
        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude("edu_teacher");
        strategyConfig.setNaming(NamingStrategy.underline_to_camel); //数据库表映射到实体的命名策略
        System.out.println(packageConfig.getModuleName()+"_");
        strategyConfig.setTablePrefix(packageConfig.getModuleName()+"_"); //生成实体时去掉表前缀
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel); //数据库表字段映射到实体的命名策略
        strategyConfig.setEntityLombokModel(true); // lombok模型
        strategyConfig.setRestControllerStyle(true); //resetful api风格控制器
        strategyConfig.setControllerMappingHyphenStyle(true);  //url中驼峰转连字符
        autoGenerator.setStrategy(strategyConfig);
        //执行
        autoGenerator.execute();
    }
}
