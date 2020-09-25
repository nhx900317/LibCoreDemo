package com.thcb.libcoredemo.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.tech57.software.filepicker.MaterialFilePicker
import com.tech57.software.filepicker.ui.FilePickerActivity
import com.thcb.libcore.function.file.FileHolder
import com.thcb.libcore.function.location.*
import com.thcb.libcore.function.weather.WeatherAb
import com.thcb.libcore.function.weather.WeatherBean
import com.thcb.libcore.function.weather.WeatherJDWX
import com.thcb.libcore.ui.*
import com.thcb.libcore.units.LogDebug
import com.thcb.libcore.units.ToastUtil
import com.thcb.libcoredemo.R
import com.thcb.libcoredemo.RApplication
import com.thcb.libcoredemo.params.StaticParameter

class MainActivity : Activity(), LocationAb.LocationCallback, WeatherAb.WeatherCallback {

    private val TAG = "MainActivity"
    private var mContext: Context? = null
    private var tvInfoShow: TextView? = null
    private var btFileHolder: Button? = null
    private var btMusic: Button? = null
    private var btVideo: Button? = null
    private var btPhoto: Button? = null
    private var btAnimation: Button? = null
    private var btWord: Button? = null
    private var btExcel: Button? = null
    private var btPPt: Button? = null
    private var btPDF: Button? = null
    private var btWeb: Button? = null
    private var btGetLocation: Button? = null
    private var btStopLocation: Button? = null
    private var btMap: Button? = null
    private var btWeather: Button? = null
    private var etWeather: EditText? = null
    private var btDoodle: Button? = null
    private var btBanner: Button? = null
    private var mLocation: LocationAb? = null
    private var mWeather: WeatherAb? = null
    private var mCity = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mContext = this
        initView()
        onClick()
    }

    private fun initView() {
        tvInfoShow = this.findViewById(R.id.info_show)
        btFileHolder = this.findViewById(R.id.file_holder_bt)
        btMusic = this.findViewById(R.id.music_bt)
        btVideo = this.findViewById(R.id.video_bt)
        btPhoto = this.findViewById(R.id.photo_bt)
        btAnimation = this.findViewById(R.id.animation_play_bt)
        btWord = this.findViewById(R.id.word_bt)
        btExcel = this.findViewById(R.id.excel_bt)
        btPPt = this.findViewById(R.id.ppt_bt)
        btPDF = this.findViewById(R.id.pdf_bt)
        btWeb = this.findViewById(R.id.web_bt)
        btGetLocation = this.findViewById(R.id.get_location_bt)
        btStopLocation = this.findViewById(R.id.stop_location_bt)
        btMap = this.findViewById(R.id.map_bt)
        btWeather = this.findViewById(R.id.weather_bt)
        etWeather = this.findViewById(R.id.weather_et)
        btDoodle = this.findViewById(R.id.doodle_bt)
        btBanner = this.findViewById(R.id.banner_bt)
    }

    private fun onClick() {
        btFileHolder!!.setOnClickListener {
            showFileChooser()
        }
        btMusic!!.setOnClickListener {
            val intent = Intent(mContext, MyMusicActivity::class.java)
            intent.putExtra("filePath", StaticParameter.DEFAULT_MUSIC_PATH)
            mContext!!.startActivity(intent)
        }
        btVideo!!.setOnClickListener {
            val intent = Intent(mContext, MyVideoActivity::class.java)
            intent.putExtra("filePath", StaticParameter.DEFAULT_VIDEO_PATH + "test.mp4")
            mContext!!.startActivity(intent)
        }
        btPhoto!!.setOnClickListener {
            val intent = Intent(mContext, MyPhotoActivity::class.java)
            intent.putExtra("filePath", StaticParameter.DEFAULT_PHOTO_PATH + "1.jpg")
            mContext!!.startActivity(intent)
        }
        btAnimation!!.setOnClickListener {
            val intent = Intent(mContext, MyAnimationActivity::class.java)
            intent.putExtra("filePath", StaticParameter.DEFAULT_GIF_PATH + "animation_smile.gif")
            mContext!!.startActivity(intent)
        }
        btWord!!.setOnClickListener {
            val intent = Intent(mContext, MyWordActivity::class.java)
            intent.putExtra("filePath", StaticParameter.DEFAULT_WORD_PATH + "test.docx")
            mContext!!.startActivity(intent)
        }
        btExcel!!.setOnClickListener {
            val intent = Intent(mContext, MyExcelActivity::class.java)
            intent.putExtra("filePath", StaticParameter.DEFAULT_EXCEL_PATH + "template.xls")
            intent.putExtra("title", "/template.xls")
            mContext!!.startActivity(intent)
        }
        btPPt!!.setOnClickListener {
            val intent = Intent(mContext, MyPPTActivity::class.java)
            intent.putExtra("filePath", StaticParameter.DEFAULT_PPT_PATH + "1234.pptx")
            mContext!!.startActivity(intent)
        }
        btPDF!!.setOnClickListener {
            val intent = Intent(mContext, MyPDFActivity::class.java)
            intent.putExtra("filePath", StaticParameter.DEFAULT_PDF_PATH + "test.pdf")
            mContext!!.startActivity(intent)
        }
        btWeb!!.setOnClickListener {
            val intent = Intent(mContext, MyExplorerActivity::class.java)
            intent.putExtra("parameter", "www.baidu.com")
            mContext!!.startActivity(intent)
        }
        btGetLocation!!.setOnClickListener {
            val locationParam = LocationParam()
            mLocation = LocationG(this, locationParam)
            mLocation!!.getLocation(this)
        }
        btStopLocation!!.setOnClickListener {
            mLocation!!.stopLocation()
        }

        btMap!!.setOnClickListener {
            val intent = Intent(mContext, MyMapActivity::class.java)
            intent.putExtra("latitude", 39.761)
            intent.putExtra("longitude", 116.434)
            mContext!!.startActivity(intent)
        }
        btWeather!!.setOnClickListener {
            when {
                etWeather!!.text.toString() != "" -> {
                    mWeather = WeatherJDWX(etWeather!!.text.toString(), "你的appkey，请前往京东万象开放平台注册")
                    mWeather!!.getWeather(this)
                }
                mCity != "" -> {
                    mWeather = WeatherJDWX(mCity, "你的appkey，请前往京东万象开放平台注册")
                    mWeather!!.getWeather(this)
                }
                else -> {
                    ToastUtil.longToast(
                        this, RApplication.context.getString(
                            R.string.input_city
                        )
                    )
                }
            }
        }
        btDoodle!!.setOnClickListener {
            val intent = Intent(mContext, MyDoodleActivity::class.java)
            intent.putExtra("fileName", StaticParameter.DEFAULT_DOODLE_PATH + "test.png")
            mContext!!.startActivity(intent)
        }
        btBanner!!.setOnClickListener {
            val intent = Intent(mContext, MyBannerActivity::class.java)
            intent.putExtra("filePath", StaticParameter.DEFAULT_PHOTO_PATH)
            intent.putExtra("delayTime", 8)
            mContext!!.startActivity(intent)
        }
    }

    override fun onStartLocation(msg: String) {
        LogDebug.d(TAG, "onStartLocation:$msg")
        tvInfoShow!!.text = msg
    }

    @SuppressLint("SetTextI18n")
    override fun onLocationFound(locationBean: LocationBean) {
        LogDebug.d(TAG, "onLocationFound:${locationBean.address}")
        tvInfoShow!!.text = locationBean.address
        mCity = locationBean.city
    }

    override fun onStopLocation(msg: String) {
        LogDebug.d(TAG, "onStopLocation:$msg")
    }

    override fun onLocationError(code: Int, info: String, detail: String) {
        LogDebug.d(TAG, "onLocationError:$code,info:$info,detail:$detail")
        tvInfoShow!!.text = detail
    }

    override fun onStartWeather(msg: String) {
        LogDebug.d(TAG, "onStartWeather:$msg")
    }

    @SuppressLint("SetTextI18n")
    override fun onWeatherFound(weatherBean: WeatherBean) {
        LogDebug.d(TAG, "onWeatherFound:${weatherBean.now.tmp}")
        tvInfoShow!!.text =
            "${weatherBean.basic.city}," +
                    "今天白天:${weatherBean.daily_forecast[0].cond.txt_d}," +
                    "温度:${weatherBean.daily_forecast[0].tmp.max}~${weatherBean.daily_forecast[0].tmp.min}℃," +
                    "风向:${weatherBean.daily_forecast[0].wind.dir}${weatherBean.daily_forecast[0].wind.sc}级"
    }

    @SuppressLint("SetTextI18n")
    override fun onWeatherError(code: String, msg: String) {
        LogDebug.d(TAG, "onWeatherError:code:$code,msg:$msg")
        tvInfoShow!!.text = "code:$code,msg:$msg"
    }

    private fun showFileChooser() {
        MaterialFilePicker()
            .withRequestCode(1)
            .withActivity(this)
            .withPath(StaticParameter.DEFAULT_PATH)
            .withHiddenFiles(true)
            .withTitle(RApplication.context.getString(R.string.select_file_title))
            .start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (data != null) {
                val path = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH)
                LogDebug.d(TAG, "path:$path")
                FileHolder.openFile(this, path)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mContext = null
    }
}
