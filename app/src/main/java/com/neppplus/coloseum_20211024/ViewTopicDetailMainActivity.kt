package com.neppplus.coloseum_20211024

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.coloseum_20211024.databinding.ActivityViewTopicDetailMainBinding

class ViewTopicDetailMainActivity : BaseActivity() {

    lateinit var binding : ActivityViewTopicDetailMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_topic_detail_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
    }

    override fun setValues() {
    }

}