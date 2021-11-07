package com.neppplus.coloseum_20211024.adapters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.coloseum_20211024.BaseActivity
import com.neppplus.coloseum_20211024.R
import com.neppplus.coloseum_20211024.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity() {

    lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_splash)
        setupEvents()

    }

    override fun setValues() {
        TODO("Not yet implemented")
    }

    override fun setupEvents() {
        TODO("Not yet implemented")
    }
}