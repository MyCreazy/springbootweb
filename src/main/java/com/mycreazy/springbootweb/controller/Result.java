package com.mycreazy.springbootweb.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Time: 上午10:21
 **/
public class Result {
    public static Map<String, Object> OK() {
        return get(CodeEnum.OK);
    }

    public static Map<String, Object> ERROR() {
        return get(CodeEnum.ERROR);
    }

    public static Map<String, Object> OK(Object msg) {
        return get(CodeEnum.OK.getCode(), msg);
    }

    public static Map<String, Object> ERROR(Object msg) {
        return get(CodeEnum.ERROR.getCode(), msg);
    }

    private static Map<String, Object> get(CodeEnum codeEnum) {
        return get(codeEnum.getCode(), codeEnum.getMsg());
    }

    private static Map<String, Object> get(String code, Object msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("msg", msg);

        return map;
    }

    enum CodeEnum {
        OK("00", "操作成功"),
        ERROR("01", "操作失败");

        private String code;
        private Object msg;

        CodeEnum(String code, Object msg) {
            this.code = code;
            this.msg = msg;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Object getMsg() {
            return msg;
        }

        public void setMsg(Object msg) {
            this.msg = msg;
        }
    }
}
