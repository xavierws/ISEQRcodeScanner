package cob.cob3_1_2.api.util

import cob.cob3_1_2.api.pref._Alias
import com.example.iseqrcodescanner.api.util.LogApp
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
//import cob.cob3_1_2.api.util.log.LogApp;

class TglTool{
    companion object {
        @JvmStatic
        fun waktuSkrg(formatTgl: String): String {
            val format = SimpleDateFormat(formatTgl)//("ddMMyyyy")
            val dateObj = Date()
            return format.format(dateObj)
        }

        fun tgl(formatTgl: String, hariDariSkrg: Int): String {
            val format = SimpleDateFormat(formatTgl)//("ddMMyyyy")
            val dateObj = Date()
            val calObj = Calendar.getInstance()
            calObj.time = dateObj
            calObj.add(Calendar.DATE, hariDariSkrg)
            return format.format(calObj.time)
        }

        fun tglSetelah(formatTgl: String, hari: String, hariSetelah: Int): String {
            val format = SimpleDateFormat(formatTgl)//("ddMMyyyy")
            val dateObj = format.parse(hari)
            val calObj = Calendar.getInstance()
            calObj.time = dateObj
            calObj.add(Calendar.DATE, hariSetelah)
            return format.format(calObj.time)
        }

        fun selisih(formatTgl: String, satuan: TimeUnit, tglSebelum: String, tglSesudah: String): Long {
            try {
                val format = SimpleDateFormat(formatTgl)//("ddMMyyyy")
                val dateSebelum = format.parse(tglSebelum)
                val dateSesudah = format.parse(tglSesudah)

                val miliSelisih = dateSesudah.time - dateSebelum.time
                return satuan.convert(miliSelisih, TimeUnit.MILLISECONDS)
            } catch (error: Exception) {
                LogApp.e("FIREBASE", "error pada = $tglSesudah \n error.message")
                throw error
            }
        }

        //sesuai struktur proyek
        fun selisihHari(tgl_habis: String, tglSkrg: String? = null): Long {
            val skrg = tglSkrg ?: waktuSkrg(_Alias.FORMAT_TGL)
            return selisih(
                    _Alias.FORMAT_TGL,
                    TimeUnit.DAYS,
                    skrg,
                    tgl_habis)
        }
/*
        fun waktuSkrgServer(konteks: Context): MutableLiveData<String>{
            val strHasil= MutableLiveData<String>()
            VolleyTool.tambahRequest(konteks, StringRequest(Request.Method.GET,
                    _Alias.APP_WAKTU_API,
                    Response.Listener<String> { data ->
                        strHasil.value= data
                    }, Response.ErrorListener { error ->
                        ToastApp.tampilkanTeks(konteks, "Ada kesalahan saat mengambil data timestamp!", Toast.LENGTH_LONG)
                    })
            )
            return strHasil
        }
    }
*/
    }
}