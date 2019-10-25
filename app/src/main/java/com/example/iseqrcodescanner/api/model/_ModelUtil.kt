package cob.cob3_1_2.api.model

import java.lang.IllegalArgumentException
import java.lang.reflect.Field

//class _ModelUtil{
//    companion object{
        const val URUT_MENAIK= 1
        const val URUT_MENURUN= 0

        fun <M: Model> Array<M>.urutkan(atrib: String, arah: Int= URUT_MENAIK): Array<M>?{
            if(this.isEmpty()) return null
//            if(first() == null) return null

            val daftarAtrib= first()::class.java.declaredFields
            var atribModel: Field?= null
            for(perAtrib in daftarAtrib)
                if(perAtrib.name == atrib){
                    atribModel= perAtrib
                    break
                }
            return urutkan(atribModel!!, arah)
//            "As".codePoints().sum()
        }
        fun <M: Model> Array<M>.urutkan(atrib: Field, arah: Int= URUT_MENAIK): Array<M>?{
            if(isEmpty()) return null

            val aksesAwal= atrib.isAccessible
            atrib.isAccessible= true

            val nilaiAtrib= atrib[first()]
            atrib.isAccessible= aksesAwal

            return when(nilaiAtrib){
                is Number -> urutkanAngka(atrib, arah)
                else -> null
            }
        }
        private fun <M: Model> Array<M>.urutkanAngka(atrib: Field, arah: Int= URUT_MENAIK): Array<M>?{
            val aksesAwal= atrib.isAccessible
            atrib.isAccessible= true

            val fungsiPembanding: (n1: Comparable<Number>, n2: Number) -> Boolean=
                    when(arah){
                        URUT_MENAIK -> _1lebihBesarDari2
                        URUT_MENURUN -> _1lebihKecilDari2
                        else -> throw IllegalArgumentException("Arah harus bernilai _ModelUtil.URUT_MENAIK _ModelUtil.URUT_MENURUN. Nilai yang dimasukan arah= $arah")
                    }

            for(i in 0 until size)
                for(u in i+1 until size)
                    if(fungsiPembanding(
                            atrib.get(this[i]) as Comparable<Number>,
                            atrib.get(this[u]) as Number)){
                        val smtr= this[i] //as Model
                        this[i]= this[u] //as Model
                        this[u]= smtr
                    }
            atrib.isAccessible= aksesAwal
            return this
        }
        private val _1lebihBesarDari2: (n1: Comparable<Number>, n2: Number) -> Boolean = {n1, n2 -> n1 > n2}
        private val _1lebihKecilDari2: (n1: Comparable<Number>, n2: Number) -> Boolean = {n1, n2 -> n1 < n2}
//    }
//}