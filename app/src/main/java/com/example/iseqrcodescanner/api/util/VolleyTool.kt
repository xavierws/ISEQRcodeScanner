package cob.cob3_1_2.api.util

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class VolleyTool{
    companion object {
        @JvmStatic private var RQ: RequestQueue?= null
/*
        @JvmStatic
        fun RQ(aktifitas: Context): RequestQueue{
            if(RQ == null){
                RQ= Volley.newRequestQueue(aktifitas)
            }
            return RQ
        }
*/
        @JvmStatic
        fun <T>tambahRequest(konteks: Context, req: Request<T>): RequestQueue?{
//            if(RQ == null){
            RQ= Volley.newRequestQueue(konteks)
            RQ!!.add(req)
            return RQ
        }
    }
}