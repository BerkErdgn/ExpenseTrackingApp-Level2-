package com.berke.expensevsincome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.Navigation
import com.berke.expensevsincome.databinding.FragmentAddItemBinding
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_add_item.*
import kotlinx.android.synthetic.main.itemlist_recycler_row.*


class AddItemFragment : Fragment() {

    private lateinit var binding: FragmentAddItemBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var firestore : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddItemBinding.inflate(inflater,container,false)

        //for ExpenseOrIncome AutoCompleteTextView
        val expenseOrIncome = resources.getStringArray(R.array.exponseSelect)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item,expenseOrIncome)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)

        //for Months AutoCompleteTextView
        val months = resources.getStringArray(R.array.months)
        val arrayAdapter2 = ArrayAdapter(requireContext(), R.layout.dropdown_item,months)
        binding.autoCompeteTextMonths.setAdapter(arrayAdapter2)


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth
        firestore = Firebase.firestore

        saveButton.setOnClickListener {
            val postMap = hashMapOf<String,Any>()
            postMap.put("expenseOrIncome",binding.autoCompleteTextView.text.toString())
            postMap.put("mounth",binding.autoCompeteTextMonths.text.toString())
            postMap.put("price",binding.priceText.text.toString())
            postMap.put("hint",binding.hintText.text.toString())
            postMap.put("date",Timestamp.now())

            firestore.collection("Posts").add(postMap).addOnSuccessListener {

                val actionAddtoList =
                    com.berke.expensevsincome.AddItemFragmentDirections.actionAddItemFragment2ToListOfItemFragment2()
                Navigation.findNavController(view).navigate(actionAddtoList)

            }.addOnFailureListener {
                Toast.makeText(this.context,it.localizedMessage,Toast.LENGTH_LONG).show()
            }

        }


    }


}