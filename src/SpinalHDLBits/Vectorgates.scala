package SpinalHDLBits

package SpinalHDLBits.Vectorgates

import spinal.core._
import SpinalHDLBits.Config._

case class Vectorgates() extends Component {
    val io = new Bundle {
        val a              = in Bits (3 bits)
        val b              = in Bits (3 bits)
        val out_not        = out Bits (6 bits)
        val out_or_bitwise = out Bits (3 bits)
        val out_or_logical = out Bool ()
    }

    setDefinitionName("top_module")
    noIoPrefix()

    io.out_not        := (~io.b, ~io.a).asBits
    io.out_or_bitwise := io.a | io.b
    io.out_or_logical := (io.a | io.b).orR

}

object VectorgatesVerilog extends App {
    Config.spinal.generate(Vectorgates())
}
