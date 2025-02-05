package SpinalHDLBits

package SpinalHDLBits.Vector3

import spinal.core._
import SpinalHDLBits.Config._

case class Vector3() extends Component {
    val io = new Bundle {
        val a, b, c, d, e, f = in Bits (5 bits)
        val w, x, y, z       = out Bits (8 bits)
    }

    setDefinitionName("top_module")
    noIoPrefix()

    (io.w, io.x, io.y, io.z) := Cat(io.a, io.b, io.c, io.d, io.e, io.f, B("11"))
}

object Vector3Verilog extends App {
    Config.spinal.generate(Vector3())
}
