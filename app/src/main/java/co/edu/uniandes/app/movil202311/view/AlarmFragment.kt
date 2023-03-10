package co.edu.uniandes.app.movil202311.view

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.app.movil202311.R
import co.edu.uniandes.app.movil202311.databinding.AlarmFragmentBinding
import co.edu.uniandes.app.movil202311.models.Global
import co.edu.uniandes.app.movil202311.view.adapters.AlarmAdapter
import co.edu.uniandes.app.movil202311.viewmodels.AlarmViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AlarmFragment : Fragment() {
    private var _binding: AlarmFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: AlarmViewModel
    private var viewModelAdapter: AlarmAdapter? = null
    private var login = false;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AlarmFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = AlarmAdapter()


        val fab = view.findViewById(R.id.add_alarm) as FloatingActionButton
        fab.setOnClickListener { view ->
            if(Global.login) {
                Navigation.findNavController(view).navigate(R.id.action_albumsFragment_to_add_alarm)
            }
        }

        val log = view.findViewById(R.id.login_button) as MaterialButton
        log.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(R.id.action_albumsFragment_to_login_button)

        }

        if (Global.login) {
            log.visibility = View.INVISIBLE

            fab.backgroundTintList = ColorStateList.valueOf(
                ResourcesCompat.getColor(resources,
                R.color.violet_background_button, getActivity()?.theme))

            val main = getActivity() as MainActivity?
            main?.updateNavigation()

        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.albumsRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }

        viewModel = ViewModelProvider(this, AlarmViewModel.Factory(activity.application)).get(AlarmViewModel::class.java)
        viewModel.alarms.observe(viewLifecycleOwner) {
            it.apply {
                viewModelAdapter!!.alarms = this
            }
        }
        viewModel.eventNetworkError.observe(viewLifecycleOwner) { isNetworkError ->
            if (isNetworkError) onNetworkError()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}