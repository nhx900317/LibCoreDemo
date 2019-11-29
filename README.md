# LibCoreDemo<br>
最近将很多之前做的功能封装成了一个aar库，这样使用起来比较方便，主要封装了UI页面和定位，天气等功能，不定期更新，喜欢请支持一下:)<br>
UI页面总体包含音乐播放，视频播放，图片查看，word文档查看，Excel表格查看，PPT幻灯片查看，PDF文档查看，网页查看，地图查看，涂鸦窗口和图片轮播窗口。功能主要使用intent的putextra方法传入参数，启动相应的Activity，具体使用方法请见Demo。<br>
目前发现的问题：在MyMapActivity，有些手机内核不同，无法使用，目前发现华为手机部分部分机型使用有问题，正在完善中。<br>

# 1. 配置和使用方法<br>

## 1.1 配置环境<br>
建议使用开发环境：Android studio，版本号为3.5.0进行开发；<br>
建议gradle版本：建议使用5.4.1以及以上版本进行开发；<br>
建议android版本：建议使用(SDKVersion 28)以及以上版本进行开发；<br>
aar中使用的SDK版本：(SDKVersion 28)版本，(minSDKVersion 21)版本 ；<br>
aar中使用的kotlin版本：1.3.41。<br>

## 使用方法<br>
将三个aar放到libs目录下；<br>
在gradle中添加三个aar和相关源的引用，具体见demo；<br>
在使用前需要对lib进行初始化，具体代码见第二章。<br>
注1:关于三个aar文件：<br>
libsiasuncore-release.aar为核心库aar；<br>
locktableview-release.aar为显示Excel和表格的控件的aar；<br>
photoview-release.aar为可缩放图片的控件的aar；<br>
注2：由于aar中有涉及页面的操作，设定屏幕分辨率有助于更美观的页面显示。<br>
注3：本文中方法和示例代码均用kotlin标注。<br>
具体使用方法可参见Demo。<br>
Demo说明:<br>
Demo分别展示了aar中的各项功能使用和调用方法。<br>

## 权限获取<br>
使用aar建议添加如下权限:<br>
必要权限：<br>
网络权限，读写权限；<br>
可选权限：<br>
位置信息(定位功能)<br>
# 初始化<br>

## 初始化操作<br>
新建InitLibCore类的对象，传入上下文，并调用该对象的init方法进行初始化。<br>

    val initLibCore = InitLibCore()

    initLibCore.init(context)

注：初始化操作必不可少，否则无法使用本aar库，传入的context建议使用应用Application的context。<br>
2.屏幕分辨率<br>
在初始化的过程中，会获取设备的分辨率和屏幕方向，只在初始化时获取，涉及屏幕旋转的操作时，需要重新获取并对参数进行赋值，以便UI正常显示。如需手动设定屏幕分辨率，可以使用设定屏幕分辨率的方法。<br>
获取屏幕分辨率的方法：ScreenResolution.getResolution(mContext:Context)<br>
设定屏幕分辨率的方法：ScreenResolution.setResolution(width:Int, height:Int)<br>

# 页面功能

本章主要对aar包里的UI页面功能进行介绍，并将使用方法和参数进行详细说明。aar包总体包含音乐播放，视频播放，图片查看，word文档查看，Excel表格查看，PPT幻灯片查看，PDF文档查看，网页查看，地图查看，涂鸦窗口和图片轮播窗口。具体见下表：<br>

Activity	         页面说明	            支持文件的格式
MyBaseActivity	     基础Activity	         下面所有的Activity都继承此Activity，主要设置全屏和常量显示等统一风格
MyMusicActivity	     音乐播放的Activity	   mp3, amr
MyVideoActivity	     视频播放的Activity	   mp4, wmv
MyPhotoActivity	     图片查看的Activity	   jpg, png, bmp
MyAnimationActivity	 动画播放的Activity	   gif
MyWordActivity	     word查看的Activity	doc, docx
MyExcelActivity	     Excel查看的Activity	xls
MyPPTActivity	     PPT查看的Activity	    ppt, pptx
MyPDFActivity	     PDF查看的Activity	    pdf
MyExplorerActivity	 网页查看的Activity	   htm, html, 网址
MyMapActivity	     地图查看的Activity	   无
MyDoodleActivity	 涂鸦窗口的Activity	   无
MyBannerActivity	 图片轮播的Activity	   png, jpg
