package SpinalHDLBits

package SpinalHDLBits.AlwaysCase

import spinal.core._
import SpinalHDLBits.Config._

case class AlwaysCase() extends Component {
    val io = new Bundle {
        val sel  = in Bits (3 bits)
        val data = in port Vec.fill(6)(Bits(4 bits))
        val ou   = out port Reg(Bits(4 bits))
    }

    setDefinitionName("top_module")
    noIoPrefix()

    io.ou.setAsComb()

    switch(io.sel) {
        io.data.indices.foreach(i => is(i) { io.ou := io.data(i) })

        default {
            io.ou := 0
        }
    }
}

object AlwaysCaseVerilog extends App {
    Config.spinal.generate(AlwaysCase()).printPruned()
}
