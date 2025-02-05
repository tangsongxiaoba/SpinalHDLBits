package SpinalHDLBits

package SpinalHDLBits.Vector1

import spinal.core._
import SpinalHDLBits.Config._

case class Vector1() extends Component {
    val io = new Bundle {
        val in_    = in Bits (16 bits)
        val out_hi = out Bits (8 bits)
        val out_lo = out Bits (8 bits)
    }

    setDefinitionName("top_module")
    noIoPrefix()

    (io.out_hi, io.out_lo) := io.in_
}

object Vector1Verilog extends App {
    Config.spinal.generate(Vector1())
}
