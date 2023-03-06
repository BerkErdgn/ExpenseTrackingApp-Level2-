package com.berke.expensevsincome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.berke.expensevsincome.databinding.FragmentDetailMainBinding
import kotlinx.android.synthetic.main.fragment_detail_main.*


class DetailMainFragment : Fragment() {


   private  var _binding: FragmentDetailMainBinding? = null
    private  val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflate the layout and bind to the _binding
        _binding = FragmentDetailMainBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        replaceFragment(TotalBanafitFragment())


        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){
                R.id.mtotalBanafit -> replaceFragment(TotalBanafitFragment())
                R.id.mList -> replaceFragment(ListOfItemFragment())
                else -> {
                }
            }
            true


        }

        addButton.setOnClickListener {
            val actionDetailtoAdd =
                com.berke.expensevsincome.DetailMainFragmentDirections.actionDetailMainFragmentToAddItemFragment2()
            Navigation.findNavController(it).navigate(actionDetailtoAdd)
        }

    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransection = fragmentManager.beginTransaction()
        fragmentTransection.replace(R.id.frame_layout,fragment)
        fragmentTransection.commit()
    }




}

