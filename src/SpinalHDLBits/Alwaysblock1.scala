package SpinalHDLBits

package SpinalHDLBits.Alwaysblock1

import spinal.core._
import SpinalHDLBits.Config._

case class Alwaysblock1() extends Component {
    val io = new Bundle {
        val a = in Bool()
        val b = in Bool()
        val out_assign = out Bool()
        val out_alwaysblock = out Bool()
    }
    
    setDefinitionName("top_module")
    noIoPrefix()

    io.out_assign := io.a & io.b
    io.out_alwaysblock := io.a & io.b
}

object Alwaysblock1Verilog extends App {
    Config.spinal.generate(Alwaysblock1()).printPruned()
}
