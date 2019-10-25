package cob.cob3_1_2.api.app

import cob.cob3_1_2.api.pref._Alias
import cob.cob3_1_2.api.util.FileTool

class LoginHelper{
    companion object{
        fun simpanTokenPeran(token: String, peran: String): Boolean{
            var hasil= FileTool.simpan(_Alias.loginDir, token, false)
            return hasil and FileTool.simpanln(_Alias.loginDir, token, true) //.simpan(_Alias.loginDir, token, false)
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