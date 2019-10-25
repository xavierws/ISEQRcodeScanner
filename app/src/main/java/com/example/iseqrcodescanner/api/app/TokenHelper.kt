package cob.cob3_1_2.api.app

import cob.cob3_1_2.api.pref._Alias
import java.util.*

class TokenHelper {
    companion object{
        val tokenLenMin= 37
        val tokenLenMax= 53

        val keyPointerIndMin= 7
        val keyPointerIndMax= 14
        val keyPointerIndCount= 4

        val charHuruf= "abcdefghijklmnopqrstuvwxyz"+
                    "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val charAngka= "0123456789"

        val charLengkap= "abcdefghijklmnopqrstuvwxyz"+  //TokenHelper::$charHuruf .TokenHelper::$charAngka;
                    "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+
                    "0123456789"

        fun generateToken(): String{
            var token = ""
            var charLen = _Alias.tabela.size - 1
            var tokenLen= rand(tokenLenMin, tokenLenMax)

            //#1. Generate Key Pointer
            val keyPointerArray= generateKeyPointer(tokenLen)

            //#2. Susun array key pointer jadi array
            var keyPointerStr= ""
            for(keyPointer in keyPointerArray)
                keyPointerStr += keyPointer

            //#3. Susun string token scr keseleruhan
            //#3.1. Susun string sblum Key Pointer
            for ( i in 0 until keyPointerIndMin)
                token += _Alias.tabela[rand(1, charLen)]
            //#3.2. Susun string dg Key Pointer
            token += keyPointerStr
            //#3.3. Susun string stelah Key Pointer
            for (i in keyPointerIndMax until tokenLen)
                token += _Alias.tabela[rand(1, charLen)]

/*
        foreach($keyPointerArray as $ind){
            echo "[$ind] => " .$token[$ind] ."<br>";
        }
*/
            return token
        }

        fun generateKeyPointer(tokenLenIn: Int): ArrayList<String>{
            var randInd= 7
            val hasil= ArrayList<String>()
            var tokenLen = tokenLenIn -1

            var indAcak= 0
            for(i in 0 until keyPointerIndCount){
                do{
                    indAcak= rand(1, tokenLen)
                } while(indAcak in keyPointerIndMin..keyPointerIndMax)

                val randIndStr= _Alias.tabela[indAcak]
                hasil.add(randIndStr)
            }
            return hasil
        }


        private fun geser(param: String?, k: String, opr: (Int, Int) -> Int): String?{
            if(param == null)
                return null
            var hasil= ""
            val batas= param.length
            val indGeser= _Alias.tabela.indexOf(k)

            for(i in 0 until batas){
                var indChar= _Alias.tabela.indexOf(param[i].toString())
                indChar= opr(indChar, indGeser)
                hasil += _Alias.tabela[indChar]
            }
            return hasil
        }
        fun enkrip(param: String?, k: String): String?{
            val pjgTabela= _Alias.tabela.size

            return geser(param, k) { indChar, indGeser ->
                ((indChar + indGeser) % pjgTabela)
            }
        }
        fun dekrip(param: String?, k: String): String?{
            val pjgTabela= _Alias.tabela.size

            return geser(param, k) { indChar, indGeser ->
                val hasil = (indChar + indGeser) % pjgTabela

                if (hasil < 0)
                    pjgTabela + hasil
                else
                    hasil
            }
        }

        fun rand(from: Int, to: Int) : Int {
            return Random().nextInt(to - from) + from
        }
    }

/*
    static $tokenLenMin= 37;
    static $tokenLenMax= 53;

    static $keyPointerIndMin= 7;
    static $keyPointerIndMax= 14;
    static $keyPointerIndCount= 4;

    static $charHuruf= "abcdefghijklmnopqrstuvwxyz"
                        ."ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static $charAngka= "0123456789";

    static $charLengkap= "abcdefghijklmnopqrstuvwxyz"  //TokenHelper::$charHuruf .TokenHelper::$charAngka;
                        ."ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                        ."0123456789";

    static function generateToken(string $keyPointerArray= ""): string{
        $token = "";
        $charLen = strlen(TokenHelper::$charLengkap) - 1;
        $tokenLen= mt_rand(TokenHelper::$tokenLenMin, TokenHelper::$tokenLenMax);

        //#1. Generate Key Pointer
        if(strlen($keyPointerArray) <= 0)
            $keyPointerArray= TokenHelper::generateKeyPointer($tokenLen-1);

        //#2. Susun array key pointer jadi array
        $keyPointerStr= "";
        foreach($keyPointerArray as $keyPointer){
            if(strlen($keyPointer) == 1)
                $keyPointer= TokenHelper::$charHuruf[mt_rand(0, 9)] .$keyPointer;
            $keyPointerStr .= $keyPointer;
        }

        //#3. Susun string token scr keseleruhan
        //#3.1. Susun string sblum Key Pointer
        for ($i = 0; $i < TokenHelper::$keyPointerIndMin; $i++) {
            if(!in_array($i, $keyPointerArray))
                $token .= TokenHelper::$charLengkap[mt_rand(0, $charLen)];
            else
                $token .= TokenHelper::$charAngka[mt_rand(0, 9)];
        }
        //#3.2. Susun string dg Key Pointer
        $token .= $keyPointerStr;
        //#3.3. Susun string stelah Key Pointer
        for ($i = TokenHelper::$keyPointerIndMax +1; $i < $tokenLen; $i++) {
            if(!in_array($i, $keyPointerArray))
                $token .= TokenHelper::$charLengkap[mt_rand(0, $charLen)];
            else
                $token .= TokenHelper::$charAngka[mt_rand(0, 9)];
        }

        return $token;
    }

    static function generateKeyPointer(int $tokenLen): array{
        $randInd= 7;
        $hasil= [];
        for($i= 0; $i < TokenHelper::$keyPointerIndCount; $i++){
            do{
                $randInd= random_int(0, $tokenLen);
            } while($randInd >= 7 && $randInd <= 14);
            array_push($hasil, $randInd);
        }
        return $hasil;
    }
*/
}