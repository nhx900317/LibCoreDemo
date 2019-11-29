package com.thcb.libcoredemo.params

import android.annotation.SuppressLint
import com.thcb.libcore.params.MyStaticParameter

/**
 * @author nhx
 * @date 2019/11/12
 * 描述：静态参数类
 */

@SuppressLint("StaticFieldLeak")
object StaticParameter {

    /** 本地目录 **/
    var DEFAULT_PATH = "${MyStaticParameter.ROOT_PATH}/LibCoreDemo"
    var DEFAULT_MUSIC_PATH = "$DEFAULT_PATH/music/"
    var DEFAULT_VIDEO_PATH = "$DEFAULT_PATH/video/"
    var DEFAULT_PHOTO_PATH = "$DEFAULT_PATH/photo/"
    var DEFAULT_GIF_PATH = "$DEFAULT_PATH/gif/"
    var DEFAULT_WORD_PATH = "$DEFAULT_PATH/word/"
    var DEFAULT_EXCEL_PATH = "$DEFAULT_PATH/excel/"
    var DEFAULT_PPT_PATH = "$DEFAULT_PATH/ppt/"
    var DEFAULT_PDF_PATH = "$DEFAULT_PATH/pdf/"
    var DEFAULT_WEB_PATH = "$DEFAULT_PATH/web/"
    var DEFAULT_DOODLE_PATH = "$DEFAULT_PATH/doodle/"
}