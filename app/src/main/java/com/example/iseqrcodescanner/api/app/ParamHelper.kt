package cob.cob3_1_2.api.app

import cob.cob3_1_2.api.pref._Alias

class ParamHelper {
    companion object{
        //--BELUM generalisasi dg enkripParam()

        fun buatParam(token: String, nilai: Map<String, String>): String{
            val paramEn= enkripParam(token, nilai)
            var paramStr= ""
            for(key in paramEn.keys)
                paramStr += "$key=${paramEn[key]}&"
            paramStr= "?" +paramStr.removeSuffix("&")
            return paramStr
        }

        /**
         * Tidak termasuk mengembalikan $token di dalam array.
         */
        fun ambilParam(param: Map<String, String>, diambil: Array<String>): Map<String, String> {
            val tabela= _Alias.tabela
            val token= param[_Alias.lain[0]]!!
            val keyPointerStr=
                    token.substring(TokenHelper.keyPointerIndMin until TokenHelper.keyPointerIndMax)

//            val memberlakuNama= _Alias.lain[1]
            val mwawmNama= _Alias.lain[5]
            val dakseidNama= _Alias.lain[6]
//            val memberlakuInd= keyPointerStr[0].toString()
            val memberlakuK= _Alias.memberlakuK //token[tabela.indexOf(memberlakuInd)].toString()

            val hambaNama= _Alias.lain[2]
            val hambaInd= keyPointerStr[1].toString()
            val hambaK= token[tabela.indexOf(hambaInd)].toString()

            val indeksNama= _Alias.lain[3]
            val indeksInd= keyPointerStr[2].toString()
            val indeksK= token[tabela.indexOf(indeksInd)].toString()

            val slulupNama= _Alias.lain[4]
            val slulupInd= keyPointerStr[3].toString()
            val slulupK= token[tabela.indexOf(slulupInd)].toString()
/*
        $memberlakuNama= $opr($memberlakuNama, $memberlakuK);
        $hambaNama= $opr($hambaNama, $hambaK);
        $indeksNama= $opr($indeksNama, $indeksK);
        $slulupNama= $opr($slulupNama, $slulupK);
*/

            var mwawmNamaEn: String? = ""
            var dakseidNamaEn: String? = ""
            var memberlakuNamaEn: String? = ""
            var hambaNamaEn: String? = ""
            var indeksNamaEn: String? = ""
            var slulupNamaEn: String? = ""
/*
            if(diambil.indexOf(memberlakuNama) >= 0)
                memberlakuNamaEn = TokenHelper.enkrip(memberlakuNama, memberlakuK)
*/
            if(diambil.indexOf(mwawmNama) >= 0)
                mwawmNamaEn= TokenHelper.enkrip(mwawmNama, memberlakuK)
            if(diambil.indexOf(dakseidNama) >= 0)
                dakseidNamaEn= TokenHelper.enkrip(dakseidNama, memberlakuK)
            if(diambil.indexOf(hambaNama) >= 0)
                hambaNamaEn= TokenHelper.enkrip(hambaNama, hambaK)
            if(diambil.indexOf(indeksNama) >= 0)
                indeksNamaEn= TokenHelper.enkrip(indeksNama, indeksK)
            if(diambil.indexOf(slulupNama) >= 0)
                slulupNamaEn= TokenHelper.enkrip(slulupNama, slulupK)

//            var memberlakuNilai= param[memberlakuNamaEn]
            var mwawmNilai= param[mwawmNamaEn]
            var dakseidNilai= param[dakseidNamaEn]
            var hambaNilai= param[hambaNamaEn]
            var indeksNilai= param[indeksNamaEn]
            var slulupNilai= param[slulupNamaEn]

//            memberlakuNilai= TokenHelper.dekrip(memberlakuNilai, memberlakuK)
            mwawmNilai= TokenHelper.dekrip(mwawmNilai, memberlakuK)
            dakseidNilai= TokenHelper.dekrip(dakseidNilai, memberlakuK)
            hambaNilai= TokenHelper.dekrip(hambaNilai, hambaK)
            indeksNilai= TokenHelper.dekrip(indeksNilai, indeksK)
            slulupNilai= TokenHelper.dekrip(slulupNilai, slulupK)

            val arrayKey= arrayOf<String?>(
//                memberlakuNama,
                mwawmNama,
                dakseidNama,
                hambaNama,
                indeksNama,
                slulupNama
            )
            val arrayNilai= arrayOf(
//                memberlakuNilai,
                mwawmNilai,
                dakseidNilai,
                hambaNilai,
                indeksNilai,
                slulupNilai
            )

            val array= masukanKeArray(arrayKey, arrayNilai)
            return array
        }

        //--BELUM generalisasi dg ambilParam()
        fun enkripParam(token: String, nilai: Map<String, String>): Map<String, String> {
            val tabela= _Alias.tabela
            val keyPointerStr=
                    token.substring(TokenHelper.keyPointerIndMin until TokenHelper.keyPointerIndMax)

//            val memberlakuNama= _Alias.lain[1]
            val mwawmNama= _Alias.lain[5]
            val dakseidNama= _Alias.lain[6]
//            val memberlakuInd= keyPointerStr[0].toString()
            val memberlakuK= _Alias.memberlakuK //token[tabela.indexOf(memberlakuInd)].toString()

            val hambaNama= _Alias.lain[2]
            val hambaInd= keyPointerStr[1].toString()
            val hambaK= token[tabela.indexOf(hambaInd)].toString()

            val indeksNama= _Alias.lain[3]
            val indeksInd= keyPointerStr[2].toString()
            val indeksK= token[tabela.indexOf(indeksInd)].toString()

            val slulupNama= _Alias.lain[4]
            val slulupInd= keyPointerStr[3].toString()
            val slulupK= token[tabela.indexOf(slulupInd)].toString()
/*
        $memberlakuNama= $opr($memberlakuNama, $memberlakuK);
        $hambaNama= $opr($hambaNama, $hambaK);
        $indeksNama= $opr($indeksNama, $indeksK);
        $slulupNama= $opr($slulupNama, $slulupK);
*/
//            var memberlakuNilai= nilai[memberlakuNama]
            var mwawmNilai= nilai[mwawmNama]
            var dakseidNilai= nilai[dakseidNama]
            var hambaNilai= nilai[hambaNama]
            var indeksNilai= nilai[indeksNama]
            var slulupNilai= nilai[slulupNama]

            var mwawmNamaEn: String? = null
            var dakseidNamaEn: String? = null
//            var memberlakuNamaEn: String? = null
            var hambaNamaEn: String? = null
            var indeksNamaEn: String? = null
            var slulupNamaEn: String? = null
/*
            if(memberlakuNilai != null)
                memberlakuNamaEn = TokenHelper.enkrip(memberlakuNama, memberlakuK)
*/

            if(mwawmNilai != null)
                mwawmNamaEn= TokenHelper.enkrip(mwawmNama, memberlakuK)
            if(dakseidNilai != null)
                dakseidNamaEn= TokenHelper.enkrip(dakseidNama, memberlakuK)
            if(hambaNilai != null)
                hambaNamaEn= TokenHelper.enkrip(hambaNama, hambaK)
            if(indeksNilai != null)
                indeksNamaEn= TokenHelper.enkrip(indeksNama, indeksK)
            if(slulupNilai != null)
                slulupNamaEn= TokenHelper.enkrip(slulupNama, slulupK)

//            memberlakuNilai= TokenHelper.enkrip(memberlakuNilai, memberlakuK)
            mwawmNilai= TokenHelper.enkrip(mwawmNilai, memberlakuK)
            dakseidNilai= TokenHelper.enkrip(dakseidNilai, memberlakuK)
            hambaNilai= TokenHelper.enkrip(hambaNilai, hambaK)
            indeksNilai= TokenHelper.enkrip(indeksNilai, indeksK)
            slulupNilai= TokenHelper.enkrip(slulupNilai, slulupK)

            val arrayKey= arrayOf(
                    _Alias.lain[0],
//                    memberlakuNamaEn,
                    mwawmNamaEn,
                    dakseidNamaEn,
                    hambaNamaEn,
                    indeksNamaEn,
                    slulupNamaEn
            )
            val arrayNilai= arrayOf(
                    token,
//                    memberlakuNilai,
                    mwawmNilai,
                    dakseidNilai,
                    hambaNilai,
                    indeksNilai,
                    slulupNilai
            )

            val array= masukanKeArray(arrayKey, arrayNilai)
            return array
        }


        fun masukanKeArray(key: Array<String?>, nilai: Array<String?>): Map<String, String> {
            val array= HashMap<String, String>()
            val batas= key.size
            for(i in 0 until batas)
                if(!key[i].isNullOrEmpty() and !nilai[i].isNullOrEmpty())
                    array[key[i]!!] = nilai[i]!!
            return array
        }
    }

}


