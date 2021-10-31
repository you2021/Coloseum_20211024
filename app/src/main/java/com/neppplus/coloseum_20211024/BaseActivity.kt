package com.neppplus.coloseum_20211024

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity:AppCompatActivity() {

   abstract fun setupEvents()
   abstract fun setValues()
}