# LibCoreDemo<br>
最近将很多之前做的功能封装成了一个aar库，这样使用起来比较方便，主要封装了UI页面和定位，天气等功能，不定期更新，喜欢请支持一下:)<br>
UI页面总体包含音乐播放，视频播放，图片查看，word文档查看，Excel表格查看，PPT幻灯片查看，PDF文档查看，网页查看，地图查看，涂鸦窗口和图片轮播窗口。功能主要使用intent的putextra方法传入参数，启动相应的Activity，具体使用方法请见Demo。<br>
目前发现的问题：在MyMapActivity，有些手机内核不同，无法使用，目前发现华为手机部分部分机型使用有问题，正在完善中。<br>

# 修改记录<br>
版本|修改记录 |修改时间|修改人
-------|-------|-------|-------
1.0|创建文档|2019-11-02|天河归来
1.1|增加在线视频播放功能<br>修改文件删除不完全和无法打开MP3文件的问题|2019-12-02|天河归来
1.2|增加在线音频播放功能|2019-12-12|天河归来
1.3|增加文件浏览器功能|2020-09-22|天河归来
2.0|增加验证和文件管理等功能<br>修复已知bug|2020-09-25|天河归来

# 1. 配置和使用方法<br>

## 1.1 配置环境<br>
建议使用开发环境：Android studio，版本号为3.5.0或以上进行开发；<br>
建议gradle版本：建议使用5.4.1或以上版本进行开发；<br>
建议android版本：建议使用(SDKVersion 28)或以上版本进行开发；<br>
aar中使用的SDK版本：SDKVersion 28，minSDKVersion 21；<br>
aar中使用的kotlin版本：1.3.41。<br>

## 1.2 使用方法<br>
将四个aar放到libs目录下；<br>
在gradle中添加四个aar和相关源的引用，具体见demo；<br>
在使用前需要对lib进行初始化，具体代码见第二章。<br>
注1:关于四个aar文件：<br>
libsiasuncore-release.aar为核心库aar；<br>
locktableview-release.aar为显示Excel和表格的控件的aar；<br>
photoview-release.aar为可缩放图片的控件的aar；<br>
filepicker-debug.aar为文浏览器的aar；<br>
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
注：初始化操作必不可少，否则无法使用本aar库，传入的context建议使用应用Application的context。<br>
初始化方法有两个，一个带回调，一个不带回调，根据需要调用其中一个就可以。<br>
### 2.1.1 初始化方法(无回调)：<br>
fun init(mContext: Context, authCode: String, deviceId: String)<br>
参数名称|参数类型 |是否必须|参数说明
-------|-------|-------|-------
mContext|Content|是|上下文参数
authCode|String|是|授权码，Demo中自带授权码
deviceId|String|否|设备id，无则传入""

### 2.1.2 初始化方法(带回调)：<br>
fun init(mContext: Context, authCode: String, deviceId: String, pAuthCodeCallback: AuthCodeAb.AuthCodeCallback)<br>
参数名称|参数类型 |是否必须|参数说明
-------|-------|-------|-------
mContext|Content|是|上下文参数
authCode|String|是|授权码，Demo中自带授权码
deviceId|String|否|设备id，无则传入""
pAuthCodeCallback|AuthCodeCallback|是|验证结果回调

### 2.1.3 回调方法<br>
验证码结果回调方法
#### 2.1.3.1  验证通过回调<br>
验证码验证成功时回调
fun onSuccess(msg: String)<br>
参数名称|参数说明
-------|-------
msg|操作成功

#### 2.1.3.2  验证失败回调<br>
验证码验证失败时回调。<br>
注：失败情况比如：验证码不存在，验证码已过期，联网超时等，具体看code和msg。<br>
onError(code: Int, msg: String)<br>

参数名称|参数说明
-------|-------
code|错误码
msg|错误信息

