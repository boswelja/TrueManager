package com.boswelja.truemanager.dashboard.logic.dataloading

import com.boswelja.truemanager.core.api.v2.reporting.ReportingGraphData
import com.boswelja.truemanager.core.api.v2.reporting.ReportingV2Api
import com.boswelja.truemanager.core.api.v2.reporting.RequestedGraph
import com.boswelja.truemanager.data.configuration.DashboardEntry

/**
 * Requests data from the server about a list of [DashboardEntry]. See [invoke] for details.
 */
class GetReportingDataForEntries(
    private val reportingV2Api: ReportingV2Api
) {

    /**
     * Takes a List of [DashboardEntry], and returns data from the server for all of them. The
     * returned List of [ReportingGraphData] will be in an order that approximately matches the
     * input list. For example, an input `listOf(CpuEntry, MemoryEntry, NetworkEntry)` might not
     * return a list of 3 [ReportingGraphData]s, but the returned list would guarantee all memory
     * data would appear after all CPU data, and before all network data.
     */
    suspend operator fun invoke(entries: List<DashboardEntry>): List<ReportingGraphData> {
        val reportingGraphsToQuery = mutableListOf<RequestedGraph>()
        entries.forEach { entry ->
            when (entry.type) {
                DashboardEntry.Type.SYSTEM_INFORMATION -> { /* no-op. We add this data later */ }
                DashboardEntry.Type.CPU -> {
                    reportingGraphsToQuery.add(RequestedGraph(CPU_GRAPH_NAME, null))
                    reportingGraphsToQuery.add(RequestedGraph(CPU_TEMP_GRAPH_NAME, null))
                }
                DashboardEntry.Type.MEMORY -> {
                    reportingGraphsToQuery.add(RequestedGraph(MEMORY_GRAPH_NAME, null))
                }
                DashboardEntry.Type.NETWORK -> {
                    val adapters = reportingV2Api.getReportingGraphs(
                        limit = null,
                        offset = null,
                        sort = null
                    ).first { it.name == INTERFACE_GRAPH_NAME }.identifiers
                    adapters!!.forEach {
                        reportingGraphsToQuery.add(RequestedGraph(INTERFACE_GRAPH_NAME, it))
                    }
                }
            }
        }
        return reportingV2Api.getGraphData(reportingGraphsToQuery)
    }

    companion object {
        private const val CPU_GRAPH_NAME = "cpu"
        private const val CPU_TEMP_GRAPH_NAME = "cputemp"
        private const val MEMORY_GRAPH_NAME = "memory"
        private const val INTERFACE_GRAPH_NAME = "interface"
    }
}
