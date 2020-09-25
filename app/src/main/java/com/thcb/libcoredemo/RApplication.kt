package com.thcb.libcoredemo

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.thcb.libcore.InitLibCore
import com.thcb.libcore.function.authcode.AuthCodeAb
import com.thcb.libcore.units.LogDebug
import com.thcb.libcoredemo.params.StaticParameter
import java.io.File

class RApplication : Application() {

    private val TAG = "RApplication"

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        LogDebug.d(TAG, "onCreate")
        val initLibCore = InitLibCore()
        initLibCore.init(
            context,
            "794d248e1629f6d2ddb36e4acacbeb56",
            "",
            object : AuthCodeAb.AuthCodeCallback {
                override fun onSuccess(msg: String) {
                    LogDebug.d(TAG, "initLibCore onSuccess:$msg")
                    LogDebug.d(TAG, "getVersion:${initLibCore.getVersion()}")
                }

                override fun onError(code: Int, msg: String) {
                    LogDebug.d(TAG, "initLibCore onError,code:$code,msg:$msg")
                }
            })
        mkdirsFolders()
    }

    private fun mkdirsFolders() {
        try {
            val localFile = File(StaticParameter.DEFAULT_PATH)
            if (!localFile.exists()) {

                var dir = File(StaticParameter.DEFAULT_MUSIC_PATH)
                if (!dir.exists()) {
                    dir.mkdirs()
                }
                dir = File(StaticParameter.DEFAULT_VIDEO_PATH)
                if (!dir.exists()) {
                    dir.mkdirs()
                }
                dir = File(StaticParameter.DEFAULT_PHOTO_PATH)
                if (!dir.exists()) {
                    dir.mkdirs()
                }
                dir = File(StaticParameter.DEFAULT_GIF_PATH)
                if (!dir.exists()) {
                    dir.mkdirs()
                }
                dir = File(StaticParameter.DEFAULT_WORD_PATH)
                if (!dir.exists()) {
                    dir.mkdirs()
                }
                dir = File(StaticParameter.DEFAULT_EXCEL_PATH)
                if (!dir.exists()) {
                    dir.mkdirs()
                }
                dir = File(StaticParameter.DEFAULT_PPT_PATH)
                if (!dir.exists()) {
                    dir.mkdirs()
                }
                dir = File(StaticParameter.DEFAULT_PDF_PATH)
                if (!dir.exists()) {
                    dir.mkdirs()
                }
                dir = File(StaticParameter.DEFAULT_WEB_PATH)
                if (!dir.exists()) {
                    dir.mkdirs()
                }
                dir = File(StaticParameter.DEFAULT_DOODLE_PATH)
                if (!dir.exists()) {
                    dir.mkdirs()
                }
            }
        } catch (e: Exception) {
            LogDebug.e(TAG, "mkdirsFolders failure")
        }
    }

}