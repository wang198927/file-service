package com.daoming.fileservice.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static Properties _prop = new Properties();

    public static void readProperties(String fileName){
        try {
            InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
            _prop.load(in);
            in.close();
        }catch (IOException e){
            e.printStackTrace();
            logger.error("配置文件读取异常",e);
        }
    }

    public static String getProperty(String key){
        String value = _prop.getProperty(key.trim());
        return value.trim();
    }
}
