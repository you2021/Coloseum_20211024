package com.neppplus.coloseum_20211024.datas

import org.json.JSONObject
import java.io.Serializable

class SideData : Serializable {

    var id = 0  // Int가 들어올것이라고 명시
    var title = "" // String이 들어올 자리라고 명시
    var voteCount = 0

    companion object{
        fun getSideDataFromJSON(jsonObject: JSONObject) : SideData{
            val sideData = SideData()
            sideData.id = jsonObject.getInt("id")
            sideData.title = jsonObject.getString("title")
            sideData.voteCount = jsonObject.getInt("vote_count")

            return sideData

        }
    }

}