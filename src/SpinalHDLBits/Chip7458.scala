package SpinalHDLBits

package SpinalHDLBits.Chip7458

import spinal.core._
import SpinalHDLBits.Config._

case class Chip7458() extends Component {
    val io = new Bundle {
        val p1a, p1b, p1c, p1d, p1e, p1f = in Bool ()
        val p2a, p2b, p2c, p2d           = in Bool ()
        val p1y, p2y                     = out Bool ()
    }

    setDefinitionName("top_module")
    noIoPrefix()

    val and_1 = io.p1a & io.p1b & io.p1c
    val and_2 = io.p1d & io.p1e & io.p1f

    val and_3 = io.p2a & io.p2b
    val and_4 = io.p2c & io.p2d

    io.p1y := and_1 | and_2
    io.p2y := and_3 | and_4
}

object Chip7458Verilog extends App {
    Config.spinal.generate(Chip7458())
}
