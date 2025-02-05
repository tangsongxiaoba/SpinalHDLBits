package SpinalHDLBits

package SpinalHDLBits.Wire4

import spinal.core._
import SpinalHDLBits.Config._

case class Wire4() extends Component {
    val io = new Bundle {
        val a, b, c    = in port Bool()
        val x, y, z, w = out port Bool()
    }

    setDefinitionName("top_module")
    noIoPrefix()

    io.w := io.a
    io.x := io.b
    io.y := io.b
    io.z := io.c

}

object Wire4Verilog extends App {
    Config.spinal.generate(Wire4())
}
