package com.neppplus.coloseum_20211024

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.neppplus.coloseum_20211024.adapters.ReplyAdapter
import com.neppplus.coloseum_20211024.databinding.ActivityViewTopicDetailMainBinding
import com.neppplus.coloseum_20211024.datas.ReplyData
import com.neppplus.coloseum_20211024.datas.TopicData
import com.neppplus.coloseum_20211024.utils.ServerUtil
import org.json.JSONObject

class ViewTopicDetailMainActivity : BaseActivity() {

    lateinit var binding : ActivityViewTopicDetailMainBinding

    lateinit var mTopicData : TopicData

    val mReplyList = ArrayList<ReplyData>()

    lateinit var mReplyAdapter: ReplyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_topic_detail_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
    }

    override fun setValues() {

        mTopicData = intent.getSerializableExtra("topic") as TopicData

        binding.topicTitleTxt.text = mTopicData.title

        Glide.with(mContext).load(mTopicData.imageURL).into(binding.topicImg)

        getTopicDetailFromServer()

        mReplyAdapter = ReplyAdapter(mContext, R.layout.reply_list_item, mReplyList)
        binding.replyListView.adapter = mReplyAdapter

    }

    fun getTopicDetailFromServer(){
        ServerUtil.getRequestTopicDetail(mContext, mTopicData.id, "NEW", object : ServerUtil.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {

                // 댓글 목록  JSONArray -> 파싱 -> mReplyList의 자료로 추가

                val dataObj = jsonObj.getJSONObject("data")
                val topicObj = dataObj.getJSONObject("topic")

                val repliesArr = topicObj.getJSONArray("replies")
                for(i in 0 until repliesArr.length()){
                    val replyObj = repliesArr.getJSONObject(i)

                    // JSONObject -> ReplyData 객체로 변함
                    val replyData = ReplyData()
                    replyData.id = replyObj.getInt("id")
                    replyData.content = replyObj.getString("content")

                    // 댓슬목록으로 추가
                    mReplyList.add(replyData)
                }
                // 리스트뷰의 목록에 변경 => 어댑터 새로고침 (UI 변경)
                runOnUiThread {
                    mReplyAdapter.notifyDataSetChanged()

                }
            }

        })
    }


}