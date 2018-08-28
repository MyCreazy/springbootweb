package com.mycreazy.springbootweb.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @Description
 **/
public class FileUtils {
    /**
     * 日志对象
     */
    private final static Logger logger = LoggerFactory.getLogger(FileUtils.class);
    public static List<String> readLine(String path) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static String read(String path) {
        StringBuffer sb = new StringBuffer();

        List<String> lines = readLine(path);
        for (String line: lines)
            sb.append(line+"\n");

        return sb.toString();
    }

    public static boolean write(String content, String path,boolean iscreatepath) {
        boolean result=true;
        try {
            File file=new File(path);
            if(iscreatepath) {
                if (!file.exists()) {
                    file.createNewFile();
                }
            }

            BufferedWriter bfWriter = Files.newBufferedWriter(Paths.get(path), StandardCharsets.UTF_8);
            bfWriter.write(content+"\n");

            bfWriter.flush();
            bfWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            result=false;
        }

        return  result;
    }

    /**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        try {
            File file = new File(fileName);
            // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    System.out.println("删除单个文件" + fileName + "成功！");
                    return true;
                } else {
                    System.out.println("删除单个文件" + fileName + "失败！");
                    return false;
                }
            } else {
                System.out.println("删除单个文件失败：" + fileName + "不存在！");
                return false;
            }
        }
        catch (Exception ex)
        {
            logger.error("删除文件"+fileName+"异常:"+ex.getMessage());
        }

        return  false;
    }
}
