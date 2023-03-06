package com.berke.expensevsincome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import com.berke.expensevsincome.databinding.FragmentTotalBanafitBinding
import com.berke.expensevsincome.viewModel.TotalViewModel
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlinx.android.synthetic.main.fragment_total_banafit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class TotalBanafitFragment : Fragment() {

    private lateinit var viewModel: TotalViewModel

    private lateinit var binding : FragmentTotalBanafitBinding
    private lateinit var expenseDataList : MutableList<String>
    private var totalExpense =0
    private var totalIncome = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTotalBanafitBinding.inflate(inflater,container,false)
        return binding.root




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        expenseDataList = mutableListOf()



           viewModel = ViewModelProviders.of(this@TotalBanafitFragment).get(TotalViewModel::class.java)
           viewModel.getTotalExpenseData {
               println(it)
               totalExpense = it.get(0)
               totalIncome = it.get(1)
                println(totalExpense)
               println(totalIncome)

               //for pieChart

               val pieEntry = arrayListOf<PieEntry>()
               pieEntry.add(PieEntry(totalExpense.toFloat()))
               pieEntry.add(PieEntry(totalIncome.toFloat()))

               //set pieChart Animation
               pieChart.animateXY(1000,1000)

               //setup PieChart Entiries Color
               val pieDataSet = PieDataSet(pieEntry,"Expense Vs Income")
                   pieDataSet.setColors(
                       resources.getColor(R.color.purple_200),
                       resources.getColor(R.color.white)
                   )

               //Setup Pied Data Set in PieData
               val pieData = PieData(pieDataSet)

               //setup Pie Data Set im PieData
               pieChart.centerText= "Expense Vs Income"
               pieChart.setHoleColor(resources.getColor(R.color.black))
               pieChart.setCenterTextColor(resources.getColor(R.color.teal_200))
               pieChart.setCenterTextSize(28f)


               //Hide the pieChart Enteries Tags
               pieChart.legend.isEnabled= false

               //Hide the description of pieChart
               pieChart.description.isEnabled = false

               // show values
               pieData.setDrawValues(true)

               pieChart.data=pieData




               incomeText.text = "Income = ${totalIncome}"
               excomeText.text = "Expense = ${totalExpense}"




           }



    }


}