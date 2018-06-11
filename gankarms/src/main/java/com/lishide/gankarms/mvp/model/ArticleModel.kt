package com.lishide.gankarms.mvp.model

import android.app.Application

import com.google.gson.Gson
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import com.lishide.gankarms.app.GreenDaoHelper
import com.lishide.gankarms.app.constant.CategoryConstant
import com.lishide.gankarms.app.greendao.GankEntityDao
import com.lishide.gankarms.mvp.contract.ArticleContract
import com.lishide.gankarms.mvp.model.entity.GankEntity

import javax.inject.Inject


@FragmentScope
class ArticleModel @Inject
constructor(repositoryManager: IRepositoryManager, private var mGson: Gson?, private var mApplication: Application?) : BaseModel(repositoryManager), ArticleContract.Model {
    override fun getEntity(): List<GankEntity> {
        return GreenDaoHelper.getDaoSession().gankEntityDao
                .queryBuilder()
                .where(GankEntityDao.Properties.Type.notEq(CategoryConstant.GIRLS_STR))
                .orderDesc(GankEntityDao.Properties.Id)
                .list()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.mGson = null
        this.mApplication = null
    }
}