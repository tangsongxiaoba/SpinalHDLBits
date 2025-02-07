package SpinalHDLBits

package SpinalHDLBits.Count15

import spinal.core._
import SpinalHDLBits.Config._
import spinal.lib.Counter

case class Count15() extends Component {
    val io = new Bundle {
        val q = out UInt(4 bits)
    }

    io.q := clockDomain.withSyncReset on RegNext(io.q + 1) init(0)
    
    setDefinitionName("top_module")
    noIoPrefix()
}

object Count15Verilog extends App {
    Config.spinal.generate(Count15()).printPruned()
}