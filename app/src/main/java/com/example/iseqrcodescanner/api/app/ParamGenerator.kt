package cob.cob3_1_2.api.app

import android.util.Log
import cob.cob3_1_2.api.pref._Alias
import cob.cob3_1_2.api.util.FileTool

class ParamGenerator{
    companion object{
        fun ambilTokenLokal(): String?{
            return FileTool.bacaLnDariFile(_Alias.loginDir, 0)
        }

        fun cekTokenLokal(): Boolean{
            return ambilTokenLokal() != null
        }

        fun keyPointer(): String?{
            val token= ambilTokenLokal()
            return token?.substring(TokenHelper.keyPointerIndMin until TokenHelper.keyPointerIndMax)
        }

        fun ambilPeranLokal(): String?{
            return FileTool.bacaLnDariFile(_Alias.loginDir, 3)
        }

        fun ambilIdLokal(): String?{
            return FileTool.bacaLnDariFile(_Alias.loginDir, 1)
        }

        fun ambilUnameLokal(): String?{
            return FileTool.bacaLnDariFile(_Alias.loginDir, 2)
        }


        fun userTujuanEn(idUser: String): String? {
            val keyPointerStr= keyPointer() ?: return null
            val token= ambilTokenLokal() ?: return null

            val hambaInd= keyPointerStr[1].toString()
            Log.e("TES","hambaInd= " +hambaInd)
            Log.e("TES", "indexOf= " +_Alias.tabela.indexOf(hambaInd))
            Log.e("TES", "token length= " +token.length)
            val hambaK= token[_Alias.tabela.indexOf(hambaInd)].toString()

            val hambaNama= _Alias.lain[2]
            val hambaNamaEn= TokenHelper.enkrip(hambaNama, hambaK)
            val hambaNilaiEn= TokenHelper.enkrip(idUser, hambaK)

            return "$hambaNamaEn=$hambaNilaiEn"
        }

        fun itemEn(idItem: String): String? {
            val keyPointerStr= keyPointer() ?: return null
            val token= ambilTokenLokal() ?: return null

            val indeksInd= keyPointerStr[2].toString()
            val indeksK= token[_Alias.tabela.indexOf(indeksInd)].toString()

            val indeksNama= _Alias.lain[3]
            val indeksNamaEn= TokenHelper.enkrip(indeksNama, indeksK)
            val indeksNilaiEn= TokenHelper.enkrip(idItem, indeksK)

            return "$indeksNamaEn=$indeksNilaiEn"
        }


        fun liatEn(idLiat: String): String? {
            val keyPointerStr= keyPointer() ?: return null
            val token= ambilTokenLokal() ?: return null

            val slulupInd= keyPointerStr[3].toString()
            val slulupK= token[_Alias.tabela.indexOf(slulupInd)].toString()

            val slulupNama= _Alias.lain[4]
            val slulupNamaEn= TokenHelper.enkrip(slulupNama, slulupK)
            val slulupNilaiEn= TokenHelper.enkrip(idLiat, slulupK)

            return "$slulupNamaEn=$slulupNilaiEn"
        }

        fun unameEn(uname: String): String?{
//            val keyPointerStr= keyPointer() ?: return null
//            val token= ambilTokenLokal() ?: return null

//            val mwawmInd= keyPointerStr[0].toString()
            val mwawmK= _Alias.memberlakuK //token[_Alias.tabela.indexOf(mwawmInd)].toString()

            val mwawmNama= _Alias.lain[5]
            val mwawmNamaEn= TokenHelper.enkrip(mwawmNama, mwawmK)
            val mwawmNilaiEn= TokenHelper.enkrip(uname, mwawmK)

            return "$mwawmNamaEn=$mwawmNilaiEn"
        }
        fun passEn(pass: String): String?{
//            val keyPointerStr= keyPointer() ?: return null
//            val token= ambilTokenLokal() ?: return null

//            val dakseidInd= keyPointerStr[0].toString()
            val dakseidK= _Alias.memberlakuK //token[_Alias.tabela.indexOf(dakseidInd)].toString()

            val dakseidNama= _Alias.lain[6]
            val dakseidNamaEn= TokenHelper.enkrip(dakseidNama, dakseidK)
            val dakseidNilaiEn= TokenHelper.enkrip(pass, dakseidK)

            return "$dakseidNamaEn=$dakseidNilaiEn"
        }


        fun scanUrl(idUserTujuan: String): String? {
            val token= ambilTokenLokal() ?: return null
            val idUserTujuanEn= userTujuanEn(idUserTujuan)

            return "${_Alias.URL_SCAN}/?${_Alias.lain[0]}=$token&$idUserTujuanEn"
        }
        fun tukarUrl(idUserTujuan: String, idItem: String): String? {
            val token= ambilTokenLokal() ?: return null
            val idUserTujuanEn= userTujuanEn(idUserTujuan)
            val idItemEn= itemEn(idItem)

            return "${_Alias.URL_TUKAR}/?${_Alias.lain[0]}=$token&$idUserTujuanEn&$idItemEn"
        }
        fun cekUrl(idUserTujuan: String): String?{
            val token= ambilTokenLokal() ?: return null
            val idUserTujuanEn= userTujuanEn(idUserTujuan)

            return "${_Alias.URL_CEK}/?${_Alias.lain[0]}=$token&$idUserTujuanEn"
        }
        fun liatUrl(jenisDaftar: String): String? {
            val token= ambilTokenLokal() ?: return null
            val jenisDaftarEn= liatEn(jenisDaftar)

            return "${_Alias.URL_LIAT}/?${_Alias.lain[0]}=$token&$jenisDaftarEn"
        }
        fun masukUrl(uname: String, pass: String): String? {
//            val token= ambilTokenLokal() ?: return null
            val uname= unameEn(uname)
            val pass= passEn(pass)

            return "${_Alias.URL_MASUK}/?$uname&$pass"
        }
        fun keluar(): String? {
            val token= ambilTokenLokal() ?: return null

            return "${_Alias.URL_KELUAR}/?${_Alias.lain[0]}=$token"
        }
    }
}