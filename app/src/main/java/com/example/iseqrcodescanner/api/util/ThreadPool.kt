package cob.cob3_1_2.api.util

import java.util.concurrent.*

class ThreadPool{
    companion object {
        val jmlProsesor= Runtime.getRuntime().availableProcessors()
        private var threadPoolService= Executors.newFixedThreadPool(jmlProsesor) as ThreadPoolExecutor
//        private val daftarTugas= HashMap<String, ArrayList<() -> Unit>>()

        //@return juga menghasilkan RunnableFuture<T>
        //simpan @return untuk membatalkan tugas di waktu kemudian dengan @method batalkan()
        fun <T> kumpulkan(tugas: () -> T?): Future<T> {
            if(threadPoolService.isShutdown)
                threadPoolService= Executors.newFixedThreadPool(jmlProsesor) as ThreadPoolExecutor
            return threadPoolService.submit(tugas)
        }

        //@param juga menerima parameter hasil @return @method kumpulkan()
        fun <T> batalkan(tugas: RunnableFuture<T>){
            threadPoolService.remove(tugas)
        }

        //mematikan ThreadPool yang lama dan meng-instansiasi object yang baru
        fun daurUlang(): List<Runnable>{
            return threadPoolService.shutdownNow()
        }
    }
}
/*
class Tugas <T> (val id: String?= null,
                 val tugas: (id: String?) -> T)

class Hasil <T>
*/