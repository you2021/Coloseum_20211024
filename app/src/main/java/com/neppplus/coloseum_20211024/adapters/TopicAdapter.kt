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
import com.neppplus.coloseum_20211024.datas.TopicData

class TopicAdapter(
    val mContext:Context,
    resId:Int,  // 일회용성으로 사용한다면 변수명을 붙이지 않는다.
    val mList:List<TopicData>) :ArrayAdapter<TopicData>(mContext,resId,mList){

    val mInflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var temPow = convertView
        if (temPow == null){
            temPow = mInflater.inflate(R.layout.tocpic_list_item, null)
        }
        val row = temPow!!

        val topicData = mList[position]

        val topicImg = row.findViewById<ImageView>(R.id.topicImg)
        val topicTitleTxt = row.findViewById<TextView>(R.id.topicTitleTxt)
        val replyCountTxt = row.findViewById<TextView>(R.id.replyCountTxt)

        topicTitleTxt.text = topicData.title
        Glide.with(mContext).load(topicData.imageURL).into(topicImg)
        replyCountTxt.text = "현재 의견 : ${topicData.replyCount}개"

        return row
    }
}