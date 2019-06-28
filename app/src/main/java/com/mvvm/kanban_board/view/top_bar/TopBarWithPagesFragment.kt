package com.mvvm.kanban_board.view.top_bar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

import com.mvvm.kanban_board.R
import kotlinx.android.synthetic.main.top_bar_with_pages.*
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.system.exitProcess

class TopBarWithPagesFragment : Fragment() {

    private lateinit var toolbar : Toolbar
    private lateinit var pagesFragmentPagerAdapter: FragmentStatePagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    companion object {
        fun newInstance() = TopBarWithPagesFragment()
    }

    private val viewModel: TopBarWithPagesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.top_bar_with_pages, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        toolbar = view.findViewById(R.id.toolbar)
        viewPager = view.findViewById(R.id.viewPager)
        tabLayout = view.findViewById(R.id.tabLayout)
        pagesFragmentPagerAdapter = PagesPagerAdapter(childFragmentManager)

        viewPager.adapter = pagesFragmentPagerAdapter
        tabLayout.setupWithViewPager(viewPager)


        val selectedPage=  tabLayout.getTabAt(viewModel.getCurrentPageIndex())
        selectedPage?.select()
//        val selectedPage=  tabLayout.getTabAt(1)
//        selectedPage?.select()


        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        setupListeners()
        super.onActivityCreated(savedInstanceState)
    }

    private fun setupListeners(){
        val navController = findNavController()

        logout_button.setOnClickListener {
            viewModel.logout()
            navController.navigate(R.id.signInFragment)
        }
        exit_board_button.setOnClickListener {
            viewModel.exitBoard()
            navController.navigate(R.id.enterBoardFragment)
        }

    }


}
