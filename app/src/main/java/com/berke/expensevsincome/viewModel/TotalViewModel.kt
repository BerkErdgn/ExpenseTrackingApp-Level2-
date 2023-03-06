package com.berke.expensevsincome.viewModel


import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class TotalViewModel : ViewModel() {

    private lateinit var db: FirebaseFirestore
    private lateinit var expenseDataList: MutableList<String>
    private lateinit var incomeDataList: MutableList<String>
    private lateinit var totalList: MutableList<Int>


    fun getTotalExpenseData(result:(MutableList<Int>)-> Unit ) {
        db = Firebase.firestore
        expenseDataList = mutableListOf()
        totalList = mutableListOf()

        db.collection("Posts")
            .whereEqualTo("expenseOrIncome", "Expense")
            .get()
            .addOnSuccessListener{ value ->
                val documents = value!!.documents
                for (document in documents) {
                    val expense = document.get("price") as String
                    expenseDataList.add(expense)

                }

                val sumExpense = expenseDataList.sumOf {
                    it.toInt()
                }
                totalList.add(sumExpense)

            }.addOnFailureListener {
                println("Hata")
            }

        incomeDataList = mutableListOf()
        db.collection("Posts")
            .whereEqualTo("expenseOrIncome", "Income")
            .get()
            .addOnSuccessListener{ value ->
                val documents = value!!.documents
                for (document in documents) {
                    val income = document.get("price") as String
                    incomeDataList.add(income)
                }

                val sumIncome = incomeDataList.sumOf {
                    it.toInt()
                }

                totalList.add(sumIncome)
                return@addOnSuccessListener result(totalList)


            }.addOnFailureListener {
                println("Hata")
            }





    }

}