package com.boswelja.capacity

import kotlin.math.roundToLong

/**
 * Represents the amount of digital data something uses.
 *
 * Capacity can represent ±8 exbibytes with byte-level accuracy.
 *
 * To construct a Capacity, use the extension functions [kibibytes], [kilobytes], [mebibytes],
 * [megabytes], and so on.
 *
 * To get the value of this Capacity expressed in a particular CapacityUnit, use the functions
 * [toLong], [toDouble], and so on.
 */
@JvmInline
value class Capacity internal constructor(private val rawValue: Long) : Comparable<Capacity> {

    override fun compareTo(other: Capacity): Int {
        return rawValue.compareTo(other.rawValue)
    }

    operator fun plus(other: Capacity): Capacity {
        return Capacity(rawValue + other.rawValue)
    }

    operator fun minus(other: Capacity): Capacity {
        return Capacity(rawValue - other.rawValue)
    }

    fun toDouble(unit: CapacityUnit): Double {
        return rawValue.toDouble() / unit.byteFactor
    }

    fun toLong(unit: CapacityUnit): Long {
        return toDouble(unit).roundToLong()
    }

    companion object {

        private fun Int.toCapacity(unit: CapacityUnit): Capacity {
            return Capacity(this * unit.byteFactor)
        }

        private fun Long.toCapacity(unit: CapacityUnit): Capacity {
            return Capacity(this * unit.byteFactor)
        }

        private fun Float.toCapacity(unit: CapacityUnit): Capacity {
            return Capacity((this * unit.byteFactor).roundToLong())
        }

        private fun Double.toCapacity(unit: CapacityUnit): Capacity {
            return Capacity((this * unit.byteFactor).roundToLong())
        }

        // region bytes
        val Int.bytes: Capacity get() = Capacity(toLong())

        val Long.bytes: Capacity get() = Capacity(this)
        // endregion

        // region binary units
        val Int.kibibytes: Capacity get() = toCapacity(CapacityUnit.KIBIBYTE)

        val Int.mebibytes: Capacity get() = toCapacity(CapacityUnit.MEBIBYTE)

        val Int.gibibytes: Capacity get() = toCapacity(CapacityUnit.GIBIBYTE)

        val Int.tebibytes: Capacity get() = toCapacity(CapacityUnit.TEBIBYTE)

        val Int.pebibytes: Capacity get() = toCapacity(CapacityUnit.PEBIBYTE)

        val Int.exbibytes: Capacity get() = toCapacity(CapacityUnit.EXBIBYTE)

        val Long.kibibytes: Capacity get() = toCapacity(CapacityUnit.KIBIBYTE)

        val Long.mebibytes: Capacity get() = toCapacity(CapacityUnit.MEBIBYTE)

        val Long.gibibytes: Capacity get() = toCapacity(CapacityUnit.GIBIBYTE)

        val Long.tebibytes: Capacity get() = toCapacity(CapacityUnit.TEBIBYTE)

        val Long.pebibytes: Capacity get() = toCapacity(CapacityUnit.PEBIBYTE)

        val Long.exbibytes: Capacity get() = toCapacity(CapacityUnit.EXBIBYTE)

        val Float.kibibytes: Capacity get() = toCapacity(CapacityUnit.KIBIBYTE)

        val Float.mebibytes: Capacity get() = toCapacity(CapacityUnit.MEBIBYTE)

        val Float.gibibytes: Capacity get() = toCapacity(CapacityUnit.GIBIBYTE)

        val Float.tebibytes: Capacity get() = toCapacity(CapacityUnit.TEBIBYTE)

        val Float.pebibytes: Capacity get() = toCapacity(CapacityUnit.PEBIBYTE)

        val Float.exbibytes: Capacity get() = toCapacity(CapacityUnit.EXBIBYTE)

        val Double.kibibytes: Capacity get() = toCapacity(CapacityUnit.KIBIBYTE)

        val Double.mebibytes: Capacity get() = toCapacity(CapacityUnit.MEBIBYTE)

        val Double.gibibytes: Capacity get() = toCapacity(CapacityUnit.GIBIBYTE)

        val Double.tebibytes: Capacity get() = toCapacity(CapacityUnit.TEBIBYTE)

        val Double.pebibytes: Capacity get() = toCapacity(CapacityUnit.PEBIBYTE)

        val Double.exbibytes: Capacity get() = toCapacity(CapacityUnit.EXBIBYTE)
        //endregion

        // region decimal units
        val Int.kilobytes: Capacity get() = toCapacity(CapacityUnit.KIBIBYTE)

        val Int.megabytes: Capacity get() = toCapacity(CapacityUnit.MEBIBYTE)

        val Int.gigabytes: Capacity get() = toCapacity(CapacityUnit.GIBIBYTE)

        val Int.terabytes: Capacity get() = toCapacity(CapacityUnit.TEBIBYTE)

        val Int.petabytes: Capacity get() = toCapacity(CapacityUnit.PEBIBYTE)

        val Int.exabytes: Capacity get() = toCapacity(CapacityUnit.EXBIBYTE)

        val Long.kilobytes: Capacity get() = toCapacity(CapacityUnit.KIBIBYTE)

        val Long.megabytes: Capacity get() = toCapacity(CapacityUnit.MEBIBYTE)

        val Long.gigabytes: Capacity get() = toCapacity(CapacityUnit.GIBIBYTE)

        val Long.terabytes: Capacity get() = toCapacity(CapacityUnit.TEBIBYTE)

        val Long.petabytes: Capacity get() = toCapacity(CapacityUnit.PEBIBYTE)

        val Long.exabytes: Capacity get() = toCapacity(CapacityUnit.EXBIBYTE)

        val Float.kilobytes: Capacity get() = toCapacity(CapacityUnit.KIBIBYTE)

        val Float.megabytes: Capacity get() = toCapacity(CapacityUnit.MEBIBYTE)

        val Float.gigabytes: Capacity get() = toCapacity(CapacityUnit.GIBIBYTE)

        val Float.terabytes: Capacity get() = toCapacity(CapacityUnit.TEBIBYTE)

        val Float.petabytes: Capacity get() = toCapacity(CapacityUnit.PEBIBYTE)

        val Float.exabytes: Capacity get() = toCapacity(CapacityUnit.EXBIBYTE)

        val Double.kilobytes: Capacity get() = toCapacity(CapacityUnit.KIBIBYTE)

        val Double.megabytes: Capacity get() = toCapacity(CapacityUnit.MEBIBYTE)

        val Double.gigabytes: Capacity get() = toCapacity(CapacityUnit.GIBIBYTE)

        val Double.terabytes: Capacity get() = toCapacity(CapacityUnit.TEBIBYTE)

        val Double.petabytes: Capacity get() = toCapacity(CapacityUnit.PEBIBYTE)

        val Double.exabytes: Capacity get() = toCapacity(CapacityUnit.EXBIBYTE)
        //endregion
    }
}
