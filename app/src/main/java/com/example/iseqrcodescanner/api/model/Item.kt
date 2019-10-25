package cob.cob3_1_2.api.model

class Item(@Model_ID private val kode: String, //{surat#3}{ayat#3}
           val nama: String, val harga: Int)
    : Model(kode)