package com.boswelja.truemanager.dashboard.logic.dataloading

import com.boswelja.capacity.Capacity.Companion.bytes
import com.boswelja.truemanager.core.api.v2.reporting.ReportingGraphData
import com.boswelja.truemanager.core.api.v2.system.SystemInfo

/**
 * Takes data received from the server and maps it to a [DashboardData.MemoryData]. See [invoke] for
 * details.
 */
class ExtractMemoryUsageData {

    /**
     * Produces a [DashboardData.MemoryData] from information retrieved from the server.
     *
     * @param systemInformation The server system information. This contains information about CPU
     * model and core count.
     * @param memoryGraph The [ReportingGraphData] containing information about memory utilisation.
     * @param uid A unique identifier for the item that will be returned.
     */
    operator fun invoke(
        systemInformation: SystemInfo,
        memoryGraph: ReportingGraphData,
        uid: Long,
    ): DashboardData.MemoryData {
        val memoryData = memoryGraph.data.last { !it.contains(null) } as List<Double>
        return DashboardData.MemoryData(
            uid = uid,
            memoryUsed = memoryData[0].toLong().bytes,
            memoryFree = memoryData[1].toLong().bytes,
            isEcc = systemInformation.eccMemory
        )
    }
}
