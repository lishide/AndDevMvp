package com.lishide.anddevmvp.base

/**
 * BaseView
 *
 * @author lishide
 * @date 2017/11/04
 */
interface BaseView<T> {

    var presenter: T?

    var isActive: Boolean
}