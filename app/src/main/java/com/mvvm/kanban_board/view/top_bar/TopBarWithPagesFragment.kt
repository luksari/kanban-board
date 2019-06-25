package com.mvvm.kanban_board.view.top_bar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

import com.mvvm.kanban_board.R
import org.koin.android.viewmodel.ext.android.viewModel

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
        super.onViewCreated(view, savedInstanceState)
    }

}
