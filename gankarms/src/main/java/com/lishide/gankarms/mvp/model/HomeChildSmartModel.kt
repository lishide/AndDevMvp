package com.lishide.gankarms.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import com.lishide.gankarms.app.constant.AppConstants
import com.lishide.gankarms.mvp.contract.HomeChildSmartContract
import com.lishide.gankarms.mvp.model.api.service.CommonService
import com.lishide.gankarms.mvp.model.entity.BaseResponse
import com.lishide.gankarms.mvp.model.entity.GankEntity
import io.reactivex.Observable
import javax.inject.Inject

@FragmentScope
class HomeChildSmartModel @Inject
constructor(repositoryManager: IRepositoryManager, private var mGson: Gson?, private var mApplication: Application?) : BaseModel(repositoryManager), HomeChildSmartContract.Model {
    override fun gank(type: String?, page: String): Observable<BaseResponse<List<GankEntity>>> {
        return mRepositoryManager.obtainRetrofitService(CommonService::class.java)
                .gank(type, AppConstants.PAGE_SIZE, page)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.mGson = null
        this.mApplication = null
    }
}