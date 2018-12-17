package com.nicname.iyeongjun.gwangju_contest.extension

import android.util.Log
import com.google.gson.Gson
import com.nicname.iyeongjun.dobike.R
import org.json.XML
fun String?.toIntViaDouble() = this?.toDouble()?.toInt()
fun String.toIntViaDouble() = this.toDouble()?.toInt()
fun String.getTag() : String = this.replace("$"," #").trim()

fun String.getResource() : Int{
    var result : Int
    when(this){
        "SKY_S01" -> result = R.drawable.weather_sunny
        "SKY_S12" -> result = R.drawable.weather_rainy
        else -> result = R.drawable.weather_cloudy
    }
    return result
}

fun String.getLimitedString( length: Int): String {
    var result = ""
    val arr = this.split("")
    var count = 0
    for (node in arr) {
        if (count < length) {
            if (node == " ")
                count += 1
            else
                count += 2
            result += node
        }
    }
    result += ".."
    return result
}

fun String?.setNull() : String{
    if(this == null){
        return "정보가 없습니다"
    }else{
        return this
    }
//    val a = "".xmlToJson(RentModel::class.java)
//    Gson().fromJson("", RentModel::class.java)
}

fun <T> String.xmlToJson(cla : Class<T>) : T{
    val str = XML.toString(this)
    return Gson().fromJson(str,cla)

}
fun String.convertType(): String {
    val TOUR = 12
    val COULTURE = 14
    val CANIVAL = 15
    val REPORTS = 28
    val INN = 32
    val SHOPPING = 38
    val FOOD = 39
    val COURSE = 25
    val ALL = 10000
    /*타국가*/
    val OTOUR = 76
    val OCOULTURE = 78
    val OCANIVAL = 85
    val OREPORTS = 75
    val OINN = 80
    val OSHOPPING = 79
    val OFOOD = 82
    val OTANS = 7

    var result = ""
    val type = Integer.parseInt(this)
    if (type == TOUR || type == OTOUR)
        result = "관광"
    else if (type == COULTURE || type == OCOULTURE)
        result = "문화시설"
    else if (type == CANIVAL || type == OCANIVAL)
        result = "행사/공연"
    else if (type == REPORTS || type == OREPORTS)
        result = "레포츠"
    else if (type == INN || type == OINN)
        result = "숙박"
    else if (type == SHOPPING || type == OSHOPPING)
        result = "쇼핑"
    else if (type == FOOD || type == OFOOD)
        result = "음식점"
    else if (type == COURSE || type == OTANS)
        result = "여행코스"
    else if (type == ALL)
        result = "문화"
    else
        result = "기타"
    return result
}

fun String.convertTypeToInt() : Int{
    val TOUR = 12
    val COULTURE = 14
    val CANIVAL = 15
    val REPORTS = 28
    val INN = 32
    val SHOPPING = 38
    val FOOD = 39
    val COURSE = 25
    val ALL = 10000

    when(this){
        "관광" -> return TOUR
        "문화시설" -> return COULTURE
        "행사/공연" -> return CANIVAL
        "레포츠" -> return REPORTS
        "숙박" -> return INN
        "쇼핑" -> return SHOPPING
        "음식점" -> return FOOD
        "여행코스" -> return COURSE
        else -> return ALL
    }
}

fun String.getTourToDate() : String {

    val temp = this.substring(0,8)
    val year = temp.substring(0,4)
    val month = temp.substring(4,6)
    val day = temp.substring(6,8)

    for (i in temp) Log.d(this,i.toString())

    return "$year - $month - $day"
}
fun String.getProvince() : String{
    var result = ""
    when(this){
        "서울" -> result = "서울특별시"
        "인천" -> result = "인천광역시"
        "울산" -> result = "울산광역시"
        "대구" -> result = "대구광역시"
        "대전" -> result = "대전광역시"
        "광주" -> result = "광주광역시"
        "부산" -> result = "부산광역시"
        "경기" -> result = "경기도"
        "제주도" -> result = "제주특별자치도"
        else -> result = this
    }
    Log.d("temp", "$this /")
    return result
}