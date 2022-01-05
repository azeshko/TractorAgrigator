package zhe.internet.tractoragrigator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        bottom navbar
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
//        val navController = findNavController(R.id.nav_host_fragment)

//        val appBarConfiguration = AppBarConfiguration(setOf(
//            R.id.navigation_home, R.id.navigation_catalog))
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)


        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_agrigation -> {
                    Log.d("FFFFFFF", "asdfasdfasdfasdfas")
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_about -> {
                    Log.d("FFFFFFF", "111111111111111")
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


        loadMaches()
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
                        layoutManager = GridLayoutManager(this@MainActivity,2)
                        adapter = MachinesAdapter(response.body()!!)
                    }
                }else{
                    Toast.makeText(this@MainActivity, "Something went wrong ${response.message()}", Toast.LENGTH_SHORT).show()

                }
            }
            override fun onFailure(call: Call<List<MyMachine>>, t: Throwable) {

                Toast.makeText(this@MainActivity, "Something went wrong $t", Toast.LENGTH_SHORT).show()

            }
        })
    }
}