package zhe.internet.tractoragrigator

import retrofit2.Call
import retrofit2.http.GET

interface MachineService {
    @GET("/machapi/v1/?op=getmach")

    fun getAffectedMachinesList () : Call<List<MyMachine>>
}