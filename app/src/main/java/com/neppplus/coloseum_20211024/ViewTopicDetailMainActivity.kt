package com.neppplus.coloseum_20211024

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.neppplus.coloseum_20211024.databinding.ActivityViewTopicDetailMainBinding
import com.neppplus.coloseum_20211024.datas.TopicData

class ViewTopicDetailMainActivity : BaseActivity() {

    lateinit var binding : ActivityViewTopicDetailMainBinding

    lateinit var mTopicData : TopicData

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

    }

}