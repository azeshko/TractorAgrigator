package zhe.internet.tractoragrigator

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zhe.internet.tractoragrigator.fragments.CatalogPresentationFragment

import android.os.Bundle


class MachinesAdapter(private val machinesList: List<MyMachine>) :RecyclerView.Adapter<MachinesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view  = LayoutInflater.from(parent.context).inflate(R.layout.machine_item,parent,false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return machinesList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Response", "List Count :${machinesList.size} ")
        //представление списка

        holder.imageView.setOnClickListener(object: View.OnClickListener{


            override fun onClick(v: View?) {

                val activity = v!!.context as AppCompatActivity
                val pos = holder.adapterPosition

                val item = machinesList.get(pos)

                val transaction = activity.supportFragmentManager.beginTransaction()
                val bundle = Bundle()
                bundle.putSerializable("mach_item", item)

                val demoFragment = CatalogPresentationFragment()
                demoFragment.arguments = bundle
                transaction.replace(R.id.rec, demoFragment).addToBackStack(null).commit()

            }
        })

        return holder.bind(machinesList[position])
    }

    class ViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView) {


        var imageView = itemView.findViewById<ImageView>(R.id.ivMachine)
        var tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
//        var tvCases = itemView.findViewById<TextView>(R.id.tvCases)
        fun bind(machine: MyMachine) {

//            tvCases.text = machine.mach_mark
            tvTitle.text = machine.id_mach.toString()
            Picasso.get().load(machine.picute_mach_link).into(imageView)
        }

    }



//    override fun passData(position: Int, im_name: kotlin.String, image: kotlin.String) {
//        Log.d("zzzzzzzzzzzzzzzz", position.toString())
//        val bundle = Bundle()
//        bundle.putString("mach_name", im_name)
//    }


}