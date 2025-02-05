package SpinalHDLBits

package SpinalHDLBits.AlwaysIf

import spinal.core._
import SpinalHDLBits.Config._

case class AlwaysIf() extends Component {
    val io = new Bundle {
        val a          = in Bool ()
        val b          = in Bool ()
        val sel_b1     = in Bool ()
        val sel_b2     = in Bool ()
        val out_assign = out Bool ()
        val out_always = out Bool ()
    }

    setDefinitionName("top_module")
    noIoPrefix()

    io.out_assign := Mux(io.sel_b1 && io.sel_b2, io.b, io.a)
    io.out_always := io.out_assign
}

object AlwaysIfVerilog extends App {
    Config.spinal.generate(AlwaysIf()).printPruned()
}
