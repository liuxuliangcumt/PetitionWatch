package com.realpower.petitionwatch.bean;

import java.util.List;

public class AddressBean {

    /**
     * code : 1305
     * name : 邢台市
     * childs : [{"code":"130502","name":"桥东区","childs":[{"code":"130502001","name":"南长街街道"},{"code":"130502002","name":"北大街街道"},{"code":"130502003","name":"西大街街道"},{"code":"130502004","name":"西门里街道"},{"code":"130502005","name":"泉东街道"},{"code":"130502006","name":"豫让桥街道"},{"code":"130502100","name":"东郭村镇"},{"code":"130502200","name":"大梁庄乡"}]},{"code":"130503","name":"桥西区","childs":[{"code":"130503001","name":"钢铁路街道"},{"code":"130503002","name":"中兴路街道"},{"code":"130503003","name":"达活泉街道"},{"code":"130503004","name":"张宽街道"},{"code":"130503005","name":"章村街道"},{"code":"130503006","name":"中华大街街道"},{"code":"130503007","name":"团结路街道"},{"code":"130503008","name":"泉西街道"},{"code":"130503100","name":"南大郭镇"},{"code":"130503101","name":"李村镇"}]},{"code":"130521","name":"邢台县","childs":[{"code":"130521103","name":"晏家屯镇"},{"code":"130521104","name":"南石门镇"},{"code":"130521105","name":"羊范镇"},{"code":"130521106","name":"皇寺镇"},{"code":"130521107","name":"会宁镇"},{"code":"130521108","name":"西黄村镇"},{"code":"130521109","name":"路罗镇"},{"code":"130521110","name":"将军墓镇"},{"code":"130521111","name":"浆水镇"},{"code":"130521112","name":"宋家庄镇"},{"code":"130521200","name":"太子井乡"},{"code":"130521201","name":"龙泉寺乡"},{"code":"130521202","name":"北小庄乡"},{"code":"130521203","name":"城计头乡"},{"code":"130521204","name":"白岸乡"},{"code":"130521206","name":"冀家村乡"}]},{"code":"130522","name":"临城县","childs":[{"code":"130522100","name":"临城镇"},{"code":"130522101","name":"东镇镇"},{"code":"130522102","name":"西竖镇"},{"code":"130522103","name":"郝庄镇"},{"code":"130522200","name":"黑城乡"},{"code":"130522201","name":"鸭鸽营乡"},{"code":"130522203","name":"石城乡"},{"code":"130522205","name":"赵庄乡"}]},{"code":"130523","name":"内丘县","childs":[{"code":"130523100","name":"内丘镇"},{"code":"130523101","name":"大孟村镇"},{"code":"130523102","name":"金店镇"},{"code":"130523103","name":"官庄镇"},{"code":"130523104","name":"柳林镇"},{"code":"130523200","name":"五郭店乡"},{"code":"130523203","name":"南赛乡"},{"code":"130523204","name":"獐獏乡"},{"code":"130523205","name":"侯家庄乡"}]},{"code":"130524","name":"柏乡县","childs":[{"code":"130524100","name":"柏乡镇"},{"code":"130524101","name":"固城店镇"},{"code":"130524102","name":"西汪镇"},{"code":"130524200","name":"王家庄乡"},{"code":"130524202","name":"龙华乡"},{"code":"130524203","name":"内步乡"}]},{"code":"130525","name":"隆尧县","childs":[{"code":"130525100","name":"隆尧镇"},{"code":"130525101","name":"魏家庄镇"},{"code":"130525102","name":"尹村镇"},{"code":"130525103","name":"山口镇"},{"code":"130525104","name":"莲子镇镇"},{"code":"130525105","name":"固城镇"},{"code":"130525200","name":"北楼乡"},{"code":"130525201","name":"东良乡"},{"code":"130525202","name":"双碑乡"},{"code":"130525203","name":"牛家桥乡"},{"code":"130525204","name":"千户营乡"},{"code":"130525205","name":"大张庄乡"},{"code":"130525400","name":"柳行农场"}]},{"code":"130526","name":"任县","childs":[{"code":"130526100","name":"任城镇"},{"code":"130526101","name":"邢家湾镇"},{"code":"130526102","name":"辛店镇"},{"code":"130526103","name":"天口镇"},{"code":"130526200","name":"西固城乡"},{"code":"130526201","name":"永福庄乡"},{"code":"130526202","name":"大屯乡"},{"code":"130526204","name":"骆庄乡"}]},{"code":"130527","name":"南和县","childs":[{"code":"130527100","name":"和阳镇"},{"code":"130527101","name":"贾宋镇"},{"code":"130527102","name":"郝桥镇"},{"code":"130527200","name":"东三召乡"},{"code":"130527201","name":"阎里乡"},{"code":"130527202","name":"河郭乡"},{"code":"130527203","name":"史召乡"},{"code":"130527204","name":"三思乡"}]},{"code":"130528","name":"宁晋县","childs":[{"code":"130528001","name":"宁北街道"},{"code":"130528100","name":"凤凰镇"},{"code":"130528101","name":"河渠镇"},{"code":"130528102","name":"北河庄镇"},{"code":"130528103","name":"耿庄桥镇"},{"code":"130528104","name":"东汪镇"},{"code":"130528105","name":"贾家口镇"},{"code":"130528106","name":"四芝兰镇"},{"code":"130528107","name":"大陆村镇"},{"code":"130528108","name":"苏家庄镇"},{"code":"130528109","name":"换马店镇"},{"code":"130528110","name":"唐邱镇"},{"code":"130528200","name":"侯口乡"},{"code":"130528202","name":"纪昌庄乡"},{"code":"130528205","name":"北鱼乡"}]},{"code":"130529","name":"巨鹿县","childs":[{"code":"130529100","name":"巨鹿镇"},{"code":"130529101","name":"王虎寨镇"},{"code":"130529102","name":"西郭城镇"},{"code":"130529103","name":"官亭镇"},{"code":"130529104","name":"阎疃镇"},{"code":"130529105","name":"小吕寨镇"},{"code":"130529106","name":"苏家营镇"},{"code":"130529200","name":"堤村乡"},{"code":"130529201","name":"张王疃乡"},{"code":"130529202","name":"观寨乡"}]},{"code":"130530","name":"新河县","childs":[{"code":"130530100","name":"新河镇"},{"code":"130530101","name":"寻寨镇"},{"code":"130530200","name":"白神首乡"},{"code":"130530201","name":"荆家庄乡"},{"code":"130530202","name":"西流乡"},{"code":"130530203","name":"仁让里乡"}]},{"code":"130531","name":"广宗县","childs":[{"code":"130531100","name":"广宗镇"},{"code":"130531101","name":"冯家寨镇"},{"code":"130531102","name":"北塘疃镇"},{"code":"130531103","name":"核桃园镇"},{"code":"130531200","name":"葫芦乡"},{"code":"130531201","name":"大平台乡"},{"code":"130531202","name":"件只乡"},{"code":"130531204","name":"东召乡"}]},{"code":"130532","name":"平乡县","childs":[{"code":"130532001","name":"平乡县中华路街道"},{"code":"130532100","name":"丰州镇"},{"code":"130532101","name":"平乡镇"},{"code":"130532102","name":"河古庙镇"},{"code":"130532200","name":"节固乡"},{"code":"130532201","name":"油召乡"},{"code":"130532202","name":"田付村乡"},{"code":"130532203","name":"寻召乡"}]},{"code":"130533","name":"威县","childs":[{"code":"130533100","name":"洺州镇"},{"code":"130533101","name":"梨园屯镇"},{"code":"130533102","name":"章台镇"},{"code":"130533103","name":"侯贯镇"},{"code":"130533104","name":"七级镇"},{"code":"130533105","name":"贺营镇"},{"code":"130533106","name":"方家营镇"},{"code":"130533107","name":"常庄镇"},{"code":"130533108","name":"第什营镇"},{"code":"130533202","name":"枣园乡"},{"code":"130533203","name":"固献乡"},{"code":"130533204","name":"贺钊乡"},{"code":"130533206","name":"张家营乡"},{"code":"130533207","name":"常屯乡"},{"code":"130533209","name":"高公庄乡"},{"code":"130533210","name":"赵村乡"}]},{"code":"130534","name":"清河县","childs":[{"code":"130534100","name":"葛仙庄镇"},{"code":"130534101","name":"连庄镇"},{"code":"130534102","name":"油坊镇"},{"code":"130534103","name":"谢炉镇"},{"code":"130534104","name":"王官庄镇"},{"code":"130534105","name":"坝营镇"}]},{"code":"130535","name":"临西县","childs":[{"code":"130535100","name":"临西镇"},{"code":"130535101","name":"河西镇"},{"code":"130535102","name":"下堡寺镇"},{"code":"130535103","name":"尖冢镇"},{"code":"130535104","name":"老官寨镇"},{"code":"130535105","name":"吕寨镇"},{"code":"130535200","name":"东枣园乡"},{"code":"130535203","name":"摇鞍镇乡"},{"code":"130535204","name":"大刘庄乡"},{"code":"130535400","name":"轴承工业园区"}]},{"code":"130581","name":"南宫市","childs":[{"code":"130581001","name":"凤岗街道"},{"code":"130581002","name":"南杜街道"},{"code":"130581003","name":"北胡街道"},{"code":"130581004","name":"西丁街道"},{"code":"130581100","name":"苏村镇"},{"code":"130581101","name":"大高村镇"},{"code":"130581102","name":"垂杨镇"},{"code":"130581103","name":"明化镇"},{"code":"130581104","name":"段芦头镇"},{"code":"130581105","name":"紫冢镇"},{"code":"130581200","name":"大村乡"},{"code":"130581201","name":"南便村乡"},{"code":"130581202","name":"大屯乡"},{"code":"130581203","name":"王道寨乡"},{"code":"130581204","name":"薛吴村乡"}]},{"code":"130582","name":"沙河市","childs":[{"code":"130582001","name":"褡裢街道"},{"code":"130582002","name":"桥东街道"},{"code":"130582003","name":"桥西街道"},{"code":"130582004","name":"赞善"},{"code":"130582005","name":"周庄街道"},{"code":"130582101","name":"新城镇"},{"code":"130582102","name":"白塔镇"},{"code":"130582103","name":"十里亭镇"},{"code":"130582104","name":"綦村镇"},{"code":"130582201","name":"册井乡"},{"code":"130582202","name":"刘石岗乡"},{"code":"130582203","name":"柴关乡"},{"code":"130582204","name":"蝉房乡"}]}]
     */

    private String code;
    private String name;
    private List<ChildsBeanX> childs;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChildsBeanX> getChilds() {
        return childs;
    }

    public void setChilds(List<ChildsBeanX> childs) {
        this.childs = childs;
    }

    public static class ChildsBeanX {
        /**
         * code : 130502
         * name : 桥东区
         * childs : [{"code":"130502001","name":"南长街街道"},{"code":"130502002","name":"北大街街道"},{"code":"130502003","name":"西大街街道"},{"code":"130502004","name":"西门里街道"},{"code":"130502005","name":"泉东街道"},{"code":"130502006","name":"豫让桥街道"},{"code":"130502100","name":"东郭村镇"},{"code":"130502200","name":"大梁庄乡"}]
         */

        private String code;
        private String name;
        private List<ChildsBean> childs;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ChildsBean> getChilds() {
            return childs;
        }

        public void setChilds(List<ChildsBean> childs) {
            this.childs = childs;
        }

        public static class ChildsBean {
            /**
             * code : 130502001
             * name : 南长街街道
             */

            private String code;
            private String name;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
