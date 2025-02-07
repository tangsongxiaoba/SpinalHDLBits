package SpinalHDLBits

package SpinalHDLBits.Dff8r

import spinal.core._
import SpinalHDLBits.Config._

case class Dff8r() extends Component {
    val io = new Bundle {
        val d = in Bits (8 bits)
        val q = out Bits (8 bits)
    }

    setDefinitionName("top_module")
    noIoPrefix()

    io.q := clockDomain.withSyncReset() on RegNext(io.d) init 0
}

object Dff8rVerilog extends App {
    Config.spinal.generate(Dff8r()).printPruned()
}
