# LibCoreDemo

最近将很多之前做的功能封装成了一个aar库，这样使用起来比较方便，主要封装了UI页面和定位，天气等功能，不定期更新，喜欢请支持一下:)

UI页面总体包含音乐播放，视频播放，图片查看，word文档查看，Excel表格查看，PPT幻灯片查看，PDF文档查看，网页查看，地图查看，涂鸦窗口和图片轮播窗口。具体使用方法请见下文。

目前发现的问题：在MyMapActivity，有些手机内核不同，无法使用，目前发现华为手机部分部分机型使用有问题，正在完善中。

# 配置和使用方法

1.1 配置环境

建议使用开发环境：Android studio，版本号为3.5.0进行开发；

建议gradle版本：建议使用5.4.1以及以上版本进行开发；

建议android版本：建议使用(SDKVersion 28)以及以上版本进行开发；

aar中使用的SDK版本：(SDKVersion 28)版本，(minSDKVersion 21)版本 ；

aar中使用的kotlin版本：1.3.41。

1.2 使用方法

将三个aar放到libs目录下；

在gradle中添加三个aar和相关源的引用，具体见demo；

在使用前需要对lib进行初始化，具体代码见第二章。

注1:关于三个aar文件：

libsiasuncore-release.aar为核心库aar；

locktableview-release.aar为显示Excel和表格的控件的aar；

photoview-release.aar为可缩放图片的控件的aar；

注2：由于aar中有涉及页面的操作，设定屏幕分辨率有助于更美观的页面显示。

注3：本文中方法和示例代码均用kotlin标注。

具体使用方法可参见Demo。

Demo说明:

Demo分别展示了aar中的各项功能使用和调用方法。

1.3 权限获取

使用aar建议添加如下权限:

必要权限：

网络权限，读写权限；

可选权限：

位置信息(定位功能)

# 初始化

2.1 初始化操作

新建InitLibCore类的对象，传入上下文，并调用该对象的init方法进行初始化。

val initLibCore = InitLibCore()

initLibCore.init(context)

注：初始化操作必不可少，否则无法使用本aar库，传入的context建议使用应用Application的context。

2.2 屏幕分辨率

在初始化的过程中，会获取设备的分辨率和屏幕方向，只在初始化时获取，涉及屏幕旋转的操作时，需要重新获取并对参数进行赋值，以便UI正常显示。如需手动设定屏幕分辨率，可以使用设定屏幕分辨率的方法。

获取屏幕分辨率的方法：ScreenResolution.getResolution(mContext:Context)

设定屏幕分辨率的方法：ScreenResolution.setResolution(width:Int, height:Int)
