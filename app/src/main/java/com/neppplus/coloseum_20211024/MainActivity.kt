package com.neppplus.coloseum_20211024

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.coloseum_20211024.databinding.ActivityMainBinding
import com.neppplus.coloseum_20211024.datas.TopicData
import com.neppplus.coloseum_20211024.utils.ServerUtil
import org.json.JSONObject
import java.util.ArrayList

class MainActivity : BaseActivity() {

    lateinit var binding : ActivityMainBinding

    val mTopicList = ArrayList<TopicData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        // /v2.main.info API가 토론 주제 목록을 내려줌
        // 서버 호출 => 파싱해서 mTopicList를 채워주자
        getTopicListFromServer()





//        // 연습 - 내 정보 받아오기 호출 => 닉네임 파싱, 텍스트뷰에 반영
//        ServerUtil.getRequestMyInfo(mContext,object : ServerUtil.JsonResponseHandler{
//            override fun onResponse(jsonObj: JSONObject) {
//                val dataObj = jsonObj.getJSONObject("data")
//                val userObj = dataObj.getJSONObject("user")
//                val nickname = userObj.getString("nick_name")
//                runOnUiThread{
//                    binding.nicknameTxt.text = nickname
//                }
//            }
//
//        })
    }

    fun getTopicListFromServer(){
        ServerUtil.getRequestMainInfo(mContext, object  : ServerUtil.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {

            }

        })
    }


}