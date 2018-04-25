package com.lishide.gankarms.mvp.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * gank 实体类
 *
 * @author lishide
 * @date 2018/04/25
 */
public class GankEntity implements Serializable {
    public boolean error;
    public List<ResultsBean> results;

    public static class ResultsBean implements Serializable {
        public String _id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String source;
        public String type;
        public String url;
        public boolean used;
        public String who;
        public List<String> images;
    }
}
