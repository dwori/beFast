package at.fh.swengb.beFast.ui.brands

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import at.fh.swengb.beFast.R

class BrandsFragment : Fragment() {

    private lateinit var brandsViewModel: BrandsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        brandsViewModel =
                ViewModelProvider(this).get(BrandsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_brands, container, false)
        val textView: TextView = root.findViewById(R.id.text_brands)
        brandsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}