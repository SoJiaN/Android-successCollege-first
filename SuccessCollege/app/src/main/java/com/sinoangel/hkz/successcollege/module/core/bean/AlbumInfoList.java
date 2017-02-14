package com.sinoangel.hkz.successcollege.module.core.bean;

import java.util.List;

/**
 * Created by lenovo on 2017/1/31.
 */

public class AlbumInfoList {

    /**
     * flag : 1
     * error : []
     * data : [{"id":"78","name":"那些年陪伴我们的恐龙们","info":"还记得那些动画片里的恐龙吗，有没有一部动画片让你喜欢上恐龙？这里为宝贝们精心挑选了更多恐龙动画片，《那些年陪伴我们的恐龙们》让我们温故的同时也让宝贝们认知一下陪伴爸爸妈妈度过童年的恐龙们，一起来看吧！","imgs":[{"id":"159","img":"/icons/2016-08-22/那些年陪伴我们的恐龙们1.png"},{"id":"160","img":"/icons/2016-08-22/那些年陪伴我们的恐龙们2.png"},{"id":"161","img":"/icons/2016-08-22/那些年陪伴我们的恐龙们3.png"},{"id":"162","img":"/icons/2016-08-22/那些年陪伴我们的恐龙们4.png"},{"id":"163","img":"/icons/2016-08-22/那些年陪伴我们的恐龙们5.png"}]},{"id":"79","name":"蓝猫淘气3000问 恐龙时代","info":"聪明勇敢的蓝猫，将带领他亲爱的伙伴们，一起穿越时空回到恐龙世纪，历经最惊险刺激的重重挑战和难关，近距离认识地球上受欢迎的爬行动物\u2014\u2014恐龙。孩子们不需要跑到博物馆，坐在家中，就可以看到那些梦幻中才能见到的栩栩如生的生命。翱翔天际的翼龙，鱼龙、蛇颈龙、幻龙\u2026\u2026还有从恐龙蛋中跃出的恐龙宝宝。","imgs":[{"id":"164","img":"/icons/2016-08-22/蓝猫淘气三千问之恐龙时代1.png"},{"id":"165","img":"/icons/2016-08-22/蓝猫淘气三千问之恐龙时代2.png"},{"id":"166","img":"/icons/2016-08-22/蓝猫淘气三千问之恐龙时代3.png"},{"id":"167","img":"/icons/2016-08-22/蓝猫淘气三千问之恐龙时代4.png"},{"id":"168","img":"/icons/2016-08-22/蓝猫淘气三千问之恐龙时代5.png"}]}]
     */

    private int flag;
    private List<?> error;
    private List<DataBean> data;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public List<?> getError() {
        return error;
    }

    public void setError(List<?> error) {
        this.error = error;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 78
         * name : 那些年陪伴我们的恐龙们
         * info : 还记得那些动画片里的恐龙吗，有没有一部动画片让你喜欢上恐龙？这里为宝贝们精心挑选了更多恐龙动画片，《那些年陪伴我们的恐龙们》让我们温故的同时也让宝贝们认知一下陪伴爸爸妈妈度过童年的恐龙们，一起来看吧！
         * imgs : [{"id":"159","img":"/icons/2016-08-22/那些年陪伴我们的恐龙们1.png"},{"id":"160","img":"/icons/2016-08-22/那些年陪伴我们的恐龙们2.png"},{"id":"161","img":"/icons/2016-08-22/那些年陪伴我们的恐龙们3.png"},{"id":"162","img":"/icons/2016-08-22/那些年陪伴我们的恐龙们4.png"},{"id":"163","img":"/icons/2016-08-22/那些年陪伴我们的恐龙们5.png"}]
         */

        private String id;
        private String name;
        private String info;
        private List<ImgsBean> imgs;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public List<ImgsBean> getImgs() {
            return imgs;
        }

        public void setImgs(List<ImgsBean> imgs) {
            this.imgs = imgs;
        }

        public static class ImgsBean {
            /**
             * id : 159
             * img : /icons/2016-08-22/那些年陪伴我们的恐龙们1.png
             */

            private String id;
            private String img;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
