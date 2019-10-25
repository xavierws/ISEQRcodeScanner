package cob.cob3_1_2.api.model

class Peserta(@Model_ID private val id_: String, //{surat#3}{ayat#3}
           val uname: String, val pass: String,
           val skor: Int, val fk_peran: String)
    : Model(id_)