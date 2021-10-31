package com.neppplus.coloseum_20211024

import android.content.Context

class ContextUtil {
    companion object {
        // 외부 노출 X  -> private
       private val prefName = "ColosseumPref"

        private val TOKEN ="TOKEN"

        // 자동로그인 체크 여부 저장
        private val AUTO_LOGIN = "AUTO_LOGIN"

        // 자동 로그인 - setter/getter 작성
        fun setAutoLogin(context: Context, isAuto:Boolean){
            val pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
            pref.edit().putBoolean(AUTO_LOGIN, isAuto).apply()
        }

        fun getAutoLogin(context: Context):Boolean{
            val pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
            return pref.getBoolean(AUTO_LOGIN, false)
        }

        //setter - 저장 기능
        fun setToken(context:Context, token:String){
            // 메모장을 불러내자.
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            // 불러낸 메모장에 token 값 기록
            pref.edit().putString(TOKEN, token).apply()

        }
        //getter - 조회 기능. LOAD

        fun getToken(context: Context) : String{
            // 메모장을 불러내자
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            // 불러낸 메모장에서 token 값 찾아서 리턴(결과로 지정)
            return  pref.getString(TOKEN,  "")!!
        }
    }
}