package com.berke.expensevsincome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.berke.expensevsincome.R
import com.berke.expensevsincome.adapter.ListRecyclerAdapter
import com.berke.expensevsincome.databinding.FragmentListOfItemBinding
import com.berke.expensevsincome.models.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListOfItemFragment : Fragment() {

    private lateinit var binding : FragmentListOfItemBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var postArrayList : ArrayList<Post>
    private lateinit var listRecyclerAdapter : ListRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListOfItemBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment

        return binding.root




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postArrayList = ArrayList<Post>()
        db = Firebase.firestore
        getData()



        binding.itemListRecyclerView.layoutManager = LinearLayoutManager(this.context)
        listRecyclerAdapter =  ListRecyclerAdapter(postArrayList)
        binding.itemListRecyclerView.adapter = listRecyclerAdapter


    }


    private fun getData (){
        db.collection("Posts").orderBy("date",Query.Direction.DESCENDING).addSnapshotListener { value, error ->

            if (error != null) {
                Toast.makeText(this.context, error.localizedMessage, Toast.LENGTH_LONG).show()
            } else {
                if (value != null ){
                    if (!value.isEmpty){
                       val documants = value.documents

                        for (document in documants){

                            val expenseOrIncome = document.get("expenseOrIncome") as String
                            val hint = document.get("hint") as String
                            val mounth = document.get("mounth") as String
                            val price = document.get("price") as String


                            val post = Post(expenseOrIncome,hint,mounth,price)
                            postArrayList.add(post)

                        }

                        listRecyclerAdapter.notifyDataSetChanged()
                    }
                }

            }
        }
    }


}