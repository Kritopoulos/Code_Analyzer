package com.example.codeanalyzer.analyzedFiles

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codeanalyzer.R
import com.example.codeanalyzer.analyzedFiles.analyzerIncome.QualityResponse
import com.example.codeanalyzer.analyzedFiles.analyzerIncome.Violation
import com.example.codeanalyzer.databinding.AnalyzedFilesFragmentBinding

@Suppress("DEPRECATION")
class AnalyzedFilesFragment : Fragment() {

    private lateinit var binding : AnalyzedFilesFragmentBinding
    private lateinit var viewModel: AnalyzedFilesViewModel
    private lateinit var viewModelFactory: AnalyzedFilesViewModelFactory

    private lateinit var url: String

    private lateinit var violationsRecyclerView: RecyclerView

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        AnalyzedFilesFragmentArgs.fromBundle(requireArguments()).url!!

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.analyzed_files_fragment,
            container,
            false
        )
        url =  AnalyzedFilesFragmentArgs.fromBundle(requireArguments()).url!!.toString()
        viewModelFactory = AnalyzedFilesViewModelFactory(
            this.requireContext(),
            url
        )
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AnalyzedFilesViewModel::class.java)
        binding.analyzedFilesViewModel = viewModel

        binding.startBar.visibility = View.VISIBLE
        violationsRecyclerView = binding.recyclerViolations

        viewModel.qualityResponse.observe(viewLifecycleOwner, Observer { qualityResponse ->
            qualityResponse?.let {

                //makes the view visible
                changeVisibilities()

                //set overall analyzed files
                binding.analyzedFilesTxt.text =
                    qualityResponse.getResults().getAnalyzedFiles().toString()
                //set violations number found
                binding.violationsFoundTxt.text =
                    qualityResponse.getResults().getViolations().toString()

                //CIRCLE PROGRESS BARS
                setProgressBars(qualityResponse)

                // first list to be shown with bugs
                updateRecyclerView(qualityResponse.getResults().getImportantViolations())

                //change the list with the important bugs list
                binding.importantViolationsBtn.setOnClickListener{
                    updateRecyclerView(qualityResponse.getResults().getImportantViolations())
                }
                //change the list with the ordinary bugs list
                binding.ordinaryViolationsBtn.setOnClickListener{
                    updateRecyclerView(qualityResponse.getResults().getOrdinaryViolations())
                }
                //change the list with the insignificant bugs list
                binding.insignificantViolationsBtn.setOnClickListener{
                    updateRecyclerView(qualityResponse.getResults().getInsignificantViolations())
                }

            } ?: run {
                Toast.makeText(this.context, "Failed to analyze the project", Toast.LENGTH_LONG).show()
                val parts: List<String> = url.split("/")
                findNavController().navigate(AnalyzedFilesFragmentDirections.actionAnalyzedFilesDestinationToUserDestination(parts[4]))
            }

        })
        return binding.root
    }

    private fun changeVisibilities(){
        binding.startBar.visibility = View.GONE
        binding.upCardView.visibility = View.VISIBLE

    }

    private fun updateRecyclerView(violations : List<Violation>){
        violations.let { it ->
            val layoutManager = LinearLayoutManager(this.context,
                LinearLayoutManager.VERTICAL, false)
            violationsRecyclerView.layoutManager = layoutManager
            binding.loading.visibility= View.VISIBLE
            violationsRecyclerView.adapter = ViolationsViewAdapter(this.requireContext(),it,binding, this.requireActivity())
        }
    }
    private fun setProgressBars(qualityResponse: QualityResponse) {

        val importantPercent: Float =
            qualityResponse.getResults()
                .getImportant() * 100.0f / qualityResponse.getResults()
                .getViolations()

        val ordinaryPercent: Float =
            qualityResponse.getResults()
                .getOrdinary() * 100.0f / qualityResponse.getResults()
                .getViolations()

        val insignificantPercent: Float =
            qualityResponse.getResults()
                .getInsignificant() * 100.0f / qualityResponse.getResults()
                .getViolations()

        //load the progress bar with % for the total bugs
        //progress bar important
        ObjectAnimator.ofInt(
            binding.progressAnalyzeBar,
            "progress",
            importantPercent.toInt()
        ).setDuration(2000).start()
        binding.importantProgressTXT.text = "${importantPercent.toInt()}%"

        //load the progress bar with % for the total bugs
        //progress bar ordinary
        ObjectAnimator.ofInt(
            binding.progressOrdinaryBar,
            "progress",
            ordinaryPercent.toInt()
        ).setDuration(2000).start()
        binding.ordinaryProgressTXT.text = "${ordinaryPercent.toInt()}%"

        //load the progress bar with % for the total bugs
        //progress bar insignificant
        ObjectAnimator.ofInt(
            binding.progressInsignificantBar,
            "progress",
            insignificantPercent.toInt()?:0
        ).setDuration(2000).start()
        binding.insignificantProgressTXT.text = "${insignificantPercent.toInt()}%"

        //load the progress bar with % for the total bugs
        //progress bar total quality
        binding.totalProgress.max = 500
        ObjectAnimator.ofInt(binding.totalProgress, "progress",(qualityResponse.getResults().getOverallScore()*100).toInt()).setDuration(2000).start()

    }

}