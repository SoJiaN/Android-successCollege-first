package com.sinoangel.hkz.successcollege.module.core.bean;

import java.util.List;

/**
 * Created by lenovo on 2017/1/18.
 */

public class Card {

    /**
     * flag : 1
     * error : []
     * data : [{"id":"2","icon":"http://cn.img.store.sinoangel.cn/icons/college/2017-01-13/php0DdFmL.png","name":"test2","isAlbumList":0,"albums":"879"},{"id":"3","icon":"http://cn.img.store.sinoangel.cn/icons/college/2017-01-13/php1jhyk2.png","name":"test3","isAlbumList":0,"albums":"98"},{"id":"4","icon":"http://cn.img.store.sinoangel.cn/icons/college/2017-01-13/phpeCO4jA.png","name":"test4","isAlbumList":1,"albums":"78,79"},{"id":"5","icon":"http://cn.img.store.sinoangel.cn/icons/college/2017-01-13/phpXVssB1.png","name":"test5","isAlbumList":0,"albums":""},{"id":"6","icon":"http://cn.img.store.sinoangel.cn/icons/college/2017-01-13/php0mwiP1.png","name":"test6","isAlbumList":0,"albums":""},{"id":"7","icon":"http://cn.img.store.sinoangel.cn/icons/college/2017-01-13/phpJrKzHC.png","name":"test7","isAlbumList":0,"albums":""},{"id":"8","icon":"http://cn.img.store.sinoangel.cn/icons/college/2017-01-13/phpmXrTUS.png","name":"test8","isAlbumList":0,"albums":""},{"id":"19","icon":"http://cn.img.store.sinoangel.cn/icons/college/2017-01-22/phpRD4d03.png","name":"test","isAlbumList":0,"albums":""},{"id":"21","icon":"http://cn.img.store.sinoangel.cn/icons/college/2017-01-22/phpwOUJSx.png","name":"test9","isAlbumList":0,"albums":""},{"id":"22","icon":"http://cn.img.store.sinoangel.cn/icons/college/2017-01-22/php7WEbGx.png","name":"test10","isAlbumList":0,"albums":""},{"id":"23","icon":"http://cn.img.store.sinoangel.cn/icons/college/2017-01-22/phpQ26TH3.png","name":"test11","isAlbumList":0,"albums":""}]
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
         * id : 2
         * icon : http://cn.img.store.sinoangel.cn/icons/college/2017-01-13/php0DdFmL.png
         * name : test2
         * isAlbumList : 0
         * albums : 879
         */

        private String id;
        private String icon;
        private String name;
        private int isAlbumList;
        private String albums;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIsAlbumList() {
            return isAlbumList;
        }

        public void setIsAlbumList(int isAlbumList) {
            this.isAlbumList = isAlbumList;
        }

        public String getAlbums() {
            return albums;
        }

        public void setAlbums(String albums) {
            this.albums = albums;
        }
    }
}
