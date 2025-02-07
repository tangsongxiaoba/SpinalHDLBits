package SpinalHDLBits

package SpinalHDLBits.AlwaysCase2

import spinal.core._
import SpinalHDLBits.Config._
import spinal.lib.WhenBuilder
import spinal.lib.PriorityMux

case class AlwaysCase2() extends Component {
    val io = new Bundle {
        val in_ = in Bits (4 bits)
        val pos = out Bits (2 bits)
    }

    setDefinitionName("top_module")
    noIoPrefix()

    // val values = Vec(Bits(2 bits), 4)

    // values.indices.foreach(i => values(i) := i)

    // io.pos := Mux(io.in_ === 0, B(0), PriorityMux(io.in_.asBools, values))

    val ctx = WhenBuilder()

    for (i <- 0 until (4)) {
        ctx.when(io.in_(i)) {
            io.pos := i
        }
    }

    ctx.otherwise {
        io.pos := 0
    }
}

object AlwaysCase2Verilog extends App {
    Config.spinal.generate(AlwaysCase2()).printPruned()
}
