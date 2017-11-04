package com.lishide.anddevmvp.base.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.text.TextUtils

/**
 * BaseBroadCastReceiver class
 *
 * @author lishide
 * @date 2017/11/04
 */
abstract class BaseBroadCastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent != null && !TextUtils.isEmpty(intent.action)) {
            handle(intent.action)
        }
    }

    protected abstract fun handle(action: String?)

}