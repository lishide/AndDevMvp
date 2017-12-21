# AndDevMvp
Android 应用架构 MVP 使用及常用基础类和工具类的封装。

## Functionality & Libraries
>* MVP

>* Kotlin 语言

>* RxJava

>* Retrofit

>* OkHttp

>* Dagger2

>* RxAndroid

>* Rxlifecycle

>* RxCache

>* RxPermissions

>* RxErrorHandler

>* Gson

>* Butterknife

>* Androideventbus

>* Timber

>* Glide

>* LeakCanary

## Tips
基础开发框架使用 [MVPArms](https://github.com/JessYanCoding/MVPArms)，更多高级用法请看 Demo 与 wiki 文档。

#### 使用模板创建 Activity/Fragment
在AS模板插件中添加[MVPArmsTemplate](https://github.com/JessYanCoding/MVPArmsTemplate)，在 Root 包下new Activity，
选择`MVPArms 全家桶`，自动生成所有整套 MVP、Dagger2 等的代码，好用的如丝般顺滑~~

选择语言为`Kotlin`时，生成的代码中`savedInstanceState: Bundle` 这个变量的类型需要改一下，加个`?`,
即`savedInstanceState: Bundle?`则正确，否则无法正常运行；

生成后，编译一下项目；

如找不到类似`DaggerMainComponent` 的类，请反复编译一下项目；

......
