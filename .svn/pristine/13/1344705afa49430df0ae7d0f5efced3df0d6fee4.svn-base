package com.realpower.petitionwatch.net.result;

/**
 * 代表请求结果的基类
 */
public class BaseResult<T> {
    private String status;
    private T message;
    /**
     * desc : {"code":"3","description":"验证码错误"}
     */

    private DescBean desc;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }

    public DescBean getDesc() {
        return desc;
    }

    public void setDesc(DescBean desc) {
        this.desc = desc;
    }

    public static class DescBean {
        /**
         * code : 3
         * description : 验证码错误
         */

        private String code = "";
        private String description = "";

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "code: " + code + "  " + "description :" + description;
        }
    }


    @Override
    public String toString() {
        return "BaseResult{" +
                "status= " + status +
                ", desc= " + desc +
                ", message= " + message + '\'' +
                '}';
    }
}
