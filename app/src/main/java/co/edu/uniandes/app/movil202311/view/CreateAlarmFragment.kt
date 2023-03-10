package co.edu.uniandes.app.movil202311.view

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import co.edu.uniandes.app.movil202311.R
import com.google.android.material.button.MaterialButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateAlbumFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateAlarmFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }

        val viewFragment = inflater.inflate(R.layout.fragment_create_alarm, container, false)


        val log = viewFragment.findViewById(R.id.create_alarm_form) as MaterialButton
        log.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(R.id.action_button_create_alarm_form_to_albumsFragment)
            Toast.makeText(getActivity(), "Alarma creada con éxito",Toast.LENGTH_LONG).show()
        }

        val am = viewFragment.findViewById(R.id.button_am) as MaterialButton
        val pm = viewFragment.findViewById(R.id.button_pm) as MaterialButton

        am.setOnClickListener { view ->
            am.setTextColor(ResourcesCompat.getColor(resources,
                R.color.white_background, getActivity()?.theme))

            am.backgroundTintList = ColorStateList.valueOf(ResourcesCompat.getColor(resources,
                R.color.violet_background_button, getActivity()?.theme
            ))

            pm.setTextColor(ResourcesCompat.getColor(resources,
                R.color.black, getActivity()?.theme))

            pm.backgroundTintList = ColorStateList.valueOf(ResourcesCompat.getColor(resources,
                R.color.white_background, getActivity()?.theme
            ))

        }
        pm.setOnClickListener { view ->
            pm.setTextColor(ResourcesCompat.getColor(resources,
                R.color.white_background, getActivity()?.theme))

            pm.backgroundTintList = ColorStateList.valueOf(ResourcesCompat.getColor(resources,
                R.color.violet_background_button, getActivity()?.theme
            ))

            am.setTextColor(ResourcesCompat.getColor(resources,
                R.color.black, getActivity()?.theme))

            am.backgroundTintList = ColorStateList.valueOf(ResourcesCompat.getColor(resources,
                R.color.white_background, getActivity()?.theme
            ))

        }


        val button_image =  viewFragment.findViewById(R.id.input_alarm_image) as MaterialButton
        button_image.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 0)
        }



        return viewFragment

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CreateAlbumFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateAlarmFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}