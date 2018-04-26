package com.lishide.gankarms.app.constant

/**
 * CategoryConstant
 *
 * @author lishide
 * @date 2018/04/26
 */
object CategoryConstant {

    val ANDROID_STR = "Android"
    val IOS_STR = "iOS"
    val QIAN_STR = "前端"
    val GIRLS_STR = "福利"


    val ANDROID_IOS = 1
    val GIRLS = 2

    fun getPageTitleByPosition(position: Int): String = when (position) {
        0 -> ANDROID_STR
        1 -> IOS_STR
        2 -> QIAN_STR
        else -> ""
    }

}