/*
    fun generateToken(): string{
        $token = "";
        $charLen = count(_Alias.tabela) - 1;
        $tokenLen= mt_rand(ParamHelper.tokenLenMin, ParamHelper.tokenLenMax);

        //#1. Generate Key Pointer
//        if(count($keyPointerArray) <= 0)
        $keyPointerArray= ParamHelper.generateKeyPointer($tokenLen);

        //#2. Susun array key pointer jadi array
        $keyPointerStr= "";
        foreach($keyPointerArray as $keyPointer){
            if(strlen($keyPointer) == 1)
                $keyPointer= ParamHelper.charHuruf[mt_rand(0, 9)] .$keyPointer;
            $keyPointerStr .= $keyPointer;
        }

        //#3. Susun string token scr keseleruhan
        //#3.1. Susun string sblum Key Pointer
        for ($i = 0; $i < TokenHelper.keyPointerIndMin; $i++) {
            if(!in_array($i, $keyPointerArray))
                $token .= _Alias.tabela[mt_rand(0, $charLen)];
            else
                $token .= ParamHelper.charAngka[mt_rand(1, 9)];
        }
        //#3.2. Susun string dg Key Pointer
        $token .= $keyPointerStr;
        //#3.3. Susun string stelah Key Pointer
        for ($i = TokenHelper.keyPointerIndMax +1; $i < $tokenLen; $i++) {
            if(!in_array($i, $keyPointerArray))
                $token .= _Alias.tabela[mt_rand(0, $charLen)];
            else
                $token .= ParamHelper.charAngka[mt_rand(1, 9)];
        }
        
        Debug.var_dumpe($keyPointerArray);
        Debug.echoe("<br>");
        foreach($keyPointerArray as $ind){
            Debug.echoe("[$ind] => " .$token[$ind] ."<br>");
        }

        return $token;
    }

    fun generateKeyPointer(int $tokenLen): array{
        $randInd= 7;
        $hasil= [];
        $tokenLen -= 1;
        for($i= 0; $i < TokenHelper.keyPointerIndCount; $i++){
            do{
                $randInd= random_int(0, $tokenLen);
            } while($randInd >= 7 && $randInd <= 14);
            array_push($hasil, $randInd);
        }
        return $hasil;
    }
* /
    fun param(array $param, $opr) {
        $token= $param[_Alias.lain[0]];
        $keyPointerStr= substr($token,
            TokenHelper.keyPointerIndMin, TokenHelper.keyPointerIndMax);
        
        $memberlakuNama= _Alias.lain[1];
        $memberlakuInd= substr($keyPointerStr, 0, 2);
        $memberlakuK= substr($keyPointerStr, 0, 2);
        $hambaNama= _Alias.lain[2];
        $hambaK= substr($keyPointerStr, 2, 4);
        $indeksNama= _Alias.lain[3];
        $indeksK= substr($keyPointerStr, 4, 6);
        $slulupNama= _Alias.lain[4];
        $slulupK= substr($keyPointerStr, 6, 8);

        $memberlakuNama= $opr($memberlakuNama)
    }

    private fun geser(string $param, string $k, $opr): string{
        $hasil= "";
        $batas= strlen($param);

        if(!is_numeric($k))
            $k= $k[1];

        for($i= 0; $i < $batas; $i++){
            $indChar= array_search($param[$i], _Alias.tabela);
            $indChar= $opr($indChar, $k);
            //$indChar= ($indChar -$k) % $pjgTabela;
            $hasil .= _Alias.tabela[$indChar];
        }
        return $hasil;
    }
    fun enkrip(string $param, string $k): string{
        $pjgTabela= count(_Alias.tabela);

        return ParamHelper.geser($param, $k, 
            function($indChar, $k) use ($pjgTabela) {
                return ($indChar +$k) % $pjgTabela;
            });
    }
    fun dekrip(string $param, string $k): string{
        $pjgTabela= count(_Alias.tabela);

        Debug.echoe("pjgTabela = $pjgTabela <br>"); 

        return ParamHelper.geser($param, $k, 
            function($indChar, $k) use ($pjgTabela) {
                $hasil= ($indChar -$k) % $pjgTabela;
                if($hasil < 0)
                    return $pjgTabela + $hasil;
                else
                    return $hasil;
            });
    }
*/