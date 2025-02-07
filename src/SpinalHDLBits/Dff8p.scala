package SpinalHDLBits

package SpinalHDLBits.Dff8p

import spinal.core._
import SpinalHDLBits.Config._

case class Dff8p() extends Component {
    val io = new Bundle {
        val d = in Bits (8 bits)
        val q = out Bits (8 bits)
    }

    setDefinitionName("top_module")
    noIoPrefix()

    io.q := (clockDomain.withRevertedClockEdge().withSyncReset()) on RegNext(io.d) init 0x34
}

object Dff8pVerilog extends App {
    Config.spinal.generate(Dff8p()).printPruned()
}
