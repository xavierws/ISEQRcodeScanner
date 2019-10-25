package cob.cob3_1_2.api.model

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class Model_ID(val JenisDB: JenisDatabase= JenisDatabase.SQLite)


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Model_Atribut_Jml(val jmlDariDepan: Int)
