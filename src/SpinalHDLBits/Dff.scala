package SpinalHDLBits

package SpinalHDLBits.Dff

import spinal.core._
import SpinalHDLBits.Config._

case class Dff() extends Component {
    val io = new Bundle {
        val d   = in Bool ()
        val q   = out Bool()
    }

    setDefinitionName("top_module")
    noIoPrefix()

    io.q := clockDomain.withoutReset() on RegNext(io.d)
}

object DffVerilog extends App {
    Config.spinal.generate(Dff()).printPruned()
}
