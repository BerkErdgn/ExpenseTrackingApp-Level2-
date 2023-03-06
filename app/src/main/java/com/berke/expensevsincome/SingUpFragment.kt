package com.berke.expensevsincome

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_sing_up.*


class SingUpFragment : Fragment() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sing_up, container, false)


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth



        singUpButtonSingup.setOnClickListener {
            val email = emailSingUpText.text.toString()
            val password = passwordSingUpText.text.toString()
            val passworCorrextin = passwordVerificationSingUpText.text.toString()

            if(Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.isNotEmpty()&& password.length > 8 && password==passworCorrextin){
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    val actionSingToContenius =
                        com.berke.expensevsincome.SingUpFragmentDirections.actionSingUpFragmentToDetailMainFragment()
                    Navigation.findNavController(view).navigate(actionSingToContenius)

                }.addOnFailureListener {
                    Toast.makeText(this.context,it.localizedMessage,Toast.LENGTH_LONG).show()
            }

        }else{

            Toast.makeText(this.context,"Wrong Entering",Toast.LENGTH_LONG).show()

        }
        }
    }
}