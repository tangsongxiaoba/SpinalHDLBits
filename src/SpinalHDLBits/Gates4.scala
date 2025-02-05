package SpinalHDLBits

package SpinalHDLBits.Gates4

import spinal.core._
import SpinalHDLBits.Config._

case class Gates4() extends Component {
    val io = new Bundle {
        val in_     = in Bits (4 bits)
        val out_and = out Bool ()
        val out_or  = out Bool ()
        val out_xor = out Bool ()
    }

    setDefinitionName("top_module")
    noIoPrefix()

    io.out_and := io.in_.andR
    io.out_or  := io.in_.orR
    io.out_xor := io.in_.xorR
}

object Gates4Verilog extends App {
    Config.spinal.generate(Gates4())
}
