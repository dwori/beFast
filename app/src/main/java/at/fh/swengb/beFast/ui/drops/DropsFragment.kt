package at.fh.swengb.beFast.ui.drops

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import at.fh.swengb.beFast.R

class DropsFragment : Fragment() {

    private lateinit var dropsViewModel: DropsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dropsViewModel =
                ViewModelProvider(this).get(DropsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_drops, container, false)
        val textView: TextView = root.findViewById(R.id.text_drops)
        dropsViewModel.text.observe(viewLifecycleOwner, Observer {
            //textView.text = it
        })
        return root
    }
}