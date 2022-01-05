package zhe.internet.tractoragrigator.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_catalog.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import zhe.internet.tractoragrigator.*


class Catalog : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadMaches()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }


    private fun loadMaches(){
        //initiate the service
        val destinationService = ServiceBuilder.buildService(MachineService::class.java)
        val requestCall = destinationService.getAffectedMachinesList()
        //make network call asynchronously


        requestCall.enqueue(object : Callback<List<MyMachine>> {


            override fun onResponse(call: Call<List<MyMachine>>, response: Response<List<MyMachine>>) {

                if (response.isSuccessful){
                    val machineList = response.body()!!
                    Log.d("Response", "MachList size : ${machineList.size}")
                    machine_recycler.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(context,2)
                        adapter = MachinesAdapter(response.body()!!)
                    }
                }else{
                    Toast.makeText(context, "Something went wrong ${response.message()}", Toast.LENGTH_SHORT).show()

                }
            }
            override fun onFailure(call: Call<List<MyMachine>>, t: Throwable) {

                Toast.makeText(context, "Something went wrong $t", Toast.LENGTH_SHORT).show()

            }
        })
    }

}