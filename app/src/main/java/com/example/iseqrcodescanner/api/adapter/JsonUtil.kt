package cob.cob3_1_2.api.adapter

import cob.cob3_1_2.api.model.Item
import cob.cob3_1_2.api.model.Model
import org.json.JSONArray
import org.json.JSONObject

class JsonUtil {
    companion object{
        private fun <M: Model> jadikanArrayList(json: JSONArray, fungsi: (JSONObject) -> M): ArrayList<M>{
            val array= ArrayList<M>()
            for(i in 0 until json.length())
                array.add(fungsi(json.getJSONObject(i)))
            return array
        }

        fun jadikanItemArrayList(json: JSONArray): ArrayList<Item>{
            return jadikanArrayList(json){ jsonObject ->
                Item(jsonObject.getString("id"),
                     jsonObject.getString("nama"),
                     jsonObject.getInt("harga"))
            }
        }
    }
}