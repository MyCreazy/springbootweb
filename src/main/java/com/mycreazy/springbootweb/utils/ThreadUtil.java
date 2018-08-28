package com.mycreazy.springbootweb.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 线程辅助类
 * Time: 下午7:56
 **/
public class ThreadUtil extends Thread{
    private Process process;
    public  ThreadUtil(Process process)
    {
        this.process=process;
    }

    public void run() {
        try {
            InputStream in = this.process.getInputStream();
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
        }
        finally {
            this.process.destroy();
        }

    }
}
