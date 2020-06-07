package com.example.checkit.views

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import butterknife.BindView
import com.example.checkit.R
import com.example.checkit.database.EntityCheckList
import com.example.checkit.database.ViewModel
import com.example.checkit.model.Checklist
import com.example.checkit.utils.BaseFragment
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CheckListFragment : BaseFragment() {

    data class Id(
        var checkboxId: CheckBox,
        var editTextId: EditText,
        var linearLayout: LinearLayout
    )

    @BindView(R.id.checkboxLayout)
    lateinit var checkBoxLayout: LinearLayout

    @BindView(R.id.include2)
    lateinit var barLayout: RelativeLayout

    @BindView(R.id.left)
    lateinit var leftIcon: ImageView

    @BindView(R.id.right)
    lateinit var rightIcon: ImageView

    @BindView(R.id.right2)
    lateinit var deleteIcon: ImageView

    @BindView(R.id.text_title_bar)
    lateinit var textTitleBar: TextView

    @BindView(R.id.add_checkBox)
    lateinit var addCheckBox: TextView

    @BindView(R.id.title)
    lateinit var titleCheckBox: EditText

    private var idList = ArrayList<Id>()

    private var count = 0

    private lateinit var getData: Checklist

    private var isAppend = false

    private lateinit var viewModel: ViewModel

    override fun getLayoutResource(): Int {
        return R.layout.fragment_view_checklist
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            getData = requireArguments().getParcelable("checklist")!!
            isAppend = requireArguments().getParcelable("append")!!
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ViewModel::class.java]
        if (isAppend)
            setData()
        init()
        addCheckBox.setOnClickListener {
            checkBox("")
        }
    }

    private fun setData() {
        titleCheckBox.setText(getData.title, TextView.BufferType.EDITABLE)
        for (i in getData.checkBoxes)
            checkBox(i)
    }

    private fun checkBox(text: String) {
        val linearLayout = LinearLayout(activity)
        val editText = EditText(activity)
        val checkBox = CheckBox(activity)
        editText.id = count
        linearLayout.id = count
        checkBox.id = count
        count += 1
        idList.add(Id(checkBox, editText, linearLayout))
        editText.requestFocus()
        editText.setText(text, TextView.BufferType.EDITABLE)
        editText.hint = "Add task"
        editText.background = null
        linearLayout.addView(checkBox)
        linearLayout.addView(editText)
        checkBoxLayout.addView(linearLayout)

        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            val index = idList.indexOf(idList.find {
                it.checkboxId.id == buttonView.id
            })
            val ll = idList[index].linearLayout
            checkBoxLayout.removeView(ll)
        }
    }

    private fun init() {
        barLayout.setBackgroundColor(
            ContextCompat.getColor(
                requireActivity(),
                android.R.color.white
            )
        )
        leftIcon.setOnClickListener {
            findNavController().navigateUp()
        }
        textTitleBar.text = "Add Task"
        rightIcon.setColorFilter(
            ContextCompat.getColor(requireActivity(), android.R.color.black),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
        deleteIcon.setColorFilter(
            ContextCompat.getColor(requireActivity(), android.R.color.black),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
        leftIcon.setColorFilter(
            ContextCompat.getColor(requireActivity(), android.R.color.black),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
        rightIcon.setImageDrawable(
            requireActivity().resources.getDrawable(
                R.drawable.ic_baseline_done_24,
                null
            )
        )
        rightIcon.setOnClickListener {
            postNotes()
        }
        deleteIcon.visibility = View.VISIBLE
        deleteIcon.setImageDrawable(
            requireActivity().resources.getDrawable(
                R.drawable.ic_baseline_delete_24,
                null
            )
        )
    }

    private fun postNotes() {
        val string = ArrayList<String>()
        for (i in idList) {
            string.add(i.editTextId.text.toString())
        }
        val c = Calendar.getInstance().time
        val df = SimpleDateFormat("dd-MMM-yyyy")
        val formattedDate: String = df.format(c)
        viewModel.insert(EntityCheckList(titleCheckBox.text.toString(), string, formattedDate))
    }
}