# AndDevMvp
目前在用的一个整合了大量主流开源项目高度可配置化的 Android MVP 快速集成框架——[MVPArms](https://github.com/JessYanCoding/MVPArms)，包含 MVP+Dagger2+Retrofit+OkHttp+RxJava2 等，及封装的常用工具类，快速开发，提高效率。
application 的模块由 Kotlin 语言开发，`app` Module 是一个用 MVParms 写的简单 demo，构建新项目可直接参考此 demo；`gankarms` Module 是由此框架写的一个 Gank.io 客户端。

## Functionality & Libraries
* MVP
* Kotlin 语言
* RxJava2
* Retrofit
* OkHttp
* Dagger2
* RxAndroid
* Rxlifecycle
* RxCache
* RxPermissions
* RxErrorHandler
* Gson
* Butterknife
* Androideventbus
* Timber
* Glide
* LeakCanary

## Tips
基础开发框架使用 [MVPArms](https://github.com/JessYanCoding/MVPArms)，更多高级用法请看 Demo 与 wiki 文档。

### 使用模板创建 Activity/Fragment
在AS模板插件中添加 [MVPArmsTemplate](https://github.com/JessYanCoding/MVPArmsTemplate)，在 Root 包下 new Activity，
选择`MVPArms 全家桶`，自动生成所有整套 MVP、Dagger2 等的代码，好用的如丝般顺滑~~

目前的模板，暂时发现有个小问题，选择语言为 `Kotlin` 时，生成的代码中 `savedInstanceState: Bundle` 这个变量的类型应为可空类型，需要改一下，加个`?`，即 `savedInstanceState: Bundle?` 则正确，否则无法正常运行；

生成后，编译一下项目；

如找不到类似 `DaggerMainComponent` 的类，请反复编译一下项目；

......
### 混淆文件添加规则
开发中发现使用混淆打包时，导出的安装包不能正常运行，报错找不到自己写的实体类和 presenter，则在混淆规则中添加 keep 规则。如：
```groovy
-keep class com.lishide.anddevmvp.mvp.model.entity.** { *;}
-keep class com.lishide.anddevmvp.mvp.presenter.** { *;}
```

### 主语言切换为 Kotlin
将工程使用主语言由 Java 切换为 Kotlin，常用类由 Kotlin 语言编写。两种语言的写法差异可查看 *2018-01-04* 两次提交。

