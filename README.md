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

## 1.2 使用方法<br>
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

## 1.3 权限获取<br>
使用aar建议添加如下权限:<br>
必要权限：<br>
网络权限，读写权限；<br>
可选权限：<br>
位置信息(定位功能)<br>
# 2. 初始化<br>

## 2.1 初始化操作<br>
新建InitLibCore类的对象，传入上下文，并调用该对象的init方法进行初始化。<br>
 
    val initLibCore = InitLibCore()

    initLibCore.init(context)

注：初始化操作必不可少，否则无法使用本aar库，传入的context建议使用应用Application的context。<br>
## 2.2 屏幕分辨率<br>
在初始化的过程中，会获取设备的分辨率和屏幕方向，只在初始化时获取，涉及屏幕旋转的操作时，需要重新获取并对参数进行赋值，以便UI正常显示。如需手动设定屏幕分辨率，可以使用设定屏幕分辨率的方法。<br>
获取屏幕分辨率的方法：ScreenResolution.getResolution(mContext:Context)<br>
设定屏幕分辨率的方法：ScreenResolution.setResolution(width:Int, height:Int)<br>

# 3. 页面功能

本章主要对aar包里的UI页面功能进行介绍，并将使用方法和参数进行详细说明。aar包总体包含音乐播放，视频播放，图片查看，word文档查看，Excel表格查看，PPT幻灯片查看，PDF文档查看，网页查看，地图查看，涂鸦窗口和图片轮播窗口。具体见下表：<br>


Activity            |页面说明                |支持文件的格式
--------------------|----------------------|------------------------------------------------------------
MyBaseActivity	    | Activity	           |下面所有的Activity都继承此Activity，主要设置全屏和常量显示等统一风格
MyMusicActivity	    |音乐播放的Activity	    |mp3, amr
MyVideoActivity	    |视频播放的Activity	    |mp4, wmv
MyPhotoActivity	    |图片查看的Activity	    |jpg, png, bmp
MyAnimationActivity	|动画播放的Activity	    |gif
MyWordActivity	    |word查看的Activity	 |doc, docx
MyExcelActivity	    |Excel查看的Activity	 |xls
MyPPTActivity	    |PPT查看的Activity	     |ppt, pptx
MyPDFActivity	    |PDF查看的Activity	     |pdf
MyExplorerActivity	|网页查看的Activity	    |htm, html, 网址
MyMapActivity	    |地图查看的Activity	    |无
MyDoodleActivity	|涂鸦窗口的Activity	    |无
MyBannerActivity	|图片轮播的Activity	    |png, jpg

## 3.1 文件查看<br>
在aar中封装一个FileHolder类，用于进行文件查看功能，支持上表3-1中的文件类型的查看操作，此方法会根据文件路径中的文件名字自动判断并选择打开方式，建议在文件预览时使用此方法打开文件。<br>
具体使用方法为：<br>
FileHolder.openFile(mContext:Context, filePath:String)<br>

参数名称             |参数类型                |参数说明
--------------------|----------------------|---------------------------------------------
mContext     	    | Context	           |上下文
filePath     	    | String	           |文件路径+文件名，例如“/sdcard/test.mp3”，下同

## 3.2 音乐播放<br>
音乐播放主要集成了播放列表和音乐播放器，并且可以拖动进度，支持MP3文件，获取歌曲名，歌手名，专辑和专辑封面，支持单曲循环，全部循环和随机播放三种方式，并提供设置播放列表宽度的接口。<br>
具体使用方法：<br>
在安卓代码中使用intent启动MyMusicActivity，通过intent.putExtra()方法，传入的参数如下：<br>

参数名称|参数类型 |是否必须|参数说明
-------|-------|-------|-------
filePath|String|是|音乐文件的路径，可以传入一个文件夹路径，<br>此时播放此路径下的所有MP3音乐文件，<br>也可传入一个文件的路径，此时只播放此文件
playListWidth|Int|否|播放列表宽度，不传默认为屏幕宽度的1/3

注1：在表传入参数是否必须一列，如果标注为是，表明此参数必须传入，下同。<br>
注2：在Activity启动时都会将传入的参数Log出来，请过滤TAG为Activity名称查看，以便确认传入参数的值。下同。<br>

## 3.3 视频播放<br>
视频播放主要提供了一个带进度条的视频播放器。<br>
具体使用方法：<br>
在安卓代码中使用intent启动MyVideoActivity，通过intent.putExtra()方法，传入的参数如下：<br>

参数名称|参数类型 |是否必须|参数说明
-------|-------|-------|-------
filePath|String|是|视频文件路径和名称，不支持传入文件夹

## 3.4 图片查看<br>
图片查看主要提供了一个可以双指缩放的图片浏览控件。<br>
注：使用此功能需要在程序中添加photoview-release.aar，详见1.2节。<br>
具体使用方法：<br>
在安卓代码中使用intent启动MyPhotoActivity，通过intent.putExtra()方法，传入的参数如下：<br>

参数名称|参数类型 |是否必须|参数说明
-------|-------|-------|-------
filePath|String|是|图片文件路径和名称，不支持传入文件夹

## 3.5 动画播放<br>
动画播放主要提供了一个gif动画播放控件，默认开始播放，点击屏幕结束播放。此控件占用内存较小，可以避免安卓播放动画可能导致内存溢出的问题。<br>
具体使用方法：<br>
在安卓代码中使用intent启动MyAnimationActivity，通过intent.putExtra()方法，传入的参数如下：<br>

参数名称|参数类型 |是否必须|参数说明
-------|-------|-------|-------
filePath|String|是|gif文件路径和名称，不支持传入文件夹

## 3.6 word查看<br>
word文档查看主要提供了一个word浏览页面，支持双指缩放和翻页功能。<br>
注：翻页按钮添加了防止快速点击功能，点击的时间间隔需要大于2秒，否则不翻页。<br>
具体使用方法：<br>
在安卓代码中使用intent启动MyWordActivity，通过intent.putExtra()方法，传入的参数如下：<br>

参数名称|参数类型 |是否必须|参数说明
-------|-------|-------|-------
filePath|String|是|word文件路径和名称，不支持传入文件夹



