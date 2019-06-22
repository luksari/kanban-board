package com.mvvm.kanban_board.view.board.pages_tab_layout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.mvvm.kanban_board.R
import org.koin.android.viewmodel.ext.android.viewModel

class PagesCollectionFragmentAdapter : Fragment() {

    private lateinit var pagesCollectionPagerAdapter: PagesCollectionPagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    companion object {
        fun newInstance() = PagesCollectionFragmentAdapter()
    }

    private val viewModel: PagesBarViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pages_bar_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pagesCollectionPagerAdapter = PagesCollectionPagerAdapter(childFragmentManager)
        viewPager = view.findViewById(R.id.pages_pager)
        viewPager.adapter = pagesCollectionPagerAdapter
        tabLayout = view.findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)

    }

}
