package com.lishide.gankarms.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import com.lishide.gankarms.app.GreenDaoHelper
import com.lishide.gankarms.app.greendao.GankEntityDao
import com.lishide.gankarms.mvp.contract.DetailContract
import com.lishide.gankarms.mvp.model.entity.GankEntity
import javax.inject.Inject

@ActivityScope
class DetailModel @Inject
constructor(repositoryManager: IRepositoryManager,
            private var mGson: Gson?,
            private var mApplication: Application?
) : BaseModel(repositoryManager), DetailContract.Model {

    override fun queryById(id: String): List<GankEntity> {
        return GreenDaoHelper.getDaoSession().gankEntityDao
                .queryBuilder()
                .where(GankEntityDao.Properties.Id.eq(id))
                .list()
    }

    override fun removeById(id: String) {
        GreenDaoHelper.getDaoSession().gankEntityDao
                .queryBuilder()
                .where(GankEntityDao.Properties.Id.eq(id))
                .buildDelete()
                .executeDeleteWithoutDetachingEntities()
    }

    override fun addToLike(entity: GankEntity) {
        GreenDaoHelper.getDaoSession().gankEntityDao
                .insert(entity)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.mGson = null
        this.mApplication = null
    }
}