package zhe.internet.tractoragrigator.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import zhe.internet.tractoragrigator.MyMachine
import zhe.internet.tractoragrigator.R
import java.io.Serializable


class CatalogPresentationFragment : Fragment() {
    lateinit private var displayMessage:Serializable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_catalog_present, container, false)
        displayMessage = arguments?.getSerializable("mach_item") as Serializable

       val myItem =  arguments?.getSerializable("mach_item") as? MyMachine

        var imageView = view.findViewById<ImageView>(R.id.ivMachPresent)
        var tvTitle = view.findViewById<TextView>(R.id.tvMachName)



            tvTitle.text = myItem?.mach_mark
            Picasso.get().load(myItem?.picute_mach_link).into(imageView)


        Log.d("FFFzzz", myItem?.mach_mark.toString())
        return view
    }
}