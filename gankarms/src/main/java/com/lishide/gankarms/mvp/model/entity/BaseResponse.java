package com.lishide.gankarms.mvp.model.entity;

import java.io.Serializable;

/**
 * 如果你服务器返回的数据格式固定为这种方式（这里只提供思想，服务器返回的数据格式可能不一致，可根据自家服务器返回的格式作更改）
 * 替换范型即可重用 {@link BaseResponse}
 *
 * @author lishide
 * @date 2017/11/09
 */
public class BaseResponse<T> implements Serializable {
    private T results;
    private boolean error;

    public T getResults() {
        return results;
    }

    public boolean isError() {
        return error;
    }
}
