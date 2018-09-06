package com.realpower.petitionwatch.net.result;

/**
 * Created by Administrator on 2017/12/21.
 */

public class UpLoadResutlText {

    /**
     * message : {"url":"D://test//Screenshot_20171213-161914.png"}
     * desc : {"code":"1","description":"上传完成"}
     * status : 1
     */

    private MessageBean message;
    private DescBean desc;
    private String status;

    @Override
    public String toString() {
        return "UpLoadResutlText{" +
                "message=" + message +
                ", desc=" + desc +
                ", status='" + status + '\'' +
                '}';
    }

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public DescBean getDesc() {
        return desc;
    }

    public void setDesc(DescBean desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class MessageBean {


        /**
         * url : D://test//Screenshot_20171213-161914.png
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "MessageBean{" +
                    "url='" + url + '\'' +
                    '}';
        }
    }

    public static class DescBean {
        /**
         * code : 1
         * description : 上传完成
         */

        private String code;
        private String description;

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
            return "DescBean{" +
                    "code='" + code + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
}
