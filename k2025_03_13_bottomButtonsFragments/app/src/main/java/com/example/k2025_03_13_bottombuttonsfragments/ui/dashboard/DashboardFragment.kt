package com.example.k2025_03_13_bottombuttonsfragments.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.k2025_03_13_bottombuttonsfragments.R
import com.example.k2025_03_13_bottombuttonsfragments.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val bundle: Bundle = Bundle()

        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _binding?.dashboardButton?.setOnClickListener{
            Toast.makeText(activity, "Hello!", Toast.LENGTH_LONG).show()
            dashboardViewModel.setText()
            bundle.putString("PGB", dashboardViewModel.text.value + " from Dashboard Fragment!" )
        }

        _binding?.gotoHomeFragment?.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_dashboard_to_navigation_home, bundle)
        }

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}