package SpinalHDLBits

package SpinalHDLBits.Dff8ar

import spinal.core._
import SpinalHDLBits.Config._

case class Dff8ar() extends Component {
    val io = new Bundle {
        val d = in Bits (8 bits)
        val q = out Bits (8 bits)
    }

    setDefinitionName("top_module")
    noIoPrefix()

    io.q := clockDomain.withAsyncReset() on RegNext(io.d) init 0

    clockDomain.reset.setName("areset")
}

object Dff8arVerilog extends App {
    Config.spinal.generate(Dff8ar()).printPruned()
}
