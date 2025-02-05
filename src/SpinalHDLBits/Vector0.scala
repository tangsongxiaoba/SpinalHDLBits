package SpinalHDLBits

package SpinalHDLBits.Vector0

import spinal.core._
import SpinalHDLBits.Config._

case class Vector0() extends Component {
    val io = new Bundle {
        val vec        = in Bits (3 bits)
        val outv       = out Bits (3 bits)
        val o2, o1, o0 = out Bool ()
    }

    setDefinitionName("top_module")
    noIoPrefix()

    io.outv               := io.vec
    (io.o2, io.o1, io.o0) := io.vec
}

object Vector0Verilog extends App {
    Config.spinal.generate(Vector0())
}
