package com.k79.tools.k79ReportGrid;

import com.k79.tools.k79ReportGrid.util.JarToolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.lang.reflect.Field;


@Slf4j
@SpringBootApplication
public class K79ReportGridApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(K79ReportGridApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        String jarDir = JarToolUtil.getSpringBootJarDir().replace("file:\\","");
//        log.info("当前jar包所在文件夹:" + jarDir);
//        String osName = System.getProperty("os.name");
//        log.info("当前操作系统类别:" + osName);
//        if (osName.equalsIgnoreCase("Linux")) {
//            String soDir = jarDir + File.separator + "bin" + File.separator + "centos" + File.separator;
//            log.info("加载{}文件夹下面的so文件", soDir);
//            System.setProperty("java.library.path", System.getProperty("java.library.path") + ";"+soDir);
//        } else if (osName.contains("Windows")) {
//            String dllDir = jarDir + File.separator + "bin" + File.separator + "win_x64" + File.separator;
//            log.info("加载{}文件夹下面的dll文件", dllDir);
//            System.setProperty("java.library.path", System.getProperty("java.library.path") + ";"+dllDir);
//        }else {
//            log.error("不支持window或centos以外的操作系统");
//            System.exit(1);
//        }
//        /*
//        加载dll或so文件
//         */
//        Field field = ClassLoader.class.getDeclaredField("sys_paths");
//        boolean isAccessible = field.isAccessible();
//        field.setAccessible(true);
//        field.set(null, null);
//        System.loadLibrary("grsvrl6");
//        System.loadLibrary("grsvrj6");
//        field.setAccessible(isAccessible);
    }
}
