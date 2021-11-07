package com.neppplus.coloseum_20211024.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.neppplus.coloseum_20211024.R
import com.neppplus.coloseum_20211024.datas.ReplyData
import com.neppplus.coloseum_20211024.datas.TopicData

class ReplyAdapter(
    val mContext:Context,
    resId:Int,  // 일회용성으로 사용한다면 변수명을 붙이지 않는다.
    val mList:List<ReplyData>) :ArrayAdapter<ReplyData>(mContext,resId,mList){

    val mInflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var temPow = convertView
        if (temPow == null){
            temPow = mInflater.inflate(R.layout.reply_list_item, null)
        }
        val row = temPow!!

        val data = mList[position]



        return row
    }
}