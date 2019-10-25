package cob.cob3_1_2.api.model

import java.io.Serializable


open class Model(@Model_ID val id: String): Serializable



/*
=================BANYAK YG HARUS DIUBAH======================
1. Cara untuk menggantikan nilai email yang panjang dengan kode yang dibuat oleh server
2. Pengecekan jml data yang disetorkan jika dilakukan pada Firestore akan memakan banyak quota gratis

======SOLUSI======
1.1. Tiap pengguna diberi id oleh server PHP scr otomatis tepat setelah pengguna mendaftar app
1.2. Alur => FirebaseAuth: email tersedia -> server PHP: buat id -> Firestore: simpan data -> server PHP: konfirmasi -> SQLite: simpan lokal
2.1. Pengecekan jml data yang disetorkan dilakukan pada DB server PHP, tepatnya pada tabel Personal
2.2. Alur => server PHP: cek jml -> Firestore: simpan data -> server PHP: konfirmasi
*/