### 2.1.4 示例代码<br>
仅列出不带回调的初始化方法，带回调的具体示例清参看Demo：

    val initLibCore = InitLibCore()
    initLibCore.init(content， "【你的authcode】"， "【你的deviceId】"）

## 2.2 屏幕分辨率<br>
在初始化的过程中，会获取设备的分辨率和屏幕方向，只在初始化时获取，涉及屏幕旋转的操作时，需要重新获取并对参数进行赋值，以便UI正常显示。如需手动设定屏幕分辨率，可以使用设定屏幕分辨率的方法。<br>
获取屏幕分辨率的方法：ScreenResolution.getResolution(mContext:Context)<br>
设定屏幕分辨率的方法：ScreenResolution.setResolution(width:Int, height:Int)<br>

# 3. 页面功能

本章主要对aar包里的UI页面功能进行介绍，并将使用方法和参数进行详细说明。aar包总体包含音乐播放，视频播放，图片查看，word文档查看，Excel表格查看，PPT幻灯片查看，PDF文档查看，网页查看，地图查看，涂鸦窗口和图片轮播窗口。具体见下表：<br>


Activity            |页面说明                |支持文件的格式
--------------------|----------------------|------------------------------------------------------------
MyBaseActivity	    | Activity	           |下面所有的Activity都继承此Activity<br>主要设置全屏和常量显示等统一风格
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
filePath|String|是|音乐文件的路径，可以传入一个文件夹路径<br>此时播放此路径下的所有MP3音乐文件<br>也可传入一个文件的路径，此时只播放此文件<br>在线音频直接传入播放的url
playListWidth|Int|否|播放列表宽度，不传默认为屏幕宽度的1/3

注1：在表传入参数是否必须一列，如果标注为是，表明此参数必须传入，下同。<br>
注2：在Activity启动时都会将传入的参数Log出来，请过滤TAG为Activity名称查看，以便确认传入参数的值。下同。<br>
注3：在MyStaticParameter静态参数类中，有String类型变量currentMyActivity，可以实时标识当前启动的aar中的Activity，需要时可以使用此参数判断。<br>
注4：在MyStaticParameter静态参数类中，有Boolean类型变量isMyMusicActivity，可以实时标识是否启动相应的Activity，启动时为true，反之为false。每个Activity都有相应的参数，即在Activity名字前面加is，需要时可以使用此参数判断，下同。<br>


## 3.3 视频播放<br>
视频播放主要提供了一个带进度条的视频播放器。可以播放本地视频和网络url视频，播放网络视频时传入视频的播放地址需要以http开头。<br>
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

## 3.7 excel查看<br>
Excel文档查看主要提供了一个Excel浏览页面。<br>
注1：使用此功能需要在程序中添加locktableview-release.aar，详见1.2节；<br>
注2：如果Excel中存在单元格合并的情况，显示行列可能会不对应。<br>
具体使用方法：<br>
在安卓代码中使用intent启动MyExcelActivity，通过intent.putExtra()方法，传入的参数如下：<br>

参数名称|参数类型 |是否必须|参数说明
-------|-------|-------|-------
filePath|String|是|excel文件路径和名称，不支持传入文件夹<br>
title|String|否|title显示，在页面的标题栏显示<br>
tableParam|TableParam|否|表格相关参数配置，具体见下表<br>不传此对象时，全部使用默认值<br>传入此对象时，如过只对其中部分参数赋值<br>程序会判断并使用赋值的参数，没赋值的参数会使用默认值<br>

其中TableParam为表格相关的参数配置，可以设置表格行高，行宽和字号等参数，具体见下表。<br>

参数名称|参数类型|默认值|参数说明
:-------|:-------|:-------|:-------
isLockFirstColumn|Boolean|false|是否锁定第一列
isLockFirstRow|Boolean|true|是否锁定第一行
iMaxColumnWidth|Int|200|列最大宽度
iMinColumnWidth|Int|60|列最小宽度
iMaxRowHeight|Int|60|行最大高度
iMinRowHeight|Int|20|行最小高度
iCellPadding|Int|15|单元格内边距dp
iTextViewSize|Int|16|单元格字体大小
sNullableString|String|“”|空值替换值

## 3.8 ppt查看<br>
PPT文档查看主要提供了一个PPT浏览页面，支持翻页功能。<br>
注：翻页按钮添加了防止快速点击功能，点击的时间间隔需要大于2秒，否则不翻页。<br>
具体使用方法：<br>
在安卓代码中使用intent启动MyPPTActivity，通过intent.putExtra()方法，传入的参数如下：<br>

参数名称|参数类型 |是否必须|参数说明
-------|-------|-------|-------
filePath|String|是|ppt文件路径和名称，不支持传入文件夹
weight|Int|否|屏幕宽度，不传入默认当前屏幕宽度
height|Int|否|屏幕高度，不传入默认当前屏幕宽度
autoPlayType|Int|否|自动播放参数，不传或者传入值有误时为0，即关闭自动播放<br>0：关闭自动播放，点击页面按钮手动翻页<br>1：固定时间翻页，配合autoPlayTime参数<br>
autoPlayTime|Int|否|翻页时间参数，用于自动播放时翻页时间间隔<br>autoPlayType参数传入非0值时有效<br>不传或者传入值有误时为5000，单位：毫秒<br>参数取值范围>=2000
playFinish|Int|否|播放完成参数，用于设置播放完成时页面状态<br>autoPlayType参数传入非0值时有效，不传或者传入值有误时为0<br>0：播放完成页面停在最后一页<br>1：播放完成关闭幻灯片的页面

## 3.9 PDF查看<br>
FDP文档查看主要提供了一个PDF浏览页面，支持翻页，设置显示比例，文件目录查看等功能。<br>
具体使用方法：<br>
在安卓代码中使用intent启动MyPDFActivity，通过intent.putExtra()方法，传入的参数如下：<br>

参数名称|参数类型 |是否必须|参数说明
-------|-------|-------|-------
filePath|String|是|pdf文件路径和名称，不支持传入文件夹

## 3.10 网页浏览<br>
网页主要提供了一个网页浏览页面，支持滑动，输入网址，刷新，前进后退等功能。<br>
具体使用方法：<br>
在安卓代码中使用intent启动MyExplorerActivity，通过intent.putExtra()方法，传入的参数如下：<br>

参数名称|参数类型 |是否必须|参数说明
-------|-------|-------|-------
parameter|String|是|网址
isShow|Int|否|设置是否显示搜索栏和前进后退等操作按钮<br>不传入或者传入值有误时为0<br>0：显示<br>1：不显示

## 3.11 地图查看<br>
地图查看主要提供了一个地图查看的页面，集成高德地图，传入经纬度信息，显示经纬度地点等地图，并支持拖动，双指放大缩小查看，重置位置显示等功能。<br>
具体使用方法：<br>
在安卓代码中使用intent启动MyMapActivity，通过intent.putExtra()方法，传入的参数如下：<br>

参数名称|参数类型 |是否必须|参数说明
-------|-------|-------|-------
title|String|否|标题显示内容，不传则不显示
latitude|Double|否|纬度，不传入默认41.8
longitude|Double|否|经度，不传入默认123.38

## 3.12 涂鸦窗口<br>
涂鸦窗口主要提供了一个涂鸦页面，并支持将涂鸦保存为png图片。<br>
具体使用方法：<br>
在安卓代码中使用intent启动MyDoodleActivity，通过intent.putExtra()方法，传入的参数如下：<br>

参数名称|参数类型 |是否必须|参数说明
-------|-------|-------|-------
fileName|String|是|涂鸦保存时的文件路径和文件名称<br>支持保存为png格式的图片<br>所以该字符串务必以.png结尾<br>例如“/sdcard/doodle.png”

## 3.13 图片轮播<br>
图片轮播窗口主要提供了一个图片轮播页面，设置图片的目录，程序自动查找目录下的图片进行轮播，支持格式为png和jpg，并可以自定义切换效果和切换时间。<br>
具体使用方法：<br>
在安卓代码中使用intent启动MyBannerActivity，通过intent.putExtra()方法，传入的参数如下：<br>

参数名称|参数类型 |是否必须|参数说明
-------|-------|-------|-------
filePath|String|是|图片路径，请传入文件夹目录
delayTime|Int|否|切换图片时间间隔，不传默认为5000ms
bannerAnimation|Int|否|切换图片动画效果，不传入或者传入值有误时为1<br>1：FlipHorizontal<br>2：FlipVertical<br>3：Stack<br>4：CubeIn<br>5：CubeOut<br>6：Accordion<br>7：RotateUp<br>8：RotateDown<br>切换图片动画的具体效果请实际查看。
bannerStyle|Int|否|banner显示风格，不传入或者传入值有误时为1<br>0：不显示指示器和标题<br>1：显示圆形指示器<br>2：显示数字指示器
isAutoPlay|Boolean|否|是否自动轮播，不传入默认true，即自动轮播

# 4. 定位功能<br>
定位功能使用高德定位。<br>
注：在使用定位功能前，请确保程序具有网络访问和位置访问的权限。<br>
## 4.1 账号注册<br>
使用在高德定位官方网站(https://lbs.amap.com )注册并填写信息并完成注册，会得到一个appkey<br>
在Manifest文件<application>节点加入如下配置：<br>
 
    <!-- auto nav location-->
    <meta-data
            android:name="com.amap.api.v2.apikey"
            android:value="在此处填写你的appkey" />
    <!-- auto nav location—>

## 4.2 使用方法<br>
在完成注册之后，在程序中使用方法如下：<br>
class LocationG(pContext: Context, locationParam: LocationParam):LocationAb<br>

参数名称|参数说明
-------|-------
pContext|上下文
locationParam|定位参数，具体见下文

LocationParam类具体如下：<br>

参数名称|参数类型 |是否必须|参数说明
-------|-------|-------|-------
isGpsFirst|Boolean|否|设置是否gps优先，不传默认为false
httpTimeOut|Long|否|设置网络请求超时时间，单位：ms，不传默认为30000ms
isOnceLocation|Boolean|否|设置是否单次定位，不传默认为false
interval|Boolean|否|设置定位时间间隔，单位：ms<br>不传默认为2000ms，仅在非单次定位时有效
isLocationCacheEnable|Boolean|否|设置是否使用缓存定位，不传默认为true

abstract fun getLocation(pLocationCallback: LocationCallback)<br>

参数名称|参数说明
-------|-------
pLocationCallback|定位回调，LocationAb.LocationCallback

abstract fun stopLocation()<br>
用于在连续定位模式下停止定位，只在连续定位时有效，单次定位模式时无效。<br>
具体使用方法：<br>
在使用定位的Activity实现LocationAb.LocationCallback接口，或者在使用时创建LocationAb.LocationCallback，以下示例代码使用Activity实现接口方式：<br>

    var mLocation: LocationAb? = null
    var mLocationParam = LocationParam()
    //在此设置定位参数，在此只设置了httpTimeOut一个参数，其他参数同理
    mLocationParam.httpTimeOut = 10000
    mLocation = LocationG(this, locationParam)
    //开始定位
    mLocation.getLocation(this)
    //停止定位
    mLocation.stopLocation()
    
注：如不用Activity实现接口方式，mLocation.getLocation(this)中的this需使用回调LocationAb.LocationCallback代替。<br>

## 4.3 回调方法<br>
### 4.3.1  开始定位回调<br>
开始定位回调，在开始定位时回调。<br>
fun onStartLocation(msg:String)<br>

参数名称|参数说明
-------|-------
msg|开始定位

### 4.3.2  定位成功回调<br>
定位成功回调，在定位成功时回调，返回定位信息。<br>
fun onLocationFound(locationBean:LocationBean)<br>

参数名称|参数说明
-------|-------
locationBean|LocationBean类对象，定位信息结果

LocationBean类具体如下：<br>

参数名称|参数类型|参数说明
-------|-------|-------
longitude|Double|经度
latitude|Double|纬度
country|String|国家
province|String|省份
city|String|城市
cityCode|String|城市代码
district|String|区县
address|String|具体地址
poiName|String|热点地名

### 4.3.3  停止定位回调
停止定位回调，在调用停止定位方法时回调。<br>
fun onStopLocation(msg:String)<br>

参数名称|参数说明
-------|-------
msg|停止定位

### 4.3.4  定位失败回调<br>
定位失败回调，在定位失败时回调。<br>
fun onLocationError(code:Int, info:String, detail:String)<br>

参数名称|参数说明
-------|-------
code|错误码，具体请根据错误信息定位分析<br>详细错误类别和错误解决方法请在高德官网查看
info|错误信息，用于开发者分析问题
detail|错误详情，可用于页面提示

# 5. 天气查询<br>
天气获取使用的是京东万象接口。在使用时请先注册自己的appKey，请在京东万象网站自行注册(http://wx.jdcloud.com )，并传入注册获得的appKey值，具体见下文WeatherJDWX方法。<br>

## 5.1 使用方法<br>
在程序中使用如下方法:<br>
class WeatherJDWX(city: String, appKey:String) : WeatherAb()<br>

参数名称|参数类型|参数说明
-------|-------|-------
city|String|获取天气的城市名称，例如：沈阳
appKey|String|获取天气的key<br>请使用者在京东万象网站自己注册账号<br>传入使用者注册的appKey

abstract fun getWeather(pWeatherCallback: WeatherCallback)<br>

参数名称|参数说明
-------|-------
pWeatherCallback|天气信息回调，WeatherCallback

在使用天气的Activity实现WeatherAb.WeatherCallback接口，或者在使用时创建WeatherAb.WeatherCallback，以下示例代码使用Activity实现接口方式

        var mWeather: WeatherAb? = null
        //传入城市和appKey
        mWeather =WeatherJDWX(“沈阳”, "在此处填写你的appkey")
        mWeather.getWeather(this)
        
注：如不使用Activity实现接口方式，mWeather.getWeather(this)中的this需使用回调WeatherAb.WeatherCallback代替。<br>

## 5.2 回调方法<br>
### 5.2.1  开始获取天气回调<br>
开始获取天气信息时回调<br>
fun onStartWeather(msg:String)<br>

参数名称|参数说明
-------|-------
msg|开始获取 $city 天气

### 5.2.2  获取天气成功回调<br>
获取天气信息成功时回调<br>
fun onWeatherFound(weatherBean:WeatherBean)<br>

参数名称|参数说明
-------|-------
weatherBean|WeatherBean类对象，天气信息结果

### 5.2.3  获取天气失败回调
获取天气失败回调，在获取天气失败时回调。<br>
fun onWeatherError(code:String, msg:String)

参数名称|参数说明
-------|-------
code|错误码
msg|返回获取天气失败的原因

获取天气信息失败的错误码，具体如下：

参数名称|参数说明
-------|-------
100|接口访问错误，请检查网络
10001|错误的请求appkey，请前往http://wx.jdcloud.com 注册并查看
10002|查询城市没有找到，请确认传入正确的城市参数
10004|appKey参数不能为空，请传入正确的appKey
11010|接口调用异常，请稍后再试
10010|接口需要付费
10020|万象系统繁忙，请稍后再试
10030|调用万象网关失败， 请与万象联系
10040|超过每天限量，请明天继续，每天次数5000次
10050|用户已被禁用
10060|提供方设置调用权限，请联系提供方

# 6. 文件管理<br>
文件管理主要是针对本地文件的查找和删除等操作，并根据使用习惯自己建立了文件实体类，主要都是以此文件实体类操作。<br>
## 6.1 文件实体类<br>
文件实体类(FileBean)为自定义的类，主要为文件的基本参数，具体如下表：<br>

参数名称|参数类型|参数说明
-------|-------|-------
isDirectory|Boolean|是否是目录，默认为false
fileName|String|文件名称
filePath|String|文件目录
fileParentPath|String|文件所在目录
fileSize|Long|文件大小
fileCustomSize|String|文件正常显示的大小，比如300KB，5.2MB等
fileType|String|文件类型，即文件拓展名
fileUpdateTime|Long|文件更新时间
fileCanRead|Boolean|文件是否可读
fileCanWrite|Boolean|文件是否可写

## 6.2 文件查找实体类<br>
文件查找实体类(FileSearchBean)为自定义的类，主要为文件查找的结果参数，具体如下表：<br>

参数名称|参数类型|参数说明
-------|-------|-------
code|Int|查找结果代码，详情见下文
msg|String|查找结果信息，详情见下文
fileBeans|List<FileBean>|查找结果列表，详情见下文
    
## 6.3 文件管理<br>
文件管理类(FileManager)，主要是一些文件查找和判断的方法，具体方法如下：<br>

### 6.3.1 判断文件存在和类型匹配<br>
判断文件是否存在，并且类型是否相符，具体方法如下表所示：<br>
fun fileExist(path: String, fileType: String): FileSearchBean<br>

参数名称|参数类型|参数说明
-------|-------|-------
path|String|查找文件的路径
fileType|String|查找文件的类型
返回值|FileSearchBean|返回类型，通过code判断查找和类型匹配结果<br>200：文件存在且格式匹配<br>300：文件存在但格式不匹<br>400：存在但不是文件，比如是目录<br>500：文件不存在<br>600：path是空<br>

### 6.3.2 查找指定目录的文件<br>
查找指定目录的所有文件，不包含子目录中的文件，如下表所示：<br>
fun findFileByPath(path: String): FileSearchBean<br>
参数名称|参数类型|参数说明
-------|-------|-------
path|String|查找文件的路径
fileType|String|查找文件的类型
返回值|FileSearchBean|返回类型，通过code判断查找结果<br>200：查找成功，fileBeans为查找文件的结果<br>300：目录为空<br>400：目录不存在<br>
注：虽然此方法不会查找子目录中的文件，但是目录也会在返回结果fileBeans中返回，可以判断isDirectory参数，如果为true，再次调用此方法查找子目录的文件。<br>

### 6.3.3 按关键字查找指定目录的文件<br>
查找指定目录包含关键字的所有文件，不包含子目录中的文件，如下表所示：<br>
fun findFileByName(path: String, keyWord: String): FileSearchBean<br>
参数名称|参数类型|参数说明
-------|-------|-------
path|String|查找文件的路径
keyWord|String|查找文件关键字
返回值|FileSearchBean|返回类型，通过code判断查找结果<br>100：path为文件<br>200：查找成功，fileBeans为查找文件的结果<br>300：目录为空<br>400：目录不存在<br>

### 6.3.4 按关键字删除指定目录的文件
删除指定目录包含关键字的所有文件，不包含子目录中的文件，如下表所示：<br>
fun deleteFile(path: String, keyWord: String): Boolean<br>
参数名称|参数类型|参数说明
-------|-------|-------
path|String|查找文件的路径
keyWord|String|查找文件关键字
返回值|FileSearchBean|返回类型，删除结果<br>true：删除成功<br>false：删除失败<br>

### 6.3.5 拆分文件路径名称和类型<br>
将文件路径+名称拆分成路径，名称和类别，例如传入“/sdcard/folder/test.docx”<br>
fun subFileName(path: String): String 方法返回文件名：test.docx<br>
fun subFilePath(path: String): String 方法返回文件路径：/sdcard/folder<br>
fun getFileType(path: String): String 方法返回文件类型：docx<br>

# 7. 日志功能<br>
在本章对aar里的日志模块进行说明，并提供相应的使用方法。<br>
## 7.1 LogDebug模块<br>
此模块对安卓自带的Log模块进行简单封装，添加Tag，具体方法如下：
参数名称|传入参数|使用说明和示例
-------|-------|-------
v|tag: String, value: String|LogDebug.v("tag", "log内容")
d|tag: String, value: String|LogDebug.d("tag", "log内容")
e|tag: String, value: String|LogDebug.e("tag", "log内容")
LogDebugTAG|tag: String|LogDebug.LogDebugTAG("通用tag")<br>通用tag，在每个LogDebug前都会添加通用tag，最后tag的内容为：通用tag+tag
LogDebugSwitch|flag: Boolean|LogDebug.LogDebugSwitch(false)<br>设置LogDebug开关，默认为true

## 7.2 Toast模块<br>
此模块ToastUtil类，将安卓自带的toast进行封装，共有如下三个方法：<br>
参数名称|传入参数|使用说明和示例
-------|-------|-------
shortToast|mContent: Content, toast: String|Toast.shortToast(mContent, "toast内容")
longToast|mContent: Content, toast: String|Toast.longToast(mContent, "toast内容")
setToastSwitch|flag: Boolean|Toast.setToastSwitch(false)

# 8. 其他功能<br>
在本章对aar里的其他小功能模块进行说明，并提供相应的使用方法。<br>


