package com.berke.expensevsincome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {


    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        val currentUser = auth.currentUser

        if (currentUser!= null){
            val actionLogintoContinius =
                com.berke.expensevsincome.LoginFragmentDirections.actionLoginFragmentToDetailMainFragment()
            Navigation.findNavController(view).navigate(actionLogintoContinius)
        }


        loginButtonLogin.setOnClickListener{
            val email = emailLoginText.text.toString()
            val password = passwordLoginText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()){
                auth.signInWithEmailAndPassword(email,password)
                    .addOnSuccessListener {
                        val actionLogintoContinius =
                            com.berke.expensevsincome.LoginFragmentDirections.actionLoginFragmentToDetailMainFragment()
                        Navigation.findNavController(view).navigate(actionLogintoContinius)
                    }.addOnFailureListener {
                        Toast.makeText(this.context,it.localizedMessage,Toast.LENGTH_LONG).show()
                    }

            }else {
                Toast.makeText(this.context,"Wrong Entiring",Toast.LENGTH_LONG).show()
            }

        }


        singUpButtonLogin.setOnClickListener {
            val actionSingUp =
                com.berke.expensevsincome.LoginFragmentDirections.actionLoginFragmentToSingUpFragment()
            Navigation.findNavController(it).navigate(actionSingUp)
        }

    }
}