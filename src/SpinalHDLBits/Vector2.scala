package SpinalHDLBits

package SpinalHDLBits.Vector2

import spinal.core._
import SpinalHDLBits.Config._

case class Vector2() extends Component {
    val io = new Bundle {
        val in_  = in Bits (32 bits)
        val out_ = out Bits (32 bits)
    }

    setDefinitionName("top_module")
    noIoPrefix()

    io.out_ := Cat(io.in_.subdivideIn(8 bits).reverse)
}

object Vector2Verilog extends App {
    Config.spinal.generate(Vector2())
}
