package com.mycreazy.springbootweb.utils;

import java.io.*;
import java.util.List;

/**
 * Time: 下午7:54
 **/
public class LinuxCmdUtil {
    public static String executeLinuxCmd(List<String> cmd, String programPath, boolean issync) {
        ////这里异步执行
        String result = "success";
        Process process = null;
        try {
            ProcessBuilder pb = new ProcessBuilder();
            pb.command(cmd);
            //切换到脚本所在目录
            pb.directory(new File(programPath));
            pb.redirectErrorStream(true);
            process = pb.start();
            ThreadUtil thread = new ThreadUtil(process);
            thread.start();
        } catch (IOException e) {
            result = "error";
            e.printStackTrace();
        }

        return result;
    }

    public static String executeCmdBySync(List<String> cmd, String programPath) {
        ////这里异步执行
        String result = "success";
        Process process = null;
        try {
            ProcessBuilder pb = new ProcessBuilder();
            pb.command(cmd);
            //切换到脚本所在目录
            pb.directory(new File(programPath));
            pb.redirectErrorStream(true);
            process = pb.start();

            InputStream in = process.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuffer sb = new StringBuffer();
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                in.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            result = "error";
            e.printStackTrace();
        } finally {
            process.destroy();
        }

        return result;
    }
}
