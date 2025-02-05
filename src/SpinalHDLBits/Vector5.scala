package SpinalHDLBits

package SpinalHDLBits.Vector5

import spinal.core._
import SpinalHDLBits.Config._

case class Vector5() extends Component {
    val io = new Bundle {
        val a, b, c, d, e = in Bool ()
        val out_          = out Bits (25 bits)
    }

    setDefinitionName("top_module")
    noIoPrefix()

    io.out_ := ~((io.a #* 5) ## (io.b #* 5) ## (io.c #* 5) ## (io.d #* 5) ## (io.e #* 5)) ^ ((
      io.a,
      io.b,
      io.c,
      io.d,
      io.e
    ).asBits #* 5)
}

object Vector5Verilog extends App {
    Config.spinal.generate(Vector5())
}
