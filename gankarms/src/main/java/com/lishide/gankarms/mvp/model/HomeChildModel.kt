package com.lishide.gankarms.mvp.model

import android.app.Application

import com.google.gson.Gson
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import com.lishide.gankarms.mvp.contract.HomeChildContract

import javax.inject.Inject


@FragmentScope
class HomeChildModel @Inject
constructor(repositoryManager: IRepositoryManager, private var mGson: Gson?, private var mApplication: Application?) : BaseModel(repositoryManager), HomeChildContract.Model {

    override fun onDestroy() {
        super.onDestroy()
        this.mGson = null
        this.mApplication = null
    }
}