package com.example.checkit.views

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.example.checkit.R
import com.example.checkit.adapters.CheckListRecyclerViewAdapter
import com.example.checkit.database.EntityCheckList
import com.example.checkit.database.ViewModel
import com.example.checkit.model.Checklist
import com.example.checkit.utils.BaseFragment

class DashboardFragment : BaseFragment() {

    @BindView(R.id.left)
    lateinit var leftIcon: ImageView

    @BindView(R.id.right)
    lateinit var rightIcon: ImageView

    @BindView(R.id.text_title_bar)
    lateinit var textTitleBar: TextView

    @BindView(R.id.app_recyclerView)
    lateinit var checkListRecyclerView: RecyclerView

    private lateinit var checkListRecyclerViewAdapter: CheckListRecyclerViewAdapter

    private var checkList = ArrayList<Checklist>()

    private lateinit var viewModel: ViewModel

    override fun getLayoutResource(): Int {
        return R.layout.fragment_dashboard
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ViewModel::class.java]
        getData()
        initAdapter()
        setupActionBar()
    }

    private fun getData() {
        val list: List<EntityCheckList> = viewModel.allCheckList.value!!
    }

    private fun setupActionBar() {
        leftIcon.setOnClickListener {
            findNavController().navigateUp()
        }
        textTitleBar.text = ""
        rightIcon.setImageDrawable(
            requireActivity().resources.getDrawable(
                R.drawable.ic_baseline_add_24,
                null
            )
        )

        rightIcon.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_checkListFragment)
        }
    }


    private fun initAdapter() {
        checkListRecyclerViewAdapter =
            CheckListRecyclerViewAdapter(object :
                CheckListRecyclerViewAdapter.CheckListAdapterInterface {
                override fun onDeleteClick(position: Int) {
//                    TODO("Not yet implemented")
                }

                override fun onClick(position: Int) {
                    val args = Bundle()
                    args.putParcelable("checklist", checkList[position])
                    args.putBoolean("append", true)
                    findNavController().navigate(
                        R.id.action_dashboardFragment_to_checkListFragment,
                        args
                    )
                }

            })
    }
}