package SpinalHDLBits

package SpinalHDLBits.Vector4

import spinal.core._
import SpinalHDLBits.Config._

case class Vector4() extends Component {
    val io = new Bundle {
        val in_  = in Bits (8 bits)
        val out_ = out Bits (32 bits)
    }

    setDefinitionName("top_module")
    noIoPrefix()

    io.out_ := io.in_(7) #* 24 ## io.in_
}

object Vector4Verilog extends App {
    Config.spinal.generate(Vector4())
}
