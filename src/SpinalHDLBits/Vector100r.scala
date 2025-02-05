package SpinalHDLBits

package SpinalHDLBits.Vector100r

import spinal.core._
import SpinalHDLBits.Config._

case class Vector100r() extends Component {
    val io = new Bundle {
        val in_  = in Bits (100 bits)
        val out_ = out Bits (100 bits)
    }

    setDefinitionName("top_module")
    noIoPrefix()

    io.out_ := io.in_.reversed
}

object Vector100rVerilog extends App {
    Config.spinal.generate(Vector100r())
}
