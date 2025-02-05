package SpinalHDLBits

package SpinalHDLBits.Vectorr

import spinal.core._
import SpinalHDLBits.Config._

case class Vectorr() extends Component {
    val io = new Bundle {
        val in_  = in Bits (8 bits)
        val out_ = out Bits (8 bits)
    }

    setDefinitionName("top_module")
    noIoPrefix()

    io.out_ := io.in_.reversed
}

object VectorrVerilog extends App {
    Config.spinal.generate(Vectorr())
}
