package cob.cob3_1_2.api.pref

import android.os.Environment

class _Alias{
    companion object{
        val SERVER= "localhost"
        val UNAME = "root"
        val PASS = ""
        val DB_NAMA = "ise_barscan"
        val DB_VERSI = 1

        val FORMAT_JAM = "HH:mm"
        val FORMAT_TGL = "dd-MM-yyyy"

        val appName= "ISE Barscan"
        val appDir= Environment.getExternalStorageDirectory().absolutePath + "/$appName"
        val loginDir= "$appDir/login/axc.ise"

        val URL_DOMAIN= "https://ise-its.000webhostapp.com" //"https://playground.icon.ise-its.com"
        val URL_SCAN= "$URL_DOMAIN/scan"
        val URL_CEK= "$URL_DOMAIN/cek"
        val URL_TUKAR= "$URL_DOMAIN/tukar"
        val URL_LIAT= "$URL_DOMAIN/liat"
        val URL_MASUK= "$URL_DOMAIN/masuk"
        val URL_KELUAR= "$URL_DOMAIN/keluar"

        val memberlakuK= "Q"

        val tabel =
                arrayOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
                        "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                        "_", "-", "$", "*", ",", "!")

        val tabela =
                arrayOf("b", "2", "Q", "E", "c", "G", ",", "d", "3", "e", "O", "f", "K", "g", "h", "-",
                        "Y", "H", "j", "k", "T", "Z", "R", "l", "n", "o", "s", "v", "A", "V", "B", "*",
                        "C", "!", "D", "5", "F", "7", "i", "L", "p", "M", "P", "S", "U", "W", "m", "w",
                        "q", "X", "u", "4", "z", "t", "0", "x", "1", "6", "I", "N", "8", "9", "$", "_",
                        "a", "J", "r", "y")

        val namaTabel =
                arrayOf(
                        "Peserta",
                        "Item",
                        "Login", // -> 2
                        "Peran",
                        "Penukaran",
                        "Scan" // -> 5
                )

        val lain =
                arrayOf(
                        "djgagak",
                        "memberlaku",
                        "hamba", // -> 2
                        "indeks",
                        "slulup",
                        "mwawm", // -> 5
                        "dakseid",
                        "unster",
                        "huwaidi" //-> 8
                )

        fun respon(key: String): String? {
            val respon = HashMap<String, String>()
            respon["ya"]= "awqe"
            respon["gak"]= "rwqwq"

            return respon[key]
        }


    }
}
