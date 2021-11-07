package com.neppplus.coloseum_20211024

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.neppplus.coloseum_20211024.adapters.SplashActivity
import com.neppplus.coloseum_20211024.adapters.TopicAdapter
import com.neppplus.coloseum_20211024.databinding.ActivityMainBinding
import com.neppplus.coloseum_20211024.datas.TopicData
import com.neppplus.coloseum_20211024.utils.ServerUtil
import org.json.JSONObject
import java.util.ArrayList

class MainActivity : BaseActivity() {

    lateinit var binding : ActivityMainBinding

    lateinit var mTopicAdapter: TopicAdapter

    val mTopicList = ArrayList<TopicData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.topicListView.setOnItemClickListener { adapterView, view, position, l ->

            // 연습 - 클릭한 주제의 제목을 토스트로
            val clickedTopic = mTopicList[position]

//            Toast.makeText(mContext, clickedTopic.title, Toast.LENGTH_SHORT).show()

            // 실제 - 클릭한 주제 상세보기 => 주제 데이터를 들고 이동.

            val myIntent = Intent(mContext, ViewTopicDetailMainActivity::class.java)
            myIntent.putExtra("topic",clickedTopic)
            startActivity(myIntent)
        }

        binding.logoutBtn.setOnClickListener {
            // 로그아웃 구현  => 진짜 로그아웃?
            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("로그아웃")
            alert.setMessage("정말 로그아웃 하시겠습니까?")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener{ dialogInterface, i ->

            // 확인 눌리면 할일
            // 로그아웃 : 저장된 토큰값을 파기(토큰 제거)
            ContextUtil.setToken(mContext, "")
            val myIntent = Intent(mContext, SplashActivity::class.java)
            startActivity(myIntent)

            finish()

            })
            alert.setNegativeButton("취소", null)
            alert.show()
        }
    }

    override fun setValues() {

        // /v2.main.info API가 토론 주제 목록을 내려줌
        // 서버 호출 => 파싱해서 mTopicList를 채워주자
        getTopicListFromServer()

        mTopicAdapter = TopicAdapter(mContext, R.layout.tocpic_list_item, mTopicList)
        binding.topicListView.adapter = mTopicAdapter

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

                val dataObj = jsonObj.getJSONObject("data")
                val topicsArr = dataObj.getJSONArray("topics")

                // 0번째 주제 - topicsArr 갯수 직전까지를 반복
                // 5개 주제 : 0 ~ 4번 주제까지 (5개)
                for (index in 0 until topicsArr.length()){
                    Log.d("반복문 확인", index.toString())

                    // [] 안에 있는 {}를 순서대로 찾아내서 파싱 하자
                    val topicObj = topicsArr.getJSONObject(index)

                    Log.d("토론주제", topicObj.getString("title"))

                    // topicObj는 토론 주제에 필요한 데이터를 들고 있다.
                    // TopicData() 형태로 변환해주자 => 목록에 추가해주자.

                    val topicData = TopicData()
                    topicData.id = topicObj.getInt("id")
                    topicData.title = topicObj.getString("title")
                    topicData.imageURL = topicObj.getString("img_url")

                    // 만들어진 topicData를 목록에 추가
                    mTopicList.add(topicData)
                }

                // for문이 끝나면, mTopicList에 모든 토론 주제가 추가된 상태다.
                // 어댑터가 변경사항을 감지하도록 처리하자 => 내용 반영 : UI 변경(백그라운드)

                runOnUiThread{  // 백그라운드이기에 이 안에 하는것이 안전하다.
                    mTopicAdapter.notifyDataSetChanged()
                }

            }

        })
    }


}