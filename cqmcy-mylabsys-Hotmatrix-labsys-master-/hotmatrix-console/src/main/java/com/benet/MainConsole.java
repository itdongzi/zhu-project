package com.benet;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author yoxking
 */
@SpringBootApplication(scanBasePackages = "com.benet",exclude = { DataSourceAutoConfiguration.class })
@MapperScan(annotationClass = Mapper.class,basePackages = "com.benet")
public class MainConsole {

    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(MainConsole.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  热度空间-控制台启动成功   ლ(´ڡ`ლ)ﾞ  \n" );
    }
}
