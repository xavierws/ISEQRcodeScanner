package cob.cob3_1_2.api.app

import cob.cob3_1_2.api.pref._Alias
import cob.cob3_1_2.api.util.FileTool

class LoginHelper{
    companion object{
        fun simpanTokenPeran(token: String, id: String, uname: String, peran: String): Boolean{
            val hasilToken= FileTool.simpanln(_Alias.loginDir, token, false)
            val hasilId= FileTool.simpanln(_Alias.loginDir, id, true)
            val hasilUname= FileTool.simpanln(_Alias.loginDir, uname, true)
            return hasilToken and hasilId and hasilUname and FileTool.simpanln(_Alias.loginDir, peran, true) //.simpan(_Alias.loginDir, token, false)
        }

        fun hapusTokenPeran(): Boolean{
            return FileTool.hapusFile(_Alias.loginDir)
        }
    }
/*
    interface LoginHelperListener{
        fun onLogin(tokenDariFile: String?): Boolean
    }

    fun cekLogin(): Boolean{
        val token= FileTool.bacaStrDariFile(_Alias.loginDir)
        return l.onLogin(token)
    }
*/
}